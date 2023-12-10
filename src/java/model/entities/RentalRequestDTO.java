/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.util.List;


public class RentalRequestDTO {

    @Override
    public String toString() {
        return "RentalRequestDTO{" + "videogames=" + videogames + ", numberOfWeeks=" + numberOfWeeks + ", customerDNI=" + customerDNI + '}';
    }
    
    
    private List<Long> videogames;

    private int numberOfWeeks;
    
    private String customerDNI;

    public String getCustomerDNI() {
        return customerDNI;
    }

    public void setCustomerDNI(String customerDNI) {
        this.customerDNI = customerDNI;
    }

    public int getNumberOfWeeks() {
        return numberOfWeeks;
    }

    public void setNumberOfWeeks(int numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }

    public List<Long> getVideogames() {
        return videogames;
    }

    public void setVideogames(List<Long> videogames) {
        this.videogames = videogames;
    }
    
    

    
}
