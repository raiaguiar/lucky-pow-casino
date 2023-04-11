package org.academiadecodigo.powrangers;

import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Insert port number as first argument and number of players as second argument.");

        } else {
            Server server = new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }
    }
}