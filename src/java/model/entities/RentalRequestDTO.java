/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.util.List;


public class RentalRequestDTO {
    
    private List<Long> videogames;
    private int numberOfWeeks;

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
