#include "HelloWorld.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_HelloWorld_printCpp(JNIEnv *env, jclass cls) {
    printf("World !\n");
}

JNIEXPORT void JNICALL
Java_HelloWorld_printStringToCpp(JNIEnv *env, jclass cl, jstring str)
{
    // Construction d'une chaîne C/C++ à partir d'une chaîne Java
    const char *cStr = env->GetStringUTFChars(str,0);
    // Affichage de la chaîne C++
    printf("%s\n", cStr);
    // Libération de la chaîne C++
    env->ReleaseStringUTFChars(str,cStr);
}

JNIEXPORT jstring JNICALL
Java_HelloWorld_stringFromCpp(JNIEnv *env, jobject obj)
{
    // Construction d'une chaîne Java à partir d'une chaîne C/C++
    return env->NewStringUTF("Chaîne en C");
}

JNIEXPORT void JNICALL
Java_HelloWorld_callJavaMethod(JNIEnv *env, jobject obj) {
    // Récupération d'un objet de Metaclasse
    jclass cls = env->GetObjectClass(obj);
    // Calcule de l'identificateur de "void test(String str)"
    jmethodID mid = env->GetStaticMethodID(cls,"test","(Ljava/lang/String;)V");
    if (mid == 0) {
        printf("Ouille, ça a planté !");
    } else {
        // Tout va bien, l'appel peut aboutir.
        jstring str = env->NewStringUTF("Ceci est un paramètre créé en C/C++");
        env->CallVoidMethod(obj,mid,str);
    }
    return; 
}

JNIEXPORT jstring JNICALL
Java_HelloWorld_toString(JNIEnv *env, jobject obj) {
char buffer[256];
    // Obtention de la Metaclasse de HelloWorld
    jclass cls = env->GetObjectClass(obj);
    // Calcule de l'identificateur de l'attribut a de type int
    jfieldID fid = env->GetFieldID(cls,"entier","I");
    // Récupération de la valeur entière de l'attribut
    int a = env->GetIntField(obj,fid);
    // Modification de la valeur entière de l'attribut
    env->SetIntField(obj, fid, a + 1);
    // Deuxieme récupération de la valeur entière de l'attribut
    a = env -> GetIntField(obj,fid);
    // Génération d'une chaîne contenant la valeur de l'attribut
    sprintf(buffer,"Hello [a = %d]",a);
    // On retourne un objet Java de chaîne de caractères
    return env->NewStringUTF(buffer);
}