package com.example.pharmacy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Window2Controller {
    ObservableList<String> choiceList = FXCollections.observableArrayList("Pharmacist", "Deliveryman");
    Authoziration au = new Authoziration();

    @FXML
    private Button enterButton1;

    @FXML
    private TextField loginTextField;

    @FXML
    private Label lognLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private ChoiceBox selectAccType;

    @FXML
    private Label showLabel;

    @FXML
    private Button signIn;

    @FXML
    private AnchorPane windowSignIn;

    @FXML

    void initialize(){
        selectAccType.setValue("Pharmacist");
        selectAccType.setItems(choiceList);
    }

    @FXML
    void SignInButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String accT = "1";
        if(selectAccType.getSelectionModel().getSelectedItem().toString().equals("Pharmacist")){
            accT = "1";
        }else if(selectAccType.getSelectionModel().getSelectedItem().toString().equals("Deliveryman")){
            accT = "2";
        }
        String login = null, password = null;
        ResultSet res = au.select("authorization", "ID_accType", accT);
        while(res.next()){
            login = res.getString("login");
            password = res.getString("password");
        }
        if(loginTextField.getText().equals(login)){
            if(passwordTextField.getText().equals(password)){
                if(accT.equals("1")){
                    Stage stage1 = (Stage) signIn.getScene().getWindow();
                    stage1.close();

                     Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PharmacistAccount.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 701, 400);
                    stage.setTitle("Pharmacist");
                    stage.setScene(scene);
                    stage.show();





                }
                else if(accT.equals("2")){
                    Stage stage1 = (Stage) signIn.getScene().getWindow();
                    stage1.close();

                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DeliverymanAccount.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    stage.setTitle("Deliveryman");
                    stage.setScene(scene);
                    stage.show();
                    System.out.println("menu of Deliveryman");
                }
            }else{

                passwordLabel.setText("Invalid password");
            }
        }else{
            lognLabel.setText("Invalid login");
        }
    }

    @FXML
    void clickOnEnterButtonAction(ActionEvent event) {

    }

}





