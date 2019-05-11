package com.github.espylapiza.compiler_mxstar.optimizers;

import java.util.List;

import com.github.espylapiza.compiler_mxstar.nasm.InstructionAdd;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionDec;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionInc;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionJmp;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionMov;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionSub;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionXor;
import com.github.espylapiza.compiler_mxstar.nasm.Label;
import com.github.espylapiza.compiler_mxstar.nasm.NASM;
import com.github.espylapiza.compiler_mxstar.nasm.OperandInt;
import com.github.espylapiza.compiler_mxstar.nasm.OperandRegister;
import com.github.espylapiza.compiler_mxstar.nasm.SectionItem;

public class NASMOptimizer {
    NASM nasm;

    public NASMOptimizer(NASM nasm) {
        this.nasm = nasm;
    }

    public void optimize() {
        context_free_optimization();
    }

    private void context_free_optimization() {
        List<SectionItem> items = nasm.sectionText.items;
        for (int i = 0; i < items.size(); i++) {
            SectionItem item0 = items.get(i);
            SectionItem item1 = null;
            if (i + 1 < items.size()) {
                item1 = items.get(i + 1);
            }

            if (item0 instanceof InstructionAdd) {
                InstructionAdd itemAdd = (InstructionAdd) item0;
                if (itemAdd.imm != null) {
                    if (itemAdd.imm == -1) {
                        items.set(i, new InstructionDec(itemAdd.dst));
                    } else if (itemAdd.imm == 0) {
                        items.remove(i);
                        i--;
                    } else if (itemAdd.imm == 1) {
                        items.set(i, new InstructionInc(itemAdd.dst));
                    }
                }
            } else if (item0 instanceof InstructionSub) {
                InstructionSub itemSub = (InstructionSub) item0;
                if (itemSub.imm != null && itemSub.imm == 1) {
                    if (itemSub.imm == -1) {
                        items.set(i, new InstructionInc(itemSub.dst));
                    } else if (itemSub.imm == 0) {
                        items.remove(i);
                        i--;
                    } else if (itemSub.imm == 1) {
                        items.set(i, new InstructionDec(itemSub.dst));
                    }
                }
            } else if (item0 instanceof InstructionMov) {
                InstructionMov itemMov = (InstructionMov) item0;
                if (itemMov.dst instanceof OperandRegister && itemMov.src instanceof OperandInt) {
                    OperandInt src = (OperandInt) itemMov.src;
                    if (src.getValue() == 0) {
                        items.set(i, new InstructionXor(itemMov.dst, itemMov.dst));
                    }
                } else if (itemMov.src.equals(itemMov.dst)) {
                    items.remove(i);
                    i--;
                } else if (item1 instanceof InstructionMov) {
                    InstructionMov itemMov1 = (InstructionMov) item1;
                    if (itemMov.src.equals(itemMov1.dst) && itemMov.dst.equals(itemMov1.src)) {
                        items.remove(i + 1);
                        i--;
                    }
                }
            } else if (item0 instanceof InstructionJmp && item1 instanceof Label) {
                InstructionJmp itemJmp = (InstructionJmp) item0;
                Label label = (Label) item1;
                if (itemJmp.scp.getLabel().equals(label.getName())) {
                    items.remove(i);
                }
            }
        }
    }
}