package lv.tsi.javacourses.bookshelf.books.boundary;

import lv.tsi.javacourses.bookshelf.auth.boundary.CurrentUser;
import lv.tsi.javacourses.bookshelf.books.model.BookEntity;
import lv.tsi.javacourses.bookshelf.books.model.ReservationEntity;
import lv.tsi.javacourses.bookshelf.books.model.ReservationStatus;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;

@Named
@ViewScoped
public class BookBean implements Serializable {
    private Long id;
    @PersistenceContext
    private EntityManager em;
    private BookEntity book;

    public void openbook() {
        System.out.println("Opening book" + id);
        book = em.find(BookEntity.class, id);
    }
    @Inject
    private CurrentUser currentUser;
    @Transactional
    public void reserve(Long id) {
        System.out.println("Trying to reservew a book" + id + "." + currentUser.getUser().getId());

        BookEntity book = em.find(BookEntity.class, id);
        ReservationEntity reservation = new ReservationEntity();
        reservation.setBook(book);
        reservation.setUser(currentUser.getUser());
        reservation.setStatus(ReservationStatus.ACTIVE);
        em.persist(reservation);

    }

    public BookEntity getBook() {
        return book;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
