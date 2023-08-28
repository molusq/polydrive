#---------------------------------------------------------------------------------
# Variables
#---------------------------------------------------------------------------------

# Compilateur et options
# ----------------------

CC=gcc
CFLAGS=-fpic -Wall -I /usr/lib/jvm/java-8-oracle/include/ -I /usr/lib/jvm/java-8-oracle/include/linux

SRC=$(wildcard *.c)
OBJ=$(SRC:.c=.o)
EXE=$(OBJ:.o=.exe)

JAVA=../java
#---------------------------------------------------------------------------------
# Cibles principales
#---------------------------------------------------------------------------------
all: $(EXE)
	cd $(JAVA) && make

%.exe: %.o
	$(CC) $(CXXFLAGS) -o $@ -L /usr/lib/jvm/java-8-oracle/jre/lib/amd64/server $< -ljvm

# Règle existant par défaut pour générer un .o à partir d'un .cpp (donc pas la peine de la réécrire)
%.o: %.c
	$(CC) $(CFLAGS) -o $@ -c $<

# Nettoyages
# ----------

# Nettoyage léger
clean:
	@rm -f \#* $(OBJ) *.so *.h

# Nettoyage en profondeur
mrproper: clean
