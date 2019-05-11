package com.github.espylapiza.compiler_mxstar.optimizers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.espylapiza.compiler_mxstar.pizza_ir.Func;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncExtra;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Inst;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstAlloc;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstBr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstCall;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstLoad;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstMov;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstOffset;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstRet;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstStore;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectBool;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectInt;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectPtr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Scope;
import com.github.espylapiza.compiler_mxstar.pizza_ir.VarList;

public class PizzaIROptimizer {
    private PizzaIR ir;

    public PizzaIROptimizer(PizzaIR ir) {
        this.ir = ir;
    }

    public void optimize() {
        constant_folding_and_propagation();
    }

    public PizzaIR getIR() {
        return ir;
    }

    private void constant_folding_and_propagation() {
        for (Func func : ir.funcList) {
            if (func instanceof FuncExtra) {
                do_function_constant_folding_and_propagation((FuncExtra) func);
            }
        }
    }

    private void do_function_constant_folding_and_propagation(FuncExtra func) {
        Map<Object, Object> alter = new HashMap<Object, Object>();

        for (Scope scp : func.getScps()) {
            List<Inst> insts = scp.getInsts();

            for (int i = 0; i < insts.size(); i++) {
                Inst inst = insts.get(i);

                if (inst instanceof InstCall) {
                    Inst instFold = inst_folding((InstCall) inst, alter);
                    if (instFold != null) {
                        insts.set(i, instFold);
                    }
                }
            }
        }

        alter_variables(func, alter);
    }

    private Inst inst_folding(InstCall instCall, Map<Object, Object> alter) {
        Object dst = instCall.dst, src = null;
        String strAddr = instCall.func.getAddr().toString();

        if (strAddr.startsWith("_MS_int.")) {
            int cntParams = instCall.params.count();

            if (cntParams == 1) {
                Object obj = instCall.params.get(0);
                if (alter.containsKey(obj)) {
                    obj = alter.get(obj);
                }

                if (obj instanceof ObjectInt) {
                    src = ((ObjectInt) obj).clone();
                    switch (strAddr) {
                    case "_MS_int.__pos__":
                        ((ObjectInt) src).value = ((ObjectInt) obj).value;
                        break;
                    case "_MS_int.__neg__":
                        ((ObjectInt) src).value = -((ObjectInt) obj).value;
                        break;
                    case "_MS_int.__bitinv__":
                        ((ObjectInt) src).value = ~((ObjectInt) obj).value;
                        break;
                    default:
                        src = null;
                    }
                }
            } else if (cntParams == 2) {
                Object obj0 = instCall.params.get(0), obj1 = instCall.params.get(1);
                if (alter.containsKey(obj0)) {
                    obj0 = alter.get(obj0);
                }
                if (alter.containsKey(obj1)) {
                    obj1 = alter.get(obj1);
                }

                if (obj0 instanceof ObjectInt && obj1 instanceof ObjectInt) {
                    switch (strAddr) {
                    case "_MS_int.__add__":
                        src = ((ObjectInt) obj0).clone();
                        ((ObjectInt) src).value = ((ObjectInt) obj0).value + ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__sub__":
                        src = ((ObjectInt) obj0).clone();
                        ((ObjectInt) src).value = ((ObjectInt) obj0).value - ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__mul__":
                        src = ((ObjectInt) obj0).clone();
                        ((ObjectInt) src).value = ((ObjectInt) obj0).value * ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__div__":
                        src = ((ObjectInt) obj0).clone();
                        ((ObjectInt) src).value = ((ObjectInt) obj0).value / ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__mod__":
                        src = ((ObjectInt) obj0).clone();
                        ((ObjectInt) src).value = ((ObjectInt) obj0).value % ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__shl__":
                        src = ((ObjectInt) obj0).clone();
                        ((ObjectInt) src).value = ((ObjectInt) obj0).value << ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__shr__":
                        src = ((ObjectInt) obj0).clone();
                        ((ObjectInt) src).value = ((ObjectInt) obj0).value >> ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__bitand__":
                        src = ((ObjectInt) obj0).clone();
                        ((ObjectInt) src).value = ((ObjectInt) obj0).value & ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__bitxor__":
                        src = ((ObjectInt) obj0).clone();
                        ((ObjectInt) src).value = ((ObjectInt) obj0).value ^ ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__bitor__":
                        src = ((ObjectInt) obj0).clone();
                        ((ObjectInt) src).value = ((ObjectInt) obj0).value | ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__lt__":
                        src = new ObjectBool(obj0.belong, null, null, null);
                        ((ObjectBool) src).value = ((ObjectInt) obj0).value < ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__gt__":
                        src = new ObjectBool(obj0.belong, null, null, null);
                        ((ObjectBool) src).value = ((ObjectInt) obj0).value > ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__le__":
                        src = new ObjectBool(obj0.belong, null, null, null);
                        ((ObjectBool) src).value = ((ObjectInt) obj0).value <= ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__ge__":
                        src = new ObjectBool(obj0.belong, null, null, null);
                        ((ObjectBool) src).value = ((ObjectInt) obj0).value >= ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__eq__":
                        src = new ObjectBool(obj0.belong, null, null, null);
                        ((ObjectBool) src).value = ((ObjectInt) obj0).value == ((ObjectInt) obj1).value;
                        break;
                    case "_MS_int.__ne__":
                        src = new ObjectBool(obj0.belong, null, null, null);
                        ((ObjectBool) src).value = ((ObjectInt) obj0).value != ((ObjectInt) obj1).value;
                        break;
                    default:
                        src = null;
                        assert false;
                    }
                }
            }
        }
        if (src != null) {
            if (dst.name == null) {
                alter.put(dst, src);
            }
            return new InstMov(dst, src);
        }
        return null;
    }

