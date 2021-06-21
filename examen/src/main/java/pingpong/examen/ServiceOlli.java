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

    //caso test 5
    public Usuaria cargaUsuaria(String name){
        Optional<Usuaria> user =  Usuaria.find("user_nom", name).firstResultOptional();
        return user.isPresent() ? user.get(): new Usuaria();
    }
    



}
