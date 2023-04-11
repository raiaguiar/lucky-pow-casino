package org.academiadecodigo.powrangers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Blackjack {
    private LinkedList<Player> tablePlayers;
    private ArrayList<Cards> tableCards;
    private ArrayList<Cards> dealerCards;
    private int maxNumberOfPlayersInTable;


    public Blackjack(int maxNumberOfPlayersInTable) {
        this.maxNumberOfPlayersInTable = maxNumberOfPlayersInTable;
        tablePlayers = new LinkedList<>();
        tableCards = new ArrayList<>();
        dealerCards = new ArrayList<>();
    }


    public void putPlayerInBlackjack(Player player) {
        System.out.println(player.getPlayerName() + " has been added to the table.");
        tablePlayers.add(player);

        player.playerPrintWriter().println("\n\n\n\n\t\tDEALER: Welcome " + player.getPlayerName() + " to our Blackjack table! :|\n\n\n");
    }

    private void getNewDeckOfCards() {
        // Clears all cards on table and on the players
        tableCards.clear();
        tableCards.addAll(Arrays.asList(Cards.values()));
        Collections.shuffle(tableCards);

        // Clears all cards that player may have
        for (Player player : tablePlayers) {
            player.getCardsList().clear();
        }
        dealerCards.clear();

    }

    public void startRound() {
        while (true) {
            getNewDeckOfCards();
            waitForTableToFillUp();
            waitForPlayersToMakeTheirBets();
            showCurrentPlayers();

            for (Player player : tablePlayers) {
                player.setReady(true);
                player.setBlownUp(false);
                player.setPlayerMove(0);
                // player.setBetAmount(0);
                player.giveCardToPlayer(tableCards.remove((int) (Math.random() * tableCards.size())));
                player.giveCardToPlayer(tableCards.remove((int) (Math.random() * tableCards.size())));
            }
            dealerCards.add(tableCards.remove((int) (Math.random() * tableCards.size())));
            dealerCards.add(tableCards.remove((int) (Math.random() * tableCards.size())));
            // STARTS ROUND LOGIC
            round();
        }
    }

    private void waitForTableToFillUp() {
        // Simply checks every 2 seconds if table is full, would be better with a wait/notify combo but no time to implement that solution
        while (tablePlayers.size() < maxNumberOfPlayersInTable) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void waitForPlayersToMakeTheirBets() {
        // Checks every 2 seconds and waits for players to make their bets before proceeding
        boolean atLeastOnePlayerHasntBet = true;
        while (atLeastOnePlayerHasntBet) {
            atLeastOnePlayerHasntBet = false;
            for (Player player : tablePlayers) {
                if (player.getBetAmount() == 0) {
                    atLeastOnePlayerHasntBet = true;
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void showCards() {
        //int sumOfCards = 0;
        for (Player player : tablePlayers) {
            player.playerPrintWriter().println("YOU have the following cards: \n\n" + drawCards(player.getCardsList()));
            player.playerPrintWriter().println("They add up to: " + sumOfCards(player.getCardsList()) + " points.\n\n");
            player.playerPrintWriter().println("The DEALER has the following cards: \n\n" + drawCards(dealerCards));
            player.playerPrintWriter().println("Dealer cards add up to: " + sumOfCards(dealerCards) + " points.\n\n");
        }
    }

    public String drawCards(ArrayList<Cards> cards) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < cards.get(0).getDrawnCard().split("\n").length; i++) {
            for (int j = 0; j < cards.size(); j++) {
                result.append(cards.get(j).getDrawnCard().split("\n")[i]);
                result.append("  ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public void round() {

            while (anyPlayersReady()) {
                // SHOW INITIAL CARDS TO ALL PLAYERS
                showCards();
                // METHOD TO ASK PLAYERS WHAT THEY WANT TO DO
                getPlayerMoves();
                // WAITS FOR PLAYERS MOVES
                waitPlayersMoves();
                // DEALS WITH PLAYERS MOVES
                handlePlayerMoves();
                // CHECK IF PLAYERS HAVE BLOWN UP
                checkPlayerBlownUp();
                blankSpace();
            }
            dealer();
            whoWins();
            showPlayersAndCoinsEnd();

    }

    public void getPlayerMoves() {
        // ITERATE OVER CURRENTLY PLAYING PLAYERS
        for (Player player : tablePlayers) {
            // ACTIVATE METHOD IN PLAYER TO GET PLAY
            if (player.getReady()) { // IF PLAYER IS MARKED FOR PLAYING, HAVE HIM MAKE HIS MOVE
                player.playerHelper();
                System.out.println(player.getPlayerName());
            } else { // IF PLAYER HAS PREVIOUSLY NOT PLAYED OR CHOSEN "STAY", SIMPLY TELL HIM TO WAIT
                player.playerPrintWriter().println("Waiting for end of round");
            }
        }
    }

    public void handlePlayerMoves() {
        //CHECK EVERY PLAYER MOVE

        for (Player player : tablePlayers) {

            if (player.getPlayerMove() == 0 || player.getPlayerMove() == 2) { // IF PLAYER FAILED TO ANSWER OR CHOSE STAY
                player.setReady(false);
                player.setPlayerMove(0);
            } else if (player.getPlayerMove() == 1) { // IF PLAYER CHOSE "HIT"
                player.giveCardToPlayer(tableCards.remove((int) (Math.random() * tableCards.size())));
                player.setPlayerMove(0);
            }
        }
    }

    public void showCurrentPlayers() {
        String players = "";
        for (Player player : tablePlayers) {
            players += player.getPlayerName() + "\n\t\t\t\t     ";
        }
        for (Player player : tablePlayers) {
            player.playerPrintWriter().println("\n\n\n\t\t\t\tCURRENT PLAYERS: \n\n" + "\t\t\t\t     " + players);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void showPlayersAndCoinsEnd() {
        String players = "";
        for (Player player : tablePlayers) {
            players += player.getPlayerName() + " now has " + player.getPlayerCoins() + "\n\t\t\t\t\t";
        }
        for (Player player : tablePlayers) {
            player.playerPrintWriter().println("\n\n\n\t\t\t\tCURRENT PLAYERS AND COINS: \n\n" + "\t\t\t\t\t" + players);
            System.out.println("\n\n\n");
            player.getThreadpool().submit(() -> {
                try {
                    player.getAndStorePlayerBet();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public int sumOfCards(ArrayList<Cards> cards) {
        int sum = 0;
        for (int i = 0; i < cards.size(); i++) {
            sum += cards.get(i).getCardValue();
        }
        return sum;
    }

    public void checkPlayerBlownUp() {

        for (Player player : tablePlayers) {
            if (sumOfCards(player.getCardsList()) > 21) {
                player.setReady(false);
                player.setBlownUp(true);
            }
        }
    }

    public boolean anyPlayersReady() {
        for (Player player : tablePlayers) {
            if (player.getReady()) {
                return true;
            }
        }
        return false;
    }

    public void waitPlayersMoves() {
        int counter = 10;
        boolean allPlayersAnswered = false;
        // CHECKS IF ALL PLAYERS HAVE ANSWERED OR TIMER REACHED ZERO
        while (!allPlayersAnswered && counter >= 0) {
            System.out.println(allPlayersAnswered);
            System.out.println(counter);
            allPlayersAnswered = true;
            for (Player player : tablePlayers) {
                if (player.getPlayerMove() == 0) {
                    allPlayersAnswered = false;
                }
            }
            counter--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void dealer() {
        if (allPlayerBlownUp()) {
            // SIMPLY SHOW CARDS AND SKIP TO WHO WINS
            showCards();
        } else {
            // START DRAWING DEALER CARDS
            handleDealerMoves();
        }
    }

    public boolean allPlayerBlownUp() {
        for (Player player : tablePlayers) {
            if (!player.isBlownUp()) {
                return false;
            }
        }
        return true;
    }


    public void handleDealerMoves() {
        while (sumOfCards(dealerCards) < 17) {
            sendMessageToAllPlayers("$$ ...DEALER DRAWS... $$\n");
            dealerCards.add(tableCards.remove((int) (Math.random() * tableCards.size())));
            blankSpace();
            showCards();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void whoWins() {
        if (sumOfCards(dealerCards) > 21) { // DEALER BLOWS UP
            sendMessageToAllPlayers("$$ ...DEALER BUSTS... $$\n");
            for (Player player : tablePlayers) {
                if (!player.isBlownUp()) {
                    // DEALER BLOWS UP AND PLAYER DIDN'T / PLAYER WINS
                    playerWin(player);
                } else {
                    // DEALER BLOWS UP BUT PLAYER ALREADY BLEW UP PREVIOUSLY / PLAYER LOSES
                    playerLoss(player);
                }
            }
        } else { // DEALER DIDN'T BLOW UP
            for (Player player : tablePlayers) {
                if (sumOfCards(player.getCardsList()) > sumOfCards(dealerCards) && !player.isBlownUp()) {
                    // PLAYER HAS A HIGHER SCORE THAN DEALER / PLAYER WINS
                    playerWin(player);
                } else if (sumOfCards(player.getCardsList()) > sumOfCards(dealerCards)) {
                    // PLAYER BLEW UP BUT DEALER DIDN'T / PLAYER LOSES
                    playerLoss(player);
                } else if (sumOfCards(player.getCardsList()) < sumOfCards(dealerCards)) {
                    // DEALER HAS MORE POINTS THAN PLAYER / PLAYER LOSES
                    playerLoss(player);
                } else {
                    playerDraw(player);
                }
            }
        }
    }

    private synchronized void playerWin(Player player) {
        // HANDLES THE PLAYER VICTORY CASE
        player.playerPrintWriter().println(won + "\n\n$$ CONGRATULATIONS YOU HAVE WON AT LUCKY POW $$\n");
        player.setPlayerCoins(player.getPlayerCoins() + ((int) Math.ceil((player.getBetAmount() * 1.5)))); // Sets player coins as current coins plus bet * 1.5
        player.setBetAmount(0); // Resets player base bet for future rounds
        player.playerPrintWriter().println("$$ YOU NOW HAVE " + player.getPlayerCoins() + " COINS $$\n");
    }

    private synchronized void playerLoss(Player player) {
        // HANDLES THE PLAYER VICTORY CASE
        player.playerPrintWriter().println(lost + "\n\n$$ YOU HAVE LOST AGAINST THE DEALER, UNLUCKY POW $$\n");
        player.setBetAmount(0); // Resets player base bet for future rounds
        player.playerPrintWriter().println("$$ YOU NOW HAVE " + player.getPlayerCoins() + " COINS $$\n");

    }

    private synchronized void playerDraw(Player player) {
        // HANDLES THE PLAYER VICTORY CASE
        player.playerPrintWriter().println("$$ YOU TIED WITH THE DEALER $$\n");
        player.setPlayerCoins(player.getPlayerCoins() + player.getBetAmount()); // Sets player coins as current coins plus the bet amount
        player.setBetAmount(0); // Resets player base bet for future rounds
        player.playerPrintWriter().println("$$ YOU NOW HAVE " + player.getPlayerCoins() + " COINS $$\n");
    }

    public void blankSpace() {
        sendMessageToAllPlayers("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public void sendMessageToAllPlayers(String message) {

        for (Player player : tablePlayers) {
            player.playerPrintWriter().println(message);
        }
    }

    private final String won = "\u001B[33m /$$     /$$ /$$$$$$  /$$   /$$       /$$      /$$  /$$$$$$  /$$   /$$       /$$\n" +
            "|  $$   /$$//$$__  $$| $$  | $$      | $$  /$ | $$ /$$__  $$| $$$ | $$      | $$\n" +
            " \\  $$ /$$/| $$  \\ $$| $$  | $$      | $$ /$$$| $$| $$  \\ $$| $$$$| $$      | $$\n" +
            "  \\  $$$$/ | $$  | $$| $$  | $$      | $$/$$ $$ $$| $$  | $$| $$ $$ $$      | $$\n" +
            "   \\  $$/  | $$  | $$| $$  | $$      | $$$$_  $$$$| $$  | $$| $$  $$$$      |__/\n" +
            "    | $$   | $$  | $$| $$  | $$      | $$$/ \\  $$$| $$  | $$| $$\\  $$$          \n" +
            "    | $$   |  $$$$$$/|  $$$$$$/      | $$/   \\  $$|  $$$$$$/| $$ \\  $$       /$$\n" +
            "    |__/    \\______/  \\______/       |__/     \\__/ \\______/ |__/  \\__/      |__/\n\u001B[0m";

    private final String lost = "\u001B[31m /$$     /$$ /$$$$$$  /$$   /$$       /$$        /$$$$$$   /$$$$$$  /$$$$$$$$            \n" +
            "|  $$   /$$//$$__  $$| $$  | $$      | $$       /$$__  $$ /$$__  $$| $$_____/            \n" +
            " \\  $$ /$$/| $$  \\ $$| $$  | $$      | $$      | $$  \\ $$| $$  \\__/| $$                  \n" +
            "  \\  $$$$/ | $$  | $$| $$  | $$      | $$      | $$  | $$|  $$$$$$ | $$$$$               \n" +
            "   \\  $$/  | $$  | $$| $$  | $$      | $$      | $$  | $$ \\____  $$| $$__/               \n" +
            "    | $$   | $$  | $$| $$  | $$      | $$      | $$  | $$ /$$  \\ $$| $$                  \n" +
            "    | $$   |  $$$$$$/|  $$$$$$/      | $$$$$$$$|  $$$$$$/|  $$$$$$/| $$$$$$$$ /$$ /$$ /$$\n" +
            "    |__/    \\______/  \\______/       |________/ \\______/  \\______/ |________/|__/|__/|__/\n\u001B[0m";

/*    private final String dealer = "             \u001B[33m       ⢀⢀⣀⠀⠀ \u001B[0m⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \u001B[33m⣾⣿⣿⣾⣷⣆⠀ \u001B[0m⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⣭⣻⣭⣽⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣝⣩⣭⡿⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀ ⠀⠀⢰⠗⢤⡴⠻⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⠂⠀⠀⢸⣶⣾⣶⣶⠅⠀⠀⠘⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⠀⠀⠀⠀⣿⣿⣿⡿⠀⠀⠀⠀⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⡇⠀⠀⠀⠀⠈⢿⡿⠀⠀⠀⠀⠀⢻⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣾⣀⠀⠀⠀⠀⣀⣀⠀⠀⠀⠀⠀⢠⡜⣿⣿⣿⡦⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣷⡶⠲⠿⣬⣻⢿⣿⣽⣿⣿⣶⣶⠐⢻⠿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "\u001B[32m⣶⣴⣴⣴⣴⣴⣴⣴⣶⣶⣶⡅⠛⢀⠠⢤⢠⣤⣤⣤⣤⣤⣤⣤⣀⢵⠽⠘⢀⣾⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶\n" +
            "\u001B[32m⢠⠀⠀⢠⢠⢠⢠⢠⢠⢠⢠⢸⢼⢠⢴⢼⢼⣼⣾⣽⣾⣾⣼⣼⣼⣼⣾⣾⣼⣼⣼⢼⣼⣤⣤⢤⢤⢤⢤⢤\u001B[0m";*/

}

