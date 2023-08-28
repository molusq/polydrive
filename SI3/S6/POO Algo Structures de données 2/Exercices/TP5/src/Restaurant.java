public class Restaurant {
	private double longitude;
	private double latitude;
	private String city;
	private String state;
	private String name;
	private String address;
	private String phone;
	
	public Restaurant(double longitude, double latitude, String city, String state, String name, String address, String phone) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.city = city;
		this.state = state;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	
	public Restaurant() {
		this(0, 0, "", "", "", "", "");
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
