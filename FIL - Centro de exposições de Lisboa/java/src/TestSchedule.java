
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestSchedule extends MyTestCase {
  private Room room1 = new Room(10L, 100L);
  private Room room2 = new Room(10L, 100L);
  private Room room3 = new Room(10L, 100L);
  private Date date1 = new Date(2019L, 1L, 1L);
  private Date date2 = new Date(2019L, 1L, 1L);
  private Date date3 = new Date(2019L, 1L, 2L);
  private Date date4 = new Date(2019L, 1L, 3L);
  private Schedule schedule1 = new Schedule(room1, date1, date2);
  private Schedule schedule2 = new Schedule(room2, date1, date1);
  private Schedule schedule3 = new Schedule(room3, date3, date3);

  private void testGets() {

    assertEqual(10L, schedule1.getRoomSpace());
    assertEqual(room1.getId(), schedule1.getIdRoom());
    assertEqual(100L, schedule1.getPricePerDay());
    assertEqual(date2, schedule1.getFinalDate());
    assertEqual(date1, schedule1.getInitialDate());
    assertEqual(1L, schedule1.getNumberOfDays());
    assertEqual(10L, schedule2.getRoomSpace());
    assertEqual(room2.getId(), schedule2.getIdRoom());
    assertEqual(100L, schedule2.getPricePerDay());
    assertEqual(date1, schedule2.getFinalDate());
    assertEqual(date1, schedule2.getInitialDate());
    assertEqual(1L, schedule1.getNumberOfDays());
    assertEqual(10L, schedule3.getRoomSpace());
    assertEqual(room3.getId(), schedule3.getIdRoom());
    assertEqual(100L, schedule3.getPricePerDay());
    assertEqual(date3, schedule3.getFinalDate());
    assertEqual(date3, schedule3.getInitialDate());
    assertEqual(1L, schedule3.getNumberOfDays());
  }

  private void testOverlaps() {

    assertTrue(!(schedule1.overlaps(date3, date4, room1.getId())));
    assertTrue(schedule1.overlaps(date2, date3, room1.getId()));
    assertTrue(schedule1.overlaps(date1, date1, room1.getId()));
    assertTrue(!(schedule1.overlaps(date1, date1, room2.getId())));
    assertTrue(!(schedule2.overlaps(date3, date4, room2.getId())));
    assertTrue(schedule2.overlaps(date2, date3, room2.getId()));
    assertTrue(schedule2.overlaps(date1, date2, room2.getId()));
    assertTrue(!(schedule2.overlaps(date2, date3, room1.getId())));
    assertTrue(schedule3.overlaps(date2, date3, room3.getId()));
    assertTrue(!(schedule3.overlaps(date1, date2, room3.getId())));
    assertTrue(schedule3.overlaps(date3, date4, room3.getId()));
    assertTrue(!(schedule3.overlaps(date4, date4, room3.getId())));
  }

  private void testFinalDateIsSmaller() {

    {
      Schedule finalDateIsSmaller = new Schedule(room3, date4, date3);
      assertEqual(100L, finalDateIsSmaller.getPricePerDay());
    }
  }

  private void testFinalDateIsSmallerOverlaps() {

    assertTrue(schedule1.overlaps(date4, date3, room1.getId()));
  }

  private void testRoomExistsOverlaps() {

    assertTrue(schedule1.overlaps(date3, date3, 1243243L));
  }

  public void test() {

    IO.println("\tSchedule Tests");
    testGets();
    testOverlaps();
    IO.println("\tTestes das reservas terminados com sucesso!");
  }

  public TestSchedule() {}

  public String toString() {

    return "TestSchedule{"
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
        + "}";
  }
}
