import com.example.Attendee;
import com.example.Event;
import com.example.Notification;
import com.example.service.EventNotificationService;
import static org.junit.jupiter.api.Assertions.*;

import com.example.service.EventNotificationServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.internal.matchers.Not;
import org.mockito.internal.matchers.NotNull;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EventNotificationTest {

    @Mock
    Event event;

    @Mock
    Attendee attende;

    @InjectMocks
    EventNotificationServiceImpl eventNotificationService;


    @Test
    public void checkIfAttendesAreNotified() {
         event = new Event();
         attende = new Attendee(1L, "sara", "sara@example.com");

        event.addAttendee(attende);
        eventNotificationService.announce(event);

        List<Notification> notifications = attende.getNotifications();

        for (Notification notification : notifications) {
            assertEquals("The next big event is coming!", notification.getMessage());

        }
    }

    @Test
    public void checkIfAtendesReceiveConfirmationMsg(){
         event = new Event();
         attende = new Attendee(1L,"sara","sara@example.com");

        event.addAttendee(attende);

        eventNotificationService.confirmAttendance(event,attende);

        List <Notification> notifications = attende.getNotifications();

        for (Notification notification : notifications) {
            assertEquals("Dear Attendee, your subscription to the event has been confirmed successfully.",notification.getMessage());
        }
    }
}














