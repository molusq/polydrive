cmake_minimum_required(VERSION 3.9)
project(TD7)

set(CMAKE_CXX_STANDARD 11)

add_executable(TD7
        Makefile
        tst_ignore.c tst_signal.c tst_sigaction.c tst_sleep.c tst_sigsleep.c tst_children.c)
