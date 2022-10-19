package pl.milej.michal.wordofreaders.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.milej.michal.wordofreaders.comment.Comment;
import pl.milej.michal.wordofreaders.user.profile.photo.ProfilePhoto;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "application_user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String hashedPassword;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "profile_photo_id", nullable = false)
    private ProfilePhoto profilePhoto;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Column(nullable = false)
    private Boolean banned = false;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;
}
