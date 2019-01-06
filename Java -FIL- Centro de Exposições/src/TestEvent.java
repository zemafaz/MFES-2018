
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestEvent extends MyTestCase {
  private Room room1 = new Room(10L, 100L);
  private Room room2 = new Room(10L, 100L);
  private Room room3 = new Room(6L, 100L);
  private Date date1 = new Date(2019L, 1L, 1L);
  private Date date2 = new Date(2019L, 1L, 4L);
  private Date date3 = new Date(2019L, 1L, 2L);
  private Date date4 = new Date(2019L, 1L, 3L);
  private Schedule schedule1 = new Schedule(room1, date1, date2);
  private Schedule schedule2 = new Schedule(room2, date1, date1);
  private Schedule schedule3 = new Schedule(room3, date3, date3);
  private Visitor visitor1 = new Visitor("João");
  private Visitor visitor2 = new Visitor("José");
  private Visitor visitor3 = new Visitor("Maria");
  private Exhibitor exhibitor1 = new Exhibitor("Paulo");
  private Exhibitor exhibitor2 = new Exhibitor("Ana");
  private Exhibitor exhibitor3 = new Exhibitor("Sofia");
  private Visitor visitor4 = new Visitor("João");
  private Visitor visitor5 = new Visitor("José");
  private Visitor visitor6 = new Visitor("Maria");
  private Visitor visitor7 = new Visitor("Maria");
  private Event event1 = new Event(schedule1, exhibitor1, 20L, "evento1");
  private Event event2 = new Event(schedule2, exhibitor2, 20L, "evento2");
  private Event event3 = new Event(schedule3, exhibitor3, 20L, "evento3");
  private Event event4 = new Event(schedule3, exhibitor3, 20L, "evento4");

  private void testCloseandForceCloseEvent() {

    event1.closeEvent(
        new Date(CurrentTime.getYear(), CurrentTime.getMonth(), CurrentTime.getDay()));
    event2.closeEvent(
        new Date(CurrentTime.getYear(), CurrentTime.getMonth(), CurrentTime.getDay()));
    event3.forceCloseEvent("password");
    assertTrue(event1.isClosed());
    assertTrue(event2.isClosed());
    assertTrue(event3.isClosed());
  }

  private void testGets() {

    assertEqual(20L, event1.getPricePerParticipant());
    assertEqual(true, event1.isClosed());
    assertEqual(400L, event1.getCostOfEvent());
    assertEqual(0L, event1.getSalesValueOfEvent());
    assertEqual(-400L, event1.profit());
    assertEqual(exhibitor1, event1.getExhibitor());
    assertEqual(SetUtil.set(), event1.getParticipants());
    assertTrue(event1.has_started());
    assertTrue(event1.has_ended());
    assertEqual(1L, event1.getId());
    assertEqual("evento1", event1.getName());
    assertEqual(20L, event2.getPricePerParticipant());
    assertEqual(true, event2.isClosed());
    assertEqual(100L, event2.getCostOfEvent());
    assertEqual(0L, event2.getSalesValueOfEvent());
    assertEqual(-100L, event2.profit());
    assertEqual(exhibitor2, event2.getExhibitor());
    assertEqual(SetUtil.set(), event2.getParticipants());
    assertTrue(event2.has_started());
    assertTrue(event2.has_ended());
    assertEqual(2L, event2.getId());
    assertEqual("evento2", event2.getName());
    assertEqual(20L, event3.getPricePerParticipant());
    assertEqual(true, event3.isClosed());
    assertEqual(100L, event3.getCostOfEvent());
    assertEqual(0L, event3.getSalesValueOfEvent());
    assertEqual(-100L, event3.profit());
    assertEqual(exhibitor3, event3.getExhibitor());
    assertEqual(SetUtil.set(), event3.getParticipants());
    assertTrue(event3.has_started());
    assertTrue(event3.has_ended());
    assertEqual(3L, event3.getId());
    assertEqual("evento3", event3.getName());
    assertEqual(20L, event4.getPricePerParticipant());
    assertEqual(false, event4.isClosed());
    assertEqual(100L, event4.getCostOfEvent());
    assertEqual(0L, event4.getSalesValueOfEvent());
    assertEqual(-100L, event4.profit());
    assertEqual(exhibitor3, event4.getExhibitor());
    assertEqual(SetUtil.set(), event4.getParticipants());
    assertTrue(event4.has_started());
    assertTrue(event4.has_ended());
    assertEqual(4L, event4.getId());
    assertEqual("evento4", event4.getName());
  }

  private void testOverlaps() {

    assertTrue(event1.overlaps(date3, date4, room1.getId()));
    assertTrue(event1.overlaps(date1, date3, room1.getId()));
    assertTrue(event1.overlaps(date1, date1, room1.getId()));
    assertTrue(!(event1.overlaps(date1, date1, room2.getId())));
    assertTrue(!(event2.overlaps(date3, date4, room2.getId())));
    assertTrue(event2.overlaps(date1, date3, room2.getId()));
    assertTrue(event2.overlaps(date1, date1, room2.getId()));
    assertTrue(!(event2.overlaps(date1, date3, room1.getId())));
    assertTrue(event3.overlaps(date1, date3, room3.getId()));
    assertTrue(!(event3.overlaps(date1, date1, room3.getId())));
    assertTrue(event3.overlaps(date3, date4, room3.getId()));
    assertTrue(!(event3.overlaps(date4, date4, room3.getId())));
  }

  private void testParticipants() {

    event4.addParticipant(visitor1);
    event4.addParticipant(visitor2);
    assertEqual(SetUtil.set(visitor1, visitor2), event4.getParticipants());
    assertEqual(2L, event4.getParticipants().size());
    assertEqual(40L, event4.getSalesValueOfEvent());
    assertEqual(-60L, event4.profit());
    event4.removeParticipant(visitor1);
    event4.removeParticipant(visitor2);
    assertEqual(0L, event4.getSalesValueOfEvent());
    assertEqual(-100L, event4.profit());
    event4.addParticipant(visitor1);
    event4.addParticipant(visitor2);
    event4.addParticipant(visitor3);
    event4.addParticipant(visitor4);
    event4.addParticipant(visitor5);
    event4.addParticipant(visitor6);
    assertEqual(6L, event4.getParticipants().size());
    assertEqual(120L, event4.getSalesValueOfEvent());
    assertEqual(20L, event4.profit());
    event4.removeParticipant(visitor1);
    event4.removeParticipant(visitor2);
    assertTrue(event4.has_participant(visitor3));
    assertTrue(!(event4.has_participant(visitor1)));
    assertEqual(4L, event4.getParticipants().size());
    assertEqual(80L, event4.getSalesValueOfEvent());
    assertEqual(-20L, event4.profit());
  }

  private void testInvName() {

    {
      Event NameTooBig =
          new Event(
              schedule1,
              exhibitor1,
              20L,
              "O meu nome tem mais de cinquenta caracteres por isso dá erro");
      assertEqual(
          "O meu nome tem mais de cinquenta caracteres por isso dá erro", NameTooBig.getName());
    }
  }

  private void testLimitOfParticipants() {

    event4.addParticipant(visitor1);
    event4.addParticipant(visitor2);
    assertEqual(6L, event4.getParticipants().size());
    event4.addParticipant(visitor7);
    assertEqual(7L, event4.getParticipants().size());
  }

  private void testCloseEventPre() {

    {
      Date currentDate =
          new Date(CurrentTime.getYear(), CurrentTime.getMonth(), CurrentTime.getDay());
      Schedule schedule = new Schedule(room1, currentDate, currentDate);
      Event event = new Event(schedule, exhibitor1, 100L, "event");
      event.closeEvent(currentDate);
      assertTrue(event.isClosed());
    }
  }

  private void testForceCloseEventWrongPassword() {

    event4.forceCloseEvent("testpassword");
  }

  private void testaddParticipantToClosedEvent() {

    event4.closeEvent(
        new Date(CurrentTime.getYear(), CurrentTime.getMonth(), CurrentTime.getDay()));
    event4.addParticipant(visitor1);
  }

  private void testRemoveParticipantFromClosedEvent() {

    event4.removeParticipant(visitor1);
  }

  public void test() {

    IO.println("\tEvent Tests");
    testCloseandForceCloseEvent();
    testGets();
    testParticipants();
    testOverlaps();
    IO.println("\tTestes a eventos terminados com sucesso!");
  }

  public TestEvent() {}

  public String toString() {

    return "TestEvent{"
        + "room1 := "
        + Utils.toString(room1)
        + ", room2 := "
        + Utils.toString(room2)
        + ", room3 := "
        + Utils.toString(room3)
        + ", date1 := "
        + Utils.toString(date1)
        + ", date2 := "
        + Utils.toString(date2)
        + ", date3 := "
        + Utils.toString(date3)
        + ", date4 := "
        + Utils.toString(date4)
        + ", schedule1 := "
        + Utils.toString(schedule1)
        + ", schedule2 := "
        + Utils.toString(schedule2)
        + ", schedule3 := "
        + Utils.toString(schedule3)
        + ", visitor1 := "
        + Utils.toString(visitor1)
        + ", visitor2 := "
        + Utils.toString(visitor2)
        + ", visitor3 := "
        + Utils.toString(visitor3)
        + ", exhibitor1 := "
        + Utils.toString(exhibitor1)
        + ", exhibitor2 := "
        + Utils.toString(exhibitor2)
        + ", exhibitor3 := "
        + Utils.toString(exhibitor3)
        + ", visitor4 := "
        + Utils.toString(visitor4)
        + ", visitor5 := "
        + Utils.toString(visitor5)
        + ", visitor6 := "
        + Utils.toString(visitor6)
        + ", visitor7 := "
        + Utils.toString(visitor7)
        + ", event1 := "
        + Utils.toString(event1)
        + ", event2 := "
        + Utils.toString(event2)
        + ", event3 := "
        + Utils.toString(event3)
        + ", event4 := "
        + Utils.toString(event4)
        + "}";
  }
}
