#include <stdio.h>
#include <assert.h>
#include <string.h>

void strcpy1(char s1[], char s2[]) {    // 1ere version
  int i = 0;

  for (i = 0; s2[i] != '\0'; i++)
    s1[i] = s2[i];

  // Ne pas oublier le '\0' final
  s1[i] = '\0';
}

void strcpy2(char s1[], char s2[]) {    // 2e version
  int i = 0;

  do {
    s1[i] = s2[i];
    i++; 
  } while (s2[i-1] != '\0');
}

void strcpy3(char s1[], char s2[]) {    // 3e version
  int i = 0;

  do {
    s1[i] = s2[i];
  } while (s2[i++] != '\0');
}

void strupper(char s[]) {
  int i;

  for (i = 0; s[i] != '\0'; i++) {
    if ('a' <= s[i] && s[i] <= 'z') // Vérifier que l'on a bien une minuscule
      s[i] += 'A' - 'a';
  }
}

int strcmp1(char s1[], char s2[]) {
  int i;

  // Avancer sur s1 et s2 tant qu'on est pas à la fin et que les caractères
  // de s1 et s2 sont égaux
  for (i = 0; s1[i] != '\0' && s2[i] != '\0' && s1[i] == s2[i]; i++) {
  }

  // Quand on est ici, on a:
  //   - soit fini les deux chaînes en même temps (alors si[i] == s2[i])
  //   - soit une différence sur les caractères courants de s1 et de s2
  //     (peut être la fin d'une des chaînes, mais aussi deux caractères
  //     non nuls mais différents)
  if (s1[i] == s2[i])
    // les chaînes s1 et s2 sont égales
    return 0;
  else if (s1[i] < s2[i])
    // s1 < s2
    return -1;
  else
    // s1 > s2
    return +1;
}

int strcmp0(char s1[], char s2[]) {
  int i;
  
  for (i = 0; s1[i] != '\0' && s1[i] == s2[i]; i++) {
  }
  return s1[i] - s2[i];
}

int main(){
 return 0;
}
