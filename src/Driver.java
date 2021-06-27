public class Driver {
    private static TicketManagementService ticketManagementService;

    public static void main(String[] args) {
        ticketManagementService = TicketManagementService.getTicketManagementService();
        TicketType type1 = new CheckWalletBalance();
         System.out.println(ticketManagementService.createTicket(type1, "check balance"));

         ticketManagementService.status(1);
        TicketType type2 = new ChangeLanguage();
        System.out.println(ticketManagementService.createTicket(type2, "change language"));
        ticketManagementService.status(2);
       TicketType type3 = new Others();
        System.out.println(ticketManagementService.createTicket(type3, "Need more details on transaction #abc"));

       Employee emp1  = new Employee("Tom");
       Employee emp2 = new Employee("Sam");
       Supervisor sup1 = new Supervisor("Harry");
       ticketManagementService.assignTicket(emp1);
       ticketManagementService.resolveTicket(emp1, "Resolved transaction issue #abc");
        ticketManagementService.status(3);
       ticketManagementService.status();
       ticketManagementService.verifyTicketResolution(sup1);
       ticketManagementService.assignTicket(sup1);
       ticketManagementService.verifyTicketResolution(sup1);
ticketManagementService.status(3);
ticketManagementService.status();
    }
}
