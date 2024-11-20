package oop.moneymanager.controller;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class MultiFxmlController {
    
    private Pane viewPane;

    public Pane getPane(String path) {
        try {
            URL url = MultiFxmlController.class.getResource("/oop/moneymanager/view/" +  path + ".fxml");
            if (url == null) {
                throw new IOException("Resource not found: " + path);
            }
            viewPane = FXMLLoader.load(url);
        } catch (IOException e) {
            System.out.println(path + " not Found");
        }
        return viewPane;
    }
}
