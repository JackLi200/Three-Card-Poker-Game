import java.util.ArrayList;

public class Player {
    private int anteBet;
    private int playBet;
    private int pairPlusBet;
    private int totalWinnings;
    ArrayList<Card> hand;

    Player() {
        hand = new ArrayList<>();
        anteBet = 0;
        playBet = 0;
        pairPlusBet = 0;
        totalWinnings = 0;
    }

    void setAnteBet(int anteBet) {
        this.anteBet = anteBet;
    }

    void setPairPlusBet(int pairPlusBet) {
        this.pairPlusBet = pairPlusBet;
    }

    void setPlayBet() {
        playBet = anteBet;
    }

    void returnPlayBet() {
        playBet = 0;
    }

    void setTotalWinnings(int ppBet, int winningBet) {
        totalWinnings += ppBet + winningBet;
    }

    int getTotalWinning() {
        return totalWinnings;
    }

    int getAnteBet() {
        return anteBet;
    }

    int getPairPlusBet() {
        return pairPlusBet;
    }

}
