package ma.emsi.glpi.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name ="Username",unique = true, nullable = false)
    private String username;

    @Setter
    @Column(name = "Password",unique = true,nullable = false)
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String password;


    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();


    @Override
    public String toString() {
        return "User{id=" + userId + ", roles :" + roles + ", username='" + username  + "'}";
    }


}
