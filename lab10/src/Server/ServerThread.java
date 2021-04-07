package Server;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread{
    private Socket socket = null;
    private BufferedReader serverInput = null;
    private PrintWriter serverOutput = null;

    // Constructor
    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        this.serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.serverOutput = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * This method executes the thread
     */
    public void run(){
        try {
            while(serverInput.ready()){
                Interface.textArea.appendText(serverInput.readLine()+"\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
