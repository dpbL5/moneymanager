module oop.moneymanager {
    requires javafx.controls;
    requires javafx.fxml;

    opens oop.moneymanager to javafx.fxml;
    exports oop.moneymanager;
}
