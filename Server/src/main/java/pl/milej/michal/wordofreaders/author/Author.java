package pl.milej.michal.wordofreaders.author;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
}
