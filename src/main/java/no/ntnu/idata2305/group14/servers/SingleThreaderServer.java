package no.ntnu.idata2305.group14.servers;

import java.net.ServerSocket;

public class SingleThreaderServer {
    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;

    public SingleThreaderServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void run() {
        openServerSocket();

        while (!isStopped()) {
            // wait for a connection
            // on receiving a request, execute the heavy computation
        }

        System.out.println("Server Stopped.");
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        // implementation to stop the server from the main thread if needed
    }

    private void openServerSocket() {
        // open server socket here
    }
}
