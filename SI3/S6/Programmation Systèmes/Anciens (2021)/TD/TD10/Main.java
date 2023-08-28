import java.lang.management.ManagementFactory;

public class Main {
	static {
		String libName = "";
		switch (OSInfo.getOs()) {
			case WINDOWS: 
				libName = "Windows";
				break;

			case UNIX:
				libName = "Linux";
				break;

			case MAC:
				libName = "Mac";
				break;

			default:
				System.out.println("Wrong OS");
		}
		System.loadLibrary(libName);
	}
	
	public static native int getPid();
	public static native void createFork();

	public static void main(String[] args) {
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
		System.out.println(pid);

		System.out.println(Main.getPid());

		Main.createFork();
	}
}
