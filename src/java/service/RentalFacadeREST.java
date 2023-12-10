/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import model.entities.Customer;
import model.entities.Rental;
import model.entities.RentalRequestDTO;
import model.entities.Videogame;


@Stateless
@Path("/rest/api/v1/rental")
public class RentalFacadeREST extends AbstractFacade<Rental> {
    
    @PersistenceContext(unitName = "Homework1PU")   
    private EntityManager em;

    public RentalFacadeREST() {
        super(Rental.class);
    }
    
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response rentVideogames(RentalRequestDTO rentalRequest) {
        try {
            // Lógica para procesar el lloguer y obtener la respuesta
            System.out.println(rentalRequest.toString());
            Rental response = processRental(rentalRequest);
            super.create(response);
 
            // Retorna la respuesta con el código 201 Created
            return Response.status(Response.Status.CREATED).entity(response).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("Error en la solicitud de lloguer").build();
        }
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRentalById(@PathParam("id") Long id) {
        try {
            Rental rental = em.find(Rental.class, id);

            if (rental != null) {
                return Response.ok(rental).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Rental not found").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error en la solicitud de lloguer").build();
        }
    }
    
    
    private Rental processRental(RentalRequestDTO rentalRequest) throws Exception {
        // Implementa la lógica para procesar el lloguer y retorna la respuesta

        // Aquí se asume que ya tienes una clase RentalResponseDTO
        Rental rental = new Rental();

        List<Long> videogameIds = rentalRequest.getVideogames();

        int numberOfWeeks = rentalRequest.getNumberOfWeeks();

        double totalPrice = 0.0;

        for (Long videogameId : videogameIds) {
            Videogame videogame = em.find(Videogame.class, videogameId);

            if (videogame != null && videogame.isAvailability()) {
                totalPrice += videogame.getWeeklyRentalPrice();

                // Update al precio total de los juegos
                videogame.setAvailability(false);
                em.merge(videogame);
            } else {
                throw new Exception("Error en la solicitud");
            }
        }
        
        rental.setTotalPrice(totalPrice * numberOfWeeks);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, numberOfWeeks);
        Date rDate = calendar.getTime();
        rental.setReturnDate(rDate);
        
        if (em.find(Customer.class, rentalRequest.getCustomerDNI()) == null) {
            throw new NoSuchElementException("Customer no encontrado");
        }

        if (checkRentalFromCustomer(rentalRequest.getCustomerDNI())) {
            throw new Exception("Ya dispones de un alquiler!!!");
        }
        rental.setCustomer(em.find(Customer.class, rentalRequest.getCustomerDNI()));

        return rental;
    }
    
        private boolean checkRentalFromCustomer(String dni) {
        List<Rental> rentals = super.findAll();
        for(Rental r : rentals){
            if(r.getCustomer().getDni().equals(dni))
                return true;
        }
        return false;
    }
        
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
}
 