package com.github.espylapiza.compiler_mxstar.back_end;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionDB;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionResq;
import com.github.espylapiza.compiler_mxstar.nasm.Label;
import com.github.espylapiza.compiler_mxstar.nasm.NASM;
import com.github.espylapiza.compiler_mxstar.nasm.Operand;
import com.github.espylapiza.compiler_mxstar.nasm.OperandDBAddr;
import com.github.espylapiza.compiler_mxstar.nasm.OperandInt;
import com.github.espylapiza.compiler_mxstar.nasm.OperandMem;
import com.github.espylapiza.compiler_mxstar.nasm.OperandRegister;
import com.github.espylapiza.compiler_mxstar.nasm.OperandString;
import com.github.espylapiza.compiler_mxstar.nasm.RegisterSet;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncExtra;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Inst;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectBool;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectConstant;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectInt;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectNull;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectString;
import com.github.espylapiza.compiler_mxstar.pizza_ir.BasicBlock;

abstract class RegisterAllocator {
    abstract Operand get(Object obj, OperandRegister prefer);

    abstract void enterFunc(FuncExtra func);

    abstract void enterBlock(BasicBlock blk);

    abstract void exitBlock(BasicBlock blk);

    abstract void arrange_fixed_register(Inst inst);

    abstract void allocateGlobal(FuncExtra initFunc);

    abstract void exitFunc();

    abstract void request(OperandRegister operandRegister);

    abstract void unfreeze_all();

    abstract void freeze_all();
}

// class StaticRegisterAllocator extends RegisterAllocator {
//     private Map<Object, Operand> maddr = new HashMap<Object, Operand>();
//     private int stackSize;

//     StaticRegisterAllocator() {
//     }

//     /**
//      * @return the stackSize
//      */
//     public int getStackSize() {
//         return stackSize;
//     }

//     void noneAllocate(NASM nasm, FuncExtra func) {
//         int top = 0; // 1 base

//         if (func.getOwnerClass() != null) {
//             if (!exists(func.getVarList().get(0))) {
//                 Operand operand = new OperandMem(RegisterSet.rbp, -8 * (++top));
//                 put(func.getVarList().get(0), operand);
//             }
//         }

//         for (Object obj : func.getParams()) {
//             if (!exists(obj)) {
//                 Operand operand;
//                 operand = new OperandMem(RegisterSet.rbp, -8 * (++top));
//                 put(obj, operand);
//             }
//         }

//         for (Object obj : func.getVarList()) {
//             if (!exists(obj)) {
//                 Operand operand;
//                 if (obj instanceof ObjectConstant) {
//                     if (obj instanceof ObjectBool) {
//                         operand = new OperandInt(((ObjectBool) obj).value);
//                     } else if (obj instanceof ObjectInt) {
//                         operand = new OperandInt(((ObjectInt) obj).value);
//                     } else if (obj instanceof ObjectString) {
//                         OperandString operandStr = new OperandString(((ObjectString) obj).value);
//                         Label label = Label.newDB();
//                         nasm.sectionData.addItem(label);
//                         nasm.sectionData.addItem(new InstructionDB(operandStr));
//                         operand = new OperandDBAddr(label);
//                     } else if (obj instanceof ObjectNull) {
//                         operand = new OperandInt(0);
//                     } else {
//                         assert false;
//                         operand = null;
//                     }
//                 } else {
//                     operand = new OperandMem(RegisterSet.rbp, -8 * (++top));
//                 }
//                 put(obj, operand);
//             }
//         }

//         for (Object obj : func.getVarList()) {
//             if (get(obj) == null) {
//                 assert false;
//             }
//         }
//         stackSize = (8 * top + 15) / 16 * 16;
//     }

//     void linearScanningAllocate(NASM nasm, FuncExtra func) {
//         final List<OperandRegister> regTemp = Arrays.asList(/*RegisterSet.rdi, RegisterSet.rsi, RegisterSet.rax,*/
//                 RegisterSet.r10, RegisterSet.r11);
//         final int cntRegTemp = 1;

//         List<LiveInterval> unhandledList = getLiveIntervals(func);
//         LiveInterval[] active = new LiveInterval[cntRegTemp];

//         for (LiveInterval interval : unhandledList) {
//             Integer curl = interval.start;

//             for (int i = 0; i < cntRegTemp; i++) {
//                 if (active[i] != null && active[i].end < curl) {
//                     active[i] = null;
//                 }
//             }

//             boolean spill = true;
//             for (int i = 0; i < cntRegTemp; i++) {
//                 if (active[i] == null) {
//                     active[i] = interval;
//                     spill = false;

//                     maddr.put(interval.obj, regTemp.get(i));
//                     break;
//                 }
//             }

//             if (spill) {
//                 int maxpos = 0;
//                 for (int i = 0; i < cntRegTemp; i++) {
//                     if (active[i].start > active[maxpos].start) {
//                         maxpos = i;
//                     }
//                 }
//                 if (active[maxpos].end > interval.end) {
//                     maddr.remove(active[maxpos].obj);
//                     active[maxpos] = interval;
//                     maddr.put(interval.obj, regTemp.get(maxpos));
//                 }
//             }
//         }

