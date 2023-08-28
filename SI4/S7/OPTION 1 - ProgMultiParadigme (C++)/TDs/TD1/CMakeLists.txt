cmake_minimum_required(VERSION 2.9)

#projet declaration
project(CellularAutomata)


#define platform independent variables
SET(GCC_COMPILE_FLAGS "-g -ansi -Wall -Wextra -Wold-style-cast -Woverloaded-virtual -D_DEBUG_ -std=c++11")
SET(GCC_LINK_FLAGS    "")

#add flags to the compiler
add_definitions(${GCC_COMPILE_FLAGS}) 


SET(ALL_SRC_FILES "main.cpp")

#executable definition
add_executable(

        cellularAutomata

        ${ALL_SRC_FILES}

)
