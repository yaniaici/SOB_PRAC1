/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Videogame implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "VIDEOGAME_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIDEOGAME_GEN")
    private Long id;

    private String gameName;
    private String console;
    private boolean availability;
    private double weeklyRentalPrice;
    private String description;
    private String gameType;
    private String storeAddress;

    // Constructors, getters, setters, etc.
    // Constructors
    public Videogame() {
        // Default constructor required by JPA
    }

    public Videogame(String gameName, String console, boolean availability,
            double weeklyRentalPrice, String description, String gameType, String storeAddress) {
        this.gameName = gameName;
        this.console = console;
        this.availability = availability;
        this.weeklyRentalPrice = weeklyRentalPrice;
        this.description = description;
        this.gameType = gameType;
        this.storeAddress = storeAddress;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public double getWeeklyRentalPrice() {
        return weeklyRentalPrice;
    }

    public void setWeeklyRentalPrice(double weeklyRentalPrice) {
        this.weeklyRentalPrice = weeklyRentalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Videogame)) {
            return false;
        }
        Videogame other = (Videogame) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "Videogame{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", console='" + console + '\'' +
                ", availability=" + availability +
                ", weeklyRentalPrice=" + weeklyRentalPrice +
                ", description='" + description + '\'' +
                ", gameType='" + gameType + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                '}';
    }
    
}
