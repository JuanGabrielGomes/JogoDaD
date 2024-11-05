package tecinfo.poo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tecinfo.poo.controller.GameController;
import tecinfo.poo.controller.RankingController;
import tecinfo.poo.controller.StartController;
import tecinfo.poo.model.DatabaseConnection;
import tecinfo.poo.model.DiceGame;
import tecinfo.poo.model.Player;

public class Main extends Application implements Initializable {
    private DiceGame diceGame;
    private DatabaseConnection dbConnection;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        diceGame = new DiceGame();
        dbConnection = new DatabaseConnection();
        
        try {
            showStartScreen();
            primaryStage.setTitle("Jogo de Dados");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialização de recursos se necessário
    }

    public void showStartScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/startScreen.fxml"));
            Parent root = loader.load();
            StartController controller = loader.getController();
            controller.initialize(this, diceGame, dbConnection);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showGameScreen(Player player) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameScreen.fxml"));
            Parent root = loader.load();
            GameController controller = loader.getController();
            controller.initialize(this, diceGame, dbConnection, player);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showRankingScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rankingScreen.fxml"));
            Parent root = loader.load();
            RankingController controller = loader.getController();
            controller.initialize(this, dbConnection);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Método para acessar o Stage principal de qualquer lugar
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}