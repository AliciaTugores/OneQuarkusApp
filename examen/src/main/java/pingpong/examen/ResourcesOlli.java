package pingpong.examen;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import pingpong.examen.Entidades.*;

@Path("/")
public class ResourcesOlli {

    //caso test 1
    @Inject
    ServiceOlli service;

    //caso test 2
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/wellcome")
    public String wellcome() {
        return "Wellcome Ollivanders!";
    }

    //caso test 3
    @GET
    @Path("/usuaria/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usuaria(@PathParam("nombre") String name){
        Usuaria usuaria = service.cargaUsuaria(name);
        return usuaria.getNombre().isEmpty() ?
                Response.status(Response.Status.NOT_FOUND).build() : 
                Response.status(Response.Status.OK).entity(usuaria).build();
    }

    //caso test 4 y 5
    @POST
    @Path("/ordena")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response ordenar(@Valid Orden orden){
        Orden pedido = service.comanda(orden.getUser().getNombre(), orden.getItem().getNombre());
        return pedido != null ?
                Response.status(Response.Status.CREATED).entity(pedido).build() : 
                Response.status(Response.Status.NOT_FOUND).build();
    }

    //caso test 6
    @GET
    @Path("/pedidos/{usuaria}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orden> list(@PathParam("usuaria") String usuaria){
        return service.cargaOrden(usuaria);
    }

    //caso test 7
    @GET
    @Path("/item/{nombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response item(@PathParam("nombre") String nombre){
        Item item = service.cargaItem(nombre);
        return item.getNombre().isEmpty() ?
                Response.status(Response.Status.NOT_FOUND).build() :
                Response.status(Response.Status.OK).entity(item).build();
    }

}