module tecinfo.poo {
    requires java.sql;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.fxml;
    
    opens tecinfo.poo to javafx.fxml;
    exports tecinfo.poo; // substitua pelo seu pacote principal
}
