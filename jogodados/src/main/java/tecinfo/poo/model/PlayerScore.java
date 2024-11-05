package tecinfo.poo.model;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PlayerScore {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty score;
    
    public PlayerScore(String name, int score) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(score);
    }
    
    public SimpleStringProperty nameProperty() {
        return name;
    }
    
    public SimpleIntegerProperty scoreProperty() {
        return score;
    }
}