cmake_minimum_required(VERSION 3.12)
project(TD6)

set(CMAKE_CXX_STANDARD 11)

file(GLOB true_src "*.cpp" "*.h")

add_executable(opengl3D ${true_src})