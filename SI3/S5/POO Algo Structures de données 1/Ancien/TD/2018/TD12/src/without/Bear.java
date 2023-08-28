package without;

class Bear implements Animal{
	private String name = "Bear";

	Bear(){}

	public String getName(){
		return this.name;
	}

	public String talk() {
        return "Bear says: Smarter than the average beer.";
    }
}