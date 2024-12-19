package pl.milej.michal.worldofreaders.user.profile.photo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.milej.michal.worldofreaders.user.User;

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

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "profilePhoto")
    private Set<User> users;

    public ProfilePhoto(String location) {
        this.location = location;
    }
}
