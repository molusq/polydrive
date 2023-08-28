#---------------------------------------------------------------------------------
# Primitives de POSIX.1 : Manipulations de processus
#---------------------------------------------------------------------------------

#---------------------------------------------------------------------------------
# Variables
#---------------------------------------------------------------------------------

# Compilateur et options
# ----------------------
CC      = gcc
CFLAGS  = -g -Wall -Wextra -std=gnu99 -fpic #-DMALLOC_DBG

DYN_LIB = libmalloc.so libmymalloc.so
EXE     = test-malloc.exe test-mymalloc.exe

#---------------------------------------------------------------------------------
# Cibles principales
#---------------------------------------------------------------------------------

all: $(DYN_LIB) $(EXE)

lib%.so: %.o
	$(CC) $(CFLAGS) -shared -Wl,-soname,$@ $^ -o $@

test-mymalloc.c:
	cp test-malloc.c test-mymalloc.c
	
test-%.exe: test-%.o lib%.so
	$(CC) $(CFLAGS) -o $@ $^

# Nettoyages
# ----------

# Nettoyage léger
clean:
	@rm -f \#* *.o $(DYN_LIB) $(EXE)

# Nettoyage en profondeur
mrproper: clean
	@rm -rf *~ *.html test-mymalloc.c

# Tests
# ----------

test: SHELL:=/bin/bash
test: $(EXE)
	@chmod 755 villoc/villoc.py
	#@LD_LIBRARY_PATH=. ltrace -S ./test-malloc.exe |& villoc/villoc.py --raw - malloc.html
	@LD_LIBRARY_PATH=. ltrace -S ./test-mymalloc.exe |& villoc/villoc.py --raw - mymalloc.html
