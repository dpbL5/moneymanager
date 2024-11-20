package oop.moneymanager.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import oop.moneymanager.PreferencesHelper;
import oop.moneymanager.dao.UserDao;
import oop.moneymanager.model.UserModel;

public class MainScreenNewController implements Initializable{
    @FXML
    public Label username_lbl;
    @FXML
    private Pane viewPane;
    @FXML
    private Label email_label;
    @FXML
    private UserModel user;
    UserModel userModel = new UserModel();

    @FXML
    void onAccountInfoButtonClicked() {
        Pane pane = new MultiFxmlController().getPane("AccountInfo");
        viewPane.getChildren().clear();
        viewPane.getChildren().add(pane);
    }

    @FXML
    void onDailyBoardButtonClicked() {
        Pane pane = new MultiFxmlController().getPane("DailyBoard");
        viewPane.getChildren().clear();
        viewPane.getChildren().add(pane);
    }

    @FXML
    void onStatisticalButtonClicked() {
        Pane pane = new MultiFxmlController().getPane("Statistical");
        viewPane.getChildren().clear();
        viewPane.getChildren().add(pane);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        user = UserDao.getInstance().selectByUserName(PreferencesHelper.getUsername());
        email_label.setText(user.getEmail());
        username_lbl.setText(PreferencesHelper.getUsername());
        Pane pane = new MultiFxmlController().getPane("DailyBoard");
        viewPane.getChildren().add(pane);
    }

    @FXML
    void onAddTransactionButtonClicked() {
        Pane pane = new MultiFxmlController().getPane("AddTransactionView");
        viewPane.getChildren().clear();
        viewPane.getChildren().add(pane);
    }

    @FXML
    void onWalletButtonClicked() {
        Pane pane = new MultiFxmlController().getPane("WalletView");
        viewPane.getChildren().clear();
        viewPane.getChildren().add(pane);
    }
}
