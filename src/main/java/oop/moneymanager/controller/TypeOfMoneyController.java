package oop.moneymanager.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oop.moneymanager.PreferencesHelper;
import oop.moneymanager.dao.UserDao;
import oop.moneymanager.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;

public class TypeOfMoneyController {
    @FXML
    private Label budgetLabel;
    private Label sumLabel;
    private Label debtLabel;

    public TypeOfMoneyController(){
    }
}
