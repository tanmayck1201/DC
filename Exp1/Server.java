import java.io.*;
import java.net.*;
import java.lang.*;

class Server {
	public static void main(String argv[]) throws Exception {

		// Reading from
		String s = null;
		ServerSocket welcomeSocket = new ServerSocket(6789);
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();

			// Read from Client
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			String y = inFromClient.readLine();
			// System.out.println("Demand Received from Client: " + y);
			System.out.println("Demand Received from Client:");

			int demand = Integer.parseInt(y);
			System.out.println("Demand No is :" + demand);

			// Initialization
			Process p1 = Runtime.getRuntime().exec("javac ContentsOfFolder.java");
			Process p2 = Runtime.getRuntime().exec("java ContentsOfFolder");

			switch (demand) {
				case 1:
					p1 = Runtime.getRuntime().exec("javac ContentsOfFolder.java");
					p2 = Runtime.getRuntime().exec("java ContentsOfFolder");
					break;

				case 2:
					p1 = Runtime.getRuntime().exec("javac DisplayFileSize.java");
					p2 = Runtime.getRuntime().exec("java DisplayFileSize");
					break;

				case 3:
					p1 = Runtime.getRuntime().exec("javac EmptySpace.java");
					p2 = Runtime.getRuntime().exec("java EmptySpace");
					break; 

				default:
					System.out.println("Enter demand no between 1 to 3");
			}

			String x = "";

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p2.getInputStream()));
			while ((s = stdInput.readLine()) != null) {
				x = x + s;
			}
			System.out.println("My  message is " + x);
			DataOutputStream DataToClient = new DataOutputStream(connectionSocket.getOutputStream());
			DataToClient.writeBytes(x + '\n');

		}

	}
}
