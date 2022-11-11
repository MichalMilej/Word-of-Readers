package pl.milej.michal.wordofreaders.review.reaction;

import lombok.Getter;
import lombok.Setter;
import pl.milej.michal.wordofreaders.review.Review;
import pl.milej.michal.wordofreaders.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "review_reaction")
public class Reaction {

    @GeneratedValue
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserReaction userReaction;

    @ManyToOne
    @JoinColumn(name = "application_user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;
}
