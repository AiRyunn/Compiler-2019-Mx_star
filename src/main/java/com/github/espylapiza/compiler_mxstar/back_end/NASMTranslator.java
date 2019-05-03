package com.github.espylapiza.compiler_mxstar.back_end;

import java.util.Arrays;
import java.util.List;
import com.github.espylapiza.compiler_mxstar.nasm.DirectiveExtern;
import com.github.espylapiza.compiler_mxstar.nasm.DirectiveGlobal;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionCall;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionDB;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionMov;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionPop;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionPush;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionRet;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionSub;
import com.github.espylapiza.compiler_mxstar.nasm.Label;
import com.github.espylapiza.compiler_mxstar.nasm.NASM;
import com.github.espylapiza.compiler_mxstar.nasm.Operand;
import com.github.espylapiza.compiler_mxstar.nasm.OperandDBAddr;
import com.github.espylapiza.compiler_mxstar.nasm.OperandFuncAddr;
import com.github.espylapiza.compiler_mxstar.nasm.OperandInt;
import com.github.espylapiza.compiler_mxstar.nasm.OperandMemory;
import com.github.espylapiza.compiler_mxstar.nasm.OperandRegister;
import com.github.espylapiza.compiler_mxstar.nasm.OperandString;
import com.github.espylapiza.compiler_mxstar.nasm.RegisterSet;
import com.github.espylapiza.compiler_mxstar.nasm.SectionItem;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Func;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncAddr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncExtern;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Inst;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstCall;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstRet;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectBool;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectConstant;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectInt;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectString;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Scope;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ScopeType;


public class NASMTranslator {
    private NASM nasm;

    private final List<OperandRegister> regParams = Arrays.asList(RegisterSet.rdi, RegisterSet.rsi,
            RegisterSet.rdx, RegisterSet.rcx, RegisterSet.r8, RegisterSet.r9);

    /**
     * 
     * @param ir
     */
    public NASMTranslator(PizzaIR ir) {
        nasm = new NASM();
        addDirectiveGlobal();
        addSections(ir);
    }

    /**
     * 
     * @return nasm
     */
    public NASM getNASM() {
        return nasm;
    }

    private void addDirectiveGlobal() {
        nasm.addDirective(new DirectiveGlobal("main"));
    }

    private void addDirectiveExtern(String strFunc) {
        nasm.addDirective(new DirectiveExtern(strFunc));
    }

    private void addSections(PizzaIR ir) {
        Func initFunc = ir.funcList.get(FuncAddr.createGlobalFuncAddr("main"));
        // TODO: process global variables here

        for (Func func : ir.funcList) {
            if (func instanceof FuncExtern) {
                addDirectiveExtern(func.getAddr().toString());
                continue;
            }
            if (func.getScps().isEmpty()) {
                // some builtin funcs of PizzaIR
                continue;
            }
            // if (func.getAddr().toString().equals("main")) {
            //     // main wrapper
            //     for (Scope scp : func.getScps()) {
            //         nasm.sectionText.addItem(new SectionItem(new Label(scp.getLabel()), null));
            //         for (Inst inst : scp) {
            //             addInst(inst, maddr);
            //         }
            //     }
            //     continue;
            // }

            addFunc(func);
        }

    }

    static Integer temp = 0;

    private Operand getOperand(Object param, RegisterAllocator allocator) {
        if (param instanceof ObjectConstant) {
            if (param instanceof ObjectBool) {
                return new OperandInt(((ObjectBool) param).value);
            } else if (param instanceof ObjectInt) {
                return new OperandInt(((ObjectInt) param).value);
            } else if (param instanceof ObjectString) {
                OperandString operand = new OperandString(((ObjectString) param).value);
                temp++;
                String label = "db_" + String.valueOf(temp);
                nasm.sectionData
                        .addItem(new SectionItem(new Label(label), new InstructionDB(operand)));
                return new OperandDBAddr(label);
            }
            return null;
        } else {
            return allocator.get(param);
        }
    }

    private void addInst(Inst inst, RegisterAllocator allocator) {
        if (inst instanceof InstCall) {
            InstCall instCall = (InstCall) inst;

            int index = 0;
            for (Object param : instCall.params) {
                Operand operand = getOperand(param, allocator);
                if (index < 6) {
                    nasm.sectionText.addItem(new SectionItem(null,
                            new InstructionMov(regParams.get(index), operand)));
                } else {
                    nasm.sectionText.addItem(new SectionItem(null, new InstructionPush(operand)));
                }
                index++;
            }
            nasm.sectionText.addItem(
                    new SectionItem(null, new InstructionMov(RegisterSet.rax, new OperandInt(0))));
            nasm.sectionText.addItem(new SectionItem(null,
                    new InstructionCall(new OperandFuncAddr(instCall.getAddr().toString()))));
        } else if (inst instanceof InstRet) {
            InstRet instRet = (InstRet) inst;
            if (instRet.obj != null) {
                nasm.sectionText.addItem(new SectionItem(null,
                        new InstructionMov(RegisterSet.rax, allocator.get(instRet.obj))));
            }
            nasm.sectionText.addItem(
                    new SectionItem(null, new InstructionMov(RegisterSet.rsp, RegisterSet.rbp)));
            nasm.sectionText.addItem(new SectionItem(null, new InstructionPop(RegisterSet.rbp)));
            nasm.sectionText.addItem(new SectionItem(null, new InstructionRet()));
        }
    }

    private void addFunc(Func func) {
        // TODO: register allocation
        // calculate stack space & offset for each variable
        RegisterAllocator allocator = new RegisterAllocator();

        int index = 0, top = 0; // 1 base
        for (Object obj : func.getParams()) {
            index++;
            if (index <= 6) {
                Operand operand = new OperandMemory(RegisterSet.rbp, -8 * (++top));
                allocator.put(obj, operand);
            } else {
                allocator.put(obj, new OperandMemory(RegisterSet.rbp, 8 * (index - 6) + 16));
            }
        }

        for (Object obj : func.getVarList()) {
            if (!allocator.exists(obj)) {
                allocator.put(obj, new OperandMemory(RegisterSet.rbp, 8 * (++top)));
            }
        }


        for (Scope scp : func.getScps()) {
            nasm.sectionText.addItem(new SectionItem(new Label(scp.getLabel()), null));
            if (scp.getType() == ScopeType.FUNC) {
                // enter the func
                nasm.sectionText
                        .addItem(new SectionItem(null, new InstructionPush(RegisterSet.rbp)));
                nasm.sectionText.addItem(new SectionItem(null,
                        new InstructionMov(RegisterSet.rbp, RegisterSet.rsp)));
                nasm.sectionText.addItem(
                        new SectionItem(null, new InstructionSub(RegisterSet.rsp, 8 * (top + 1))));

                index = 0;
                for (Object obj : func.getParams()) {
                    if (index < 6) {
                        Operand operand = allocator.get(obj);
                        nasm.sectionText.addItem(new SectionItem(null,
                                new InstructionMov(operand, regParams.get(index))));
                    }
                    index++;
                }
            }
            for (Inst inst : scp) {
                addInst(inst, allocator);
            }
        }

        // exit the func
        // nasm.sectionText.addItem(
        //         new SectionItem(null, new InstructionMov(RegisterSet.rsp, RegisterSet.rbp)));
        // nasm.sectionText.addItem(new SectionItem(null, new InstructionPop(RegisterSet.rbp)));
        // nasm.sectionText.addItem(new SectionItem(null, new InstructionRet()));
    }
}