//         System.out.println("****{");
//         for (Map.Entry<Object, Operand> entry : maddr.entrySet()) {
//             System.out.println("---");
//             System.out.println(entry.getKey());
//             System.out.println(entry.getValue());
//         }
//         System.out.println("}****");
//         noneAllocate(nasm, func);
//     }

//     /**
//      * Get liveness intervals.
//      * @param func
//      * @return liveness intervals sorted by liveStart.
//      */
//     private List<LiveInterval> getLiveIntervals(FuncExtra func) {
//         Map<Object, LiveInterval> mIntervals = new HashMap<Object, LiveInterval>();
//         Integer lineNum = 0;
//         if (func.getOwnerClass() != null) {
//             Object obj = func.getVarList().get(0);
//             if (!exists(obj)) {
//                 mIntervals.put(obj, new LiveInterval(lineNum, lineNum, obj));
//             }
//         }

//         for (Object obj : func.getParams()) {
//             if (!exists(obj)) {
//                 mIntervals.put(obj, new LiveInterval(lineNum, lineNum, obj));
//             }
//         }

//         for (BasicBlock scp : func.getBlocks()) {
//             for (Inst inst : scp.getInsts()) {
//                 lineNum++;

//                 List<Object> objs = new ArrayList<Object>();
//                 for (Object obj : inst.getObjects()) {
//                     objs.add(obj);
//                 }
//                 if (inst.dst != null) {
//                     objs.add(inst.dst);
//                 }

//                 for (Object obj : objs) {
//                     if (obj != null && obj.belong != null) {
//                         if (obj instanceof ObjectConstant) {
//                             continue;
//                         }

//                         if (!mIntervals.containsKey(obj)) {
//                             mIntervals.put(obj, new LiveInterval(lineNum, lineNum, obj));
//                         }
//                         mIntervals.get(obj).end = lineNum;
//                     }
//                 }
//             }
//         }

//         List<LiveInterval> intervals = new ArrayList<LiveInterval>();
//         for (Map.Entry<Object, LiveInterval> entry : mIntervals.entrySet()) {
//             intervals.add(entry.getValue());
//         }

//         intervals.sort((lhs, rhs) -> lhs.start.compareTo(rhs.start));
//         return intervals;
//     }

//     void allocate(NASM nasm, FuncExtra func) {
//         Map<Object, LiveInterval> intervals = new HashMap<Object, LiveInterval>();

//         Integer lineNum = 0;

//         if (func.getOwnerClass() != null) {
//             Object obj = func.getVarList().get(0);
//             if (!exists(obj)) {
//                 intervals.put(obj, new LiveInterval(-1, -1, obj));
//             }
//         }

//         for (Object obj : func.getParams()) {
//             if (!exists(obj)) {
//                 intervals.put(obj, new LiveInterval(-1, -1, obj));
//             }
//         }

//         for (BasicBlock scp : func.getBlocks()) {
//             for (Inst inst : scp.getInsts()) {
//                 lineNum++;

//                 List<Object> objs = new ArrayList<Object>();
//                 for (Object obj : inst.getObjects()) {
//                     objs.add(obj);
//                 }
//                 if (inst.dst != null) {
//                     objs.add(inst.dst);
//                 }

//                 for (Object obj : objs) {
//                     if (obj != null && obj.belong != null) {
//                         if (obj instanceof ObjectConstant) {
//                             continue;
//                         }

//                         if (!intervals.containsKey(obj)) {
//                             intervals.put(obj, new LiveInterval(lineNum, lineNum, obj));
//                         }
//                         intervals.get(obj).end = lineNum;
//                     }
//                 }
//             }
//         }

//         for (Map.Entry<Object, LiveInterval> entry : intervals.entrySet()) {
//             LiveInterval interval = entry.getValue();
//             if (interval.start + 1 == interval.end) {
//                 maddr.put(interval.obj, RegisterSet.r10);
//             }
//         }

//         noneAllocate(nasm, func);
//     }

//     public void bssAllocate(NASM nasm, FuncExtra initFunc) {
//         for (Object obj : initFunc.getDefinedVariables()) {
//             nasm.sectionBSS.addItem(new InstructionResq("$" + obj.name));
//             Operand operand = new OperandMem("$" + obj.name, 0);
//             put(obj, operand);
//         }
//     }

//     private void put(Object object, Operand operand) {
//         maddr.put(object, operand);
//     }

//     private boolean exists(Object obj) {
//         return maddr.containsKey(obj);
//     }

//     @Override
//     Operand get(Object obj) {
//         Operand result = maddr.get(obj);
//         return result;
//     }

//     @Override
//     void cache(Object obj) {

//     }

//     @Override
//     void enterBlock(BasicBlock blk) {

//     }

//     @Override
//     void exitBlock(BasicBlock blk) {

//     }

//     @Override
//     void arrange_fixed_register(Inst inst) {

//     }
// }

// class LiveInterval {
//     Integer start, end;
//     Object obj;

//     LiveInterval(Integer l, Integer r, Object obj) {
//         this.start = l;
//         this.end = r;
//         this.obj = obj;
//     }
// }
