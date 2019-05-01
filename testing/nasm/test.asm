





default rel

global main
global global_variable1
global aaa
global global_variable2


SECTION .text   

main:
        push    rbp
        mov     rbp, rsp
        mov     dword [rbp-8H], 6
        mov     edx, dword [rel global_variable1]
        mov     eax, dword [rel global_variable2]
        add     edx, eax
        mov     eax, dword [rbp-8H]
        add     eax, edx
        mov     dword [rbp-4H], eax
        mov     eax, 0
        pop     rbp
        ret



SECTION .data   align=8

global_variable1:
        dd 00000003H, 00000000H

aaa:    dq 0000000000000063H

global_variable2:
        dd 00000003H


SECTION .bss    


