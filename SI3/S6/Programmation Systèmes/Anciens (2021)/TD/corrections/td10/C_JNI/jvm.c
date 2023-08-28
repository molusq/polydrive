#include <string.h>
#include <jni.h>

int main(int argc, char *argv[])
{
	JavaVMOption options[1];
    options[0].optionString = "-Djava.class.path=.";

	JavaVMInitArgs vm_args;
    memset(&vm_args, 0, sizeof(vm_args));
    vm_args.version  = JNI_VERSION_1_2;
    vm_args.nOptions = 1;
    vm_args.options  = options;

    JavaVM *jvm;
    JNIEnv *env;
    jint res = JNI_CreateJavaVM(&jvm, (void **) &env, &vm_args);
    printf("%d", res);
    
    jclass classe = (*env)->FindClass(env, "CVersJava");
    jmethodID methodeID = (*env)->GetStaticMethodID(env,classe,"main","([Ljava/lang/String;)V");
    jstring jstr = (*env)->NewStringUTF(env," depuis du c !!!");
    jobjectArray args = (*env)->NewObjectArray(env,1,(*env)->FindClass(env,"java/lang/String"),jstr);
    (*env)->CallStaticVoidMethod(env,classe,methodeID,args);
    (*jvm)->DestroyJavaVM(jvm);

    return (0);
}