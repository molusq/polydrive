cmake_minimum_required(VERSION 3.12)
project(code)

set(CMAKE_CXX_STANDARD 11)

file(GLOB true_src "*.cpp" "*.h")

add_executable(main.exe ${true_src})