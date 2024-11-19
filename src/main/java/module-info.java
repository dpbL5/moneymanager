module oop.moneymanager {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.prefs;

    opens oop.moneymanager to javafx.fxml;
    exports oop.moneymanager;

    opens oop.moneymanager.model to javafx.base;
    opens oop.moneymanager.controller to javafx.fxml, de.jensd.fx.glyphs.commons;
    exports oop.moneymanager.controller;

}
