/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import model.entities.Customer;
import model.entities.CustomerDTO;

/**
 *
 * @author Yani Aici
 */
@Stateless
@Path("/rest/api/v1/customers")
public class CustomerFacadeREST extends AbstractFacade<Customer> {
    
    @PersistenceContext(unitName = "Homework1PU")
    protected EntityManager em;

    public CustomerFacadeREST() {
        super(Customer.class);
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllUsers() {
        List<Customer> customers = super.findAll();
        List<CustomerDTO> customersDTO = new ArrayList<>();

        if (customers.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        
        for (Customer customer : customers) {
            customersDTO.add(buildCustomerDTO(customer));
        }
        
        return Response.status(Response.Status.OK).entity(customersDTO).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchById(
            @PathParam("id") String id
    ) {
        Customer c = em.find(Customer.class, id);
        if (c == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(buildCustomerDTO(c)).build();
    }
    
    @PUT
    @Path("/{id}")
    @Secured
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response edditCustomer(
            @PathParam("id") String id,
            Customer cNew
    ) {

        if(cNew == null)
            return Response.status(Response.Status.BAD_REQUEST).entity("Error en la solicitud.").build();
        Customer c = em.find(Customer.class, id);
        if (c == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Cliente no encontrado.").build();
        }

        if (cNew.getAddress() != null) {
            c.setAddress(cNew.getAddress());
        }

        if (cNew.getName() != null) {
            c.setName(cNew.getName());
        }

        if (cNew.getPassword() != null) {
            c.setPassword(cNew.getPassword());
        }

        if (cNew.getPhoneNumber() != null) //Se podria añadir un control para que el telefono tenga 9 numeros y sea real
        {
            c.setPhoneNumber(cNew.getPhoneNumber());
        }

        super.edit(c);
        return Response.status(Response.Status.OK).entity(buildCustomerDTO(c)).build();
    }
    
        private CustomerDTO buildCustomerDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setAddress(customer.getAddress());
        dto.setName(customer.getName());
        dto.setPhoneNumber(customer.getPhoneNumber());
        return dto;
    }
        
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    
    
}
