#include "Main.h"
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

JNIEXPORT jint JNICALL Java_Main_getPid(JNIEnv *env, jclass cls) {
    return getpid();
}

JNIEXPORT void JNICALL Java_Main_createFork(JNIEnv *env, jclass cls) {
	pid_t f = fork();
    switch(f){
    	case -1:
    		printf("Error in fork\n");
    		return;
    	case 0:
    		printf("In son\n");
    		return;
    	default:
    		sleep(1);
    		printf("In father\n");
    		return;
    }
}