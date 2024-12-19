package pl.milej.michal.worldofreaders.book.review.reaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.milej.michal.worldofreaders.book.review.Review;
import pl.milej.michal.worldofreaders.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    private Review review;
}
