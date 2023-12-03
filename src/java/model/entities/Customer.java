package model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String dni;

    private String name;
    private String address;
    private String password;
    private String phoneNumber;

    @OneToOne(mappedBy = "customer")
    @JoinColumn(name="RENTAL_ID")
    private Rental rental;

    public Customer() {

    }

    public Customer(String dni, String name, String address, String password, String phoneNumber) {
        this.dni = dni;
        this.name = name;
        this.address = address;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}