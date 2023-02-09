package no.ntnu.idata2305.group14;

import no.ntnu.idata2305.group14.computation.SearchingSimulator;
import no.ntnu.idata2305.group14.servers.MultiThreadedServer;
import no.ntnu.idata2305.group14.servers.SingleThreaderServer;

public class Main {

    private static boolean singleThreadMode = true;

    private static int serverPort = 8080;

    public static void main(String[] args) {
        System.out.println("Starting server");
        if (singleThreadMode) {
            new SingleThreaderServer(serverPort).run();
        } else {
            new MultiThreadedServer(serverPort).run();
        }
    }
}