public class NotificationService {
    private static NotificationService notificationService;

    public static NotificationService getInstance() {
        if (notificationService == null)
            synchronized (NotificationService.class) {
                notificationService = new NotificationService();
            }
        return notificationService;
    }

    public void sendSms()
    {
        System.out.println("sent an sms to customer");
        return;
    }
    public void makeIVRCall()
    {
        System.out.println("made an call to customer");
    }
}
