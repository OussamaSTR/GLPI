package ma.emsi.glpi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role {

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 20)
    private String roleName;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(name = "USER_ROLE")
    private List<User> users = new ArrayList<>();


    @Override
    public String toString() {
        return "Role{id=" + id + ", roleName='" + roleName + "'}";
    }

}
