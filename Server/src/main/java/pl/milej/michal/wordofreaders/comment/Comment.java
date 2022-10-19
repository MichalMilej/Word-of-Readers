package pl.milej.michal.wordofreaders.comment;

import pl.milej.michal.wordofreaders.user.User;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String text;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
