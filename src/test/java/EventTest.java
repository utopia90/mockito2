import com.example.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

import com.example.service.EventNotificationService;
import com.example.service.EventNotificationServiceImpl;
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
    @Test
    @DisplayName("Check if remove attendee")
    public void removeAttendee(){
        Event event = new Event();
        Attendee attendee = new Attendee(1L,"Marta","marta@example.com");
        event.addAttendee(attendee);
        event.removeAttendee(attendee);

        assertFalse(event.getAttendees().contains(attendee));

    }
    @Test
    @DisplayName("Check if remove multiple attendees")
    public void removeAttendees(){
        Event event = new Event();
        List<Attendee> attendees = Arrays.asList(
                new Attendee(1L, "sofia", "sofia@example.com"),
                new Attendee(2L, "erik", "erik@example.com"),
                new Attendee(3L, "maría", "maria@example.com")
        );
        event.addAttendees(attendees);
        event.removeAttendees(attendees);

        assertFalse(event.getAttendees().contains(attendees));

    }
    @Test
    @DisplayName("Check if event notify Assistants")
    public void notifyAssistants(){
        EventNotificationService eventNotificationService = new EventNotificationServiceImpl();
        Event event = new Event(1L,"Advanced Java Event", EventType.TECH, eventNotificationService);
        Attendee attendee = new Attendee(1L, "Macarena", "macarena@gmail.com");
        event.addAttendee(attendee);
        event.notifyAssistants();
        List<Notification> notifications = attendee.getNotifications();

        for (Notification notification : notifications) {
            assertEquals("The next big event is coming!", notification.getMessage());

        }
    }

    @Test
    @DisplayName("check if add speaker")
    public void addSpeaker(){
        Speaker speaker = new Speaker(1L,"Alan", "Java");
        Event event = new Event();

        event.addSpeaker(speaker);
        assertTrue(event.getSpeakers().contains(speaker));

    }
    @Test
    @DisplayName("check if remove speaker")
    public void removeSpeaker(){
        Speaker speaker = new Speaker(1L,"Alan", "Java");
        Event event = new Event();
        event.addSpeaker(speaker);
        event.removeSpeaker(speaker);

        assertFalse(event.getSpeakers().contains(speaker));

    }
}
