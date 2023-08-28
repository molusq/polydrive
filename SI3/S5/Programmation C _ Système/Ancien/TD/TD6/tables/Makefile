#						-*- mode: Makefile -*-
# Makefile pour le Td5
#
# On construit deux exécutables: 
#      - impl-1 qui utilise la version de la table avec des tableaux
#      - impl-1 qui utilise la veesion de la table avec des listes
#
#
#	    Author: Erick Gallesio [eg@unice.fr]
#    Creation date: 21-Sep-2012 19:29 (eg)
# Last file update:  7-Dec-2015 10:02 (eg)

CC=gcc
CFLAGS=-Wall -std=gnu99 -g
LDFLAGS=


EXE= impl-1 impl-2

all: $(EXE)

impl-1: main.o table-tableau.o
	$(CC) $(CFLAGS) -o $@ $^

impl-2: main.o table-liste.o
	$(CC) $(CFLAGS) -o $@ $^

clean:
	rm -f $(EXE) *.o *~
