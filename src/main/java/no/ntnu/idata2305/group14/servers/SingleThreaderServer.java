package no.ntnu.idata2305.group14.servers;

import no.ntnu.idata2305.group14.computation.SearchingSimulator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server stopped!");
                    return;
                }
                throw new RuntimeException("Error", e);
            }
            try {
                processClientRequest(clientSocket);
            } catch (Exception e) {
            }
        }

        System.out.println("Server Stopped.");
    }

    private void processClientRequest(Socket clientSocket) throws Exception {
        try {
            SearchingSimulator.processClientRequest(clientSocket, "hei");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        // implementation to stop the server from the main thread if needed
    }

    private void openServerSocket() {
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
