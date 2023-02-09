package no.ntnu.idata2305.group14.servers;

import no.ntnu.idata2305.group14.computation.AsyncSearchSimulator;

import java.io.IOException;
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
        try {
            openServerSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        while (!isStopped()) {
            try {
            // wait for a connection
            clientSocket = serverSocket.accept();
            // on receiving a request, execute the heavy computation in a new thread

                new Thread(
                        new AsyncSearchSimulator(
                                clientSocket,
                                "Multithreaded Server"
                        )
                ).start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Server Stopped.");
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        // implementation to stop the server from the main thread if needed
    }

    private void openServerSocket() throws IOException {
        // open server socket her
         serverSocket = new ServerSocket(serverPort);
    }
}