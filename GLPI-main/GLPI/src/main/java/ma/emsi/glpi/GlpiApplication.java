package ma.emsi.glpi;


import ma.emsi.glpi.Entity.Role;
import ma.emsi.glpi.Entity.Ticket;
import ma.emsi.glpi.Entity.User;
import ma.emsi.glpi.Services.TicketServ;
import ma.emsi.glpi.Services.UserServ;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class GlpiApplication {



    public static void main(String[] args) {
        SpringApplication.run(GlpiApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(UserServ userServ, TicketServ ticketServ){
        return args -> {
            User user2 = new User();
            user2.setUsername("Omar");
            user2.setPassword("password2");
            userServ.addNewUser(user2);

            User user3 = new User();
            user3.setUsername("Ali");
            user3.setPassword("password3");
            userServ.addNewUser(user3);

            User user4 = new User();
            user4.setUsername("Mohamed");
            user4.setPassword("password4");
            userServ.addNewUser(user4);

            User user5 = new User();
            user5.setUsername("Badr");
            user5.setPassword("password5");
            userServ.addNewUser(user5);

            Stream.of("ADMIN","USER","TECHNICIEN").forEach(r -> {
                Role role = new Role();
                role.setRoleName(r);
                userServ.addNewRole(role);
            });

            userServ.addRoleToUser("Omar","USER");
            userServ.addRoleToUser("Omar","ADMIN");
            userServ.addRoleToUser("Omar","TECHNICIEN");

            userServ.addRoleToUser("Mohamed", "USER");
            userServ.addRoleToUser("Mohamed", "TECHNICIEN");

            userServ.addRoleToUser("Badr", "ADMIN");
            userServ.addRoleToUser("Badr", "USER");

            userServ.addRoleToUser("Ali", "USER");
            userServ.addRoleToUser("Ali", "TECHNICIEN");



            Ticket ticket = new Ticket();
            ticket.setTitle("Probleme souris");
            ticket.setDescription("j'arrive pas a bouger la souris de mon poste de depuis ce matin ");
            ticket.setPriorite("FAIBLE");
            ticket.setStatue("Open");
            ticket.setDateOuverture(new Date());
            ticket.setCreatedBy(user2);
            ticketServ.addTicket(ticket);

            Ticket ticket2 = new Ticket();
            ticket2.setTitle("Probleme de RAM");
            ticket2.setDescription("Mon pc est trop long il n'est pas fluide , j'arrive pas à travailler ");
            ticket2.setPriorite("HIGH");
            ticket2.setStatue("Open");
            ticket2.setDateOuverture(new Date());
            ticket2.setCreatedBy(user2);
            ticketServ.addTicket(ticket2);

            Ticket ticket3 = new Ticket();
            ticket3.setTitle("Problème d'imprimante");
            ticket3.setDescription("L'imprimante ne répond pas. Assistance urgente requise.");
            ticket3.setPriorite("HAUTE");
            ticket3.setStatue("Ouvert");
            ticket3.setDateOuverture(new Date());
            ticket3.setCreatedBy(user3);
            ticketServ.addTicket(ticket3);

            Ticket ticket4 = new Ticket();
            ticket4.setTitle("Problème de connectivité réseau");
            ticket4.setDescription("Impossible de se connecter au réseau. Besoin d'aide dès que possible.");
            ticket4.setPriorite("HAUTE");
            ticket4.setStatue("Ouvert");
            ticket4.setDateOuverture(new Date());
            ticket4.setCreatedBy(user4);
            ticketServ.addTicket(ticket4);

            Ticket ticket5 = new Ticket();
            ticket5.setTitle("Demande d'installation de logiciel");
            ticket5.setDescription("Besoin d'installer un logiciel spécifique sur mon poste de travail.");
            ticket5.setPriorite("MOYENNE");
            ticket5.setStatue("Ouvert");
            ticket5.setDateOuverture(new Date());
            ticket5.setCreatedBy(user5);
            ticketServ.addTicket(ticket5);

            //modifier le ticket avec id 1
            ticketServ.updateTicket(ticket2,1L);

            ticketServ.deleteTicket(2L);

            userServ.ListUsers().forEach(System.out::println);;






        };
    }

}
