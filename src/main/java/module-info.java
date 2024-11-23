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
    requires javafx.base;
    
    opens oop.moneymanager.controller to javafx.fxml;
    exports oop.moneymanager.controller;
    opens oop.moneymanager.dao to java.sql;
    exports oop.moneymanager.dao;
    exports oop.moneymanager.model;
    exports oop.moneymanager;
}
