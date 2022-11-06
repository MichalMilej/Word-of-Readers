package pl.milej.michal.wordofreaders.book.score;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.milej.michal.wordofreaders.book.Book;
import pl.milej.michal.wordofreaders.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserScore {

    @Id
    @GeneratedValue
    private Long id;

    private Integer score;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "application_user_id")
    private User user;
}
