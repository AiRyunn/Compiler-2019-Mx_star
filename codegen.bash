#!/bin/bash

# this script is called when the judge wants our compiler to compile a source file.
# print the compiled source, i.e. asm code, directly to stdout.
# don't print anything other to stdout.
# if you would like to print some debug information, please go to stderr.

set -e
cd "$(dirname "$0")"
mkdir -p output
java -ea -classpath "./lib/antlr-4.7.1-complete.jar:./lib/gson-2.8.5.jar:./bin" com.github.espylapiza.compiler_mxstar.Main -o output/source.asm $*
cat lib/builtin_functions/builtin_functions.asm output/source.asm > output/target.asm
cat output/target.asm