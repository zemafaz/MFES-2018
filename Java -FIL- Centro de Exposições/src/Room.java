
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Room {
  private static Number nextId = 1L;
  private Number id_room;
  private Number space;
  private Number pricePerDay;

  public void cg_init_Room_1(final Number s, final Number ppd) {

    space = s;
    pricePerDay = ppd;
    id_room = Room.nextId;
    nextId = Room.nextId.longValue() + 1L;
    return;
  }

  public Room(final Number s, final Number ppd) {

    cg_init_Room_1(s, ppd);
  }

  public Number getSpace() {

    return space;
  }

  public Number getId() {

    return id_room;
  }

  public Number getPricePerDay() {

    return pricePerDay;
  }

  public static Boolean roomExists(final Number idToCheck) {

    return idToCheck.longValue() < Room.nextId.longValue();
  }

  public Room() {}

  public String toString() {

    return 	"Room ID:" + Utils.toString(id_room) + 
    		"\tPrice Per Day:" + Utils.toString(pricePerDay) + 
    		"\tNumber of people allowed:" + Utils.toString(space) + "\n";
  }
}
