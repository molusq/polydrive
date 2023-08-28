cmake_minimum_required(VERSION 3.12)
project(TD7)

set(CMAKE_CXX_STANDARD 11)

file(GLOB true_src "*.cpp" "*.h")

add_executable(main ${true_src})