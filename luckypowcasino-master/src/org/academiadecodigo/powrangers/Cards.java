package org.academiadecodigo.powrangers;

public enum Cards {
    // SPADES // black color \u001B[30m
    ACEOFSPADES("Ace of Spades", 1, " \u001B[47m\u001B[30mA    \u001B[0m\n" + " \u001B[47m \u001B[30m ♠  \u001B[0m\n" + " \u001B[47m  \u001B[30m  A\u001B[0m\n"),
    TWOOFSPADES("Two of Spades", 2, " \u001B[47m\u001B[30m2    \u001B[0m\n" + " \u001B[47m \u001B[30m ♠  \u001B[0m\n" + " \u001B[47m  \u001B[30m  2\u001B[0m\n"),
    THREEOFSPADES("Three of Spades", 3, " \u001B[47m\u001B[30m3    \u001B[0m\n" + " \u001B[47m \u001B[30m ♠  \u001B[0m\n" + " \u001B[47m  \u001B[30m  3\u001B[0m\n"),
    FOUROFSPADES("Four of Spades", 4, " \u001B[47m\u001B[30m4    \u001B[0m\n" + " \u001B[47m \u001B[30m ♠  \u001B[0m\n" + " \u001B[47m  \u001B[30m  4\u001B[0m\n"),
    FIVEOFSPADES("Five of Spades", 5, " \u001B[47m\u001B[30m5    \u001B[0m\n" + " \u001B[47m \u001B[30m ♠  \u001B[0m\n" + " \u001B[47m  \u001B[30m  5\u001B[0m\n"),
    SIXOFSPADES("Six of Spades", 6, " \u001B[47m\u001B[30m6    \u001B[0m\n" + " \u001B[47m \u001B[30m ♠  \u001B[0m\n" + " \u001B[47m  \u001B[30m  6\u001B[0m\n"),
    SEVENOFSPADES("Seven of Spades", 7, " \u001B[47m\u001B[30m7    \u001B[0m\n" + " \u001B[47m \u001B[30m ♠  \u001B[0m\n" + " \u001B[47m  \u001B[30m  7\u001B[0m\n"),
    EIGHTOFSPADES("Eight of Spades", 8, " \u001B[47m\u001B[30m8    \u001B[0m\n" + " \u001B[47m \u001B[30m ♠  \u001B[0m\n" + " \u001B[47m  \u001B[30m  8\u001B[0m\n"),
    NINEOFSPADES("Nine of Spades", 9, " \u001B[47m\u001B[30m9    \u001B[0m\n" + " \u001B[47m \u001B[30m ♠  \u001B[0m\n" + " \u001B[47m  \u001B[30m  9\u001B[0m\n"),
    TENOFSPADES("Ten of Spades", 10, " \u001B[47m\u001B[30m10   \u001B[0m\n" + " \u001B[47m \u001B[30m ♠  \u001B[0m\n" + " \u001B[47m  \u001B[30m 10\u001B[0m\n"),
    JACKOFSPADES("Jack of Spades", 10, " \u001B[47m\u001B[30mJ    \u001B[0m\n" + " \u001B[47m \u001B[30m ♠  \u001B[0m\n" + " \u001B[47m  \u001B[30m  J\u001B[0m\n"),
    QUEENOFSPADES("Queen of Spades", 10, " \u001B[47m\u001B[30mQ    \u001B[0m\n" + " \u001B[47m \u001B[30m ♠  \u001B[0m\n" + " \u001B[47m  \u001B[30m  Q\u001B[0m\n"),
    KINGOFSPADES("King of Spades", 10, " \u001B[47m\u001B[30mK    \u001B[0m\n" + " \u001B[47m \u001B[30m ♠  \u001B[0m\n" + " \u001B[47m  \u001B[30m  K\u001B[0m\n"),
    // HEARTS
    ACEOFHEARTS("Ace of Hearts", 1, " \u001B[47m\u001B[31mA    \u001B[0m\n" + " \u001B[47m \u001B[31m ♥  \u001B[0m\n" + " \u001B[47m  \u001B[31m  A\u001B[0m\n"),
    TWOOFHEARTS("Two of Hearts", 2, " \u001B[47m\u001B[31m2    \u001B[0m\n" + " \u001B[47m \u001B[31m ♥  \u001B[0m\n" + " \u001B[47m  \u001B[31m  2\u001B[0m\n"),
    THREEOFHEARTS("Three of Hearts", 3, " \u001B[47m\u001B[31m3    \u001B[0m\n" + " \u001B[47m \u001B[31m ♥  \u001B[0m\n" + " \u001B[47m  \u001B[31m  3\u001B[0m\n"),
    FOUROFHEARTS("Four of Hearts", 4, " \u001B[47m\u001B[31m4    \u001B[0m\n" + " \u001B[47m \u001B[31m ♥  \u001B[0m\n" + " \u001B[47m  \u001B[31m  4\u001B[0m\n"),
    FIVEOFHEARTS("Five of Hearts", 5, " \u001B[47m\u001B[31m5    \u001B[0m\n" + " \u001B[47m \u001B[31m ♥  \u001B[0m\n" + " \u001B[47m  \u001B[31m  5\u001B[0m\n"),
    SIXOFHEARTS("Six of Hearts", 6, " \u001B[47m\u001B[31m6    \u001B[0m\n" + " \u001B[47m \u001B[31m ♥  \u001B[0m\n" + " \u001B[47m  \u001B[31m  6\u001B[0m\n"),
    SEVENOFHEARTS("Seven of Hearts", 7, " \u001B[47m\u001B[31m7    \u001B[0m\n" + " \u001B[47m \u001B[31m ♥  \u001B[0m\n" + " \u001B[47m  \u001B[31m  7\u001B[0m\n"),
    EIGHTOFHEARTS("Eight of Hearts", 8, " \u001B[47m\u001B[31m8    \u001B[0m\n" + " \u001B[47m \u001B[31m ♥  \u001B[0m\n" + " \u001B[47m  \u001B[31m  8\u001B[0m\n"),
    NINEOFHEARTS("Nine of Hearts", 9, " \u001B[47m\u001B[31m9    \u001B[0m\n" + " \u001B[47m \u001B[31m ♥  \u001B[0m\n" + " \u001B[47m  \u001B[31m  9\u001B[0m\n"),
    TENOFHEARTS("Ten of Hearts", 10, " \u001B[47m\u001B[31m10   \u001B[0m\n" + " \u001B[47m \u001B[31m ♥  \u001B[0m\n" + " \u001B[47m  \u001B[31m 10\u001B[0m\n"),
    JACKOFHEARTS("Jack of Hearts", 10, " \u001B[47m\u001B[31mJ    \u001B[0m\n" + " \u001B[47m \u001B[31m ♥  \u001B[0m\n" + " \u001B[47m  \u001B[31m  J\u001B[0m\n"),
    QUEENOFHEARTS("Queen of Hearts", 10, " \u001B[47m\u001B[31mQ    \u001B[0m\n" + " \u001B[47m \u001B[31m ♥  \u001B[0m\n" + " \u001B[47m  \u001B[31m  Q\u001B[0m\n"),
    KINGOFHEARTS("King of Hearts", 10, " \u001B[47m\u001B[31mK    \u001B[0m\n" + " \u001B[47m \u001B[31m ♥  \u001B[0m\n" + " \u001B[47m  \u001B[31m  K\u001B[0m\n"),
    // CLUBS
    ACEOFCLUBS("Ace of Clubs", 1, " \u001B[47m\u001B[30mA    \u001B[0m\n" + " \u001B[47m \u001B[30m ♣  \u001B[0m\n" + " \u001B[47m  \u001B[30m  A\u001B[0m\n"),
    TWOOFCLUBS("Two of Clubs", 2, " \u001B[47m\u001B[30m2    \u001B[0m\n" + " \u001B[47m \u001B[30m ♣  \u001B[0m\n" + " \u001B[47m  \u001B[30m  2\u001B[0m\n"),
    THREEOFCLUBS("Three of Clubs", 3, " \u001B[47m\u001B[30m3    \u001B[0m\n" + " \u001B[47m \u001B[30m ♣  \u001B[0m\n" + " \u001B[47m  \u001B[30m  3\u001B[0m\n"),
    FOUROFCLUBS("Four of Clubs", 4, " \u001B[47m\u001B[30m4    \u001B[0m\n" + " \u001B[47m \u001B[30m ♣  \u001B[0m\n" + " \u001B[47m  \u001B[30m  4\u001B[0m\n"),
    FIVEOFCLUBS("Five of Clubs", 5, " \u001B[47m\u001B[30m5    \u001B[0m\n" + " \u001B[47m \u001B[30m ♣  \u001B[0m\n" + " \u001B[47m  \u001B[30m  5\u001B[0m\n"),
    SIXOFCLUBS("Six of Clubs", 6, " \u001B[47m\u001B[30m6    \u001B[0m\n" + " \u001B[47m \u001B[30m ♣  \u001B[0m\n" + " \u001B[47m  \u001B[30m  6\u001B[0m\n"),
    SEVENOFCLUBS("Seven of Clubs", 7, " \u001B[47m\u001B[30m7    \u001B[0m\n" + " \u001B[47m \u001B[30m ♣  \u001B[0m\n" + " \u001B[47m  \u001B[30m  7\u001B[0m\n"),
    EIGHTOFCLUBS("Eight of Clubs", 8, " \u001B[47m\u001B[30m8    \u001B[0m\n" + " \u001B[47m \u001B[30m ♣  \u001B[0m\n" + " \u001B[47m  \u001B[30m  8\u001B[0m\n"),
    NINEOFCLUBS("Nine of Clubs", 9, " \u001B[47m\u001B[30m9    \u001B[0m\n" + " \u001B[47m \u001B[30m ♣  \u001B[0m\n" + " \u001B[47m  \u001B[30m  9\u001B[0m\n"),
    TENOFCLUBS("Ten of Clubs", 10, " \u001B[47m\u001B[30m10   \u001B[0m\n" + " \u001B[47m \u001B[30m ♣  \u001B[0m\n" + " \u001B[47m  \u001B[30m 10\u001B[0m\n"),
    JACKOFCLUBS("Jack of Clubs", 10, " \u001B[47m\u001B[30mJ    \u001B[0m\n" + " \u001B[47m \u001B[30m ♣  \u001B[0m\n" + " \u001B[47m  \u001B[30m  J\u001B[0m\n"),
    QUEENOFCLUBS("Queen of Clubs", 10, " \u001B[47m\u001B[30mQ    \u001B[0m\n" + " \u001B[47m \u001B[30m ♣  \u001B[0m\n" + " \u001B[47m  \u001B[30m  Q\u001B[0m\n"),
    KINGOFCLUBS("King of Clubs", 10, " \u001B[47m\u001B[30mK    \u001B[0m\n" + " \u001B[47m \u001B[30m ♣  \u001B[0m\n" + " \u001B[47m  \u001B[30m  K\u001B[0m\n"),
    // DIAMONDS
    ACEOFDIAMONDS("Ace of Diamonds", 1, " \u001B[47m\u001B[31mA    \u001B[0m\n" + " \u001B[47m \u001B[31m ♦  \u001B[0m\n" + " \u001B[47m  \u001B[31m  A\u001B[0m\n"),
    TWOOFDIAMONDS("Two of Diamonds", 2, " \u001B[47m\u001B[31m2    \u001B[0m\n" + " \u001B[47m \u001B[31m ♦  \u001B[0m\n" + " \u001B[47m  \u001B[31m  2\u001B[0m\n"),
    THREEOFDIAMONDS("Three of Diamonds", 3, " \u001B[47m\u001B[31m3    \u001B[0m\n" + " \u001B[47m \u001B[31m ♦  \u001B[0m\n" + " \u001B[47m  \u001B[31m  3\u001B[0m\n"),
    FOUROFDIAMONDS("Four of Diamonds", 4, " \u001B[47m\u001B[31m4    \u001B[0m\n" + " \u001B[47m \u001B[31m ♦  \u001B[0m\n" + " \u001B[47m  \u001B[31m  4\u001B[0m\n"),
    FIVEOFDIAMONDS("Five of Diamonds", 5, " \u001B[47m\u001B[31m5    \u001B[0m\n" + " \u001B[47m \u001B[31m ♦  \u001B[0m\n" + " \u001B[47m  \u001B[31m  5\u001B[0m\n"),
    SIXOFDIAMONDS("Six of Diamonds", 6, " \u001B[47m\u001B[31m6    \u001B[0m\n" + " \u001B[47m \u001B[31m ♦  \u001B[0m\n" + " \u001B[47m  \u001B[31m  6\u001B[0m\n"),
    SEVENOFDIAMONDS("Seven of Diamonds", 7, " \u001B[47m\u001B[31m7    \u001B[0m\n" + " \u001B[47m \u001B[31m ♦  \u001B[0m\n" + " \u001B[47m  \u001B[31m  7\u001B[0m\n"),
    EIGHTOFDIAMONDS("Eight of Diamonds", 8, " \u001B[47m\u001B[31m8    \u001B[0m\n" + " \u001B[47m \u001B[31m ♦  \u001B[0m\n" + " \u001B[47m  \u001B[31m  8\u001B[0m\n"),
    NINEOFDIAMONDS("Nine of Diamonds", 9, " \u001B[47m\u001B[31m9    \u001B[0m\n" + " \u001B[47m \u001B[31m ♦  \u001B[0m\n" + " \u001B[47m  \u001B[31m  9\u001B[0m\n"),
    TENOFDIAMONDS("Ten of Diamonds", 10, " \u001B[47m\u001B[31m10   \u001B[0m\n" + " \u001B[47m \u001B[31m ♦  \u001B[0m\n" + " \u001B[47m  \u001B[31m 10\u001B[0m\n"),
    JACKOFDIAMONDS("Jack of Diamonds", 10, " \u001B[47m\u001B[31mJ    \u001B[0m\n" + " \u001B[47m \u001B[31m ♦  \u001B[0m\n" + " \u001B[47m  \u001B[31m  J\u001B[0m\n"),
    QUEENOFDIAMONDS("Queen of Diamonds", 10, " \u001B[47m\u001B[31mQ    \u001B[0m\n" + " \u001B[47m \u001B[31m ♦  \u001B[0m\n" + " \u001B[47m  \u001B[31m  Q\u001B[0m\n"),
    // KINGOFDIAMONDS("King of Diamonds", 10, " \u001B[47m\u001B[31mK    \u001B[0m\n" + " \u001B[47m \u001B[31m ♦  \u001B[0m\n" + " \u001B[47m  \u001B[31m  K\u001B[0m\n");
    KINGOFDIAMONDS("King of Diamonds", 10, " \u001B[47m\u001B[31mK    \u001B[0m\n" + " \u001B[47m \u001B[31m ♦  \u001B[0m\n" + " \u001B[47m  \u001B[31m  K\u001B[0m\n");
    private String cardName;
    private int cardValue;
    private String drawnCard;

    Cards(String cardName, int cardValue, String drawnCard) {
        this.cardName = cardName;
        this.cardValue = cardValue;
        this.drawnCard = drawnCard;
    }

    public int getCardValue() {
        return cardValue;
    }

    public String getDrawnCard() {

        return drawnCard;


    }

}