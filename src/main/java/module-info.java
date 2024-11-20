module oop.moneymanager {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.prefs;
    requires java.mail;
    requires de.jensd.fx.glyphs.commons;
    requires org.apache.poi.ooxml;

    exports oop.moneymanager;
    exports oop.moneymanager.model;
    exports oop.moneymanager.controller;
}
