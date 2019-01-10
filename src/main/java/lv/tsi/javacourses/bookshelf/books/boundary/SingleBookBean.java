package lv.tsi.javacourses.bookshelf.books.boundary;

import lv.tsi.javacourses.bookshelf.books.model.BookEntity;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Named
@ViewScoped
public class SingleBookBean implements Serializable {
    private Long id;
    @PersistenceContext
    private EntityManager em;


    public BookEntity getBook() {
        System.out.println("Opening book" + id);
        return em.find(BookEntity.class, id);


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
