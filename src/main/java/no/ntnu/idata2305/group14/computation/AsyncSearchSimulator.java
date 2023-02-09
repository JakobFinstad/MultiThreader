package no.ntnu.idata2305.group14.computation;

import no.ntnu.idata2305.group14.utils.ResponseGenerator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class AsyncSearchSimulator implements Runnable {

    protected Socket clientSocket = null;
    protected String serverText = null;

    public AsyncSearchSimulator(Socket clientSocket, String serverText) throws InterruptedException, IOException {
        this.clientSocket = clientSocket;
        this.serverText = serverText;


    }

    public void run() {
        long time1 = System.currentTimeMillis();
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long time2 = System.currentTimeMillis();

        String httpBody = ResponseGenerator.generatorResponseHTML(time1, time2);
        String httpHeader = ResponseGenerator.generatorResponseHeader(httpBody.length());

        try {
            new PrintWriter(clientSocket.getOutputStream(), true).println(httpHeader + httpBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}