    private void alter_variables(FuncExtra func, Map<Object, Object> alter) {
        if (alter == null) {
            alter = new HashMap<Object, Object>();
        }

        Set<Object> used = new HashSet<Object>();

        for (Scope scp : func.getScps()) {
            for (Inst inst : scp.getInsts()) {
                if (inst instanceof InstAlloc) {
                    if (alter.containsKey(((InstAlloc) inst).size)) {
                        ((InstAlloc) inst).size = alter.get(((InstAlloc) inst).size);
                    } else {
                        used.add(((InstAlloc) inst).size);
                    }
                } else if (inst instanceof InstBr) {
                    if (alter.containsKey(((InstBr) inst).obj)) {
                        ((InstBr) inst).obj = alter.get(((InstBr) inst).obj);
                    } else {
                        used.add(((InstBr) inst).obj);
                    }
                } else if (inst instanceof InstCall) {
                    for (int i = 0; i < ((InstCall) inst).params.count(); i++) {
                        Object obj = ((InstCall) inst).params.get(i);
                        if (obj instanceof ObjectPtr) {
                            // used.add(obj);
                            obj = ((ObjectPtr) obj).obj;
                            used.add(obj);
                            continue;
                        }

                        if (alter.containsKey(obj)) {
                            ((InstCall) inst).params.set(i, alter.get(obj));
                        } else {
                            used.add(obj);
                        }
                    }
                    if (alter.containsKey(((InstCall) inst).objThis)) {
                        ((InstCall) inst).objThis = alter.get(((InstCall) inst).objThis);
                    } else {
                        used.add(((InstCall) inst).objThis);
                    }
                } else if (inst instanceof InstLoad) {
                    if (alter.containsKey(((InstLoad) inst).src)) {
                        ((InstLoad) inst).src = alter.get(((InstLoad) inst).src);
                    } else {
                        used.add(((InstLoad) inst).src);
                    }
                } else if (inst instanceof InstMov) {
                    if (alter.containsKey(((InstMov) inst).src)) {
                        ((InstMov) inst).src = alter.get(((InstMov) inst).src);
                    } else {
                        used.add(((InstMov) inst).src);
                    }
                } else if (inst instanceof InstOffset) {
                    if (alter.containsKey(((InstOffset) inst).src)) {
                        ((InstOffset) inst).src = alter.get(((InstOffset) inst).src);
                    } else {
                        used.add(((InstOffset) inst).src);
                    }
                    if (alter.containsKey(((InstOffset) inst).offset)) {
                        ((InstOffset) inst).offset = alter.get(((InstOffset) inst).offset);
                    } else {
                        used.add(((InstOffset) inst).offset);
                    }
                } else if (inst instanceof InstRet) {
                    if (alter.containsKey(((InstRet) inst).obj)) {
                        ((InstRet) inst).obj = alter.get(((InstRet) inst).obj);
                    } else {
                        used.add(((InstRet) inst).obj);
                    }
                } else if (inst instanceof InstStore) {
                    if (alter.containsKey(((InstStore) inst).addr)) {
                        ((InstStore) inst).addr = alter.get(((InstStore) inst).addr);
                    } else {
                        used.add(((InstStore) inst).addr);
                    }
                    if (alter.containsKey(((InstStore) inst).src)) {
                        ((InstStore) inst).src = alter.get(((InstStore) inst).src);
                    } else {
                        used.add(((InstStore) inst).src);
                    }
                }
            }
        }

        for (Scope scp : func.getScps()) {
            for (Inst inst : scp.getInsts()) {
                if (inst.dst != null && !used.contains(inst.dst)) {
                    if (inst instanceof InstAlloc) {
                        inst.valid = false;
                    } else if (inst instanceof InstCall) {
                        inst.dst = null;
                    } else if (inst instanceof InstLoad) {
                        inst.valid = false;
                    } else if (inst instanceof InstMov) {
                        if (!(inst.dst != null && inst.dst.belong == null && inst.dst.name != null)) {
                            inst.valid = false;
                        }
                    } else if (inst instanceof InstOffset) {
                        inst.valid = false;
                    }
                }
            }

            List<Inst> insts = new ArrayList<Inst>();
            for (Inst inst : scp.getInsts()) {
                if (inst.valid) {
                    insts.add(inst);
                }
            }

            scp.setInsts(insts);
        }

        update_variable_list(func);
    }

