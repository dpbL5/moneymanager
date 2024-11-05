module oop.moneymanager {
    // requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens oop.moneymanager to javafx.fxml;
    // opens oop.moneymanager.controller to java.fxml;
    exports oop.moneymanager;
}
