#=======================================================================
# Basic C++: makefile example to use as a reminder or as a template
#-----------------------------------------------------------------------
# Julien DeAntoni --- No Copyright 2008
# $Id: MyMakefileExample,v 0.1 2008/10/02 $
#=======================================================================

# Miscellaneous paths (do not forget that this file is included 
# two levels and sometimes three levels below) 

#if you have usefull functions along project, place them into files outside of the project and use this variable to include them into the current project (here none are used)
INCL_COMMON_FONCTION_DIR = none

#specify the place where you want to put the generated executable file
INSTALL_DIR = ./bin


#you can define as many as variable you like to be used as command, as pathes, etc, they are just MACRO for the remainder of the Makefile
#example name of the executable to generate
EXE_NAME=document

# Well-known commands
INCL = -I$(INCL_DIR)

#list of the options that must be passed to the compiler g++(man g++for details)
CXX_FLAGS =-g -ansi -Wall -Wextra -Wold-style-cast -Woverloaded-virtual -D_DEBUG_ -std=c++11
#shortcut to call the compiler call with the specified options and includes
CXX = g++ $(CXX_FLAGS) -I $(INCL_COMMON_FONCTION_DIR) -c


#Same than the two former lines but for the C compiler
CFLAGS = -g -ansi -Wall
CC = gcc $(CFLAGS)

#variable for the linkers (which are here include in the same command line than the compiler
LINK_CXX = g++
LINK_CC = gcc



# Common targets
# Variables ALL must be defined in specific makefiles; in addition 
# FOR NON TEMPLATE FILES

# 	
# simple: main.o sample_class.o
# 	$(LINK_CXX) main.o sample_class.o -o $(EXE_NAME)
# main.o: main.cpp
# 	$(CXX)  main.cpp
# sample_class.o: sample_class.cpp sample_class.h
# 	$(CXX) sample_class.cpp
# 
# 
# tres_simple: main1.o
# 	$(LINK_CXX) main1.o -o tresSimple
# main1.o: main1.cpp
# 	$(CXX)  main1.cpp


document: main.o document.o paragraph.o item_paragraph.o
	$(LINK_CXX) main.o document.o paragraph.o item_paragraph.o -o $(EXE_NAME)
main.o: main.cpp
	$(CXX)  main.cpp
document.o: document.cpp document.h
	$(CXX)  document.cpp
paragraph.o: paragraph.cpp paragraph.h
	$(CXX)  paragraph.cpp
item_paragraph.o: item_paragraph.cpp item_paragraph.h
	$(CXX)  item_paragraph.cpp
# FOR TEMPLATE FILES
#we do not make separate compilation of the templated entities
# 	
# simple: main.o 
# 	$(LINK_CXX) main.o -o $(EXE_NAME)
# main.o: main.cpp sample_class.h sample_class.cpp
# 
# Note that in the Makefile shown above, the .h files are listed, but there are no references in their corresponding commands. This is because the .h files are referred within the corresponding .c files through the #include "file.h". If you do not explicitly include these in your Makefile, your program will not be updated if you make a change to your header (.h) files. 

clean:
	-rm -f *.o a.out *.ps \#* *~
	-rm -f *.stackdump core
	-rm -f $(EXE_NAME)
	-make extra_clean

install: 
	-mkdir -p $(INSTALL_DIR)
	-cp $(EXE_NAME) $(INSTALL_DIR)

uninstall:
	-rm $(INSTALL_DIR)/$(EXE_NAME)

extra_all:
extra_clean:

#-----------------------------------------------------------------------


#usage

#make project_Stack
#--> construct the executable

#make clean
#--> clean the compilation file as well as the executable created


