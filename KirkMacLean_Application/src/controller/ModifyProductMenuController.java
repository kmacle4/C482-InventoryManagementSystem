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
public class ModifyProductMenuController implements Initializable {

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
    private TextField modProductIdText;

    @FXML
    private TextField modProductNameText;

    @FXML
    private TextField modProductInvText;

    @FXML
    private TextField modProductPriceText;

    @FXML
    private TextField modProductMinText;

    @FXML
    private TextField modProductMaxText;
    
    Stage stage;
    Parent scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionSearchProduct(ActionEvent event) {
    }

    @FXML
    private void onActionAddProduct(ActionEvent event) {
    }

    @FXML
    private void onActionDeleteProduct(ActionEvent event) {
    }

    @FXML
    private void onActionSaveProduct(ActionEvent event) {
    }

    @FXML
    private void onActionCancelProduct(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all values. Are you sure you want to cancel?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK){
            
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            
            stage.setScene(new Scene(scene));
            
            stage.show();
        }
    }
    
}
