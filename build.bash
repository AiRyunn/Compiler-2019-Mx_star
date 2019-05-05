#!/bin/bash

# this script is called when the judge is building your compiler.
# Arguments:
#   --builtin-functions           compile builtin-functions.c

set -e
cd "$(dirname "$0")"
mkdir -p bin
find ./src/main -name *.java | javac -d bin -classpath "./lib/antlr-4.7.1-complete.jar" @/dev/stdin

if [ "$1" == "--builtin-functions" ]; then
    . utils/nasm/c2nasm.bash ./lib/builtin_functions/builtin_functions.c
fi
