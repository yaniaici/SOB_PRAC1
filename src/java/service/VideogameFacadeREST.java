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
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import model.entities.Videogame;

/**
 *
 * @author Yani Aici
 */

@Stateless
@Path("/rest/api/v1/game")
public class VideogameFacadeREST extends AbstractFacade<Videogame> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    
    public VideogameFacadeREST() {
        super(Videogame.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVideogames(
            @QueryParam("type") String type,
            @QueryParam("console") String console
    ) {
        try {
            List<Videogame> videoGames;
            
            if (type == null) {
                if (console == null) {
                    if (type != null && console != null) {
                        videoGames = super.findVideogamesByTypeAndConsole(type, console);
                    } else if (type != null) {
                        videoGames = super.findVideogamesByType(type);
                    } else if (console != null) {
                        videoGames = super.findVideogamesByConsole(console);
                    } else {
                        videoGames = super.findAll();
                    }

                    return Response.ok(videoGames).build();
                }
            }

            return Response.status(Response.Status.BAD_REQUEST).entity("Parámetro incorrecto.").build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error en la solicitud").build();
        }
    }
    
    @POST
    @Secured
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVideogame(Videogame videogame) {
        try {
            // Si no existe, crear el videojuego
            super.create(videogame);

            // Devolver el código HTTP 201 Created
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error en la solicitud").build();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
