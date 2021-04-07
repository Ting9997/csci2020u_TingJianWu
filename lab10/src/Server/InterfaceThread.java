package Server;

public class InterfaceThread extends Thread {
    public void run(){
        Interface.loadInterface();
    }
}
