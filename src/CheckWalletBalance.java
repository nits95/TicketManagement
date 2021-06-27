public class CheckWalletBalance implements TicketType {
    private NotificationService notificationService;

    public CheckWalletBalance() {
    }

    @Override
    public void takeAction(Ticket ticket) {
        notificationService = NotificationService.getInstance();
        notificationService.sendSms();
        ticket.setStatus(Status.AutoResolved);
        ticket.setResolutionComment("sent automatic SMS to customer");
    }
}
