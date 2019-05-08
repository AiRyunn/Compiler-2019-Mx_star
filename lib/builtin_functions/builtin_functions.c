#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

void _MS_print(char *s) { printf("%s", s + 4); }

void _MS_println(char *s) { printf("%s\n", s + 4); }

char *_MS_getString() {
    char buf[4 + 256 + 1];
    scanf("%s", buf);
    int len = strlen(buf);
    size_t size = len * sizeof(char) + 4;
    buf[0] = len / 256 / 256 / 256;
    buf[1] = len / 256 / 256 % 256;
    buf[2] = len / 256 % 256;
    buf[3] = len % 256;
    char *str = (char *)malloc(size);
    memcpy(str, buf + 4, size);
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
    sprintf(str + 4, "%d", var_int);
    int len = strlen(str + 4);
    str[0] = len / 256 / 256 / 256;
    str[1] = len / 256 / 256 % 256;
    str[2] = len / 256 % 256;
    str[3] = len % 256;
    return str;
}
