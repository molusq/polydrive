#---------------------------------------------------------------------------------
# Génération des exécutables et bibliothèques
#---------------------------------------------------------------------------------

# Définition des éléments d'une règle: 
# 	%' sert de "joker" dans la règle
#	$@ est le nom de la cible (partie gauche de la règle)
#	$^ est la liste des dépendances (partie droite de la règle)
# Noter que la librairie mathématiques n'est pas utile pour toutes les stratégies, 
# mais ici, on veut une règle unique...

## Version de base des programmes compilés
BASIC_EXE=$(EXE:.exe=.exec)
tri_%.exec: main.o utils.o %.o unused.o
	$(CC) -o $@ $^

####
## Exercice 3:
STATIC_EXE=$(EXE:.exe=.staticexec)
tri_%.staticexec: main.o utils.o %.o unused.o
	$(CC) -static -o $@ $^

####
## Exercice 4:
STATIC_LIB=$(EXE:.exe=.slibexec)
libtri_%.a: utils.o %.o unused.o
	ar -r $@ $^
	ranlib $@

tri_%.slibexec: main.o libtri_%.a
	$(CC) -o $@ $^

####
## Exercice 5:
DYNAMIC_LIB=$(EXE:.exe=.dyn)
libdyn_%.so: utils.o %.o unused.o
	$(CC) -shared -Wl,-soname,$@ -o $@ $^

tri_%.dyn: main.o libdyn_%.so
	$(CC) -o $@ $^

####
## Exercice 6:
#DYNAMIC_LOAD=tri.exe
#tri.exe: main_dynload.o utils.o load_library.o
#	$(CC) -rdynamic -o $@ $^ -ldl


#---------------------------------------------------------------------------------
# DO NOT EDIT below this line
#---------------------------------------------------------------------------------

CC=gcc
CFLAGS=-Wall -Wextra -std=gnu99 -g -fpic

SRC=$(wildcard *.c)
OBJ=$(SRC:.c=.o)

EXE=tri_bubble.exe tri_insertion.exe tri_merge.exe tri_quick.exe

# Si .SECONDARY est défini, permet d'éviter d'effacer les .o générés (ils ne sont plus considérés comme intermédiaires)
.SECONDARY: $(OBJS)

# Définition des programmes "sans" bibliothèque
# Variable contenant le nom de tous les programmes à générer
PROGS=$(BASIC_EXE) $(STATIC_EXE) $(STATIC_LIB) $(DYNAMIC_LIB) $(DYNAMIC_LOAD)

# Cible par défaut: génère l'ensemble des programmes spécifiés dans la variable PROGS
all: $(PROGS)

# Nettoyage des sources: efface tous les fichiers générés par la compilation
clean:
	rm -f *.a *.so *.exe *.exec *.staticexec *.slibexec *.dyn *.o *~ \#*

#---------------------------------------------------------------------------------
# Tests
#---------------------------------------------------------------------------------

test: all
	@echo "*** Début des tests des programmes générés"
	@for sortProg in $(PROGS) ; do \
		echo "Lancement du programme: $$sortProg" ;\
		./$$sortProg libdyn_buuble.so; \
		echo ;\
	done
	@echo "*** Fin des tests"
