package pl.milej.michal.wordofreaders.user.profile.photo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.milej.michal.wordofreaders.user.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ProfilePhoto {

    @GeneratedValue
    @Id
    Long id;

    @Column
    private String name;

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "profilePhoto")
    private Set<User> users;

    public ProfilePhoto(String name, String location) {
        this.name = name;
        this.location = location;
    }
}
