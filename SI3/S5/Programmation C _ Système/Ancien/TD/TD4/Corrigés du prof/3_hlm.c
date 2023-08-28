/*                                                      -*- coding: utf-8 -*-
 * hlm.c        -- HLM: les grand ensembles
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 22-Oct-2012 20:01 (eg)
 * Last file update: 15-Oct-2019 17:49 (eg)
 */

#include <stdio.h>

#define CHAR_SIZE  8                        /* nombre de bits dans un char */
#define MAX_BIGSET 125                      /* nombre de cellules dans un ens */
#define MAX_VAL    (CHAR_SIZE * MAX_BIGSET)

typedef unsigned char BigSet[MAX_BIGSET];


void BigSet_init(BigSet bs) {
  for (int i = 0; i < MAX_BIGSET; i++)
    bs[i] = 0;
}


int BigSet_is_in(BigSet bs, int i) {
  int slot = i / CHAR_SIZE;
  int num  = i % CHAR_SIZE;

  return bs[slot] & (1 << num);
}


void BigSet_add(BigSet bs, int i){
  int slot = i / CHAR_SIZE;
  int num  = i % CHAR_SIZE;

  bs[slot] |= 1 << num;
}

void BigSet_print(BigSet bs) {
  int virgule=0;

  // Pour debugger: cela peut être pas mal....
  //   for (i = 0; i < MAX_BIGSET; i++)
  //     printf("%08x ", bs[i]);

  printf("{");
  for (int i = 0; i < MAX_VAL; i++) {
    if (BigSet_is_in(bs, i))
      printf("%s%d", (virgule++) ? ", ": "" , i);
  }
  printf("}\n");
}


void BigSet_inter(BigSet s1, BigSet s2, BigSet res) {
  BigSet_init(res);
  
  for (int i = 0; i < MAX_BIGSET; i++)
    res[i] = s1[i] & s2[i];
}


int main(void) {
  BigSet e1, e2, e3;

  BigSet_init(e1); BigSet_init(e2);

  for (int i = 0; i < 140; i += 12) BigSet_add(e2, i);
  for (int i = 0; i < 140; i += 9) BigSet_add(e1, i);

  BigSet_inter(e1, e2, e3);
  printf("e1 = "); BigSet_print(e1);
  printf("e2 = "); BigSet_print(e2);
  printf("e3 = "); BigSet_print(e3);

  return 0;
}
