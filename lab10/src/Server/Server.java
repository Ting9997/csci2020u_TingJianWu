package Server;

import java.net.*;
import java.io.*;

public class Server{
    // Class Parameters
    private static int serverPort = 1024;   // default port number being used
    private static int maxClients = 25;     // maximum number of threads
    private int numClients = 0;             // counter
    private ServerThread[] threads = null;
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;

    // Constructor
    public Server() {
        try {
            serverSocket = new ServerSocket(serverPort);                    // Create a server socket
            threads = new ServerThread[maxClients];                         // Create an array of threads
            while (true) {                                                   // Wait for client to connect to server
                clientSocket = serverSocket.accept();                       // Clients Socket (which port the client is using)
                threads[numClients] = new ServerThread(clientSocket);       // Create a new thread
                threads[numClients].start();
                numClients++;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args){
        InterfaceThread interfaceThread = new InterfaceThread();
        interfaceThread.start(); // Assign a thread to the interface and have it run
        Server server = new Server();
    }
}
