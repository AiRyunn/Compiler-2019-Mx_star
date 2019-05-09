





default rel

global _MS_print
global _MS_println
global _MS_getString
global _MS_getInt
global _MS_toString
global _string_parseInt
global _string_ord
global _string_substring
global _string___add__

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
        lea     rdi, [rel L_006]
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
        sub     rsp, 288


        mov     rax, qword [fs:abs 28H]
        mov     qword [rbp-8H], rax
        xor     eax, eax
        lea     rax, [rbp-110H]
        mov     rsi, rax
        lea     rdi, [rel L_006]
        mov     eax, 0
        call    __isoc99_scanf
        lea     rax, [rbp-110H]
        mov     rdi, rax
        call    strlen
        mov     dword [rbp-11CH], eax
        mov     eax, dword [rbp-11CH]
        add     eax, 5
        cdqe
        mov     rdi, rax
        call    malloc
        mov     qword [rbp-118H], rax
        mov     eax, dword [rbp-11CH]
        movsxd  rdx, eax
        mov     rax, qword [rbp-118H]
        lea     rcx, [rax+4H]
        lea     rax, [rbp-110H]
        mov     rsi, rax
        mov     rdi, rcx
        call    memcpy
        mov     eax, dword [rbp-11CH]
        lea     edx, [rax+0FFFFFFH]
        test    eax, eax
        cmovs   eax, edx
        sar     eax, 24
        mov     edx, eax
        mov     rax, qword [rbp-118H]
        add     rax, 3
        mov     byte [rax], dl
        mov     eax, dword [rbp-11CH]
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
        mov     rax, qword [rbp-118H]
        add     rax, 2
        mov     byte [rax], dl
        mov     eax, dword [rbp-11CH]
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
        mov     rax, qword [rbp-118H]
        add     rax, 1
        mov     byte [rax], dl
        mov     eax, dword [rbp-11CH]
        cdq
        shr     edx, 24
        add     eax, edx
        movzx   eax, al
        sub     eax, edx
        mov     edx, eax
        mov     rax, qword [rbp-118H]
        mov     byte [rax], dl
        mov     eax, dword [rbp-11CH]
        cdqe
        lea     rdx, [rax+4H]
        mov     rax, qword [rbp-118H]
        add     rax, rdx
        mov     byte [rax], 0
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
        lea     rdi, [rel L_007]
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
        lea     rsi, [rel L_007]
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
        add     rax, 3
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
        add     rax, 2
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
        add     rax, 1
        mov     byte [rax], dl
        mov     eax, dword [rbp-0CH]
        cdq
        shr     edx, 24
        add     eax, edx
        movzx   eax, al
        sub     eax, edx
        mov     edx, eax
        mov     rax, qword [rbp-8H]
        mov     byte [rax], dl
        mov     rax, qword [rbp-8H]
        leave
        ret


_string_parseInt:
        push    rbp
        mov     rbp, rsp
        mov     qword [rbp-18H], rdi
        add     qword [rbp-18H], 4
        mov     qword [rbp-10H], 1
        mov     qword [rbp-8H], 0
        mov     rax, qword [rbp-18H]
        movzx   eax, byte [rax]
        cmp     al, 45
        jnz     L_004
        mov     qword [rbp-10H], -1
        add     qword [rbp-18H], 1
        jmp     L_004

L_003:  mov     rdx, qword [rbp-8H]
        mov     rax, rdx
        shl     rax, 2
        add     rax, rdx
        add     rax, rax
        mov     rdx, rax
        mov     rax, qword [rbp-18H]
        movzx   eax, byte [rax]
        movzx   eax, al
        add     rax, rdx
        sub     rax, 48
        mov     qword [rbp-8H], rax
        add     qword [rbp-18H], 1
L_004:  mov     rax, qword [rbp-18H]
        movzx   eax, byte [rax]
        cmp     al, 47
        jbe     L_005
        mov     rax, qword [rbp-18H]
        movzx   eax, byte [rax]
        cmp     al, 57
        jbe     L_003
L_005:  mov     rax, qword [rbp-10H]
        imul    rax, qword [rbp-8H]
        pop     rbp
        ret


_string_ord:
        push    rbp
        mov     rbp, rsp
        mov     qword [rbp-8H], rdi
        mov     qword [rbp-10H], rsi
        mov     rax, qword [rbp-10H]
        lea     rdx, [rax+4H]
        mov     rax, qword [rbp-8H]
        add     rax, rdx
        movzx   eax, byte [rax]
        movzx   eax, al
        pop     rbp
        ret


