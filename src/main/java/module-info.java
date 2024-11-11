module oop.moneymanager {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.prefs;

    opens oop.moneymanager to javafx.fxml;
    exports oop.moneymanager;
    
    opens oop.moneymanager.controllers to javafx.fxml;
    exports oop.moneymanager.controllers;

}
