/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kirkmaclean_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;
import model.Outsourced;

/**
 *
 * @author kirkmaclean
 */
public class KirkMacLean_Application extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        //InHouse Sample Data
        Part inhousePart1 = new InHouse(1, "Part 1", 1.99, 15, 1, 150, 136789);
        Part inhousePart2 = new InHouse(2, "Part 2", 2.99, 10, 1, 100, 875372);
        Part inhousePart3 = new InHouse(3, "Part 3", 3.99, 400, 50, 1000, 993883);
        
        //Outsourced Sample Data
        Part outsourcedPart1 = new Outsourced(4, "Part 4", 4.99, 7, 1, 10, "Schneider");
        Part outsourcedPart2 = new Outsourced(5, "Part 5", 5.99, 150, 1, 1000, "Wilson");
        Part outsourcedPart3 = new Outsourced(6, "Part 6", 6.99, 15, 10, 20, "Marco");
        
        Product product1 = new Product(1, "Product 1", 10.01, 4, 3, 5);
        product1.addAssociatedPart(inhousePart1);
        
        
        Product product2 = new Product(2, "Product 2", 20.01, 5, 4, 6);
        product2.addAssociatedPart(inhousePart2);
        
        
        Product product3 = new Product(3, "Product 3", 30.01, 6, 5, 7);
        product3.addAssociatedPart(outsourcedPart3);
        
        
        Inventory.addPart(inhousePart1);
        Inventory.addPart(inhousePart2);
        Inventory.addPart(inhousePart3);
        Inventory.addPart(outsourcedPart1);
        Inventory.addPart(outsourcedPart2);
        Inventory.addPart(outsourcedPart3);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        
        launch(args);
    }
    
}
