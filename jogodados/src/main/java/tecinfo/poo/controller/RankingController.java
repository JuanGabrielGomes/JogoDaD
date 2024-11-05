package tecinfo.poo.controller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tecinfo.poo.Main;
import tecinfo.poo.model.DatabaseConnection;
import tecinfo.poo.model.PlayerScore;

public class RankingController {
    private Main main;
    private DatabaseConnection dbConnection;
    
    @FXML
    private TableView<PlayerScore> rankingTableView;
    @FXML
    private TableColumn<PlayerScore, String> nameColumn;
    @FXML
    private TableColumn<PlayerScore, Integer> scoreColumn;

    public void initialize(Main main, DatabaseConnection dbConnection) {
        this.main = main;
        this.dbConnection = dbConnection;
        
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty().asObject());
        
        updateRanking();
    }
    
    private void updateRanking() {
        List<PlayerScore> scores = dbConnection.getTopScores();
        rankingTableView.getItems().clear();
        rankingTableView.getItems().addAll(scores);
    }
    
    @FXML
    private void handleBackToStart() {
        main.showStartScreen();
    }
}