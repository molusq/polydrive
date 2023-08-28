package without;

class Roadrunner implements Animal{
	private String name = "Roadrunner";

	public String getName(){
		return this.name;
	}

	public String talk() {
        return "Roadrunner says: Hmeep hmeep!";
    }
}