_string_substring:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 48
        mov     qword [rbp-18H], rdi
        mov     qword [rbp-20H], rsi
        mov     qword [rbp-28H], rdx
        mov     rax, qword [rbp-28H]
        mov     edx, eax
        mov     rax, qword [rbp-20H]
        sub     edx, eax
        mov     eax, edx
        add     eax, 1
        mov     dword [rbp-0CH], eax
        mov     eax, dword [rbp-0CH]
        add     eax, 5
        cdqe
        mov     rdi, rax
        call    malloc
        mov     qword [rbp-8H], rax
        mov     eax, dword [rbp-0CH]
        cdqe
        mov     rdx, qword [rbp-18H]
        lea     rsi, [rdx+4H]
        mov     rdx, qword [rbp-8H]
        lea     rcx, [rdx+4H]
        mov     rdx, rax
        mov     rdi, rcx
        call    memcpy
        mov     rax, qword [rbp-28H]
        lea     rdx, [rax+3H]
        mov     rax, qword [rbp-8H]
        add     rax, rdx
        mov     byte [rax], 0
        mov     eax, dword [rbp-0CH]
        lea     edx, [rax+0FFFFFFH]
        test    eax, eax
        cmovs   eax, edx
        sar     eax, 24
        mov     edx, eax
        mov     rax, qword [rbp-8H]
        add     rax, 3
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
        add     rax, 2
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
        add     rax, 1
        mov     byte [rax], dl
        mov     eax, dword [rbp-0CH]
        cdq
        shr     edx, 24
        add     eax, edx
        movzx   eax, al
        sub     eax, edx
        mov     edx, eax
        mov     rax, qword [rbp-8H]
        mov     byte [rax], dl
        mov     rax, qword [rbp-8H]
        leave
        ret


_string___add__:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 48
        mov     qword [rbp-28H], rdi
        mov     qword [rbp-30H], rsi
        mov     rax, qword [rbp-28H]
        add     rax, 3
        movzx   eax, byte [rax]
        movzx   eax, al
        shl     eax, 8
        mov     edx, eax
        mov     rax, qword [rbp-28H]
        add     rax, 2
        movzx   eax, byte [rax]
        movzx   eax, al
        add     eax, edx
        shl     eax, 8
        mov     edx, eax
        mov     rax, qword [rbp-28H]
        add     rax, 1
        movzx   eax, byte [rax]
        movzx   eax, al
        add     eax, edx
        shl     eax, 8
        mov     edx, eax
        mov     rax, qword [rbp-28H]
        movzx   eax, byte [rax]
        movzx   eax, al
        add     eax, edx
        mov     dword [rbp-14H], eax
        mov     rax, qword [rbp-30H]
        add     rax, 3
        movzx   eax, byte [rax]
        movzx   eax, al
        shl     eax, 8
        mov     edx, eax
        mov     rax, qword [rbp-30H]
        add     rax, 2
        movzx   eax, byte [rax]
        movzx   eax, al
        add     eax, edx
        shl     eax, 8
        mov     edx, eax
        mov     rax, qword [rbp-30H]
        add     rax, 1
        movzx   eax, byte [rax]
        movzx   eax, al
        add     eax, edx
        shl     eax, 8
        mov     edx, eax
        mov     rax, qword [rbp-30H]
        movzx   eax, byte [rax]
        movzx   eax, al
        add     eax, edx
        mov     dword [rbp-10H], eax
        mov     edx, dword [rbp-14H]
        mov     eax, dword [rbp-10H]
        add     eax, edx
        mov     dword [rbp-0CH], eax
        mov     eax, dword [rbp-0CH]
        add     eax, 5
        cdqe
        mov     rdi, rax
        call    malloc
        mov     qword [rbp-8H], rax
        mov     eax, dword [rbp-14H]
        cdqe
        mov     rdx, qword [rbp-28H]
        lea     rsi, [rdx+4H]
        mov     rdx, qword [rbp-8H]
        lea     rcx, [rdx+4H]
        mov     rdx, rax
        mov     rdi, rcx
        call    memcpy
        mov     eax, dword [rbp-10H]
        cdqe
        mov     rdx, qword [rbp-30H]
        lea     rsi, [rdx+4H]
        mov     edx, dword [rbp-14H]
        movsxd  rdx, edx
        lea     rcx, [rdx+4H]
        mov     rdx, qword [rbp-8H]
        add     rcx, rdx
        mov     rdx, rax
        mov     rdi, rcx
        call    memcpy
        mov     eax, dword [rbp-0CH]
        cdqe
        lea     rdx, [rax+4H]
        mov     rax, qword [rbp-8H]
        add     rax, rdx
        mov     byte [rax], 0
        mov     eax, dword [rbp-0CH]
        lea     edx, [rax+0FFFFFFH]
        test    eax, eax
        cmovs   eax, edx
        sar     eax, 24
        mov     edx, eax
        mov     rax, qword [rbp-8H]
        add     rax, 3
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
        add     rax, 2
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
        add     rax, 1
        mov     byte [rax], dl
        mov     eax, dword [rbp-0CH]
        cdq
        shr     edx, 24
        add     eax, edx
        movzx   eax, al
        sub     eax, edx
        mov     edx, eax
        mov     rax, qword [rbp-8H]
        mov     byte [rax], dl
        mov     rax, qword [rbp-8H]
        leave
        ret



SECTION .data   


SECTION .bss    


SECTION .rodata 

L_006:
        db 25H, 73H, 00H

L_007:
        db 25H, 64H, 00H


