package pl.milej.michal.wordofreaders.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.milej.michal.wordofreaders.book.score.UserScore;
import pl.milej.michal.wordofreaders.review.Review;
import pl.milej.michal.wordofreaders.review.reaction.Reaction;
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

    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "profile_photo_id", nullable = false)
    private ProfilePhoto profilePhoto;

    @Column(nullable = false)
    private Boolean banned = false;

    private Boolean activated = true;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;

    @OneToMany(mappedBy = "user")
    private Set<Reaction> reactions;

    @OneToMany(mappedBy = "user")
    private Set<UserScore> userScores;
}
