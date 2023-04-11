package org.academiadecodigo.powrangers;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Player {
    private Socket socket;
    private String playerName;
    private int playerCoins;
    private int betAmount;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<Cards> cardsList;
    private Prompt playerPrompt;

    private boolean isBlownUp;
    private boolean isReady;
    private boolean isInitialized;
    private volatile int playerMove = 0;
    private Blackjack blackjackTable;
    private ExecutorService threadpool;

    public Player(Socket socket, Blackjack blackjackTable) {
        this.socket = socket;
        this.blackjackTable = blackjackTable;
        this.playerCoins = 100;
        this.betAmount = 0;
        this.isInitialized = false;
        cardsList = new ArrayList<>();
        threadpool = Executors.newCachedThreadPool();
        threadpool.submit(new PlayerNameHandler());
    }

    private void init() {
        // INITIALIZES THE PLAYER STATE WHEN ASSIGNING TO TABLE, IF NOT ALREADY, AND ASKS FOR INITIAL BET AMOUNT
        try {
            if (!isInitialized) {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                getAndStorePlayerName();
            }
            isInitialized = true;
            blackjackTable.putPlayerInBlackjack(this);
            getAndStorePlayerBet();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ExecutorService getThreadpool() {
        return threadpool;
    }

    private void getAndStorePlayerName() throws IOException {
        // Simply asks and stores the player name while printing a welcome message (should probably be elsewhere this last one)
        playerPrompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));
        StringInputScanner askName = new StringInputScanner();
        askName.setMessage(welcome + "\n\n\n\t\t\tINSERT YOUR NAME PLEASE: ");
        playerName = playerPrompt.getUserInput(askName);
        out.println("\n\n\n\n\n\t\tWelcome to LUCKY POW CASINO, hope you enjoy " + playerName + "\n\n\n\n" + rules);
    }

    public synchronized void getAndStorePlayerBet() throws IOException {
        if (playerCoins != 0) {

        // After the player is inserted into a blackjack table, asks and stores the player bet (removing it from the player wallet)
        IntegerRangeInputScanner askBet = new IntegerRangeInputScanner(1, playerCoins);
        askBet.setMessage("\t\t\tYou currently have: " + playerCoins + " coins.\n\n\t\t   Insert bet amount between 1 and " + playerCoins + ": ");
        betAmount = playerPrompt.getUserInput(askBet);
        playerCoins -= betAmount;
        out.println("\t\t\tYou have bet " + betAmount + ". Good luck! :)\n\n");
        } else {
            playerCoins += 10;
            IntegerRangeInputScanner askBet = new IntegerRangeInputScanner(1, playerCoins);
            askBet.setMessage("\t\t\tYou currently have: " + 0 + " coins LOSER. We gave you 10 free coins to keep playing!\n\n\t\t   Insert bet amount between 1 and " + playerCoins + ": ");
            betAmount = playerPrompt.getUserInput(askBet);
            playerCoins -= betAmount;
            out.println("\t\t\tYou have bet " + betAmount + ". Good luck! :)\n\n");
        }
    }

    public void askPlayerMove() {
        // ASKS PLAYER FOR MOVE AND STORES IT IN A LOCAL PROPERTY;
        String[] moves = {"Hit", "Stay"};
        MenuInputScanner menu = new MenuInputScanner(moves);
        menu.setMessage("DEALER: What do you want to do " + playerName + "? You have 10 seconds!\n");
        menu.setError("DEALER: Pick a valid option.");
        playerMove = playerPrompt.getUserInput(menu);
    }

    public PrintWriter playerPrintWriter() {
        return out;
    }

    public void giveCardToPlayer(Cards card) {
        cardsList.add(card);
    }

    public ArrayList<Cards> getCardsList() {
        return cardsList;
    }

    public boolean getReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isBlownUp() {
        return isBlownUp;
    }

    public void setBlownUp(boolean blownUp) {
        isBlownUp = blownUp;
    }

    public void playerHelper() {
        threadpool.submit(new PlayerMoveHandler());
    }

    public int getPlayerMove() {
        return playerMove;
    }

    public void setPlayerMove(int playerMove) {
        this.playerMove = playerMove;
    }

    public int getPlayerCoins() {
        return playerCoins;
    }

    public void setPlayerCoins(int playerCoins) {
        this.playerCoins = playerCoins;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }


    public class PlayerMoveHandler implements Runnable {
        @Override
        public void run() {
            askPlayerMove();
        }
    }

    public class PlayerNameHandler implements Runnable {
        @Override
        public void run() {
            init();
        }
    }

    private final String welcome =  " \u001B[32m/\u001B[33m$$\u001B[0m       \u001B[32m/\u001B[33m$$\u001B[0m   \u001B[32m/\u001B[33m$$  \u001B[32m/\u001B[33m$$$$$$  \u001B[32m/\u001B[33m$$   \u001B[32m/\u001B[33m$$ \u001B[32m/\u001B[33m$$     \u001B[32m/\u001B[33m$$       \u001B[32m/\u001B[33m$$$$$$$   \u001B[32m/\u001B[33m$$$$$$  \u001B[32m/\u001B[33m$$      \u001B[32m/\u001B[33m$$        \u001B[32m/\u001B[33m$$$$$$   \u001B[32m/\u001B[33m$$$$$$   \u001B[32m/\u001B[33m$$$$$$  \u001B[32m/\u001B[33m$$$$$$ \u001B[32m/\u001B[33m$$   \u001B[32m/\u001B[33m$$  \u001B[32m/\u001B[33m$$$$$$ \n" +
            "\u001B[32m| \u001B[33m$$\u001B[0m      \u001B[32m| \u001B[33m$$\u001B[0m  \u001B[32m| \u001B[33m$$ \u001B[32m/\u001B[33m$$\u001B[32m__  \u001B[33m$$\u001B[32m| \u001B[33m$$  \u001B[32m/\u001B[33m$$\u001B[32m/|  \u001B[33m$$   \u001B[32m/\u001B[33m$$\u001B[32m\u001B[32m/      \u001B[32m| \u001B[33m$$\u001B[32m__  \u001B[33m$$ /$$\u001B[32m__  \u001B[33m$$\u001B[32m| \u001B[33m$$  \u001B[32m/\u001B[33m$ \u001B[32m| \u001B[33m$$       \u001B[32m/\u001B[33m$$\u001B[32m__  \u001B[33m$$ \u001B[32m/\u001B[33m$$\u001B[32m__  \u001B[33m$$ \u001B[32m/\u001B[33m$$\u001B[32m__  \u001B[33m$$\u001B[32m|_  \u001B[33m$$\u001B[32m_/| \u001B[33m$$$ \u001B[32m| \u001B[33m$$ \u001B[32m/\u001B[33m$$\u001B[32m__  \u001B[33m$$\n" +
            "\u001B[32m| \u001B[33m$$\u001B[0m      \u001B[32m| \u001B[33m$$\u001B[0m  \u001B[32m| \u001B[33m$$\u001B[32m| \u001B[33m$$  \u001B[32m\\__/| \u001B[33m$$ \u001B[32m/\u001B[33m$$\u001B[32m/  \u001B[32m\\  \u001B[33m$$ \u001B[32m/\u001B[33m$$\u001B[32m/       \u001B[32m| \u001B[33m$$  \u001B[32m\\ \u001B[33m$$\u001B[32m| \u001B[33m$$  \u001B[32m\\ \u001B[33m$$\u001B[32m| \u001B[33m$$ \u001B[32m/\u001B[33m$$$\u001B[32m| \u001B[33m$$      \u001B[32m| \u001B[33m$$  \u001B[32m\u001B[32m\\__/| \u001B[33m$$  \u001B[32m\\ \u001B[33m$$\u001B[32m| \u001B[33m$$  \u001B[32m\\__/  \u001B[32m| \u001B[33m$$  \u001B[32m| \u001B[33m$$$$\u001B[32m| \u001B[33m$$\u001B[32m| \u001B[33m$$  \u001B[32m\\ \u001B[33m$$\n" +
            "\u001B[32m| \u001B[33m$$\u001B[0m      \u001B[32m| \u001B[33m$$\u001B[0m  \u001B[32m| \u001B[33m$$\u001B[32m| \u001B[33m$$      \u001B[32m| \u001B[33m$$$$$\u001B[32m/    \u001B[32m\\  \u001B[33m$$$$\u001B[32m/        \u001B[32m| \u001B[33m$$$$$$$\u001B[32m\u001B[32m/| \u001B[33m$$  \u001B[32m| \u001B[33m$$\u001B[32m| \u001B[33m$$\u001B[32m/\u001B[33m$$ \u001B[33m$$ \u001B[33m$$      \u001B[32m| \u001B[33m$$      \u001B[32m| \u001B[33m$$$$$$$$\u001B[32m|  \u001B[33m$$$$$$   \u001B[32m| \u001B[33m$$  \u001B[32m| \u001B[33m$$ \u001B[33m$$ \u001B[33m$$\u001B[32m| \u001B[33m$$  \u001B[32m| \u001B[33m$$\n" +
            "\u001B[32m| \u001B[33m$$\u001B[0m      \u001B[32m| \u001B[33m$$\u001B[0m  \u001B[32m| \u001B[33m$$\u001B[32m| \u001B[33m$$      \u001B[32m| \u001B[33m$$  \u001B[33m$$     \u001B[32m\\  \u001B[33m$$\u001B[32m/         \u001B[32m| \u001B[33m$$\u001B[32m____/ \u001B[32m| \u001B[33m$$  \u001B[32m| \u001B[33m$$\u001B[32m| \u001B[33m$$$$\u001B[32m_  \u001B[33m$$$$      \u001B[32m| \u001B[33m$$      \u001B[32m\u001B[32m| \u001B[33m$$\u001B[32m__  \u001B[33m$$ \u001B[32m\\____  \u001B[33m$$  \u001B[32m| \u001B[33m$$  \u001B[32m| \u001B[33m$$  \u001B[33m$$$$\u001B[32m| \u001B[33m$$  \u001B[32m| \u001B[33m$$\n" +
            "\u001B[31m| \u001B[33m$$\u001B[0m      \u001B[31m| \u001B[33m$$\u001B[0m  \u001B[31m| \u001B[33m$$\u001B[31m| \u001B[33m$$    \u001B[33m$$\u001B[31m| \u001B[33m$$\u001B[31m\\  \u001B[33m$$     \u001B[31m| \u001B[33m$$          \u001B[31m| \u001B[33m$$      \u001B[31m| \u001B[33m$$  \u001B[31m| \u001B[33m$$\u001B[31m| \u001B[33m$$$\u001B[31m/ \\  \u001B[33m$$$      \u001B[31m| \u001B[33m$$    \u001B[33m$$\u001B[31m| \u001B[33m$$  \u001B[31m| \u001B[33m$$ \u001B[31m/\u001B[33m$$  \u001B[31m\\ \u001B[33m$$  \u001B[31m| \u001B[33m$$  \u001B[31m| \u001B[33m$$\u001B[31m\\  \u001B[33m$$$\u001B[31m| \u001B[33m$$  \u001B[31m| \u001B[33m$$\n" +
            "\u001B[31m| \u001B[33m$$$$$$$$\u001B[0m\u001B[31m|  \u001B[33m$$$$$$\u001B[0m\u001B[31m/|  \u001B[33m$$$$$$\u001B[31m/| \u001B[33m$$ \u001B[31m\\  \u001B[33m$$    \u001B[31m| \u001B[33m$$          \u001B[31m| \u001B[33m$$      \u001B[31m|  \u001B[33m$$$$$$\u001B[31m/| \u001B[33m$$\u001B[31m/   \\  \u001B[33m$$      \u001B[31m|  \u001B[33m$$$$$$\u001B[31m/| \u001B[33m$$  \u001B[31m| \u001B[33m$$\u001B[31m|  \u001B[33m$$$$$$\u001B[31m/ /\u001B[33m$$$$$$\u001B[31m| \u001B[33m$$ \u001B[31m\\  \u001B[33m$$\u001B[31m|  \u001B[33m$$$$$$\u001B[31m/\n" +
            "\u001B[31m\\________/ \\______/  \\______/ |__/  \\__/    |__/          |__/       \\______/ |__/     \\__/       \\______/ |__/  |__/ \\______/ |______/|__/  \\__/ \\______/\u001B[0m \n";



    private final String rules = "RULES:\n\t1: TRY TO GET MORE POINTS THAN THE DEALER TO WIN" +
            "\n\t2: CHOOSE HOW MUCH YOU WANT TO BET, IF YOU WIN YOU GET 1.5 TIMES YOUR BET" +
            "\n\t3: YOUR POINTS ARE THE SUM OF THE VALUE OF YOUR CARDS, WITH THE FIGURES BEING WORTH 10 AND THE ACE WORTH 1" +
            "\n\t4: BOTH YOU AND THE DEALER START WITH TWO CARDS" +
            "\n\t5: YOU THEN CHOOSE WHETHER TO HIT[1] WHICH IS TO DRAW MORE CARDS, OR STAY[2] WHICH IS KEEP YOUR CURRENT CARDS" +
            "\n\t6: WHILE HITTING, IF YOU GO OVER 21 YOU LOSE" +
            "\n\t7: ONCE YOU STAY, YOUR ROUND ENDS AND IT'S TIME FOR THE DEALER TO DRAW CARDS" +
            "\n\t8: ONCE THE DEALER STAYS YOUR SUMS ARE COMPARED AND THE WINNER IS DECIDED";
}

