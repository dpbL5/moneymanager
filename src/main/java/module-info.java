module oop.moneymanager {
    // requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens oop.moneymanager to javafx.fxml;
    exports oop.moneymanager;
}
