package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Artist;
import lt.vu.persistence.ArtistDAO;
import lt.vu.rest.contracts.ArtistDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/artist")
public class ArtistsController {
    @Inject
    @Getter
    @Setter
    ArtistDAO artistDAO;

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Artist> songs = artistDAO.loadAll();
        List<ArtistDTO> artistsContract = songs.stream().map(ArtistDTO::new).collect(Collectors.toList());
        return Response.ok(artistsContract).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Artist artist = artistDAO.findOne(id);
        if (artist == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ArtistDTO artistContract = new ArtistDTO(artist);
        return Response.ok(artistContract).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer songId, ArtistDTO artistData) {
        try {
            Artist existingArtist = artistDAO.findOne(songId);
            if (existingArtist == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingArtist.setName(artistData.getName());
            existingArtist.setYear(artistData.getYear());

            artistDAO.update(existingArtist);
            artistDAO.flush();
            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ArtistDTO artistData) {
        Artist newArtist = new Artist();
        newArtist.setName(artistData.getName());
        newArtist.setYear(artistData.getYear());

        artistDAO.persist(newArtist);
        return Response.ok().build();
    }
}
