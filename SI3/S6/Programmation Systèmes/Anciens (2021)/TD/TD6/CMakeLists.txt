cmake_minimum_required(VERSION 3.9)
project(TD6)

set(CMAKE_CXX_STANDARD 11)

add_executable(TD6
        Makefile
        redir.c tube_anonyme1.c tube_anonyme2.c tube_anonyme_multi.c ecrivain.c lecteur.c)
