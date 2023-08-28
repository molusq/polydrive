#include <stdio.h>
#include <assert.h>
#include <string.h>

int palindrome(const char str[]) {
  for (int i = 0, j = strlen(str)-1; i < j; i++, j--) {
    if (str[i] != str[j]) return 0;
  }
  // si on est ici c'est qu'on a pas trouvé de différence ⇒ renvoyer 1
  return 1;
}

int main(){
  assert(palindrome("ressasser") == 1);
  assert(palindrome("kayak") == 1);
  assert(palindrome("X") == 1);
  assert(palindrome("test") == 0);

  //printf("terminé");
  return 0; 
}