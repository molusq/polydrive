#include "HelloWorld.h" 
#include <stdio.h>

JNIEXPORT void JNICALL Java_HelloWorld_printCpp(JNIEnv *, jclass) {
	printf("World !\n");
}

JNIEXPORT void JNICALL Java_HelloWorld_printStringToCpp(JNIEnv *env, jclass cl, jstring str)
{
	// Construction d'une cha�ne C/C++ � partir d'une cha�ne Java
	const char *cStr = env->GetStringUTFChars(str, 0);
	// Affichage de la cha�ne C++
	printf("%s\n", cStr);
	// Lib�ration de la cha�ne C++  
	env->ReleaseStringUTFChars(str, cStr);
}

JNIEXPORT jstring JNICALL Java_HelloWorld_stringFromCpp(JNIEnv *env, jobject obj)
{
	// Construction d'une cha�ne Java � partir d'une cha�ne C/C++
	return env->NewStringUTF("Cha�ne en C pass�e � Java\n");
}

JNIEXPORT void JNICALL Java_HelloWorld_callJavaMethod(JNIEnv *env, jobject obj) {
	// R�cup�ration d'un objet de Metaclasse
	jclass cls = env->GetObjectClass(obj);

	// Calcule de l'identificateur de "void test(String str)"
	jmethodID mid = env->GetStaticMethodID(cls, "test", "(Ljava/lang/String;)V");

	if (mid == 0) {
		// Ca a plant� !!!
		printf("Ouille, �a a plant� !\n");
	} else {
		// Tout va bien, l'appel peut aboutir.
		jstring str = env->NewStringUTF("Ceci est un param�tre cr�� en C/C++\n");
		env->CallVoidMethod(obj, mid, str);
	}
	return;
}

JNIEXPORT jstring JNICALL Java_HelloWorld_toString(JNIEnv *env, jobject obj) {
	char buffer[256];

	// Obtention de la Metaclasse de HelloWorld
	jclass cls = env->GetObjectClass(obj);

	// Calcule de l'identificateur de l'attribut a de type int
	jfieldID fid = env->GetFieldID(cls, "entier", "I");

	// R�cup�ration de la valeur enti�re de l'attribut
	int a = env->GetIntField(obj, fid);

	// Modification de la valeur enti�re de l'attribut
	env->SetIntField(obj, fid, a + 1);

	// Deuxieme r�cup�ration de la valeur enti�re de l'attribut
	a = env->GetIntField(obj, fid);

	// G�n�ration d'une cha�ne contenant la valeur de l'attribut
	sprintf(buffer, "Hello [entier = %d]", a);

	// On retourne un objet Java de cha�ne de caract�res
	return env->NewStringUTF(buffer);
}
