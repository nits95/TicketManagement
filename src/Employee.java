import java.util.concurrent.atomic.AtomicInteger;

public class Employee extends User{
    private String name;
    private int id;
    private int ticketId;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    private static final AtomicInteger count = new AtomicInteger(0);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee(String name) {
        this.name = name;
        this.id = count.incrementAndGet();
    }

}
