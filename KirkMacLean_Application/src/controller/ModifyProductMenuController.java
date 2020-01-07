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
public class ModifyProductMenuController implements Initializable {

    @FXML
    private Button searchProductBtn;

    @FXML
    private TextField searchProductText;

    @FXML
    private TableView<Part> topTableView;

    @FXML
    private TableColumn<Part, Integer> topIdCol;

    @FXML
    private TableColumn<Part, String> topNameCol;

    @FXML
    private TableColumn<Part, Integer> topInvCol;

    @FXML
    private TableColumn<Part, Double> topPriceCol;

    @FXML
    private Button addProductBtn;

    @FXML
    private TableView<Part> bottomTableView;

    @FXML
    private TableColumn<Part, Integer> bottomIdCol;

    @FXML
    private TableColumn<Part, String> bottomNameCol;

    @FXML
    private TableColumn<Part, Integer> bottomInvCol;

    @FXML
    private TableColumn<Part, Double> bottomPriceCol;

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
    
    ObservableList<Part> search = FXCollections.observableArrayList();
    ObservableList<Part> associated = FXCollections.observableArrayList();
    
    Stage stage;
    Parent scene;
    
    private Product product;
    private static int index;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        product = MainMenuController.selectedProduct;
        index = MainMenuController.selectedProductIndex;
        
        
        modProductIdText.setText(Integer.toString(product.getId()));
        modProductNameText.setText(product.getName());
        modProductInvText.setText(Integer.toString(product.getStock()));
        modProductPriceText.setText(Double.toString(product.getPrice()));
        modProductMinText.setText(Integer.toString(product.getMin()));
        modProductMaxText.setText(Integer.toString(product.getMax()));
    }    

    @FXML
    private void onActionSearchProduct(ActionEvent event) {
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
                topIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                topNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                topInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                topPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            }
        
        }
        catch(Exception e){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Warning");
           alert.setContentText("Missing Part"); 
        }
    }

    @FXML
    private void onActionAddProduct(ActionEvent event) {
        associated.add(topTableView.getSelectionModel().getSelectedItem());
        Part part = topTableView.getSelectionModel().getSelectedItem();
        bottomTableView.setItems(associated);
        bottomIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bottomNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        bottomInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bottomPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    private void onActionDeleteProduct(ActionEvent event) {
        associated.remove(bottomTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void onActionSaveProduct(ActionEvent event) throws IOException {
        int id = Integer.parseInt(modProductIdText.getText());
        String name = modProductNameText.getText();
        int stock = Integer.parseInt(modProductInvText.getText());
        double price = Double.parseDouble(modProductPriceText.getText());
        int min = Integer.parseInt(modProductMinText.getText());
        int max = Integer.parseInt(modProductMaxText.getText());
        
        try{
            if(min > max){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Minimum is greater than maximum");
                alert.showAndWait();
            }
            else{
                Product product = new Product(id, name, price, stock, min, max);
                
                Inventory.updateProduct(index, product);
            }
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setContentText("Missing Data");
            alert.showAndWait();
        }
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
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
