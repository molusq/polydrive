/*
 * 1_stack.c    -- Test d'une pile de nombres (version 1)
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  4-Dec-2013 22:30 (eg)
 * Last file update: 25-Nov-2016 10:06 (eg)
 */

#include <stdio.h>
#include <stdlib.h>

struct element {
  int value;
  struct element *next;
};

typedef struct element Element;


Element *push_item(Element *stack, int n) {
  struct element *new = malloc(sizeof(Element)); // create a new element

  if (!new) {
    fprintf(stderr, "allocation failed\n");
    exit(1);
  }
  new->value  = n;
  new->next   = stack;
  return new;           // return the new stack
}

Element *pop_item(Element *stack) {
  if (stack) {
    Element *old = stack;

    stack = stack->next;
    free(old);
    return stack;
  }
  fprintf(stderr, "pop on an empty stack!!!\n");
  exit(1);
}

int top_value(Element *stack) {
  if (stack) {
    return stack->value;
  }
  fprintf(stderr, "top_value on an empty stack!!!\n");
  exit(1);
}

void print_stack(Element *stack) {
  Element *tmp;

  printf("{");

  for (tmp = stack; tmp; tmp = tmp->next)
    printf("%d%s", tmp->value, (tmp->next)? ", " : "");

  printf("}\n");
}


int main(void) {
  Element *S = NULL;
  int i;

  printf("Empilement:\n");
  for (i = 0; i <10; i++){
    printf("empile %d   ", i);
    S = push_item(S, i);
    print_stack(S);
  }

  printf("Depilement:\n");
  for (i = 0; i <10; i++){
    printf("depile %d   ", top_value(S));
    S = pop_item(S);
    print_stack(S);
  }

  return 0;
}
