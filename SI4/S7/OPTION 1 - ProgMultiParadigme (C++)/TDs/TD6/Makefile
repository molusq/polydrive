#=======================================================================
# Basic C++: makefile example to use as a reminder or as a template
#-----------------------------------------------------------------------
# Julien DeAntoni --- No Copyright 2016
# $Id: convenient Makefile but muist be tuned for templated project 
#      v0.2 2016/09/06
#=======================================================================

#only ok for project with no templated classes

#compiler
COMPILER = g++
#linker
LINKER = g++

#options for linker and compiler
FLAGS =-W -Wall -Wextra -Wold-style-cast -Woverloaded-virtual -D_DEBUG_ -std=c++11

EXE_NAME= opengl3D

#can have several ones separated by spaces, only cpp files
SOURCES = opengl3D.cpp Color.cpp Point.cpp Carre.cpp

#PATH to extra header used in the project (when using libs not installed in the OS)
INC_PATHS= -I

#extra libs used in the project (for instance -lpthread)
LIBS	= -L/usr/local/lib -lSDLmain -lSDL -Wl,-framework,Cocoa -framework GLUT -framework OpenGL

LINKOBJ	= $(SOURCES:.cpp=.o)
SOURCEHEADERS = $(SOURCES:.cpp=.h)

$(EXE_NAME): $(LINKOBJ)
	$(LINKER) $(LINKOBJ) $(INC_PATHS) $(LIBS) -o $(EXE_NAME) $(FLAGS)

#main.o: main.cpp Tree.h Node.h Node.cpp Tree.cpp
#	$(COMPILER) -g -c $< -o $@ $(INC_PATHS) $(FLAGS)

%.o: %.cpp
	$(COMPILER) -g -c $< -o $@ $(INC_PATHS) $(FLAGS)

main: opengl3D.cpp
	$(COMPILER) -o opengl3D opengl3D.cpp $(FLAGS) $(LIBS)

# cleanup
clean:
	rm -f $(LINKOBJ)

