package org.academiadecodigo.powrangers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private ServerSocket serverSocket;
    private Map<String, Player> playerList;
    private final int PORT_NUMBER;
    private int connectionCounter = 0;
    private Blackjack blackjackTable;
    private int numberOfPlayers;

    public Server(int port_number, int numberOfPlayers) {
        try {
            this.serverSocket = new ServerSocket(port_number);
            this.numberOfPlayers = numberOfPlayers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.playerList = new HashMap<>();
        PORT_NUMBER = port_number;
        init();
    }

    private void init() {
        System.out.println("Server initialized! Now accepting connections :)");
        blackjackTable = new Blackjack(numberOfPlayers);
        try {
            while (connectionCounter != numberOfPlayers) {
                connectionCounter++;
                playerList.put("Player-" + connectionCounter, new Player(serverSocket.accept(),blackjackTable));
                System.out.println("Connection with new player successful! :)");
                // blackjackTable.putPlayerInBlackjack(playerList.get("Player-" + connectionCounter));
            }
            blackjackTable.startRound();
        } catch (IOException e) {
            System.out.println("Connection has failed! Kaboom! :(");
            e.printStackTrace();
        }
    }
}
