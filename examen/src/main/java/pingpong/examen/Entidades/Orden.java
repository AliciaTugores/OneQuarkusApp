package pingpong.examen;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;


@Entity
@Table(name="t_ordenes")
public class Orden extends PanacheEntityBase{

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ord_id")
    public Long id;

    @OneToOne
    @JoinColumn(name="ord_user")
    public Usuaria user;

    @OneToOne
    @JoinColumn(name="ord_item")
    public Item item;

    public Orden(){}

    public Orden(Usuaria user, Item item) {
        this.user = user;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuaria getUser() {
        return user;
    }

    public void setUser(Usuaria user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}