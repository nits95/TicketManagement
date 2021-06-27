import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {

    private TicketType type;
    private String description;
    private Status status;
    private Employee assignedTo;
    private Supervisor verifiedBy;
    private String resolutionComment;

    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;

    public Ticket(TicketType type, String description) {
        this.type = type;
        this.description = description;
        this.id = count.incrementAndGet();
    }

    public String getResolutionComment() {
        return resolutionComment;
    }

    public void setResolutionComment(String resolutionComment) {
        this.resolutionComment = resolutionComment;
    }

    public void takeAction()
    {
        this.type.takeAction(this);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Supervisor getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(Supervisor verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                Objects.equals(type, ticket.type) &&
                Objects.equals(description, ticket.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, description, id);
    }
}


