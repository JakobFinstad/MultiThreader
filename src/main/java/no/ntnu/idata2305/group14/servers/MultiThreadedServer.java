package no.ntnu.idata2305.group14.servers;

import no.ntnu.idata2305.group14.computation.AsyncSearchSimulator;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer implements Runnable {

    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected Socket clientSocket;
    protected boolean isStopped = false;

    public MultiThreadedServer(int port) {
        this.serverPort = port;
    }

    public void run() {
        openServerSocket();

        while (!isStopped()) {
            // wait for a connection

            // on receiving a request, execute the heavy computation in a new thread
            new Thread(
                    new AsyncSearchSimulator(
                            clientSocket,
                            "Multithreaded Server"
                    )
            ).start();
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