
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Room {
  public static Number nextId = 1L;
  private Number id_room;
  private Number space;

  public void cg_init_Room_1(final Number s) {

    space = s;
    id_room = Room.nextId;
    nextId = Room.nextId.longValue() + 1L;
    return;
  }

  public Room(final Number s) {

    cg_init_Room_1(s);
  }

  public Number getSpace() {

    return space;
  }

  public Number getId() {

    return id_room;
  }

  public static Boolean roomExists(final Number idToCheck) {

    return idToCheck.longValue() < Room.nextId.longValue();
  }

  public Room() {}

  public String toString() {

    return "Room{"
        + "nextId := "
        + Utils.toString(nextId)
        + ", id_room := "
        + Utils.toString(id_room)
        + ", space := "
        + Utils.toString(space)
        + "}";
  }
}