    private void update_variable_list(FuncExtra func) {
        Set<Object> used = new HashSet<Object>();

        for (Scope scp : func.getScps()) {
            for (Inst inst : scp.getInsts()) {
                if (inst.dst != null) {
                    used.add(inst.dst);
                }

                if (inst instanceof InstAlloc) {
                    used.add(((InstAlloc) inst).size);
                } else if (inst instanceof InstBr) {
                    used.add(((InstBr) inst).obj);
                } else if (inst instanceof InstCall) {
                    for (int i = 0; i < ((InstCall) inst).params.count(); i++) {
                        Object obj = ((InstCall) inst).params.get(i);
                        used.add(obj);
                    }
                    used.add(((InstCall) inst).objThis);
                } else if (inst instanceof InstLoad) {
                    used.add(((InstLoad) inst).src);
                } else if (inst instanceof InstMov) {
                    used.add(((InstMov) inst).src);
                } else if (inst instanceof InstOffset) {
                    used.add(((InstOffset) inst).src);
                    used.add(((InstOffset) inst).offset);
                } else if (inst instanceof InstRet) {
                    used.add(((InstRet) inst).obj);
                } else if (inst instanceof InstStore) {
                    used.add(((InstStore) inst).addr);
                    used.add(((InstStore) inst).src);
                }
            }
        }

        VarList varList = new VarList();

        int nParams = func.getParams().count();
        if (func.getOwnerClass() != null) {
            nParams++;
        }

        for (int i = 0; i < func.getVarList().count(); i++) {
            Object obj = func.getVarList().get(i);

            if (func.getAddr().toString().equals("_init") && obj.name != null) {
                varList.add(obj);
            } else if (i < nParams || used.contains(obj)) {
                varList.add(obj);
            }
        }

        func.setVarList(varList);
    }
}
