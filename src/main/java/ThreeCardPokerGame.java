import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.HashMap;

public class ThreeCardPokerGame extends Application {
    Player playerOne;
    Player playerTwo;
    Dealer theDealer;

    Text title, wager1Text, wager2Text, p1, p2, d1, anteInfo1, anteInfo2;
    TextField TFgetWager1, TFgetWager2;
    Button BTNbet1, BTNbet2, BTNplay, BTNskip1, BTNskip2, BTNfold1, BTNfold2, BTNwager1, BTNwager2;
    ImageView dpic1, dpic2, dpic3, p1pic1, p1pic2, p1pic3, p2pic1, p2pic2, p2pic3;
    ObservableList<String> strList = FXCollections.observableArrayList();
    ListView<String> listView = new ListView<>(strList);

    PauseTransition pause = new PauseTransition(Duration.seconds(2));
    MenuBar mBar = new MenuBar();
    Menu menu = new Menu("Option");
    MenuItem reStart = new MenuItem("Fresh Start");
    MenuItem exit = new MenuItem("Exit");
    MenuItem newLook = new MenuItem("New Look");
    BorderPane root = new BorderPane();
    Boolean p1FinishBet = false;
    Boolean p2FinishBet = false;
    Boolean p1Fold = false;
    Boolean p2Fold = false;
    Boolean p1tie = false;
    Boolean p2tie = false;

