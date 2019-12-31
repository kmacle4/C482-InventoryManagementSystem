/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author kirkmaclean
 */
public class InHouse extends Part{
    
    //Fields
    private int machineId;
    
    //Constructor
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    
    //Getters
    public int getMachineId() {
        return this.machineId;
    }

    
    //Setters
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
    
    
    
}
