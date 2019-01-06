
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestExhibitionCentre extends MyTestCase {
  private ExhibitionCentre centre = ExhibitionCentre.getInstance();
  private Visitor visitor1 = centre.registerVisitor("visitor1");
  private Visitor visitor2 = centre.registerVisitor("visitor2");
  private Visitor visitor3 = centre.registerVisitor("visitor3");
  private Visitor visitor4 = centre.registerVisitor("visitor4");
  private Visitor visitor5 = centre.registerVisitor("visitor5");
  private Exhibitor exhibitor1 = centre.registerExhibitor("exhibitor1");
  private Exhibitor exhibitor3 = centre.registerExhibitor("exhibitor2");
  private Exhibitor exhibitor2 = centre.registerExhibitor("exhibitor3");
  private Exhibitor exhibitor4 = centre.registerExhibitor("exhibitor4");
  private Exhibitor exhibitor5 = centre.registerExhibitor("exhibitor5");
  private Room room1 = centre.addRoom(30L, 3L);
  private Room room2 = centre.addRoom(20L, 3L);
  private Room room3 = centre.addRoom(10L, 3L);
  private Date date1 = new Date(2019L, 1L, 1L);
  private Date date2 = new Date(2019L, 1L, 2L);
  private Date date3 = new Date(2019L, 1L, 4L);
  private Date date4 = new Date(3000L, 1L, 1L);
  private Date date5 = new Date(3000L, 2L, 28L);
  private Event event1;
  private Event event2;
  private Event event3;
  private Event event4;
  private Event event5;
  private Event event6;

  private void testEntities() {

    assertEqual(
        SetUtil.set(visitor1, visitor2, visitor3, visitor4, visitor5), centre.getVisitors());
    assertEqual(
        SetUtil.set(exhibitor1, exhibitor2, exhibitor3, exhibitor4, exhibitor5),
        centre.getExhibitors());
    assertEqual(visitor1, centre.getVisitor(visitor1.getId()));
    assertEqual(visitor2, centre.getVisitor(visitor2.getId()));
    assertEqual(exhibitor1, centre.getExhibitor(exhibitor1.getId()));
    assertEqual(exhibitor3, centre.getExhibitor(exhibitor3.getId()));
    centre.removeVisitor(visitor4);
    VDMSet setCompResult_1 = SetUtil.set();
    VDMSet set_1 = centre.getEvents();
    for (Iterator iterator_1 = set_1.iterator(); iterator_1.hasNext(); ) {
      Event event = ((Event) iterator_1.next());
      if (event.has_participant(visitor4)) {
        setCompResult_1.add(event);
      }
    }
    assertEqual(SetUtil.set(event2, event4), Utils.copy(setCompResult_1));

    centre.removeVisitor(visitor2);
    VDMSet setCompResult_2 = SetUtil.set();
    VDMSet set_2 = centre.getEvents();
    for (Iterator iterator_2 = set_2.iterator(); iterator_2.hasNext(); ) {
      Event event = ((Event) iterator_2.next());
      if (event.has_participant(visitor2)) {
        setCompResult_2.add(event);
      }
    }
    assertEqual(SetUtil.set(event1), Utils.copy(setCompResult_2));

    assertEqual(SetUtil.set(event1, event5), centre.getEventsFromExhibitor(exhibitor1));
    centre.removeExhibitor(exhibitor1);
    VDMSet setCompResult_3 = SetUtil.set();
    VDMSet set_3 = centre.getEvents();
    for (Iterator iterator_3 = set_3.iterator(); iterator_3.hasNext(); ) {
      Event event = ((Event) iterator_3.next());
      if (Utils.equals(event.getExhibitor(), exhibitor1)) {
        setCompResult_3.add(event);
      }
    }
    assertEqual(SetUtil.set(event1, event5), Utils.copy(setCompResult_3));

    assertEqual(4L, centre.getEventsFromExhibitor(exhibitor2).size());
    centre.removeExhibitor(exhibitor2);
    VDMSet setCompResult_4 = SetUtil.set();
    VDMSet set_4 = centre.getEvents();
    for (Iterator iterator_4 = set_4.iterator(); iterator_4.hasNext(); ) {
      Event event = ((Event) iterator_4.next());
      if (Utils.equals(event.getExhibitor(), exhibitor2)) {
        setCompResult_4.add(event);
      }
    }
    assertEqual(1L, setCompResult_4.size());

    assertEqual(SetUtil.set(event4), centre.getEventsFromExhibitor(exhibitor4));
    centre.removeExhibitor(exhibitor4);
    VDMSet setCompResult_5 = SetUtil.set();
    VDMSet set_5 = centre.getEvents();
    for (Iterator iterator_5 = set_5.iterator(); iterator_5.hasNext(); ) {
      Event event = ((Event) iterator_5.next());
      if (Utils.equals(event.getExhibitor(), exhibitor4)) {
        setCompResult_5.add(event);
      }
    }
    assertEqual(SetUtil.set(event4), Utils.copy(setCompResult_5));

    return;
  }

  private void testRooms() {

    assertEqual(3L, centre.getRooms().size());
    for (Iterator iterator_6 = centre.getRooms().iterator(); iterator_6.hasNext(); ) {
      Room room = (Room) iterator_6.next();
      assertEqual(room, centre.getRoom(room.getId()));
    }
    for (Iterator iterator_7 = centre.getRooms().iterator(); iterator_7.hasNext(); ) {
      Room room = (Room) iterator_7.next();
      centre.removeRoom(room);
    }
    assertEqual(0L, centre.getRooms().size());
    room1 = centre.addRoom(30L, 3L);
    room2 = centre.addRoom(20L, 3L);
    room3 = centre.addRoom(10L, 3L);
    assertEqual(3L, centre.getRooms().size());
  }

  private void testEvents() {

    assertEqual(3L, centre.getRooms().size());
    event1 = centre.createEvent(date1, date2, room1, exhibitor1, 20L, "event1");
    event2 = centre.createEvent(date1, date2, room2, exhibitor2, 20L, "event2");
    event3 = centre.createEvent(date1, date2, room3, exhibitor3, 20L, "event3");
    event4 = centre.createEvent(date3, date4, room1, exhibitor4, 20L, "event4");
    event5 = centre.createEvent(date4, date5, room2, exhibitor1, 20L, "event5");
    event6 = centre.createEvent(date5, date5, room3, exhibitor2, 20L, "event6");
    assertEqual(6L, centre.getEvents().size());
    for (Iterator iterator_8 = centre.getEvents().iterator(); iterator_8.hasNext(); ) {
      Event event = (Event) iterator_8.next();
      assertEqual(event, centre.getEvent(event.getId()));
    }
    centre.addParticipantToEvent(event1, visitor1);
    centre.addParticipantsToEvent(event1, SetUtil.set(visitor2, visitor3));
    assertEqual(3L, centre.getEvent(event1.getId()).getParticipants().size());
    assertEqual(SeqUtil.seq(20L, 60L, 60L, 0L, 3L), centre.getEventStatistics(event1));
    centre.addParticipantToEvent(event2, visitor4);
    centre.addParticipantToEvent(event2, visitor5);
    assertEqual(2L, centre.getEvent(event2.getId()).getParticipants().size());
    assertEqual(SeqUtil.seq(20L, 40L, 40L, 0L, 2L), centre.getEventStatistics(event2));
    centre.addParticipantToEvent(event3, visitor1);
    centre.addParticipantsToEvent(event3, SetUtil.set(visitor2, visitor3));
    assertEqual(3L, centre.getEvent(event3.getId()).getParticipants().size());
    assertEqual(SeqUtil.seq(20L, 20L, 60L, 40L, 3L), centre.getEventStatistics(event3));
    centre.addParticipantToEvent(event4, visitor4);
    centre.addParticipantToEvent(event4, visitor5);
    assertEqual(2L, centre.getEvent(event4.getId()).getParticipants().size());
    assertEqual(
        SeqUtil.seq(20L, 10749030L, 40L, -10748990L, 2L), centre.getEventStatistics(event4));
    centre.addParticipantToEvent(event5, visitor1);
    centre.addParticipantsToEvent(event5, SetUtil.set(visitor2, visitor3));
    assertEqual(3L, centre.getEvent(event5.getId()).getParticipants().size());
    assertEqual(SeqUtil.seq(20L, 1180L, 60L, -1120L, 3L), centre.getEventStatistics(event5));
    centre.addParticipantToEvent(event6, visitor4);
    centre.addParticipantToEvent(event6, visitor5);
    assertEqual(2L, centre.getEvent(event6.getId()).getParticipants().size());
    assertEqual(SeqUtil.seq(20L, 10L, 40L, 30L, 2L), centre.getEventStatistics(event6));
    assertEqual(
        SetUtil.set(event1, event3, event5),
        SetUtil.union(
            SetUtil.union(
                centre.getEventsFromVisitor(visitor1), centre.getEventsFromVisitor(visitor2)),
            centre.getEventsFromVisitor(visitor3)));
    assertEqual(
        SetUtil.set(event2, event4, event6),
        SetUtil.union(
            centre.getEventsFromVisitor(visitor4), centre.getEventsFromVisitor(visitor5)));
    assertEqual(SetUtil.set(event1, event5), centre.getEventsFromExhibitor(exhibitor1));
    assertEqual(SetUtil.set(event2, event6), centre.getEventsFromExhibitor(exhibitor2));
    assertEqual(SetUtil.set(event3), centre.getEventsFromExhibitor(exhibitor3));
    assertEqual(SetUtil.set(), centre.getEventsFromExhibitor(exhibitor5));
    assertEqual(60L, centre.totalMoneySpent(visitor1));
    assertEqual(60L, centre.totalMoneySpent(visitor2));
    assertEqual(60L, centre.totalMoneySpent(visitor3));
    assertEqual(60L, centre.totalMoneySpent(visitor4));
    assertEqual(60L, centre.totalMoneySpent(visitor5));
    assertEqual(-1120L, centre.totalProfit(exhibitor1));
    assertEqual(30L, centre.totalProfit(exhibitor2));
    assertEqual(40L, centre.totalProfit(exhibitor3));
    assertEqual(-10748990L, centre.totalProfit(exhibitor4));
    assertEqual(0L, centre.totalProfit(exhibitor5));
    centre.removeParticipantFromEvent(event1, visitor1);
    assertEqual(2L, centre.getEvent(event1.getId()).getParticipants().size());
    centre.removeParticipantsFromEvent(event3, SetUtil.set(visitor1, visitor2));
    assertEqual(1L, centre.getEvent(event3.getId()).getParticipants().size());
    centre.removeParticipantsFromEvent(event5, SetUtil.set(visitor1));
    assertEqual(2L, centre.getEvent(event5.getId()).getParticipants().size());
    assertEqual(SetUtil.set(), centre.getEventsFromVisitor(visitor1));
    assertEqual(SetUtil.set(event1, event5), centre.getEventsFromVisitor(visitor2));
    assertEqual(0L, centre.totalMoneySpent(visitor1));
    assertEqual(40L, centre.totalMoneySpent(visitor2));
    assertEqual(SetUtil.set(), centre.getAvailableRooms(date1, date2));
    assertEqual(SetUtil.set(), centre.getAvailableRooms(date1, date3));
    assertEqual(SetUtil.set(), centre.getAvailableRooms(date1, date1));
    assertEqual(SetUtil.set(room3), centre.getAvailableRooms(date3, date4));
    assertEqual(SetUtil.set(), centre.getAvailableRooms(date4, date5));
    assertEqual(SetUtil.set(room1), centre.getAvailableRooms(date5, date5));
    assertTrue(centre.overlapsEvents(date1, date2, room1.getId()));
    assertTrue(centre.overlapsEvents(date1, date2, room2.getId()));
    assertTrue(centre.overlapsEvents(date1, date2, room3.getId()));
    assertTrue(centre.overlapsEvents(date3, date3, room1.getId()));
    assertTrue(centre.overlapsEvents(date3, date4, room2.getId()));
    assertTrue(!(centre.overlapsEvents(date3, date4, room3.getId())));
    assertTrue(centre.overlapsEvents(date4, date5, room1.getId()));
    assertTrue(centre.overlapsEvents(date4, date5, room2.getId()));
    assertTrue(centre.overlapsEvents(date4, date5, room3.getId()));
    assertTrue(!(centre.overlapsEvents(date5, date5, room1.getId())));
    assertTrue(centre.overlapsEvents(date5, date5, room2.getId()));
    assertTrue(centre.overlapsEvents(date5, date5, room3.getId()));
    ExhibitionCentre.getInstance().start();
    Boolean whileCond_1 = true;
    while (whileCond_1) {
      whileCond_1 =
          !(Utils.equals(centre.getNotClosedEvents(), SetUtil.set(event4, event5, event6)));
      if (!(whileCond_1)) {
        break;
      }

      IO.println("\t\tTesting thread");
    }

    assertEqual(SetUtil.set(event4, event5, event6), centre.getNotClosedEvents());
    centre.forceCloseEvent(event5, "password");
    assertEqual(SetUtil.set(event4, event6), centre.getNotClosedEvents());
    assertEqual(SetUtil.set(room2), centre.getAvailableRooms(date4, date5));
    assertTrue(centre.overlapsEvents(date4, date5, room1.getId()));
    assertTrue(!(centre.overlapsEvents(date4, date5, room2.getId())));
    assertTrue(centre.overlapsEvents(date4, date5, room3.getId()));
    assertEqual(SetUtil.set(room1, room2, room3), centre.getAvailableRooms(date1, date2));
    assertTrue(!(centre.overlapsEvents(date1, date2, room1.getId())));
    assertTrue(!(centre.overlapsEvents(date1, date2, room2.getId())));
    assertTrue(!(centre.overlapsEvents(date1, date2, room3.getId())));
    centre.removeEvent(event6);
    assertEqual(5L, centre.getEvents().size());
    assertEqual(SetUtil.set(event4), centre.getNotClosedEvents());
    assertEqual(SetUtil.set(room1, room2, room3), centre.getAvailableRooms(date5, date5));
    event6 = centre.createEvent(date5, date5, room3, exhibitor2, 20L, "event6");
    centre.addParticipantToEvent(event6, visitor4);
    centre.addParticipantToEvent(event6, visitor5);
    event6 = centre.createEvent(date5, date5, room1, exhibitor2, 20L, "event6");
    centre.addParticipantToEvent(event6, visitor4);
    centre.addParticipantToEvent(event6, visitor5);
    event6 = centre.createEvent(date5, date5, room2, exhibitor2, 20L, "event6");
    centre.addParticipantToEvent(event6, visitor4);
    centre.addParticipantToEvent(event6, visitor5);
  }

  private void testpreGetEvent() {

    event2 = centre.getEvent(9999999999999999L);
  }

  private void testpreGetRoom() {

    room3 = centre.getRoom(9999999999999999L);
  }

  private void testpreGetVisitor() {

    visitor5 = centre.getVisitor(9999999999999999L);
  }

  private void testpreGetExhibitor() {

    exhibitor5 = centre.getExhibitor(9999999999999999L);
  }

  private void testpreRemoveEvent() {

    centre.removeEvent(event1);
  }

  private void testpreGetAvailableRooms() {

    assertTrue(
        SetUtil.inSet(
            room1, centre.getAvailableRooms(new Date(2019L, 1L, 2L), new Date(2019L, 1L, 1L))));
  }

  private void testpreOverlapsEvents() {

    assertTrue(
        !(centre.overlapsEvents(new Date(2019L, 1L, 2L), new Date(2019L, 1L, 2L), 999999999999L)));
  }

  private void testpreCreateEvent() {

    event6 = centre.createEvent(date5, date5, room3, exhibitor5, 20L, "event6");
    event6 = centre.createEvent(date5, date5, room3, exhibitor5, 20L, "event6");
  }

  public void test() {

    IO.println("\tExhibition Centre Tests");
    testRooms();
    testEvents();
    testEntities();
    IO.println("\tTestes do Centro de Exposições terminados com sucesso!");
  }

  public TestExhibitionCentre() {}

  public String toString() {

    return "TestExhibitionCentre{"
        + "centre := "
        + Utils.toString(centre)
        + ", visitor1 := "
        + Utils.toString(visitor1)
        + ", visitor2 := "
        + Utils.toString(visitor2)
        + ", visitor3 := "
        + Utils.toString(visitor3)
        + ", visitor4 := "
        + Utils.toString(visitor4)
        + ", visitor5 := "
        + Utils.toString(visitor5)
        + ", exhibitor1 := "
        + Utils.toString(exhibitor1)
        + ", exhibitor3 := "
        + Utils.toString(exhibitor3)
        + ", exhibitor2 := "
        + Utils.toString(exhibitor2)
        + ", exhibitor4 := "
        + Utils.toString(exhibitor4)
        + ", exhibitor5 := "
        + Utils.toString(exhibitor5)
        + ", room1 := "
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
        + ", date5 := "
        + Utils.toString(date5)
        + ", event1 := "
        + Utils.toString(event1)
        + ", event2 := "
        + Utils.toString(event2)
        + ", event3 := "
        + Utils.toString(event3)
        + ", event4 := "
        + Utils.toString(event4)
        + ", event5 := "
        + Utils.toString(event5)
        + ", event6 := "
        + Utils.toString(event6)
        + "}";
  }
}
