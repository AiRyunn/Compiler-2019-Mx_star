





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
global _strcmp

extern memcpy
extern __sprintf_chk
extern __stack_chk_fail
extern malloc
extern __isoc99_scanf
extern puts
extern __printf_chk
extern _GLOBAL_OFFSET_TABLE_


SECTION .text   

_MS_print:
        lea     rdx, [rdi+4H]
        lea     rsi, [rel _LC0]
        mov     edi, 1
        xor     eax, eax
        jmp     __printf_chk







ALIGN   16

_MS_println:
        add     rdi, 4
        jmp     puts






ALIGN   8

_MS_getString:
        push    rbp
        lea     rdi, [rel _LC0]
        push    rbx
        sub     rsp, 280


        mov     rax, qword [fs:abs 28H]
        mov     qword [rsp+108H], rax
        xor     eax, eax
        mov     rbp, rsp
        mov     rsi, rbp
        mov     rbx, rbp
        call    __isoc99_scanf
L_001:  mov     edx, dword [rbx]
        add     rbx, 4
        lea     eax, [rdx-1010101H]
        not     edx
        and     eax, edx
        and     eax, 80808080H
        jz      L_001
        mov     edx, eax
        shr     edx, 16
        test    eax, 8080H
        cmove   eax, edx
        lea     rdx, [rbx+2H]
        cmove   rbx, rdx
        mov     edi, eax
        add     dil, al
        sbb     rbx, 3
        sub     rbx, rbp
        lea     edi, [rbx+5H]
        movsxd  rdi, edi
        call    malloc
        lea     rsi, [rax+4H]
        cmp     ebx, 8
        jnc     L_003
        test    bl, 04H
        jne     L_005
        test    ebx, ebx
        jnz     L_004
L_002:  xor     edx, edx
        mov     word [rax], bx
        mov     word [rax+2H], dx
        mov     byte [rax+rbx+4H], 0
        mov     rsi, qword [rsp+108H]


        xor     rsi, qword [fs:abs 28H]
        jnz     L_006
        add     rsp, 280
        pop     rbx
        pop     rbp
        ret





ALIGN   8
L_003:  mov     rdx, qword [rsp]
        lea     rdi, [rax+0CH]
        and     rdi, 0FFFFFFFFFFFFFFF8H
        mov     qword [rax+4H], rdx
        mov     edx, ebx
        mov     rcx, qword [rbp+rdx-8H]
        mov     qword [rsi+rdx-8H], rcx
        sub     rsi, rdi
        mov     rcx, rsi
        mov     rsi, rbp
        sub     rsi, rcx
        add     ecx, ebx
        shr     ecx, 3
        rep movsq
        jmp     L_002





ALIGN   8
L_004:  movzx   edx, byte [rbp]
        mov     byte [rax+4H], dl
        test    bl, 02H
        jz      L_002
        mov     edx, ebx
        movzx   ecx, word [rbp+rdx-2H]
        mov     word [rsi+rdx-2H], cx
        jmp     L_002





ALIGN   8
L_005:  mov     edx, dword [rbp]
        mov     dword [rax+4H], edx
        mov     edx, ebx
        mov     ecx, dword [rbp+rdx-4H]
        mov     dword [rsi+rdx-4H], ecx
        jmp     L_002

L_006:  call    __stack_chk_fail




ALIGN   8

_MS_getInt:
        sub     rsp, 24
        lea     rdi, [rel _LC1]


        mov     rax, qword [fs:abs 28H]
        mov     qword [rsp+8H], rax
        xor     eax, eax
        mov     rsi, rsp
        call    __isoc99_scanf
        mov     rax, qword [rsp]
        mov     rdx, qword [rsp+8H]


        xor     rdx, qword [fs:abs 28H]
        jnz     L_007
        add     rsp, 24
        ret

L_007:  call    __stack_chk_fail





ALIGN   16

_MS_toString:
        push    r12
        mov     r12, rdi
        mov     edi, 16
        push    rbp
        push    rbx
        call    malloc
        mov     r8, r12
        mov     edx, 12
        lea     rcx, [rel _LC1]
        lea     rbp, [rax+4H]
        mov     rbx, rax
        mov     esi, 1
        xor     eax, eax
        mov     rdi, rbp
        call    __sprintf_chk
        mov     rax, rbp
L_008:  mov     ecx, dword [rax]
        add     rax, 4
        lea     edx, [rcx-1010101H]
        not     ecx
        and     edx, ecx
        and     edx, 80808080H
        jz      L_008
        mov     ecx, edx
        shr     ecx, 16
        test    edx, 8080H
        cmove   edx, ecx
        lea     rcx, [rax+2H]
        cmove   rax, rcx
        mov     esi, edx
        add     sil, dl
        sbb     rax, 3
        sub     rax, rbp
        test    eax, eax
        lea     edx, [rax+0FFFFFFH]
        cmovns  edx, eax
        sar     edx, 24
        test    eax, eax
        mov     byte [rbx+3H], dl
        lea     edx, [rax+0FFFFH]
        cmovns  edx, eax
        mov     ecx, edx
        sar     edx, 31
        shr     edx, 24
        sar     ecx, 16
        add     ecx, edx
        movzx   ecx, cl
        sub     ecx, edx
        test    eax, eax
        lea     edx, [rax+0FFH]
        cmovns  edx, eax
        mov     byte [rbx+2H], cl
        mov     ecx, edx
        sar     edx, 31
        shr     edx, 24
        sar     ecx, 8
        add     ecx, edx
        movzx   ecx, cl
        sub     ecx, edx
        cdq
        shr     edx, 24
        mov     byte [rbx+1H], cl
        add     eax, edx
        movzx   eax, al
        sub     eax, edx
        mov     byte [rbx], al
        mov     rax, rbx
        pop     rbx
        pop     rbp
        pop     r12
        ret






