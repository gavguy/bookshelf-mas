package lv.tsi.javacourses.bookshelf.books.model;

import lv.tsi.javacourses.bookshelf.auth.model.UserEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Reservation")
@Table(name = "reservation")
public class ReservationEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private BookEntity book;
    @ManyToOne
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
