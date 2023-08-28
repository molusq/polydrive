import java.util.Scanner;

public class HelloWorld {
	int entier = 1;

	static {
		System.loadLibrary("HelloWorld"); // Chargement de la bibliothèque
	}

	public static native void printStringToCpp(String s); // Déclaration prototype méthode native
	public static native void printCpp();
	public native String stringFromCpp();
	public native void callJavaMethod();
	public native String toString();

	public static void main(String args[]) {
		System.out.print("Hello ");       // Affiche Hello en Java
		HelloWorld.printCpp();
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		HelloWorld.printStringToCpp(s);

		HelloWorld hi = new HelloWorld();  
		System.out.println(hi.stringFromCpp());

		hi.callJavaMethod();

		System.out.println(hi.toString());

	}

	public static void test(String s){
		System.out.println(s);
	}
}