public class ChangeLanguage implements TicketType {
    private NotificationService notificationService;

    public ChangeLanguage() {
    }

    @Override
    public void takeAction(Ticket ticket) {
        notificationService = NotificationService.getInstance();
        notificationService.makeIVRCall();
        ticket.setStatus(Status.AutoResolved);
        ticket.setResolutionComment("automatic IVR call made to the customer");
    }
}
