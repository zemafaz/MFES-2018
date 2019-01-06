
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestRoom extends MyTestCase {
  private Room room1 = new Room(10L, 100L);
  private Room room2 = new Room(10L, 100L);
  private Room room3 = new Room(10L, 100L);

  private void testGets() {

    assertEqual(1L, room1.getId());
    assertEqual(10L, room1.getSpace());
    assertEqual(2L, room2.getId());
    assertEqual(10L, room2.getSpace());
    assertEqual(3L, room3.getId());
    assertEqual(10L, room3.getSpace());
    assertEqual(100L, room1.getPricePerDay());
    assertEqual(100L, room2.getPricePerDay());
    assertEqual(100L, room3.getPricePerDay());
  }

  private void testRoomExists() {

    assertTrue(Room.roomExists(1L));
    assertTrue(Room.roomExists(2L));
    assertTrue(Room.roomExists(3L));
    assertTrue(!(Room.roomExists(4L)));
  }

  public void test() {

    IO.println("\tRoom Tests");
    testGets();
    testRoomExists();
    IO.println("\tTestes das salas terminados com sucesso!");
  }

  public TestRoom() {}

  public String toString() {

    return "TestRoom{"
        + "room1 := "
        + Utils.toString(room1)
        + ", room2 := "
        + Utils.toString(room2)
        + ", room3 := "
        + Utils.toString(room3)
        + "}";
  }
}
