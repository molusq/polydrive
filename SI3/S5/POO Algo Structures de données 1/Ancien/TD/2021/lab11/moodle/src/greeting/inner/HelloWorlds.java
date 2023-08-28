package greeting.inner;

/**
 * Demonstration of using inner classes.
 * @author Peter Sander
 */
class HelloWorlds {

    /**
     * Interface determines behaviour which will be specialized
     * differently by a number of language-specific
     * implementations.
     * @param name Person being greeted.
     */
    private interface Hello {
        void greet(String name);
    }

    /**
     * Defines language-specific objects based on the interface.
     */
    void sayHello() {
        /*
         * Defines anonymous inner classes which implement
         * the interface Hello differently for each language.
         */
        Hello englishGreeting = name -> System.out.println("Hello " + name);

        Hello frenchGreeting = name -> System.out.println("Bonjour " + name);

        Hello spanishGreeting = name -> System.out.println("Hola  " + name);

        // executes each greeting for an approriate person
        englishGreeting.greet("John");
        englishGreeting.greet("Paul");
        frenchGreeting.greet("Georges");
        spanishGreeting.greet("Gringo");
    }

    /**
     * Executes the application.
     */
    public static void main(String... args) {
        new HelloWorlds().sayHello();
    }
}