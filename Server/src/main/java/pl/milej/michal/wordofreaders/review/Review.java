package pl.milej.michal.wordofreaders.review;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.milej.michal.wordofreaders.book.Book;
import pl.milej.michal.wordofreaders.review.comment.Comment;
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

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "publication_date", nullable = false)
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "application_user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "review")
    private Set<Comment> comments;
}
