package pingpong.examen;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.transaction.Transactional;
import javax.persistence.PersistenceContext;

import javax.enterprise.context.ApplicationScoped;

import pingpong.examen.Entidades.*;

@ApplicationScoped
public class ServiceOlli {

    //caso test 5 y 6
    public Usuaria cargaUsuaria(String name){
        Optional<Usuaria> user =  Usuaria.find("user_nom", name).firstResultOptional();
        return user.isPresent() ? user.get() : new Usuaria("", 0);
    }
    
    //caso test 7 y 8
    public Item cargaItem(String name){
        Optional<Item> item = Item.find("item_nom", name).firstResultOptional();
        return item.isPresent() ? item.get() : new Item("", 0, "");
    }

    //caso test 9 y 10
    public List<Orden> cargaOrden(String name){
        Optional<Orden> orden = Orden.find("ord_user", name).firstResultOptional();
        List<Orden> pedidos = new ArrayList<Orden>();
        if (orden.isPresent()){
            pedidos.add(orden.get());
            return pedidos;
        }
        else{
            return pedidos;
        }
    }

    //caso test 11, 12, 13 y 14
    @Transactional
    public Orden comanda(String name_user, String name_item){
        Optional<Usuaria> user = Usuaria.find("user_nom", name_user).firstResultOptional();
        Optional<Item> item = Item.find("item_nom", name_item).firstResultOptional();

        if (item.isPresent() && user.isPresent() && user.get().getDestreza() >= item.get().getQuality()){
            Orden pedido = new Orden(user.get(), item.get());
            pedido.persist();
            return pedido;
        }
        else{
            return null;
        }
       
    }
    
    //caso test 15, 16 y 17
    @Transactional
    public List<Orden> comandaMultiple(String name_user, List<String> items){
        List<Orden> ordenes = new ArrayList<Orden>();
        for (String item : items) {
            Orden pedido = comanda(name_user, item);
            if (pedido != null){
                ordenes.add(pedido);
            }
        }
        return ordenes;
    }
}