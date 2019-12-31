/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author kirkmaclean
 */
public class Inventory{
    
    //Fields
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int partCounter = 0;
    private static int productCounter = 0;
    //Methods
    
    /*  All
        Parts
        Methods
    */
    
    //Add Part
    public static void addPart(Part newPart){
        
        allParts.add(newPart);
        
    }
    
    //Delete Part
    public static void deletePart(Part selectedPart){
        
        allParts.remove(selectedPart);
       
    }
    
    //Update Part
    public static void updatePart(int index, Part selectedPart){
        
        allParts.set(index, selectedPart);
        
    }
    
    //Lookup Part by ID
    public static Part lookupPart(int partId){
 
        return null;
    }
        
    
    //Lookup Part by Name
    public static ObservableList<Part> lookupPart(String partName){
   
        return null;
   
    }

    
    //Returns all parts
    public static ObservableList<Part> getAllParts(){
        
        return allParts;
    
    }
    
    //Returns ID numbers for Parts
    public static int getPartCounter(){
        
        partCounter++;
        return partCounter;
        
    }
    
    /*  All
        Products
        Methods
    */
    
    
    //Add product
    public static void addProduct(Product newProduct){
        
        allProducts.add(newProduct);
        
    }
    
     //Delete Product
    public static void deleteProduct(Product selectedProduct){
        
        allProducts.remove(selectedProduct);
        
    }
    
     //Update Product
    public static void updateProduct(int index, Product selectedProduct){
        
        allProducts.set(index, selectedProduct);
        
    }
    
    //Lookup Product by ID
    public static Product lookupProduct(int productId){
        
        return null;
    
    }
    
    //Lookup Product by name
    public static ObservableList<Product> lookupProduct(String productName){
       
        return null;
    
    }
  

    //Returns all products
    public static ObservableList<Product> getAllProducts(){
        
        return allProducts;
    
    }
    
    //Returns ID numbers for Products
    public static int getProductCounter(){
        
        productCounter++;
        return productCounter;
        
    }

    
}
