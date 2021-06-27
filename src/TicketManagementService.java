import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketManagementService {

    private static TicketManagementService ticketManagementService;
    private int resolvedTickets;
    private List<Integer> openTickets;
    private List<Integer> pendingVerifications;
    private int assignedTickets;
    private Map<Integer, Ticket> tickets;


    public TicketManagementService() {
        openTickets = new ArrayList<>();
        tickets = new HashMap<>();
        pendingVerifications = new ArrayList<>();
    }

    public static TicketManagementService getTicketManagementService() {
        if (ticketManagementService == null)
            synchronized (TicketManagementService.class) {
                ticketManagementService = new TicketManagementService();

            }
        return ticketManagementService;
    }

    public int createTicket(TicketType ticketType, String description) {
        Ticket ticket = new Ticket(ticketType, description);
        System.out.println("status " + ticket.getId());
        ticket.takeAction();
        if (ticket.getStatus() == Status.AutoResolved) {
            resolvedTickets++;
            tickets.put(ticket.getId(), ticket);
        }
        if (ticket.getStatus() == Status.Open) {
            openTickets.add(ticket.getId());
            tickets.put(ticket.getId(), ticket);
        }
        return ticket.getId();
    }

    public void assignTicket(Employee user) {
        if (openTickets != null && !openTickets.isEmpty()) {
            int ticketId = openTickets.get(0);
            Ticket ticket = tickets.get(ticketId);
            ticket.setStatus(Status.Assigned);
            openTickets.remove(0);
            ticket.setAssignedTo(user);
            user.setTicketId(ticketId);
            assignedTickets++;
            System.out.println("Ticket-" + ticket.getId() + "->" + user.getName());
        } else
            System.out.println("No Open tickets available");
    }

    public void assignTicket(Supervisor supervisor) {
        if (pendingVerifications != null && !pendingVerifications.isEmpty()) {
            int ticketId = pendingVerifications.get(0);
            supervisor.setTicketId(ticketId);
            System.out.println("Ticket-" + ticketId + " " + supervisor.getName());
        } else {
            System.out.println("No ticket to assign to supervisor");
        }
    }

    public void resolveTicket(Employee emp, String comment) {
        if (emp.getTicketId() > 0) {
            int ticketId = emp.getTicketId();
            Ticket ticket = tickets.get(ticketId);
            ticket.setStatus(Status.Resolved);
            ticket.setResolutionComment(comment);
            pendingVerifications.add(ticketId);
            System.out.println("Ticket-" + ticket.getId() + " resolved by " + emp.getName() + " with comment " + comment);
        } else {
            System.out.println("No ticket assigned to " + emp.getName());
        }
    }

    public void status(int id) {
        if (tickets.containsKey(id)) {
            Ticket ticket = tickets.get(id);
            String resolvedBy = ticket.getAssignedTo() == null ? null : ticket.getAssignedTo().getName();
            String verifiedBy = ticket.getVerifiedBy() == null ? null : ticket.getVerifiedBy().getName();

            System.out.println("Ticket-" + id + " status:" + ticket.getStatus().toString() + " comment: " + ticket.getResolutionComment() + " resolved by: " + resolvedBy
                    + " verified by: " + verifiedBy);
        } else {
            System.out.println("wrong ticket id");
        }
    }

    public void verifyTicketResolution(Supervisor supervisor) {
        if (supervisor.getTicketId() > 0) {
            int ticketId = supervisor.getTicketId();
            Ticket ticket = tickets.get(ticketId);
            ticket.setVerifiedBy(supervisor);
            resolvedTickets++;
            assignedTickets--;
            System.out.println("Ticket-" + ticket.getId() + "resolution verified by supervisor " + ticket.getVerifiedBy().getName());
        } else {
            System.out.println(" Error! Harry has no ticket assigned to him.");
        }
    }

    public void status() {
        System.out.println(tickets.size() + "-> Total tickets");
        System.out.println(resolvedTickets + "-> CLOSED TICKETS");
        System.out.println(assignedTickets + "-> ASSIGNED TICKETS");
        System.out.println(assignedTickets + "-> OPEN TICKETS");

    }


}
