cmake_minimum_required(VERSION 3.9)
project(TD2)

set(CMAKE_CXX_STANDARD 11)

add_executable(TD2
        bubble.c
        insertion.c
        main.c
        main_dynload.c
        merge.c
        quick.c
        sort.h
        unused.c
        utils.c
        utils.h
        load_library.c
        load_library.h)
