package com.github.espylapiza.compiler_mxstar.back_end;

import java.util.HashMap;
import java.util.Map;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionDB;
import com.github.espylapiza.compiler_mxstar.nasm.Label;
import com.github.espylapiza.compiler_mxstar.nasm.NASM;
import com.github.espylapiza.compiler_mxstar.nasm.Operand;
import com.github.espylapiza.compiler_mxstar.nasm.OperandDBAddr;
import com.github.espylapiza.compiler_mxstar.nasm.OperandInt;
import com.github.espylapiza.compiler_mxstar.nasm.OperandMemory;
import com.github.espylapiza.compiler_mxstar.nasm.OperandString;
import com.github.espylapiza.compiler_mxstar.nasm.RegisterSet;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncExtra;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectBool;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectConstant;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectInt;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectString;

// (8 * (top + 1) + 15) / 16 * 16
public class RegisterAllocator {
    Map<Object, Operand> maddr = new HashMap<Object, Operand>();
    private int stackSize;

    RegisterAllocator() {
    }

    /**
     * @return the stackSize
     */
    public int getStackSize() {
        return stackSize;
    }

    void naiveAllocate(NASM nasm, FuncExtra func) {
        int index = 0, top = 0; // 1 base
        for (Object obj : func.getParams()) {
            index++;
            if (index <= 6) {
                Operand operand = new OperandMemory(RegisterSet.rbp, -8 * (++top));
                put(obj, operand);
            } else {
                put(obj, new OperandMemory(RegisterSet.rbp, 8 * (index - 6) + 16));
            }
        }

        for (Object obj : func.getVarList()) {
            if (!exists(obj)) {
                Operand operand;
                if (obj instanceof ObjectConstant) {
                    if (obj instanceof ObjectBool) {
                        operand = new OperandInt(((ObjectBool) obj).value);
                    } else if (obj instanceof ObjectInt) {
                        operand = new OperandInt(((ObjectInt) obj).value);
                    } else if (obj instanceof ObjectString) {
                        OperandString operandStr = new OperandString(((ObjectString) obj).value);
                        Label label = Label.newDB();
                        nasm.sectionData.addItem(label);
                        nasm.sectionData.addItem(new InstructionDB(operandStr));
                        operand = new OperandDBAddr(label);
                    } else {
                        operand = null;
                    }
                } else {
                    operand = new OperandMemory(RegisterSet.rbp, -8 * (++top));
                }
                put(obj, operand);
            }
        }
        stackSize = (8 * top + 15) / 16 * 16;
    }

    private void put(Object object, Operand operand) {
        maddr.put(object, operand);
    }

    boolean exists(Object object) {
        return maddr.containsKey(object);
    }

    Operand get(Object object) {
        if (object instanceof ObjectInt) {
            return new OperandInt(((ObjectInt) object).value);
        } else if (object instanceof ObjectBool) {
            return new OperandInt(((ObjectBool) object).value);
        }
        return maddr.get(object);
    }
}
