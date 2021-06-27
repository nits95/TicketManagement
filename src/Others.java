public class Others implements TicketType {

    @Override
    public void takeAction(Ticket ticket) {
        ticket.setStatus(Status.Open);
    }
}
