JC=javac
JH=javah
JFLAGS=-g

.SUFFIXES: .java .class

SRC=$(wildcard *.java)
OBJ=$(SRC:.java=.class)

C_JNI=../C_JNI

all: $(OBJ)

%.class: %.java
	$(JC) $(JFLAGS) $<
#	$(JH) $*
#	mv $*.h $(C_JNI)
	mv $@ $(C_JNI)

clean:
	rm -f \#* $(OBJ) *.h
	cd $(C_JNI) && make clean