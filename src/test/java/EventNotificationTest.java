import com.example.Attendee;
import com.example.Event;
import com.example.Notification;
import static org.junit.jupiter.api.Assertions.*;

import com.example.service.EventNotificationServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import static org.mockito.Mockito.*;


import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EventNotificationTest {

    //we want to mock only specific behaviors and call the real methods for unstubbed behaviors, then we create a spy object using Mockito spy() method.
    @Spy
    Event event;

    @Spy
    Attendee attende;

    @InjectMocks
    EventNotificationServiceImpl eventNotificationService;

    @Test
    public void checkIfAttendesAreNotified() {

        event.addAttendee(attende);

        eventNotificationService.announce(event);
        List<Notification> notifications = attende.getNotifications();

        for (Notification notification : notifications) {
            assertEquals("The next big event is coming!", notification.getMessage());
        }
        verify(attende,times(2)).getNotifications();
    }

    @Test
    public void checkIfAtendesReceiveConfirmationMsg(){

        event.addAttendee(attende);

        eventNotificationService.confirmAttendance(event,attende);

        List <Notification> notifications = attende.getNotifications();

        for (Notification notification : notifications) {
            assertEquals("Dear Attendee, your subscription to the event has been confirmed successfully.",notification.getMessage());
        }
        verify(event, times(1)).addAttendee(attende);
    }
}














