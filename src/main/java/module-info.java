module oop.moneymanager {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens oop.moneymanager to javafx.fxml;
    exports oop.moneymanager;
    
    opens oop.moneymanager.controller to javafx.fxml;
    exports oop.moneymanager.controller;

}
