package problemSet5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Final {
	// Continue with Cohort Exercise 3. Assume that you would like to further
	// speed up the factoring using
	// multiple computers. Assume that there are 11 computers. One runs a server
	// program and 10 runs a
	// client program. For simplicity, assume that the server knows the IP of
	// the clients and the clients know
	// the IP of the server. The server program works as follows. The user
	// inputs the number on the server; the
	// server sends the number to all clients (along with additional information
	// if necessary) and waits for an
	// answer from any client. Once an answer is received, the server sends a
	// message to all clients to sign that
	// task has been completed. The client program works as follows. After
	// establishing connection with the
	// server, the client waits a number from the server. Once received, the
	// client starts factoring the number.
	// Once a factor is found, the client sends the factor back to the server.
	// Anytime the client received a
	// message stating that the task has been completed, the clients stop
	// factoring.
	// For a simple example of connecting Server and a client, refer to
	// EchoServer.java and EchoClient.java
	class ServerThread extends Thread {
		ServerSocket server = null;
		List<ConnectionHandler> connections = new ArrayList<>();

		public void run() {
			try {
				server = new ServerSocket(4321);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// listen to connections in a Thread.
			while (connections.size() != 10) {
				try {
					// to allow multiple clients, the server needs to start a
					// new thread per client.
					Socket socket = server.accept();
					ConnectionHandler handler = new ConnectionHandler(socket, connections.size());
					connections.add(handler);
					handler.start();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Connection Error");

				}
			}
			// the main server thread listens to the console
			// read from console
			
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			System.out.println("Type in desired factor: ");
			try {
				line = stdInput.readLine();
				// broadcast to all clients
				for (ConnectionHandler connection : connections) {
					if (!connection.isInterrupted()) {
						PrintWriter output = new PrintWriter(connection.socket.getOutputStream());
						output.println(line);
						output.flush();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // read factor from console
			boolean anyFound = false;
			while (!anyFound) {
				// check if any has found
				for (ConnectionHandler connection : connections) {
					if (connection.found) {
						anyFound = true;
						break;
					}
				}
			}
			
			for (ConnectionHandler connection : connections) {
				PrintWriter output;
				try {
					output = new PrintWriter(connection.socket.getOutputStream());
					output.println("FOUND");
					output.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			}
		}
	}

	public static void main(String[] args) {
		Final f = new Final();
		// a thread for server needs to be started to allow the Java instance to
		// start clients.
		f.new ServerThread().start();
		System.out.println("listening...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			// to start multiple clients on the same Java instance, a thread
			// needs to be run for each of them.
			ClientThread c = new ClientThread(i+2, 10);
			c.start();
		}

		// total number of threads:
		// 1 ServerThread, 10 ConnectionHandlers, 10 ClientThreads, 10 Listeners per ClientThread to check if any others have finished.
	}

	class ConnectionHandler extends Thread {
		
		private Socket socket;
		private int clientNumber;
		boolean found = false;
		

		public ConnectionHandler(Socket socket, int clientNumber) {
			this.socket = socket;
			this.clientNumber = clientNumber;
		}

		public void run() {
			System.out.println("connection established on client #" + clientNumber);
			while (!found){
				try {
					BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					// if found, stop calculation
					String line = input.readLine();
					if (line != null)
						System.out.println("Factors: " + line);
					found = true;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

class ClientThread extends Thread {
	String hostIP = "192.168.1.142";
	int portNumber = 4321;
	BigInteger n;
	BigInteger init;
	BigInteger stepSize;
	BigInteger result;
	boolean found = false;
	Socket socket;
	
	
	public ClientThread (int init, int stepSize) {
		this.init = BigInteger.valueOf(init);
		this.stepSize = BigInteger.valueOf(stepSize);
	}
	
	class InputListener extends Thread {

		@Override
		public void run() {
			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// if found, stop calculation
				if (input.readLine().equals("FOUND")){
					found = true;
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				found = true;
			}
		}
		
	}
	
	public void run() {
		socket = new Socket();
		SocketAddress sockaddr = new InetSocketAddress(hostIP, portNumber);
		try {
			socket.connect(sockaddr, 100);
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter output = new PrintWriter(socket.getOutputStream());
			
			String response = input.readLine();
			System.out.println("Factoring: " + response);
			n = BigInteger.valueOf(Long.parseLong(response));
			
			BigInteger zero = new BigInteger("0");
			
			// before it begins factoring, create a thread to listen to input.
			new InputListener().start();
			
			while (init.compareTo(n) < 0 && !found) {
				if (n.remainder(init).compareTo(zero) == 0) {
					output.println(init);
					output.flush();
					break;
				}
				
				init = init.add(stepSize);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Stopping client..");
		}
	}
}
