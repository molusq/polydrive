#                                                 -*- mode: Makefile -*-
#           Author: Erick Gallesio [eg@unice.fr]
#    Creation date: 21-Sep-2012 19:29 (eg)
# Last file update:  2-Jan-2016 09:51 (eg)


CC=gcc
CFLAGS=-Wall -Wextra -std=gnu99 -O3 -g

SRC      = $(wildcard *.c) 
OBJ      = $(SRC:.c=.o)
EXE      = test-hash test-hash-resize

all: $(EXE)

test-hash: hash.o main.o
	$(CC) $(CFLAGS) -o test-hash hash.o main.o

test-hash-resize: hash-resize.o main.o
	$(CC) $(CFLAGS) -o test-hash-resize  hash-resize.o main.o

$(OBJ): hash.h

clean:
	rm -rf $(EXE) $(OBJ) *~


