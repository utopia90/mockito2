import com.example.*;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service.EventNotificationService;
import com.example.service.EventNotificationServiceImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.Arrays;
import java.util.jar.JarOutputStream;

public class EventTest {
    @Test
    @DisplayName("Check add attendes")
    public void addAttendee() {
        Event event = new Event();
        Attendee attendee = new Attendee(1L,"Roberto", "roberto@example.com");
        event.addAttendee(attendee);

        assertEquals("Roberto", event.getAttendees().get(0).getNickname());

    }
    @Test
    @DisplayName("Check dont add null attendes")
    public void createNullAttendee(){
        Event event = new Event();
        Attendee attendee = null;
        event.addAttendee(attendee);

        assertTrue(event.getAttendees().size() == 0);
    }
    @Test
    @DisplayName("Check dont add attendes already registered")
    public void createDoubleAttendee(){
        Event event = new Event();
        Attendee attendee = new Attendee(1L,"Maria", "maria@example.com");
        event.addAttendee(attendee);
        event.addAttendee(attendee);

        assertTrue(event.getAttendees().size() == 1);
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

        assertTrue(event.getAttendees().size() == 3);

    }
    @Test
    @DisplayName("Check if remove attendee")
    public void removeAttendee(){
        Event event = new Event();
        Attendee attendee = new Attendee(1L,"Marta","marta@example.com");
        event.addAttendee(attendee);
        event.removeAttendee(attendee);
        assertTrue(event.getAttendees().size() == 0);

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

        assertTrue(event.getAttendees().size() == 0);

    }
    @Test
    @DisplayName("Check dont add multiple attendees already registered")
    public void CreateDoubleAttendees(){
        Event event = new Event();
        List<Attendee> attendees = Arrays.asList(
                new Attendee(1L, "sofia", "sofia@example.com"),
                new Attendee(2L, "erik", "erik@example.com"),
                new Attendee(3L, "maría", "maria@example.com")
        );
        event.addAttendees(attendees);
        event.addAttendees(attendees);


        assertTrue(event.getAttendees().size() == 3);

    }
    @Mock
    EventNotificationService eventNotificationService;

    @InjectMocks
    Event event;

    @Test
    @DisplayName("Check if event notify Assistants")
    @ExtendWith(MockitoExtension.class)
    public void notifyAssistants(){

     event.setId(1L);
     event.setTitle("Java event");
     event.setType(EventType.TECH);

        event.notifyAssistants();
        ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);

        Mockito.verify(eventNotificationService).announce(eventCaptor.capture());

        assertEquals(EventType.TECH, eventCaptor.getValue().getType());

    }

    @Test
    @DisplayName("check if add speaker")
    public void addSpeaker(){
        Speaker speaker = new Speaker(1L,"Alan", "Java");
        Event event = new Event();

        event.addSpeaker(speaker);
        assertTrue(event.getSpeakers().get(0).getId() == 1);

    }
    @Test
    @DisplayName("check if remove speaker")
    public void removeSpeaker(){
        Speaker speaker = new Speaker(1L,"Alan", "Java");
        Event event = new Event();
        event.addSpeaker(speaker);
        event.removeSpeaker(speaker);

        assertTrue(event.getSpeakers().size() == 0);


    }

    @Test
    @DisplayName("check if set & return ID")
    public void getID(){
        Event event = new Event();
        event.setId(1L);
        assertEquals(1L,event.getId());
    }
    @Test
    @DisplayName("check if set & return Title")
    public void getTitle(){
        Event event = new Event();
        event.setTitle("Java event");
        assertEquals("Java event",event.getTitle());
    }
    @Test
    @DisplayName("check if set & return Type")
    public void getType(){
        Event event = new Event();
        event.setType(EventType.TECH);
        assertEquals(EventType.TECH,event.getType());
    }
    @Test
    @DisplayName("check if set & return speakers")
    public void getSpeakers(){
        Event event = new Event();
        List<Speaker> speakers = Arrays.asList(
             new Speaker(1L,"Marcos","Java"),
                new Speaker(1L,"Lola","Junit")
        );
        event.setSpeakers(speakers);
        assertEquals(speakers,event.getSpeakers());
    }
}
