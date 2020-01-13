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
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Inventory;
import model.Part;

/**
 * FXML Controller class
 *
 * @author kirkmaclean
 */
public class AddPartMenuController implements Initializable {
    
    @FXML
    private RadioButton inHouseRBtn;

    @FXML
    private RadioButton outsourcedRBtn;

    @FXML
    private Label addPartId;

    @FXML
    private TextField addPartIDText;

    @FXML
    private TextField addPartNameText;

    @FXML
    private TextField addPartInvText;

    @FXML
    private TextField addPartPriceText;

    @FXML
    private TextField addPartMinText;

    @FXML
    private TextField addPartMaxText;

    @FXML
    private Label addPartLabelChange;

    @FXML
    private TextField addPartChangeText;
    
    Stage stage;
    Parent scene;
    private boolean isInHouse;
    private int id;
    private int counter;
    private ToggleGroup radioButtons;
    

    //Notifies the user they are canceling the submission
    //Verifies Cancel
    @FXML
    void onActionCancelAddPart(ActionEvent event) throws IOException {
        
        System.out.println("Cancel Add Part");
        
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
    void onActionSaveAddPart(ActionEvent event) throws IOException {
        
        System.out.println("Save Part");
        
        String name = addPartNameText.getText();
        double price = Double.parseDouble(addPartPriceText.getText());
        int stock = Integer.parseInt(addPartInvText.getText());
        int min = Integer.parseInt(addPartMinText.getText());
        int max = Integer.parseInt(addPartMaxText.getText());


        try {
            
            if(min > max){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("WARNING");
                    alert.setContentText("Minimum is greater than maximum");
                    alert.showAndWait();
                    return;
                } //if min max
                else{
                    if (isInHouse){

                        int machineId = Integer.parseInt(addPartChangeText.getText());

                        InHouse inhousePart = new InHouse(counter, name, price, stock, min, max, machineId);
                       
                        Inventory.addPart(inhousePart);

                    } //if inhouse
                
                    else{

                        String companyName = addPartChangeText.getText();

                        Outsourced outPart = new Outsourced (counter, name, price, stock, min, max, companyName);
                        
                        Inventory.addPart(outPart);

                    } //else outsourced
                } //else not min max check
            } //end try
            catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialogue");
                alert.setContentText("Please enter a valid value for each text field!");
                alert.showAndWait();
                return;
            }

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();

            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));

            stage.setScene(new Scene(scene));

            stage.show();


        }
    
    /*@FXML
    void onActionSetToInHouse(ActionEvent event) {
        
        
        System.out.println("In House selected");
        
        
        //If InHouse Radio button is selected
        //set isInHouse to true and change label
        if (inHouseRBtn.isSelected()){
            isInHouse = true;
            addPartLabelChange.setText("Machine ID");
            outsourcedRBtn.setSelected(false);
        }
        
    }
    @FXML
    void onActionSetToOutscourced(ActionEvent event) {
        
        System.out.println("Outsourced Selected");
        
        //If outsourced Radio button is selected
        //set isInHouse to false and change label
        if (outsourcedRBtn.isSelected()){
            isInHouse = false;
            addPartLabelChange.setText("Company Name");
            inHouseRBtn.setSelected(false);
        }
    }
    */
    
    public void radioButtonSelection(){
    
        if(this.radioButtons.getSelectedToggle().equals(this.inHouseRBtn)){
            addPartLabelChange.setText("Machine ID");
            isInHouse = true;
            System.out.println("In House Selected");
        }  
        
        if(this.radioButtons.getSelectedToggle().equals(this.outsourcedRBtn)){
            addPartLabelChange.setText("Company Name");
            isInHouse = false;
            System.out.println("Outsourced Selected");
        }   
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Toggle Group
        radioButtons = new ToggleGroup();
        this.inHouseRBtn.setToggleGroup(radioButtons);
        this.outsourcedRBtn.setToggleGroup(radioButtons);
        
        //Selects InHouse as defualt and sets value to true
        this.inHouseRBtn.setSelected(true);
        isInHouse = true;
        
        //Autogenerates Part ID
        counter = Inventory.getPartCounter();
        addPartIDText.setText(Integer.toString(counter));

    }    
    
}
