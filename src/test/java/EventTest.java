import com.example.Attendee;
import com.example.Event;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    @DisplayName("Check add attendes")
    public void addAttendee() {
        Event event = new Event();
        Attendee attendee = new Attendee(1L,"Roberto", "roberto@example.com");
        event.addAttendee(attendee);
        assertTrue(event.getAttendees().contains(attendee));
    }
}
