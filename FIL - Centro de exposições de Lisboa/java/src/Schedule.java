
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Schedule {
  private Room room;
  private Date initialDate;
  private Date finalDate;

  public void cg_init_Schedule_1(final Room r, final Date iDate, final Date fDate) {

    room = r;
    initialDate = iDate;
    finalDate = fDate;
    return;
  }

  public Schedule(final Room r, final Date iDate, final Date fDate) {

    cg_init_Schedule_1(r, iDate, fDate);
  }

  public Number getRoomSpace() {

    return room.getSpace();
  }

  public Number getIdRoom() {

    return room.getId();
  }

  public Number getPricePerDay() {

    return room.getPricePerDay();
  }

  public Date getFinalDate() {

    return finalDate;
  }

  public Date getInitialDate() {

    return initialDate;
  }

  public Number getNumberOfDays() {

    return Date.daysElapsed(initialDate, finalDate);
  }

  public Boolean overlaps(final Date iDate, final Date fDate, final Number idRoom) {

    if (!(Utils.equals(idRoom, room.getId()))) {
      return false;

    } else {
      if (iDate.compareDate(finalDate)) {
        return false;

      } else {
        if (initialDate.compareDate(fDate)) {
          return false;

        } else {
          return true;
        }
      }
    }
  }

  public Schedule() {}

  public String toString() {

    return "Schedule{"
        + "room := "
        + Utils.toString(room)
        + ", initialDate := "
        + Utils.toString(initialDate)
        + ", finalDate := "
        + Utils.toString(finalDate)
        + "}";
  }
}
