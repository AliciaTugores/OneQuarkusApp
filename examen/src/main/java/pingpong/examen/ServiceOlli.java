package pingpong.examen;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import javax.transaction.Transactional;
import javax.persistence.PersistenceContext;

import javax.enterprise.context.ApplicationScoped;

import pingpong.examen.Item;
import pingpong.examen.Usuaria;

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



}
