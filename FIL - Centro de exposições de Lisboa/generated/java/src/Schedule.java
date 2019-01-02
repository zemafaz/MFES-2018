
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Schedule {
  private Room reserva;
  private Date initialDate;
  private Date finalDate;

  public Schedule() {}

  public String toString() {

    return "Schedule{"
        + "reserva := "
        + Utils.toString(reserva)
        + ", initialDate := "
        + Utils.toString(initialDate)
        + ", finalDate := "
        + Utils.toString(finalDate)
        + "}";
  }
}
