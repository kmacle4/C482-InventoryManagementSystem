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
    private static ObservableList<Part> searchedParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Product> searchedProducts = FXCollections.observableArrayList();
    private static int partCount = 6;
    private static int productCount = 3;
    

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
 
        int index = -1;
        for (int i = 0; i < allParts.size(); i++){
            if (allParts.get(i).getId() == partId){
                index = i;
            }
        }
        
        return allParts.get(index);
    }
        
    
    //Lookup Part by Name
    public static ObservableList<Part> lookupPart(String partName){
   
        searchedParts.clear();
        
        for (int i = 0; i < allParts.size(); i++){
            if (allParts.get(i).getName().equals(partName)){
                searchedParts.add(allParts.get(i));
            }
        }
        
        return searchedParts;
    }

    
    //Returns all parts
    public static ObservableList<Part> getAllParts(){
        
        return allParts;
    
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
        
        int index = -1;
        for (int i = 0; i < allProducts.size(); i++){
            if (allProducts.get(i).getId() == productId){
                index = i;
            }
        }
        
        return allProducts.get(index);
    
    }
    
    //Lookup Product by name
    public static ObservableList<Product> lookupProduct(String productName){
       
        searchedProducts.clear();
        
        for (int i = 0; i < allProducts.size(); i++){
            if (allProducts.get(i).getName().equals(productName)){
                searchedProducts.add(allProducts.get(i));
            }
        }
        
        return searchedProducts;
    
    }
  

    //Returns all products
    public static ObservableList<Product> getAllProducts(){
        
        return allProducts;
    
    }
    
    public static int getPartCounter(){
        partCount++;
        return partCount;
    }
    
    public static int getProductCounter(){
        productCount++;
        return productCount;
    }
    

    
}
