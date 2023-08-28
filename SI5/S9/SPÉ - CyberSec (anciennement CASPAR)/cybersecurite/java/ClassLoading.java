import java.rmi.server.*;
import java.rmi.*;
import java.net.*;


public class ClassLoading {
  public static void main(String argv[]) throws Exception {
    ClassLoading cl=new ClassLoading();
    cl.haveARide();
  }

  String Name_of_Vehicle;

  public void haveARide() throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException {
    Name_of_Vehicle = "Car";
    Vehicle car = (Vehicle) Class.forName(Name_of_Vehicle).newInstance();
    car.ride();

    Name_of_Vehicle = "Truck";
    Vehicle truck = (Vehicle) RMIClassLoader.loadClass((URL) null, Name_of_Vehicle).newInstance();
    truck.ride();

    Name_of_Vehicle = "Bicycle";
    Vehicle bicycle = (Vehicle) RMIClassLoader.loadClass(new URL("http://localhost:3000/Bicycle.class"), Name_of_Vehicle).newInstance();
    bicycle.ride();

    Name_of_Vehicle = "Motorcycle";
    MyClassLoader myClassLoader = new MyClassLoader();
    Vehicle motorcycle = (Vehicle) myClassLoader.loadClass(Name_of_Vehicle).newInstance();
    motorcycle.ride();
  }

}
