cmake_minimum_required(VERSION 3.9)
project(TD8)

set(CMAKE_CXX_STANDARD 11)

add_executable(TD8
        villoc/pintool/pintool.cpp
        Makefile
        malloc.c
        malloc.h
        mymalloc.c
        test-malloc.c)
