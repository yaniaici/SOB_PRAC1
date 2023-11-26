/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.entities.Rental;
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
            Rental response = processRental(rentalRequest);
 
            // Retorna la respuesta con el código 201 Created
            return Response.status(Response.Status.CREATED).entity(response).build();
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

        return rental;
    }
        
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
}
 