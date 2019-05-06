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

    // al cl
    public static final OperandRegister8Bit al = new OperandRegister8Bit("al");
    public static final OperandRegister8Bit cl = new OperandRegister8Bit("cl");
}
