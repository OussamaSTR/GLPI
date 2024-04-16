package ma.emsi.glpi.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.emsi.glpi.Entity.Ticket;
import ma.emsi.glpi.Reposetories.TicketRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TicketServImpl implements TicketServ {
    private TicketRepos ticketRepos;
    @Override
    public Ticket addTicket(Ticket ticket) {
        return ticketRepos.save(ticket);
    }



     @Override
        public Ticket updateTicket(Ticket ticket, Long id) {
         Optional<Ticket> optionalTicket = ticketRepos.findById(id);
         if (optionalTicket.isPresent()) {
             Ticket existingTicket = optionalTicket.get();
             existingTicket.setTitle(ticket.getTitle());
             existingTicket.setDateFermeture(ticket.getDateFermeture());
             existingTicket.setDateOuverture(ticket.getDateOuverture());
             existingTicket.setDescription(ticket.getDescription());
             existingTicket.setPriorite(ticket.getPriorite());
             existingTicket.setStatue(ticket.getStatue());

             return ticketRepos.save(existingTicket);
         } else {
             return null;
         }
        }

    @Override
    public boolean deleteTicket(Long id) {

        if ( ticketRepos.findByticketId(id)) {
            ticketRepos.deleteById(id);
            System.out.println("ticket deleted successfully");
            return true;
        } else {
            return false;
        }

    }


}
