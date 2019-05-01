#!/bin/bash

########################################################################
#
# Usage: $bash nasm.bash FILENAME.asm
# The bash will generate and run an executive file FILENAME.
#
########################################################################

set -e
ASM_FILE="$1"
BASE_NAME="${ASM_FILE%.*}"

nasm -f elf64 "$BASE_NAME.asm"
gcc "$BASE_NAME.o" -o "$BASE_NAME" -no-pie
$BASE_NAME
