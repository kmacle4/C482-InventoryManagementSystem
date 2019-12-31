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
    private int id;
    Part part;
    
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
        
        
        String name = modifyNameText.getText();
        double price = Double.parseDouble(modifyPriceText.getText());
        int stock = Integer.parseInt(modifyInvText.getText());
        int min = Integer.parseInt(modifyMinText.getText());
        int max = Integer.parseInt(modifyMaxText.getText());
        
        try{
            if(isInHouse){

                int machineId = Integer.parseInt(modifyChangeText.getText());

                InHouse inHouseMod = new InHouse(id, name, price, stock, min, max, machineId);
                inHouseMod.setId(id);
                inHouseMod.setName(name);
                inHouseMod.setPrice(price);
                inHouseMod.setStock(stock);
                inHouseMod.setMin(min);
                inHouseMod.setMax(max);
                inHouseMod.setMachineId(machineId);
                Inventory.updatePart(id, inHouseMod);
            }
            else {
            
                String companyName = modifyChangeText.getText();

                Outsourced outsourcedMod = new Outsourced(id, name, price, stock, min, max, String.valueOf(companyName));
                outsourcedMod.setId(id);
                outsourcedMod.setName(name);
                outsourcedMod.setPrice(price);
                outsourcedMod.setStock(stock);
                outsourcedMod.setMin(min);
                outsourcedMod.setMax(max);
                outsourcedMod.setCompanyName(String.valueOf(companyName));
                Inventory.updatePart(id, outsourcedMod);
            
            
            }
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialogue");
            alert.setContentText("Please enter a valid value for each text field!");
            alert.showAndWait();
        }

        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
        
        scene = loader.load();
        
        Scene view = new Scene(scene);
        
        stage.setScene(view);
        
        stage.show();
        

    }
    
    @FXML
    void onActionModInHouse(ActionEvent event) {

        System.out.println("In House selected");
        
        //If InHouse Radio button is selected
        //set isInHouse to true and change label
        if (inHouseModRBtn.isSelected()){
            isInHouse = true;
            modifyChangeLabel.setText("Machine ID");
            outsourceModRBtn.setSelected(false);
        }
    }

    @FXML
    void onActionModOutsourced(ActionEvent event) {

        System.out.println("Outsourced selected");
        
        //If InHouse Radio button is selected
        //set isInHouse to true and change label
        if (outsourceModRBtn.isSelected()){
            isInHouse = false;
            modifyChangeLabel.setText("Company Name");
            inHouseModRBtn.setSelected(false);
        }
        
    }
    
    public void setFields(Part part){
        this.part = part;
        
        String name = null;
        double price = 0.00;
        int stock = 0;
        int min = 0;
        int max = 0;
        int machineId = 0;
        String companyName = null;
        
        InHouse inhousePart = new InHouse(id, name, price, stock, min, max, machineId);
        Outsourced outPart = new Outsourced (id, name, price, stock, min, max, companyName);
        
        
        modifyIdText.setText(Integer.toString(part.getId()));
        modifyNameText.setText(part.getName());
        modifyInvText.setText(Integer.toString(part.getStock()));
        modifyPriceText.setText(Double.toString(part.getPrice()));
        modifyMinText.setText(Integer.toString(part.getMin()));
        modifyMaxText.setText(Integer.toString(part.getMax()));
        
        
        /*if(isInHouse){
            inHouseModRBtn.setSelected(true);
            modifyChangeText.setText(Integer.toString(inhousePart.getMachineId()));
        }
        else{
            outsourceModRBtn.setSelected(true);
            modifyChangeText.setText(outPart.getCompanyName());
        }
*/
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
}
