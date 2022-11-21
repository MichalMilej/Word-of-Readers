package pl.milej.michal.wordofreaders.book.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.milej.michal.wordofreaders.book.Book;
import pl.milej.michal.wordofreaders.book.review.reaction.Reaction;
import pl.milej.michal.wordofreaders.user.User;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String text;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "publication_date", nullable = false)
    private Date publicationDate;

    private Integer likes = 0;

    private Integer dislikes = 0;

    @OneToMany(mappedBy = "review")
    private Set<Reaction> usersWhoReacted;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "application_user_id", nullable = false)
    private User user;
}
