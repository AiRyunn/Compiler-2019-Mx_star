package com.github.espylapiza.compiler_mxstar.nasm;

public class RegisterSet {
    // rax, rcx, rdx, rbx, rsp, rbp, rsi, rdi, r8, r9, r10, r11, r12, r13, r14, r15
    public static final OperandRegister64Bit rax = new OperandRegister64Bit("rax");
    public static final OperandRegister64Bit rcx = new OperandRegister64Bit("rcx");
    public static final OperandRegister64Bit rdx = new OperandRegister64Bit("rdx");
    public static final OperandRegister64Bit rbx = new OperandRegister64Bit("rbx");
    public static final OperandRegister64Bit rsp = new OperandRegister64Bit("rsp");
    public static final OperandRegister64Bit rbp = new OperandRegister64Bit("rbp");
    public static final OperandRegister64Bit rsi = new OperandRegister64Bit("rsi");
    public static final OperandRegister64Bit rdi = new OperandRegister64Bit("rdi");
    public static final OperandRegister64Bit r8 = new OperandRegister64Bit("r8");
    public static final OperandRegister64Bit r9 = new OperandRegister64Bit("r9");
    public static final OperandRegister64Bit r10 = new OperandRegister64Bit("r10");
    public static final OperandRegister64Bit r11 = new OperandRegister64Bit("r11");
    public static final OperandRegister64Bit r12 = new OperandRegister64Bit("r12");
    public static final OperandRegister64Bit r13 = new OperandRegister64Bit("r13");
    public static final OperandRegister64Bit r14 = new OperandRegister64Bit("r14");
    public static final OperandRegister64Bit r15 = new OperandRegister64Bit("r15");

    // eax, ecx
    public static final OperandRegister32Bit eax = new OperandRegister32Bit("eax");
    public static final OperandRegister32Bit ecx = new OperandRegister32Bit("ecx");
    public static final OperandRegister32Bit edx = new OperandRegister32Bit("edx");
    public static final OperandRegister32Bit ebx = new OperandRegister32Bit("ebx");
    public static final OperandRegister32Bit esp = new OperandRegister32Bit("esp");
    public static final OperandRegister32Bit ebp = new OperandRegister32Bit("ebp");
    public static final OperandRegister32Bit esi = new OperandRegister32Bit("esi");
    public static final OperandRegister32Bit edi = new OperandRegister32Bit("edi");
    public static final OperandRegister32Bit r8d = new OperandRegister32Bit("r8d");
    public static final OperandRegister32Bit r9d = new OperandRegister32Bit("r9d");
    public static final OperandRegister32Bit r10d = new OperandRegister32Bit("r10d");
    public static final OperandRegister32Bit r11d = new OperandRegister32Bit("r11d");
    public static final OperandRegister32Bit r12d = new OperandRegister32Bit("r12d");
    public static final OperandRegister32Bit r13d = new OperandRegister32Bit("r13d");
    public static final OperandRegister32Bit r14d = new OperandRegister32Bit("r14d");
    public static final OperandRegister32Bit r15d = new OperandRegister32Bit("r15d");

    // al, cl
    public static final OperandRegister8Bit al = new OperandRegister8Bit("al");
    public static final OperandRegister8Bit cl = new OperandRegister8Bit("cl");

    public static OperandRegister32Bit trans64_32(OperandRegister64Bit operand) {
        switch (operand.name) {
        case "rax":
            return eax;
        case "rcx":
            return ecx;
        case "rdx":
            return edx;
        case "rbx":
            return ebx;
        case "rsp":
            return esp;
        case "rbp":
            return ebp;
        case "rsi":
            return esi;
        case "rdi":
            return edi;
        case "r8":
            return r8d;
        case "r9":
            return r9d;
        case "r10":
            return r10d;
        case "r11":
            return r11d;
        case "r12":
            return r12d;
        case "r13":
            return r13d;
        case "r14":
            return r14d;
        case "r15":
            return r15d;
        default:
            return null;
        }
    }
}
