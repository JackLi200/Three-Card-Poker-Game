import java.util.ArrayList;
import java.util.Objects;

public class ThreeCardLogic {

    // pass in three int to check if they are straight
    private static boolean checkStraight(int one, int two, int three) {
        int max = Math.max(one, Math.max(two, three));
        int min = Math.min(one, Math.min(two, three));
        int mid;
        if (one == max) {
            if (two == min) {
                mid = three;
            } else {
                mid = two;
            }
        } else if (two == max) {
            if (one == min) {
                mid = three;
            } else {
                mid = one;
            }
        } else { // three == max
            if (one == min) {
                mid = two;
            } else {
                mid = one;
            }
        }

        if (max - min != 2) {
            return false;
        } else {
            return max - mid == 1 && mid - min == 1;
        }

    }

    // This method return an integer value representing the value of the hand passed
    // in
    public static int evalHand(ArrayList<Card> hand) {
        Card one = hand.get(0);
        Card two = hand.get(1);
        Card three = hand.get(2);

        if (one.suit == two.suit && two.suit == three.suit) {
            if (checkStraight(one.value, two.value, three.value)) {
                return 1;
            } else {
                return 4;
            }
        } else if (checkStraight(one.value, two.value, three.value)) {
            return 3;
        } else if (one.value == two.value && two.value == three.value) {
            return 2;
        } else if (one.value == two.value || one.value == three.value || two.value == three.value) {
            return 5;
        } else {
            return 0;
        }
    }

    // This method return the amount won for PairPlus bet.
    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
        int eval = evalHand(hand);
        switch (eval) {
            case 1:
                return bet * 40 + bet;
            case 2:
                return bet * 30 + bet;
            case 3:
                return bet * 6 + bet;
            case 4:
                return bet * 3 + bet;
            case 5:
                return bet * 2;
            case 0:
                return 0;
        }
        return 0;
    }

    // This method check if the dealer has the high card value. return 0 if draw, 1
    // if dealer won, 2 if player won
    private static int whoWinHigh(ArrayList<Card> dealer, ArrayList<Card> player) {
        ArrayList<Integer> dealerCopy = new ArrayList<>();
        ArrayList<Integer> playerCopy = new ArrayList<>();
        dealerCopy.add(dealer.get(0).value);
        dealerCopy.add(dealer.get(1).value);
        dealerCopy.add(dealer.get(2).value);
        playerCopy.add(player.get(0).value);
        playerCopy.add(player.get(1).value);
        playerCopy.add(player.get(2).value);

        int dealerHigh = Math.max(dealerCopy.get(0), Math.max(dealerCopy.get(1), dealerCopy.get(2)));
        int playerHigh = Math.max(playerCopy.get(0), Math.max(playerCopy.get(1), playerCopy.get(2)));

        if (dealerHigh == playerHigh) { // first high equal
            dealerCopy.remove((Integer) dealerHigh);
            playerCopy.remove((Integer) playerHigh);
            dealerHigh = Math.max(dealerCopy.get(0), dealerCopy.get(1));
            playerHigh = Math.max(playerCopy.get(0), playerCopy.get(1));

            if (dealerHigh == playerHigh) { // second high equal
                dealerCopy.remove((Integer) dealerHigh);
                playerCopy.remove((Integer) playerHigh);

                if (Objects.equals(dealerCopy.get(0), playerCopy.get(0))) { // third high equal
                    return 0;
                } else if (dealerCopy.get(0) > playerCopy.get(0)) { // dealer third higher
                    return 1;
                } else {
                    return 2;
                }
            } else if (dealerHigh > playerHigh) { // dealer second higher
                return 1;
            } else {
                return 2;
            }
        } else if (dealerHigh > playerHigh) { // dealer first higher
            return 1;
        } else {
            return 2;
        }
        //
    }

    // This method checks the highest card value in two Card list. return 0 if draw,
    // 1 if dealer won, 2 if player won
    private static int whoWinOneHigh(ArrayList<Card> dealer, ArrayList<Card> player) {
        int dealerHigh = Math.max(dealer.get(0).value, Math.max(dealer.get(1).value, dealer.get(2).value));
        int playerHigh = Math.max(player.get(0).value, Math.max(player.get(1).value, player.get(2).value));
        if (dealerHigh == playerHigh) {
            return 0;
        } else if (dealerHigh > playerHigh) {
            return 1;
        } else {
            return 2;
        }
    }

    // This method checks the highest pair value first, if they are equal, check the
    // third single card's highest
    private static int checkPairHigh(ArrayList<Card> dealer, ArrayList<Card> player) {
        int d1 = -1, d2 = -1, p1 = -1, p2 = -1;
        if (dealer.get(0).value == dealer.get(1).value) {
            d1 = 2;
            d2 = 1;
        } else if (dealer.get(0).value == dealer.get(2).value) {
            d1 = 1;
            d2 = 2;
        } else if (dealer.get(1).value == dealer.get(2).value) {
            d1 = 0;
            d2 = 2;
        }

        if (player.get(0).value == player.get(1).value) {
            p1 = 2;
            p2 = 1;
        } else if (player.get(0).value == player.get(2).value) {
            p1 = 1;
            p2 = 2;
        } else if (player.get(1).value == player.get(2).value) {
            p1 = 0;
            p2 = 2;
        }

        if (dealer.get(d2).value == player.get(p2).value) {
            if (dealer.get(d1).value == player.get(p1).value) {
                return 0;
            } else if (dealer.get(d1).value > player.get(p1).value) {
                return 1;
            } else {
                return 2;
            }
        } else if (dealer.get(d2).value > player.get(p2).value) {
            return 1;
        } else {
            return 2;
        }

    }

    // This method compare the two hands passed in and determine who won
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        int dHand = evalHand(dealer);
        int pHand = evalHand(player);
        // determine if the game is played or not
        if (dHand == 0) {
            if (Math.max(dealer.get(0).value, Math.max(dealer.get(1).value, dealer.get(2).value)) < 12) {
                return 0;
            } else { // dealer has queen high
                if (pHand > 0) { // player has pair or higher
                    return 2;
                } else {
                    return whoWinHigh(dealer, player);
                }
            }
        }

        switch (dHand) {
            case 1:
                if (pHand == 1) {
                    return whoWinOneHigh(dealer, player);
                } else {
                    return 1;
                }
            case 2:
                if (pHand == 2) {
                    return whoWinHigh(dealer, player);
                } else if (pHand == 1) {
                    return 2;
                } else {
                    return 1;
                }
            case 3:
                if (pHand == 3) {
                    return whoWinOneHigh(dealer, player);
                } else if (pHand == 0) {
                    return 1;
                } else if (pHand < 3) {
                    return 2;
                } else {
                    return 1;
                }
            case 4:
                if (pHand == 4) {
                    return whoWinHigh(dealer, player);
                } else if (pHand == 0 || pHand == 5) {
                    return 1;
                } else {
                    return 2;
                }
            case 5:
                if (pHand == 5) {
                    return checkPairHigh(dealer, player);
                } else if (pHand == 0) {
                    return 1;
                } else {
                    return 2;
                }
        }

        return -1;
    }
}
