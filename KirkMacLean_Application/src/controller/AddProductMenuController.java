/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kirkmaclean
 */
public class AddProductMenuController implements Initializable {

    @FXML
    private Button searchProductBtn;

    @FXML
    private TextField searchProductText;

    @FXML
    private TableView<?> addProductTableView;

    @FXML
    private Button addProductBtn;

    @FXML
    private TableView<?> deleteProductTableView;

    @FXML
    private Button deleteProductBtn;

    @FXML
    private Button saveProductBtn;

    @FXML
    private Button cancelProduct;

    @FXML
    private TextField addProductIdText;

    @FXML
    private TextField addProductNameText;

    @FXML
    private TextField addProductInvText;

    @FXML
    private TextField addProductPriceText;

    @FXML
    private TextField addProductMinText;

    @FXML
    private TextField addProductMaxText;
    
    Stage stage;
    Parent scene;

    @FXML
    void onActionAddProduct(ActionEvent event) {

    }

    @FXML
    void onActionCancelProduct(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all values. Are you sure you want to cancel?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK){
            
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            
            stage.setScene(new Scene(scene));
            
            stage.show();
        }

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

    }

    @FXML
    void onActionSaveProduct(ActionEvent event) {

    }

    @FXML
    void onActionSearchProduct(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        

    }  
    
}
