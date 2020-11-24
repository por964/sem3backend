package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Favourite implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<User> users;



    @Id
    private String name;
    
    
    public Favourite() {
    }

    public Favourite(String name) {
        this.name = name;
    }
    
    public List<User> getUsers() {
        return users;
    }
        
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
