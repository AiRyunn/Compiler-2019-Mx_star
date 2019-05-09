#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

typedef unsigned char uchar;

void _MS_print(uchar *s) { printf("%s", s + 4); }

void _MS_println(uchar *s) { printf("%s\n", s + 4); }

uchar *_MS_getString() {
    uchar buf[4 + 256 + 1];
    scanf("%s", buf);
    int len = strlen(buf);

    uchar *str = (uchar *)malloc(len + 5);

    memcpy(str + 4, buf, sizeof(uchar) * len);

    str[3] = len / 256 / 256 / 256;
    str[2] = len / 256 / 256 % 256;
    str[1] = len / 256 % 256;
    str[0] = len % 256;
    str[len + 4] = '\0';

    return str;
}

int _MS_getInt() {
    int var_int;
    scanf("%d", &var_int);
    return var_int;
}

uchar *_MS_toString(int var_int) {
    uchar *str = (uchar *)malloc(16 * sizeof(uchar));
    // itoa(var_int, str, 10);
    sprintf(str + 4, "%d", var_int);
    int len = strlen(str + 4);
    str[3] = len / 256 / 256 / 256;
    str[2] = len / 256 / 256 % 256;
    str[1] = len / 256 % 256;
    str[0] = len % 256;
    return str;
}

long long _string_parseInt(uchar *str) {
    str += 4;
    long long s = 1, r = 0;
    if (*str == '-') {
        s = -1;
        str++;
    }
    while ('0' <= *str && *str <= '9') {
        r = r * 10 + *str - '0';
        str++;
    }
    return s * r;
}

long long _string_ord(uchar *s, long long pos) { return (long long)s[pos + 4]; }

uchar *_string_substring(uchar *s, long long left, long long right) {
    int len = right - left + 1;
    uchar *str = (uchar *)malloc(len + 5);
    memcpy(str + 4, s + 4, sizeof(uchar) * (len));
    str[right + 4 - 1] = '\0';
    str[3] = len / 256 / 256 / 256;
    str[2] = len / 256 / 256 % 256;
    str[1] = len / 256 % 256;
    str[0] = len % 256;
    return str;
}

uchar *_string___add__(uchar *lhs, uchar *rhs) {
    int lenl =
        lhs[3] * 256 * 256 * 256 + lhs[2] * 256 * 256 + lhs[1] * 256 + lhs[0];
    int lenr =
        rhs[3] * 256 * 256 * 256 + rhs[2] * 256 * 256 + rhs[1] * 256 + rhs[0];
    int len = lenl + lenr;
    uchar *str = (uchar *)malloc(len + 5);
    memcpy(str + 4, lhs + 4, sizeof(uchar) * lenl);
    memcpy(str + lenl + 4, rhs + 4, sizeof(uchar) * lenr);
    str[len + 4] = '\0';
    str[3] = (uchar)(len / 256 / 256 / 256);
    str[2] = (uchar)(len / 256 / 256 % 256);
    str[1] = (uchar)(len / 256 % 256);
    str[0] = (uchar)(len % 256);
    return str;
}