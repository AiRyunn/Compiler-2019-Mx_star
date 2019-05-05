#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

void _MS_print(char *s) { printf("%s", s); }

void _MS_println(char *s) { printf("%s\n", s); }

char *_MS_getString() {
    char buf[256 + 1];
    scanf("%s", buf);
    size_t size = strlen(buf) * sizeof(char);
    char *str = (char *)malloc(size);
    memcpy(str, buf, size);
    return str;
}

int _MS_getInt() {
    int var_int;
    scanf("%d", &var_int);
    return var_int;
}

char *_MS_toString(int var_int) {
    char *str = (char *)malloc(16 * sizeof(char));
    // itoa(var_int, str, 10);
    sprintf(str, "%d", var_int);
    return str;
}
