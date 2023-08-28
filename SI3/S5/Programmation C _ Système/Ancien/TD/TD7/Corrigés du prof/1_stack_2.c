/*
 * 1_stack.c    -- Test d'une pile de nombres (version 2)
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  4-Dec-2013 22:30 (eg)
 * Last file update: 17-Nov-2020 11:53 (eg)
 */

// Dans cette version, pour éviter les doubles pointeurs, on définit le
// type Stack comme étant un pointeur sur un élément. C'est juste de
// la réécriture, mais cela simplifie un peu les choses.

#include <stdio.h>
#include <stdlib.h>

struct element {
  int value;
  struct element *next;
};

typedef struct element Element;
typedef Element *Stack;
 

void push_item(Stack *stack, int n) {
  Element *new = malloc(sizeof(Element)); // create a new element

  if (!new) {
    fprintf(stderr, "allocation failed\n");
    exit(1);
  }
  new->value  = n;
  new->next   = *stack;
  *stack      = new;            // set the given stack to its new value
}

int pop_item(Stack *stack) {
  Element *old = *stack;

  if (old) {
    int old_value = old->value;

    *stack = (*stack)->next;
    free(old);
    return old_value;
  }
  fprintf(stderr, "pop on an empty stack!!!\n");
  exit(1);
}

int top_value(Stack stack) {
  if (stack) {
    return stack->value;
  }
  fprintf(stderr, "top_value on an empty stack!!!\n");
  exit(1);
}


void print_stack(Stack stack) {
  Element *tmp;

  printf("{");

  for (tmp = stack; tmp; tmp = tmp->next)
    printf("%d%s", tmp->value, (tmp->next)? ", " : "");

  printf("}\n");
}


int main(void) {
  Stack S = NULL;
  int i;

  printf("Empilement:\n");
  for (i = 0; i <10; i++){
    printf("empile %d   ", i);
    push_item(&S, i);
    print_stack(S);
  }

  printf("Depilement:\n");
  for (i = 0; i <10; i++){
    printf("depile %d   ", pop_item(&S));
    print_stack(S);
  }

  return 0;
}
