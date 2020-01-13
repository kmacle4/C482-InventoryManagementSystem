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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Product;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Inventory;
import model.Part;

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
    private TableView<Part> topTableView;

    @FXML
    private TableColumn<Part, Integer> partsIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private Button addProductBtn;

    @FXML
    private TableView<Part> bottomTableView;

    @FXML
    private TableColumn<Part, Integer> associatedIdCol;

    @FXML
    private TableColumn<Part, String> associatedNameCol;

    @FXML
    private TableColumn<Part, Integer> associatedInvCol;

    @FXML
    private TableColumn<Part, Double> associatedPriceCol;

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
    private int counter;
    
    ObservableList<Part> search = FXCollections.observableArrayList();
    ObservableList<Part> associated = FXCollections.observableArrayList();

    @FXML
    void onActionAddProduct(ActionEvent event) {
        Part part = topTableView.getSelectionModel().getSelectedItem();
        associated.add(part);
        bottomTableView.setItems(associated);
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
    void onActionDeleteProduct(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The selected Item will be deleted. Are you sure you want to delete?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK){
            
            associated.remove(bottomTableView.getSelectionModel().getSelectedItem());
            
        }
       
    }

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {
        
        String name = addProductNameText.getText();
        double price = Double.parseDouble(addProductPriceText.getText());
        int stock = Integer.parseInt(addProductInvText.getText());
        int min = Integer.parseInt(addProductMinText.getText());
        int max = Integer.parseInt(addProductMaxText.getText());
        
        Product product = new Product(counter, name, price, stock, min, max);
        try{
            if(min > max){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Minimum is greater than Maximum");
                alert.showAndWait();
            }
            else{
                
                for(Part part : associated) {
                    product.addAssociatedPart(part);
                }
                
                Inventory.addProduct(product);
                
            }
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Missing Data");
            alert.showAndWait();
        }
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    @FXML
    void onActionSearchProduct(ActionEvent event) {
        try {
            try{
                int id = Integer.parseInt(searchProductText.getText());
                search.add(Inventory.lookupPart(id));
            }
            catch(Exception e){
                String name = searchProductText.getText();
                search = Inventory.lookupPart(name);
            }
            if(search.size() == 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Missing Part");
            }
            else {
                topTableView.setItems(search);
                partsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            }
        
        }
        catch(Exception e){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Warning");
           alert.setContentText("Missing Part"); 
        }
        
        //If user searches a blank text field table displays all parts
        if (searchProductText.getText().equals("")){
            topTableView.setItems(Inventory.getAllParts());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Top Table
        partsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Product Table
        associatedIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Update Table
        topTableView.setItems(Inventory.getAllParts());
        bottomTableView.setItems(associated);
        
        counter = Inventory.getProductCounter();
        addProductIdText.setText(Integer.toString(counter));

    }  
    
}
