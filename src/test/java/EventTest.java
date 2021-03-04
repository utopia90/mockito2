import com.example.Attendee;
import com.example.Event;
import static org.junit.jupiter.api.Assertions.*;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.Arrays;

public class EventTest {
    @Test
    @DisplayName("Check add attendes")
    public void addAttendee() {
        Event event = new Event();
        Attendee attendee = new Attendee(1L,"Roberto", "roberto@example.com");
        event.addAttendee(attendee);
        assertTrue(event.getAttendees().contains(attendee));

    }
    @Test
    @DisplayName("Check dont add null attendes")
    public void createNullAttendee(){
        Event event = new Event();
        Attendee attendee = null;
        event.addAttendee(attendee);

        assertFalse(event.getAttendees().contains(attendee));
    }

    @Test
    @DisplayName("Check add multiple attendees")
    public void addAttendees() {
        Event event = new Event();
        List<Attendee> attendees = Arrays.asList(
                new Attendee(1L, "sofia", "sofia@example.com"),
                new Attendee(2L, "erik", "erik@example.com"),
                new Attendee(3L, "maría", "maria@example.com")
        );

        event.addAttendees(attendees);

        assertTrue(event.getAttendees().containsAll(attendees));

    }
}
