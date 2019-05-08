





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


SECTION .text   

_MS_print:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        mov     qword [rbp-8H], rdi
        mov     rax, qword [rbp-8H]
        add     rax, 4
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
        add     rax, 4
        mov     rdi, rax
        call    puts
        nop
        leave
        ret


_MS_getString:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 304


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
        mov     dword [rbp-124H], eax
        mov     eax, dword [rbp-124H]
        cdqe
        add     rax, 4
        mov     qword [rbp-120H], rax
        mov     eax, dword [rbp-124H]
        lea     edx, [rax+0FFFFFFH]
        test    eax, eax
        cmovs   eax, edx
        sar     eax, 24
        mov     byte [rbp-110H], al
        mov     eax, dword [rbp-124H]
        lea     edx, [rax+0FFFFH]
        test    eax, eax
        cmovs   eax, edx
        sar     eax, 16
        mov     edx, eax
        mov     eax, edx
        sar     eax, 31
        shr     eax, 24
        add     edx, eax
        movzx   edx, dl
        sub     edx, eax
        mov     eax, edx
        mov     byte [rbp-10FH], al
        mov     eax, dword [rbp-124H]
        lea     edx, [rax+0FFH]
        test    eax, eax
        cmovs   eax, edx
        sar     eax, 8
        mov     edx, eax
        mov     eax, edx
        sar     eax, 31
        shr     eax, 24
        add     edx, eax
        movzx   edx, dl
        sub     edx, eax
        mov     eax, edx
        mov     byte [rbp-10EH], al
        mov     eax, dword [rbp-124H]
        cdq
        shr     edx, 24
        add     eax, edx
        movzx   eax, al
        sub     eax, edx
        mov     byte [rbp-10DH], al
        mov     rax, qword [rbp-120H]
        mov     rdi, rax
        call    malloc
        mov     qword [rbp-118H], rax
        lea     rax, [rbp-110H]
        add     rax, 4
        mov     rdx, qword [rbp-120H]
        mov     rcx, qword [rbp-118H]
        mov     rsi, rax
        mov     rdi, rcx
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
        mov     rax, qword [rbp-8H]
        lea     rcx, [rax+4H]
        mov     eax, dword [rbp-14H]
        mov     edx, eax
        lea     rsi, [rel L_004]
        mov     rdi, rcx
        mov     eax, 0
        call    sprintf
        mov     rax, qword [rbp-8H]
        add     rax, 4
        mov     rdi, rax
        call    strlen
        mov     dword [rbp-0CH], eax
        mov     eax, dword [rbp-0CH]
        lea     edx, [rax+0FFFFFFH]
        test    eax, eax
        cmovs   eax, edx
        sar     eax, 24
        mov     edx, eax
        mov     rax, qword [rbp-8H]
        mov     byte [rax], dl
        mov     eax, dword [rbp-0CH]
        lea     edx, [rax+0FFFFH]
        test    eax, eax
        cmovs   eax, edx
        sar     eax, 16
        mov     edx, eax
        mov     eax, edx
        sar     eax, 31
        shr     eax, 24
        add     edx, eax
        movzx   edx, dl
        sub     edx, eax
        mov     eax, edx
        mov     edx, eax
        mov     rax, qword [rbp-8H]
        add     rax, 1
        mov     byte [rax], dl
        mov     eax, dword [rbp-0CH]
        lea     edx, [rax+0FFH]
        test    eax, eax
        cmovs   eax, edx
        sar     eax, 8
        mov     edx, eax
        mov     eax, edx
        sar     eax, 31
        shr     eax, 24
        add     edx, eax
        movzx   edx, dl
        sub     edx, eax
        mov     eax, edx
        mov     edx, eax
        mov     rax, qword [rbp-8H]
        add     rax, 2
        mov     byte [rax], dl
        mov     eax, dword [rbp-0CH]
        cdq
        shr     edx, 24
        add     eax, edx
        movzx   eax, al
        sub     eax, edx
        mov     edx, eax
        mov     rax, qword [rbp-8H]
        add     rax, 3
        mov     byte [rax], dl
        mov     rax, qword [rbp-8H]
        leave
        ret



SECTION .data   


SECTION .bss    


SECTION .rodata 

L_003:
        db 25H, 73H, 00H

L_004:
        db 25H, 64H, 00H


