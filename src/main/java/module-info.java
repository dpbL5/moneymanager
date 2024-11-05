module oop.moneymanager {
    // requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens oop.moneymanager to javafx.fxml;
    exports oop.moneymanager;
}
