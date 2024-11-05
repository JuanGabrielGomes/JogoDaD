package tecinfo.poo.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private Connection connection;
    
    public DatabaseConnection() {
        try {
            // Ajuste as informações do banco de dados conforme necessário
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String password = "juan0108";
            
            connection = DriverManager.getConnection(url, user, password);
            createTablesIfNotExist();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void createTablesIfNotExist() {
        try (Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS scores (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "player_name VARCHAR(255) NOT NULL," +
                        "score INT NOT NULL," +
                        "played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void saveScore(String playerName, int score) {
        String sql = "INSERT INTO scores (player_name, score) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, playerName);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<PlayerScore> getTopScores() {
        List<PlayerScore> scores = new ArrayList<>();
        String sql = "SELECT player_name, score FROM scores ORDER BY score DESC LIMIT 10";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String name = rs.getString("player_name");
                int score = rs.getInt("score");
                scores.add(new PlayerScore(name, score));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return scores;
    }
    
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}