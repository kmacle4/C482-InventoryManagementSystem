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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

/**
 * FXML Controller class
 *
 * @author kirkmaclean
 */
public class MainMenuController implements Initializable {

    @FXML
    private Label inventoryManagementSystemLabel;
    @FXML
    private Button searchPartBtn;
    @FXML
    private TextField partSearchText;
    @FXML
    private TableView<Part> partsTableView;
    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partInvCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private Button addPartBtn;
    @FXML
    private Button modifyPartBtn;
    @FXML
    private Button deletePartBtn;
    @FXML
    private Button searchProductBtn;
    @FXML
    private TextField productSearchText;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, Integer> productIdCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Integer> productInvCol;
    @FXML
    private TableColumn<Product, Double> productPriceCol;
    @FXML
    private Button addProductBtn;
    @FXML
    private Button modifyProductBtn;
    @FXML
    private Button deleteProductBtn;
    @FXML
    private Button exitAppButton;
    
    Stage stage;
    Parent scene;
    public static Part selectedPart;
    public static Product selectedProduct;
    public static int selectedPartIndex;
    public static int selectedProductIndex;
    private ObservableList<Part> searchParts = FXCollections.observableArrayList();
    private ObservableList<Product> searchProducts = FXCollections.observableArrayList();
    
    /*
        Parts
    */
    
    @FXML
    private void onActionSearchPart(ActionEvent event) {
    
        System.out.println("Search Part");
        
        searchParts.clear();
        
        try {
            try{
                int id = Integer.parseInt(partSearchText.getText());
                searchParts.add(Inventory.lookupPart(id));
            }
            catch(Exception e){
                String name = partSearchText.getText();
                searchParts = Inventory.lookupPart(name);
            }
            if(searchParts.size() == 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Missing Part");
            }
            else {
                partsTableView.setItems(searchParts);
                partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
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
    }
        
    

    //When Clicked switch to Add Part screen
    @FXML
    private void onActionAddPart(ActionEvent event) throws IOException {
        
        System.out.println("Add Part");
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartMenu.fxml"));
        
        stage.setScene(new Scene(scene));
        
        stage.show();
        
       
    }

    //When Clicked switch to Modify Part screen
    @FXML
    private void onActionModifyPart(ActionEvent event) throws IOException {
        
        System.out.println("Modify Part");
        
        selectedPart = partsTableView.getSelectionModel().getSelectedItem();
        selectedPartIndex = Inventory.getAllParts().indexOf(selectedPart);
        
        if(selectedPart != null){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyPartMenu.fxml"));

            scene = loader.load();

            Scene view = new Scene(scene);

            stage.setScene(view);

            stage.show();
        }
        else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Please select Part");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void onActionDeletePart(ActionEvent event) {
        
       Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
       
       Inventory.deletePart(selectedPart);
       
       System.out.println("Delete Part");
         
    }
        
    /*
        Products
    */   

    @FXML
    private void onActionSearchProduct(ActionEvent event) {
        System.out.println("Search Product");
        
        searchProducts.clear();
        
        try {
            try{
                int id = Integer.parseInt(productSearchText.getText());
                searchProducts.add(Inventory.lookupProduct(id));
            }
            catch(Exception e){
                String name = productSearchText.getText();
                searchProducts = Inventory.lookupProduct(name);
            }
            if(searchProducts.size() == 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Missing Part");
            }
            else {
                productTableView.setItems(searchProducts);
                productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            }
        
        }
        catch(Exception e){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Warning");
           alert.setContentText("Missing Part"); 
        }
    }

    
    
    @FXML
    private void onActionAddProduct(ActionEvent event) throws IOException {
        
        System.out.println("Add Product");
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductMenu.fxml"));
        
        stage.setScene(new Scene(scene));
        
        stage.show();
        
    }

    @FXML
    private void onActionModifyProduct(ActionEvent event) throws IOException {
        
        System.out.println("Modify Product");
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyProductMenu.fxml"));
        
        stage.setScene(new Scene(scene));
        
        stage.show();
    }

    @FXML
    private void onActionDeleteProduct(ActionEvent event) {
        
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
       
        Inventory.deleteProduct(selectedProduct);
       
        System.out.println("Delete Product");
    }

    //When Exit Button is clicked App exit app
    @FXML
    private void onActionExit(ActionEvent event) {
        
        System.out.println("Exit");
        
        System.exit(0);
        
    }
    
    //Update table views
    //Parts table
    public void updatePartsTable(){
        partsTableView.setItems(Inventory.getAllParts());
    }
    
    //Products table
    public void updateProductsTable(){
        productTableView.setItems(Inventory.getAllProducts());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Part Table
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Product Table
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Update Table
        updatePartsTable();
        updateProductsTable();
    }
    
}
