import lv.tsi.javacourses.bookshelf.auth.boundary.CurrentUser;
import lv.tsi.javacourses.bookshelf.books.model.ReservationEntity;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
@ViewScoped
public class MyBooksBean implements Serializable {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private CurrentUser currentUser;
    private List<ReservationEntity> aveilableResult;
    private List<ReservationEntity> inQuinResult;

    public void prepare() {
        aveilableResult = new ArrayList<>();
        inQuinResult = new ArrayList<>();
        List<ReservationEntity> userReservations = em.createQuery(
                "select r from Reservation r " +
                        "where r.user = :user and r.status = 'ACTIVE'", ReservationEntity.class)
                .setParameter("user", currentUser.getUser())
                .getResultList();

        for (ReservationEntity r : userReservations) {
            Long reservationId = r.getId();
            Optional<ReservationEntity> firstReservation = em.createQuery(
                    "select r from Reservation r " +
                            "where r.book = :book and r.status <> 'CLOSED' " +
                            "order by r.created", ReservationEntity.class)
                    .setParameter("book", r.getBook())
                    .getResultStream()
                    .findFirst();
            if (firstReservation.isEmpty() || firstReservation.get().getId().equals(reservationId)) {
                aveilableResult.add(r);
            } else {
                inQuinResult.add(r);
            }
        }

    }

    public List<ReservationEntity> getAvailableBooks() {

        return aveilableResult;
    }
    public List<ReservationEntity> getInQuinResult() {

        return inQuinResult;
    }
}