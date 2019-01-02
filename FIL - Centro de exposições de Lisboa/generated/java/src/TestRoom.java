
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestRoom extends MyTestCase {
  private Room room1 = new Room(10L);
  private Room room2 = new Room(10L);
  private Room room3 = new Room(10L);

  private void testGets() {

    assertEqual(1L, room1.getId());
    assertEqual(10L, room1.getSpace());
    assertEqual(2L, room2.getId());
    assertEqual(10L, room2.getSpace());
    assertEqual(3L, room3.getId());
    assertEqual(10L, room3.getSpace());
  }

  private void testRoomExists() {

    assertTrue(Room.roomExists(1L));
    assertTrue(Room.roomExists(2L));
    assertTrue(Room.roomExists(3L));
    assertTrue(!(Room.roomExists(4L)));
  }

  private void testSpace0() {

    {
      Room room = new Room(0L);
      assertEqual(0L, room.getSpace());
    }
  }

  private void testExists0() {

    assertTrue(Room.roomExists(1L - 1L));
  }

  public void test() {

    IO.println("\tDate Tests");
    testGets();
    testRoomExists();
    IO.println("\tTestes das datas terminados com sucesso!");
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
