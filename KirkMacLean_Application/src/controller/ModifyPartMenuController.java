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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

/**
 * FXML Controller class
 *
 * @author kirkmaclean
 */
public class ModifyPartMenuController implements Initializable {
    
    @FXML
    private RadioButton inHouseModRBtn;

    @FXML
    private RadioButton outsourceModRBtn;

    @FXML
    private TextField modifyIdText;

    @FXML
    private TextField modifyNameText;

    @FXML
    private TextField modifyInvText;

    @FXML
    private TextField modifyPriceText;

    @FXML
    private TextField modifyMinText;

    @FXML
    private TextField modifyMaxText;

    @FXML
    private Label modifyChangeLabel;

    @FXML
    private TextField modifyChangeText;

    Stage stage;
    Parent scene;
    private boolean isInHouse;
    private Part part;
    private static int index;
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    void onActionCancelMod(ActionEvent event) throws IOException {
        
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
    void onActionSaveMod(ActionEvent event) throws IOException {
        
       
       int id = Integer.parseInt(modifyIdText.getText());
       String name = modifyNameText.getText();
       double price = Double.parseDouble(modifyPriceText.getText());
       int stock = Integer.parseInt(modifyInvText.getText());
       int min = Integer.parseInt(modifyMinText.getText());
       int max = Integer.parseInt(modifyMaxText.getText());
       
       if(min > max){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Error");
           alert.setContentText("Minimum is greater than Maximum");
           alert.showAndWait();
       }
       else{
           try{
               if(isInHouse){
                   int machineId = Integer.parseInt(modifyChangeLabel.getText());
                   
                   InHouse part = new InHouse(id, name, price, stock, min, max, machineId);
                   
                   Inventory.updatePart(index, part);
                   
                   //SUCCESSFUL
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("SUCCESSFUL");
                   alert.setContentText("Modification Successful");
                   alert.showAndWait();
                   
                   stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
        
                    scene = loader.load();

                    Scene view = new Scene(scene);

                    stage.setScene(view);

                    stage.show();
               }
               else{
                   String companyName = modifyChangeLabel.getText();
                   
                   Outsourced part = new Outsourced(id, name, price, stock, min, max, companyName);
                   
                   Inventory.updatePart(index, part);
                   
                   //SUCCESSFUL
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("SUCCESSFUL");
                   alert.setContentText("Modification Successful");
                   alert.showAndWait();
                   
                   stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
        
                    scene = loader.load();

                    Scene view = new Scene(scene);

                    stage.setScene(view);

                    stage.show();
               }
           }
           catch(NumberFormatException e){
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setContentText("Error Modifying Part");
               alert.showAndWait();
           }
       }
    }
    
    @FXML
    void onActionModInHouse(ActionEvent event) {

        System.out.println("In House selected");
        
        isInHouse = true;
        modifyChangeLabel.setText("Machine ID");
        outsourceModRBtn.setSelected(false);
        
    }

    @FXML
    void onActionModOutsourced(ActionEvent event) {

        System.out.println("Outsourced selected");
        
        isInHouse = false;
        modifyChangeLabel.setText("Company Name");
        inHouseModRBtn.setSelected(false);
        
        
    }
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        part = MainMenuController.selectedPart;
        index = MainMenuController.selectedPartIndex;
        
        modifyIdText.setText(Integer.toString(part.getId()));
        modifyNameText.setText(part.getName());
        modifyInvText.setText(Integer.toString(part.getStock()));
        modifyPriceText.setText(Double.toString(part.getPrice()));
        modifyMinText.setText(Integer.toString(part.getMin()));
        modifyMaxText.setText(Integer.toString(part.getMax()));
        
        if (part instanceof InHouse){
            modifyChangeLabel.setText("Machine ID");
            modifyChangeText.setText(Integer.toString(((InHouse)Inventory.getAllParts().get(index)).getMachineId()));
            inHouseModRBtn.setSelected(true);
            isInHouse = true;
        }
        else{
            modifyChangeLabel.setText("Company Name");
            modifyChangeText.setText((((Outsourced)Inventory.getAllParts().get(index)).getCompanyName()));
            outsourceModRBtn.setSelected(true);
            isInHouse = false;
        }
        
    }    
    
}
