package raftalk.server;

/**
 * Klasa koja pokrece server na zadatom portu.
 * 
 * @author milossmi
 */
public class ServerMain {

	@SuppressWarnings("unused")
	private int port;
	
	@SuppressWarnings("unused")
	private Server server;
	
	public ServerMain(int port) {
		this.port = port;
		
		try {
			System.out.println("Port " + port + " is opened!");
			server = new Server(port);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		if (args.length != 1) {
//			System.out.println("Usage: java -jar Server.jar <port>");
//			return;
//		}
//		
//		int port = Integer.parseInt(args[0]);
//		new ServerMain(port);
//	}
	
	public static void main(String[] args) {
		new ServerMain(3000);
	}
}