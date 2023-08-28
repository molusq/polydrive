import fr.epu.bicycle.*;

import java.util.Locale;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		EBike e1 = new EBike();
		final String STOP = "s";
		String stop = "f";
		final double MILES_CONVERSION = 1.609344;
		Scanner keyboard = new Scanner(System.in);
		keyboard.useLocale(Locale.US);//To read the doubles with . and not ,
		
		while(!STOP.equals(stop)) {
			System.out.print("\t What distance did you travel in km or mi ? ");
			if(keyboard.hasNextDouble()) {
				double newDistance = keyboard.nextDouble();//reading a double
				keyboard.nextLine();
				e1.addKm(newDistance);
				System.out.println("\t Your bike has therefore travelled " + e1.getKm() + " km or " + e1.getKm() / MILES_CONVERSION + " mi");
			}
			else {
				System.out.println("\t a number is expected for example  1 or 1.5 ");
			}
			System.out.print("Do you want to continue or stop (" + STOP + ") ? ");
			stop = keyboard.nextLine();
		}
	}
}
