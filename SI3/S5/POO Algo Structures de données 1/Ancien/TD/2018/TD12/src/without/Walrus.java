package without;

class Walrus implements Animal{
	private String name = "Walrus";

	public String getName(){
		return this.name;
	}

	public String talk() {
        return "Walrus says: I am the walrus.";
    }
}