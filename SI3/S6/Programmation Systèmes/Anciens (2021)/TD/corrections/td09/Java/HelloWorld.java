/**
 * Created by lavirott on 13/05/2017.
 */
import java.util.Scanner;

public class HelloWorld {
    // Méthodes natives fournies grâce à une bibliothèque
    public static native void printCpp();
    public static native void printStringToCpp(String str);
    public native String stringFromCpp();
    public native void callJavaMethod();
    public native String toString();

    public int entier = 4;

    static {
        System.out.println("Loading Hello World native library...");
        System.loadLibrary("HelloWorld");
        System.out.println("done.");
    }

    public static void test(String str) {
        System.out.println("Appel de la méthode test (pour test appel de méthode depuis C/C++");
        System.out.println(str);
    }

    public static void main(String args[]) {
        // Print from Java and from C/C++
        System.out.print("Hello ");
        HelloWorld.printCpp();

        // Print this Java string with C/C++
        HelloWorld.printStringToCpp("Bonjour depuis Java avec des caractères spéciaux: àéïôù");

        // Print a String in Java coming from C/C++
        String str = new HelloWorld().stringFromCpp();
        System.out.println(str);

        // Call a Java method (test) from C/C++
        new HelloWorld().callJavaMethod();

        // Accès à un attribut Java depuis C/C++
        String entierStr = new HelloWorld().toString();
        System.out.println(entierStr);

        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez appuyer sur Entrée pour terminer le programme...");
        sc.nextLine();
        sc.close();
    }
}
