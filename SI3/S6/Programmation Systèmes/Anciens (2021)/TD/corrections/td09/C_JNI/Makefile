#---------------------------------------------------------------------------------
# Variables
#---------------------------------------------------------------------------------

# Compilateur et options
# ----------------------

CC=g++
CXXFLAGS=-fpic -Wall -I /usr/lib/jvm/java-8-oracle/include/ -I /usr/lib/jvm/java-8-oracle/include/linux

SRC=$(wildcard *.cpp)
OBJ=$(SRC:.cpp=.o)

#---------------------------------------------------------------------------------
# Cibles principales
#---------------------------------------------------------------------------------
all: libHelloWorld.so

libHelloWorld.so: $(OBJ)
	$(CC) -Wl,-soname,$@ -shared -o $@ $< 

# Règle existant par défaut pour générer un .o à partir d'un .cpp (donc pas la peine de la réécrire)
#%.o: %.cpp
#	$(CC) $(CXXFLAGS) -o $@ -c $<

# Nettoyages
# ----------

# Nettoyage léger
clean:
	@rm -f \#* $(OBJ)

# Nettoyage en profondeur
mrproper: clean
