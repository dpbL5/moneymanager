module oop.moneymanager {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.prefs;

    opens oop.moneymanager to javafx.fxml;
    exports oop.moneymanager;
    exports oop.moneymanager.Controller;
    opens oop.moneymanager.Controller to javafx.fxml;

}
