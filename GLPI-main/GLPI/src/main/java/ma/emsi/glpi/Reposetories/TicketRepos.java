package ma.emsi.glpi.Reposetories;

import ma.emsi.glpi.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepos extends JpaRepository<Ticket,Long> {

    boolean findByticketId(Long id);

    void deleteByticketId(Long id);





}
