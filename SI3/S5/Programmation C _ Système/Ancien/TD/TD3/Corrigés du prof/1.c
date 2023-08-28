#include <stdio.h>
#include <assert.h>
#include <string.h>

int indice(const char str[], const char c) {
  for(int i = 0; str[i] != '\0'; i ++) {
    if (str[i] == c) return i;
  }
  // Si on est là c'est qu'on a pas trouvé c
  return -1;
}

int indice_droite_v0(const char str[], const char c) {
  for(int i = strlen(str)-1; i >= 0; i --) {
    if (str[i] == c) return i;
  }
  // Si on est là c'est qu'on a pas trouvé c
  return -1;
}

int indice_droite(const char str[], const char c) {
  int res = -1;  // a priori on a pas trouvé

  for(int i = 0; str[i] != '\0'; i ++) {
    if (str[i] == c) res = i; 
  }
  return res;
}

int main(){
  assert(indice("Test", 'T') == 0);
  assert(indice("Test", 't') == 3);
  assert(indice("Test", 'z') == -1);
  assert(indice ("Tester", 'e') == 1);

  assert(indice_droite("Test", 'T') == 0);
  assert(indice_droite("Test", 't') == 3);
  assert(indice_droite("Test", 'z') == -1);
  assert(indice_droite("Tester", 'e') == 4);

  assert(indice("1234567890ezlmekrgmldkfgmldkhmlùkreyjzpoitjzeifjekdnvdfnX", 'X') == 57); 
  printf("%d", indice("1234567890ezlmekrgmldkfgmldkhmlùkreyjzpoitjzeifjekdnvdfnX", 'X')); 
  return 0;
}