ALIGN   8

_string_parseInt:
        movzx   edx, byte [rdi+4H]
        lea     rcx, [rdi+4H]
        mov     r8d, 1
        cmp     dl, 45
        jnz     L_010
        movzx   edx, byte [rdi+5H]
        lea     rcx, [rdi+5H]
L_009:



        db 49H, 0C7H, 0C0H, 0FFH, 0FFH, 0FFH, 0FFH
L_010:  lea     esi, [rdx-30H]
        xor     eax, eax
        cmp     sil, 9
        ja      L_012




ALIGN   8
L_011:  lea     rsi, [rax+rax*4]
        add     rcx, 1
        lea     rax, [rdx+rsi*2-30H]
        movzx   edx, byte [rcx]
        lea     esi, [rdx-30H]
        cmp     sil, 9
        jbe     L_011
        imul    rax, r8
        ret





ALIGN   8
L_012:  ret







ALIGN   16

_string_ord:
        movzx   eax, byte [rdi+rsi+4H]
        ret







ALIGN   16

_string_substring:
        push    r15
        mov     r15, rdi
        push    r14
        mov     r14, rsi
        push    r13
        push    r12
        push    rbp
        push    rbx
        mov     ebx, edx
        sub     ebx, esi
        lea     edi, [rbx+6H]
        lea     ebp, [rbx+1H]
        sub     rsp, 8
        movsxd  rdi, edi
        movsxd  r13, ebp
        call    malloc
        lea     rsi, [r15+r14+4H]
        mov     rdx, r13
        lea     rdi, [rax+4H]
        mov     r12, rax
        call    memcpy
        test    ebp, ebp
        lea     eax, [rbx+1000000H]
        mov     byte [r12+r13+4H], 0
        cmovns  eax, ebp
        sar     eax, 24
        test    ebp, ebp
        mov     byte [r12+3H], al
        lea     eax, [rbx+10000H]
        cmovns  eax, ebp
        add     ebx, 256
        mov     edx, eax
        sar     eax, 31
        shr     eax, 24
        sar     edx, 16
        add     edx, eax
        movzx   edx, dl
        sub     edx, eax
        test    ebp, ebp
        cmovns  ebx, ebp
        mov     byte [r12+2H], dl
        mov     edx, ebp
        sar     edx, 31
        mov     eax, ebx
        sar     ebx, 31
        shr     ebx, 24
        sar     eax, 8
        add     eax, ebx
        shr     edx, 24
        movzx   eax, al
        sub     eax, ebx
        mov     byte [r12+1H], al
        lea     eax, [rbp+rdx]
        movzx   eax, al
        sub     eax, edx
        mov     byte [r12], al
        add     rsp, 8
        mov     rax, r12
        pop     rbx
        pop     rbp
        pop     r12
        pop     r13
        pop     r14
        pop     r15
        ret







ALIGN   16

_string___add__:
        push    r15
        push    r14
        push    r13
        push    r12
        mov     r12, rdi
        push    rbp
        mov     rbp, rsi
        push    rbx
        sub     rsp, 8
        movzx   eax, byte [rdi+3H]
        movzx   edx, byte [rdi+2H]
        movzx   r13d, byte [rdi]
        movzx   ebx, byte [rsi+3H]
        shl     eax, 8
        add     eax, edx
        movzx   edx, byte [rdi+1H]
        shl     ebx, 8
        shl     eax, 8
        add     eax, edx
        movzx   edx, byte [rsi+1H]
        shl     eax, 8
        add     r13d, eax
        movzx   eax, byte [rsi+2H]
        add     ebx, eax
        shl     ebx, 8
        add     ebx, edx
        movzx   edx, byte [rsi]
        shl     ebx, 8
        add     ebx, edx
        lea     r15d, [r13+rbx]
        movsxd  r13, r13d
        lea     edi, [r15+5H]
        movsxd  rdi, edi
        call    malloc
        lea     rsi, [r12+4H]
        mov     rdx, r13
        mov     r14, rax
        lea     rdi, [rax+4H]
        call    memcpy
        lea     rdi, [r14+r13+4H]
        movsxd  rdx, ebx
        lea     rsi, [rbp+4H]
        call    memcpy
        movsxd  rax, r15d
        mov     byte [r14+rax+4H], 0
        mov     rax, r14
        mov     dword [r14], r15d
        add     rsp, 8
        pop     rbx
        pop     rbp
        pop     r12
        pop     r13
        pop     r14
        pop     r15
        ret






ALIGN   8

_strcmp:
        movzx   edx, byte [rdi+4H]
        movzx   ecx, byte [rsi+4H]
        cmp     cl, dl
        jnz     L_015
        test    cl, cl
        jz      L_016
        mov     eax, 5
        jmp     L_014






ALIGN   16
L_013:  add     rax, 1
        test    dl, dl
        jz      L_016
L_014:  movzx   edx, byte [rdi+rax]
        movzx   ecx, byte [rsi+rax]
        cmp     dl, cl
        jz      L_013
L_015:



        db 48H, 0C7H, 0C0H, 0FFH, 0FFH, 0FFH, 0FFH

        db 38H, 0CAH

        db 73H, 01H

        db 0C3H
L_016:  xor     eax, eax
        cmp     cl, dl
        setb    al
        ret



SECTION .data   


SECTION .bss    


SECTION .rodata.str1.1 

_LC0:
        db 25H, 73H, 00H

_LC1:
        db 25H, 6CH, 6CH, 64H, 00H