    int newLooked = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        playerOne = new Player();
        playerTwo = new Player();
        theDealer = new Dealer();
        primaryStage.setTitle("Welcome to Project #2");
        listView.setItems(strList);
        listView.setPrefHeight(100);
        BTNplay = new Button("play");
        menu.getItems().addAll(reStart, exit, newLook);
        mBar.getMenus().add(menu);
        exit.setOnAction(e -> Platform.exit());
        reStart.setOnAction(e -> reSet());
        newLook.setOnAction(e->newLook());
        root.setTop(mBar);
        root.setCenter(createStartVbox());
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();
    }

    // This method resets the game.
    public void reSet() {
        PauseTransition pause3 = new PauseTransition(Duration.seconds(2));
        pause3.setOnFinished(e -> {
            root.setCenter(createStartVbox());
            strList.clear();
            p1FinishBet = false;
            p2FinishBet = false;
            p1Fold = false;
            p2Fold = false;
            p1tie = true;
            p2tie = true;
            root.setBottom(null);
        });
        pause3.play();
    }

    // This method changes the style of the app
    public void newLook() {
        PauseTransition pause3 = new PauseTransition(Duration.seconds(2));
        pause3.setOnFinished(e -> {
            if (newLooked == 0) {
                root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                newLooked++;
            } else if (newLooked == 1) {
                root.setBackground(new Background(new BackgroundFill(Color.VIOLET, CornerRadii.EMPTY, Insets.EMPTY)));

                newLooked++;
            }  else if (newLooked == 2) {
                root.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                newLooked = 0;
            }
        });
        pause3.play();
    }

    // This method set up the player Vbox(how much to bet)
    public VBox startPlayerBetVbox(int player) {
        if (player == 1) {
            wager1Text = new Text("Ante Wager");
            wager1Text.setFont(Font.font(15));
            TFgetWager1 = new TextField("5-25");
            p1 = new Text("player 1");
            p1.setFont(Font.font(15));

            BTNskip1 = new Button("skip");
            BTNskip1.setDisable(true);
            BTNbet1 = new Button("bet");

            if (p1tie) {
                p1FinishBet = true;
                BTNbet1.setDisable(true);
                TFgetWager1.setEditable(false);
            }
            BTNbet1.setOnAction(e -> {
                int bet = Integer.parseInt(TFgetWager1.getText());
                if (bet >= 5 && bet <= 25) {
                    playerOne.setAnteBet(bet);
                    BTNbet1.setDisable(true);
                    TFgetWager1.setEditable(false);
                    p1FinishBet = true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect value!");

                    alert.showAndWait();
                }
            });


            return new VBox(wager1Text, TFgetWager1, BTNbet1, BTNskip1, p1);

        } else {
            wager2Text = new Text("Ante Wager");
            wager2Text.setFont(Font.font(15));
            TFgetWager2 = new TextField("5-25");
            p2 = new Text("player 2");
            p2.setFont(Font.font(15));

            BTNskip2 = new Button("skip");
            BTNskip2.setDisable(true);
            BTNbet2 = new Button("bet");

            if (p2tie) {
                p2FinishBet = true;
                BTNbet2.setDisable(true);
                TFgetWager2.setEditable(false);
            }
            BTNbet2.setOnAction(e -> {
                int bet2 = Integer.parseInt(TFgetWager2.getText());
                if (bet2 >= 5 && bet2 <= 25) {
                    playerTwo.setAnteBet(bet2);
                    BTNbet2.setDisable(true);
                    TFgetWager2.setEditable(false);
                    p2FinishBet = true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect value!");

                    alert.showAndWait();
                }
            });

            return new VBox(wager2Text, TFgetWager2, BTNbet2, BTNskip2, p2);
        }
    }

    // This method set up the start scene of the game
    public VBox createStartVbox() {
        title = new Text("3 Card Poker");
        title.setFont(Font.font(35));

        VBox p1BetVbox = startPlayerBetVbox(1);
        VBox p2BetVbox = startPlayerBetVbox(2);

        HBox playerInfo = new HBox(p1BetVbox, p2BetVbox);
        VBox back = new VBox(title, playerInfo, BTNplay);

        p1BetVbox.setAlignment(Pos.CENTER);
        p2BetVbox.setAlignment(Pos.CENTER);
        playerInfo.setAlignment(Pos.CENTER);
        back.setAlignment(Pos.BASELINE_CENTER);
        p1BetVbox.setSpacing(10);
        p2BetVbox.setSpacing(10);
        playerInfo.setSpacing(90);
        back.setSpacing(70);
        TFgetWager1.setOnMouseClicked(e -> TFgetWager1.clear());
        TFgetWager2.setOnMouseClicked(e -> TFgetWager2.clear());
        BTNplay.setOnAction(e -> {
            if (p1FinishBet && p2FinishBet) {
                setPPWager();
                sendOutCards();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Please Finish Bet First!");

                alert.showAndWait();
            }

        });
        return back;
    }

    // This method change the start scene to ask for Pair Plus wager input
    public void setPPWager() {
        p1FinishBet = false;
        p2FinishBet = false;
        TFgetWager1.setText("5-25");
        TFgetWager1.setEditable(true);
        TFgetWager2.setText("5-25");
        TFgetWager2.setEditable(true);

        BTNbet1.setDisable(false);
        BTNbet2.setDisable(false);

        wager1Text.setText("Pair Plus Wager");
        wager2Text.setText("Pair Plus Wager");
        BTNskip1.setDisable(false);
        BTNskip2.setDisable(false);

        BTNbet1.setOnAction(e -> {
            int bet1 = Integer.parseInt(TFgetWager1.getText());
            if (bet1 >= 5 && bet1 <= 25) {
                playerOne.setPairPlusBet(bet1);
                BTNbet1.setDisable(true);
                BTNskip1.setDisable(true);
                TFgetWager1.setEditable(false);
                p1FinishBet = true;
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect value!");

                alert.showAndWait();
            }
        });

        BTNbet2.setOnAction(e -> {
            int bet2 = Integer.parseInt(TFgetWager2.getText());
            if (bet2 >= 5 && bet2 <= 25) {
                playerTwo.setPairPlusBet(bet2);
                BTNbet2.setDisable(true);
                BTNskip2.setDisable(true);
                TFgetWager2.setEditable(false);
                p2FinishBet = true;
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect value!");

                alert.showAndWait();
            }

        });

        BTNskip1.setOnAction(e -> {
            playerOne.setPairPlusBet(0);
            BTNbet1.setDisable(true);
            BTNskip1.setDisable(true);
            TFgetWager1.setEditable(false);
            p1FinishBet = true;
        });

        BTNskip2.setOnAction(e -> {
            playerTwo.setPairPlusBet(0);
            BTNbet2.setDisable(true);
            BTNskip2.setDisable(true);
            TFgetWager2.setEditable(false);
            p2FinishBet = true;
        });

        BTNplay.setOnAction(e -> {
            if (p1FinishBet && p2FinishBet) {
                PauseTransition pause2 = new PauseTransition(Duration.seconds(2));
                pause2.setOnFinished(d -> {
                    root.setCenter(createGameVbox());
                    root.setBottom(listView);
                });
                pause2.play();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Please Finish Bet First!");

                alert.showAndWait();
            }
        });

    }

    // This method sends out the cards to players' hand and dealer's hand
    public void sendOutCards() {
        playerOne.hand = theDealer.dealHand();
        playerTwo.hand = theDealer.dealHand();
        theDealer.dealersHand = theDealer.dealHand();
    }

    // This method returns the name of the suit of the passed in card
    public String returnSuit(Card card) {
        if (card.suit == 'C') {
            return "clubs";
        } else if (card.suit == 'D') {
            return "diamonds";
        } else if (card.suit == 'H') {
            return "hearts";
        } else if (card.suit == 'S') {
            return "spades";
        }

        return null;
    }

    // This method shows the card in images. With mode of 1 it displays the back of
    // the card.
    public void displayCards(ArrayList<Card> hand, ImageView pic1, ImageView pic2, ImageView pic3, int mode) {
        if (mode == 1) {
            pic1.setImage(new Image("back.png"));
            pic2.setImage(new Image("back.png"));
            pic3.setImage(new Image("back.png"));
        } else {
            String Card1 = hand.get(0).value + "_of_" + returnSuit(hand.get(0)) + ".png";
            pic1.setImage(new Image(Card1));
            String Card2 = hand.get(1).value + "_of_" + returnSuit(hand.get(1)) + ".png";
            pic2.setImage(new Image(Card2));
            String Card3 = hand.get(2).value + "_of_" + returnSuit(hand.get(2)) + ".png";
            pic3.setImage(new Image(Card3));
        }
        pic1.setFitHeight(115);
        pic1.setFitWidth(75);
        pic1.setPreserveRatio(true);
        pic2.setFitHeight(115);
        pic2.setFitWidth(75);
        pic2.setPreserveRatio(true);
        pic3.setFitHeight(115);
        pic3.setFitWidth(75);
        pic3.setPreserveRatio(true);
    }

    // This method shows the player area of the game(cards, bets, infos)
    public VBox playerShowCardVbox(int player) {
        if (player == 1) {
            BTNfold1 = new Button("fold");
            p1 = new Text("player 1" + "    total winning: " + playerOne.getTotalWinning());
            BTNwager1 = new Button("play");
            anteInfo1 = new Text("Ante:     " + playerOne.getAnteBet() + "   Pair Plus:       "
                    + playerOne.getPairPlusBet() + "   Play wager:      " + playerOne.getAnteBet());
            p1pic1 = new ImageView();
            p1pic2 = new ImageView();
            p1pic3 = new ImageView();
            displayCards(playerOne.hand, p1pic1, p1pic2, p1pic3, 0);
            HBox p1Card = new HBox(p1pic1, p1pic2, p1pic3);
            p1Card.setSpacing(20);
            BorderPane playInfo = new BorderPane();
            playInfo.setCenter(p1);
            playInfo.setLeft(BTNfold1);
            playInfo.setRight(BTNwager1);
            playInfo.setBottom(anteInfo1);

            BTNfold1.setOnAction(e -> {
                p1Fold = true;
                BTNwager1.setDisable(true);
                BTNfold1.setDisable(true);
                playerOne.setTotalWinnings(-playerOne.getPairPlusBet(), -playerOne.getAnteBet());
                playerOne.setAnteBet(0);
                playerOne.setPairPlusBet(0);
                strList.add(0, "PlayerOne fold");
                if ((p1FinishBet || p1Fold) && (p2FinishBet || p2Fold)) {
                    checkWin();
                }
            });

            BTNwager1.setOnAction(e -> {
                playerOne.setPlayBet();
                BTNwager1.setDisable(true);
                BTNfold1.setDisable(true);
                p1FinishBet = true;

                if ((p1FinishBet || p1Fold) && (p2FinishBet || p2Fold)) {
                    checkWin();
                }
            });

            return new VBox(p1Card, playInfo);
        } else {
            BTNfold2 = new Button("fold");
            p2 = new Text("player 2" + "    total winning: " + playerTwo.getTotalWinning());
            BTNwager2 = new Button("play");
            anteInfo2 = new Text("Ante:     " + playerTwo.getAnteBet() + "   Pair Plus:       "
                    + playerTwo.getPairPlusBet() + "   Play wager:      " + playerTwo.getAnteBet());
            p2pic1 = new ImageView();
            p2pic2 = new ImageView();
            p2pic3 = new ImageView();

            displayCards(playerTwo.hand, p2pic1, p2pic2, p2pic3, 0);
            HBox p2Card = new HBox(p2pic1, p2pic2, p2pic3);
            p2Card.setSpacing(20);
            BorderPane playInfo2 = new BorderPane();
            playInfo2.setCenter(p2);
            playInfo2.setLeft(BTNfold2);
            playInfo2.setRight(BTNwager2);
            playInfo2.setBottom(anteInfo2);

            BTNfold2.setOnAction(e -> {
                p2Fold = true;
                BTNwager2.setDisable(true);
                BTNfold2.setDisable(true);
                playerTwo.setTotalWinnings(-playerTwo.getPairPlusBet(), -playerTwo.getAnteBet());
                playerTwo.setAnteBet(0);
                playerTwo.setPairPlusBet(0);
                strList.add(0, "PlayerTwo fold");

                if ((p1FinishBet || p1Fold) && (p2FinishBet || p2Fold)) {
                    checkWin();
                }
            });

            BTNwager2.setOnAction(e -> {
                playerTwo.setPlayBet();
                BTNwager2.setDisable(true);
                BTNfold2.setDisable(true);
                p2FinishBet = true;

                if ((p1FinishBet || p1Fold) && (p2FinishBet || p2Fold)) {
                    checkWin();
                }
            });

            return new VBox(p2Card, playInfo2);
        }
    }

    // This method creates the scene in the playing game stage
    public VBox createGameVbox() {
        p1FinishBet = false;
        p2FinishBet = false;
        d1 = new Text("Dealer");
        dpic1 = new ImageView();
        dpic2 = new ImageView();
        dpic3 = new ImageView();

        displayCards(theDealer.dealersHand, dpic1, dpic2, dpic3, 1);
        HBox dealerCard = new HBox(dpic1, dpic2, dpic3);
        VBox p1Vbox = playerShowCardVbox(1);
        VBox p2Vbox = playerShowCardVbox(2);

        HBox playerCard = new HBox(p1Vbox, p2Vbox);
        VBox bg = new VBox(d1, dealerCard, playerCard);

        p1Vbox.setAlignment(Pos.BOTTOM_LEFT);
        p2Vbox.setAlignment(Pos.BOTTOM_RIGHT);
        p1Vbox.setSpacing(10);
        p2Vbox.setSpacing(10);
        bg.setAlignment(Pos.BASELINE_CENTER);
        dealerCard.setAlignment(Pos.BASELINE_CENTER);
        playerCard.setAlignment(Pos.BOTTOM_CENTER);
        playerCard.setSpacing(90);
        dealerCard.setSpacing(20);
        bg.setSpacing(15);
        return bg;
    }

    // This method outputs in listview on who won the game
    public void checkWin() {
        displayCards(theDealer.dealersHand, dpic1, dpic2, dpic3, 0);
        int p1ppwin = 0, p2ppwin = 0, p1win = 0, p2win = 0;
        pause.setOnFinished(e -> strList.add(0, "Round Finished!"));
        pause.play();
        if (p1FinishBet) {
            if (playerOne.getPairPlusBet() >= 5) {
                p1ppwin = ThreeCardLogic.evalPPWinnings(playerOne.hand, playerOne.getPairPlusBet());
                if (p1ppwin > 0) {
                    strList.add(0, "Player one wins Pair Plus");

                } else {
                    strList.add(0, "Player one loses Pair Plus");
                    p1ppwin = -playerOne.getPairPlusBet();

                }
            }
            int win = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
            if (win == 0) {
                strList.add(0, "Player one and dealer neither has won; ante wager is pushed");
                playerOne.returnPlayBet();
                p1tie = true;
            } else if (win == 1) {
                strList.add(0, "Player one loses to dealer");
                p1win = -(playerOne.getAnteBet() * 2);
                playerOne.setAnteBet(0);
                playerOne.setPlayBet();

            } else {
                strList.add(0, "Player one beats dealer");
                p1win = playerOne.getAnteBet() * 4;
                playerOne.setAnteBet(0);
                playerOne.setPlayBet();

            }
            playerOne.setPairPlusBet(0);
            playerOne.setTotalWinnings(p1ppwin, p1win);
        }
        if (p2FinishBet) {
            if (playerTwo.getPairPlusBet() >= 5) {
                p2ppwin = ThreeCardLogic.evalPPWinnings(playerTwo.hand, playerTwo.getPairPlusBet());
                if (p2ppwin > 0) {
                    strList.add(0, "Player two wins Pair Plus");

                } else {
                    strList.add(0, "Player two loses Pair Plus");
                    p2ppwin = -playerTwo.getPairPlusBet();

                }
            }
            int win = ThreeCardLogic.compareHands(theDealer.dealersHand, playerTwo.hand);
            if (win == 0) {
                strList.add(0, "Player two and dealer neither has won; ante wager is pushed");
                playerTwo.returnPlayBet();
                p2tie = true;

            } else if (win == 1) {
                strList.add(0, "Player two loses to dealer");
                p2win = -(playerTwo.getAnteBet() * 2);
                playerTwo.setAnteBet(0);
                playerTwo.setPlayBet();

            } else {
                strList.add(0, "Player two beats dealer");
                p2win = playerTwo.getAnteBet() * 4;
                playerTwo.setAnteBet(0);
                playerTwo.setPlayBet();

            }
            playerTwo.setPairPlusBet(0);
            playerTwo.setTotalWinnings(p2ppwin, p2win);
        }
    }

}
