package no.ntnu.idata2305.group14.computation;

import no.ntnu.idata2305.group14.utils.ResponseGenerator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SearchingSimulator {
    public static void processClientRequest(Socket socket, String title) throws Exception {
        long time1 = System.currentTimeMillis();
        System.out.println("Request processing started at: " + time1);
        Thread.sleep(10 * 1000);
        long time2 = System.currentTimeMillis();
        System.out.println("Request processing ended at: " + time2);

        String httpBody = ResponseGenerator.generatorResponseHTML(time1,time2);
        String httpHeader = ResponseGenerator.generatorResponseHeader(httpBody.length());

        new PrintWriter(socket.getOutputStream(), true).println(httpHeader + httpBody);
    }
}