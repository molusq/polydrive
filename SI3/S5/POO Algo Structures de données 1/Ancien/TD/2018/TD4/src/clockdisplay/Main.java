package clockdisplay;

class Main{
	public static void main(String[] args) {
		ClockDisplay clock = new ClockDisplay(11,59);
		clock.timeTick();
		System.out.println(clock.getTime());
		clock.setTime(11,30);
		clock.timeTick();
		System.out.println(clock.getTime());
		NumberDisplay number = new NumberDisplay(24);
		number.setValue(10);
		System.out.println(number.getDisplayValue());
	}
}