package ma.emsi.glpi.Services;

import ma.emsi.glpi.Entity.Ticket;


public interface TicketServ {

    Ticket addTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket, Long id);

    void deleteTicket(Long id);


}
