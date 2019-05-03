package com.github.espylapiza.compiler_mxstar.back_end;

import java.util.HashMap;
import java.util.Map;
import com.github.espylapiza.compiler_mxstar.nasm.Operand;
import com.github.espylapiza.compiler_mxstar.nasm.OperandInt;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectBool;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectInt;

public class RegisterAllocator {
    Map<Object, Operand> maddr = new HashMap<Object, Operand>();

    RegisterAllocator() {
    }

    void put(Object object, Operand operand) {
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
