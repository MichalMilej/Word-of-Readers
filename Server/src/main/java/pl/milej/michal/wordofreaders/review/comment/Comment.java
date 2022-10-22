package pl.milej.michal.wordofreaders.review.comment;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.milej.michal.wordofreaders.review.Review;
import pl.milej.michal.wordofreaders.user.User;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(name = "publication_date", nullable = false)
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @ManyToOne
    @JoinColumn(name = "application_user_id", nullable = false)
    private User user;
}
