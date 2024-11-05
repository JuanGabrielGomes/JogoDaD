package tecinfo.poo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tecinfo.poo.Main;
import tecinfo.poo.model.DatabaseConnection;
import tecinfo.poo.model.DiceGame;
import tecinfo.poo.model.Player;

public class StartController {
    private Main main;
    private DiceGame diceGame;
    private DatabaseConnection dbConnection;
    
    @FXML
    private TextField playerNameField;

    public void initialize(Main main, DiceGame diceGame, DatabaseConnection dbConnection) {
        this.main = main;
        this.diceGame = diceGame;
        this.dbConnection = dbConnection;
    }
    
    @FXML
    private void handleStartGame() {
        String playerName = playerNameField.getText().trim();
        if (!playerName.isEmpty()) {
            Player player = new Player(playerName);
            main.showGameScreen(player);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, insira um nome válido!");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleShowRanking() {
        main.showRankingScreen();
    }
}