





default rel

global _MS_print
global _MS_println
global _MS_getString
global _MS_getInt
global _MS_toString

extern sprintf
extern __stack_chk_fail
extern memcpy
extern malloc
extern strlen
extern __isoc99_scanf
extern puts
extern printf
extern _GLOBAL_OFFSET_TABLE_


SECTION .text   align=1

_MS_print:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        mov     qword [rbp-8H], rdi
        mov     rax, qword [rbp-8H]
        mov     rsi, rax
        lea     rdi, [rel L_003]
        mov     eax, 0
        call    printf
        nop
        leave
        ret


_MS_println:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        mov     qword [rbp-8H], rdi
        mov     rax, qword [rbp-8H]
        mov     rdi, rax
        call    puts
        nop
        leave
        ret


_MS_getString:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 288


        mov     rax, qword [fs:abs 28H]
        mov     qword [rbp-8H], rax
        xor     eax, eax
        lea     rax, [rbp-110H]
        mov     rsi, rax
        lea     rdi, [rel L_003]
        mov     eax, 0
        call    __isoc99_scanf
        lea     rax, [rbp-110H]
        mov     rdi, rax
        call    strlen
        mov     qword [rbp-120H], rax
        mov     rax, qword [rbp-120H]
        mov     rdi, rax
        call    malloc
        mov     qword [rbp-118H], rax
        mov     rdx, qword [rbp-120H]
        lea     rcx, [rbp-110H]
        mov     rax, qword [rbp-118H]
        mov     rsi, rcx
        mov     rdi, rax
        call    memcpy
        mov     rax, qword [rbp-118H]
        mov     rcx, qword [rbp-8H]


        xor     rcx, qword [fs:abs 28H]
        jz      L_001
        call    __stack_chk_fail
L_001:  leave
        ret


_MS_getInt:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16


        mov     rax, qword [fs:abs 28H]
        mov     qword [rbp-8H], rax
        xor     eax, eax
        lea     rax, [rbp-0CH]
        mov     rsi, rax
        lea     rdi, [rel L_004]
        mov     eax, 0
        call    __isoc99_scanf
        mov     eax, dword [rbp-0CH]
        mov     rdx, qword [rbp-8H]


        xor     rdx, qword [fs:abs 28H]
        jz      L_002
        call    __stack_chk_fail
L_002:  leave
        ret


_MS_toString:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 32
        mov     dword [rbp-14H], edi
        mov     edi, 16
        call    malloc
        mov     qword [rbp-8H], rax
        mov     edx, dword [rbp-14H]
        mov     rax, qword [rbp-8H]
        lea     rsi, [rel L_004]
        mov     rdi, rax
        mov     eax, 0
        call    sprintf
        mov     rax, qword [rbp-8H]
        leave
        ret



SECTION .data   align=1


SECTION .bss    align=1


SECTION .rodata align=1

L_003:
        db 25H, 73H, 00H

L_004:
        db 25H, 64H, 00H


