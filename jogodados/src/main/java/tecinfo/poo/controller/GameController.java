package tecinfo.poo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tecinfo.poo.Main;
import tecinfo.poo.model.DatabaseConnection;
import tecinfo.poo.model.DiceGame;
import tecinfo.poo.model.Player;

public class GameController {
    private Main main;
    private DiceGame diceGame;
    private DatabaseConnection dbConnection;
    private Player currentPlayer;
    
    @FXML
    private Label playerNameLabel;
    @FXML
    private ImageView dice1ImageView;
    @FXML
    private ImageView dice2ImageView;
    @FXML
    private Label resultLabel;

    public void initialize(Main main, DiceGame diceGame, DatabaseConnection dbConnection, Player player) {
        this.main = main;
        this.diceGame = diceGame;
        this.dbConnection = dbConnection;
        this.currentPlayer = player;
        playerNameLabel.setText(currentPlayer.getName());
    }
    
    @FXML
    private void handleRollDice() {
        int[] results = diceGame.rollDice();
        updateDiceDisplay(results);
        int score = results[0] + results[1];
        resultLabel.setText("Total: " + score);
        
        dbConnection.saveScore(currentPlayer.getName(), score);
    }
    
    private void updateDiceDisplay(int[] results) {
        dice1ImageView.setImage(new Image("/images/dice" + results[0] + ".png"));
        dice2ImageView.setImage(new Image("/images/dice" + results[1] + ".png"));
    }
    
    @FXML
    private void handleBackToStart() {
        main.showStartScreen();
    }
}