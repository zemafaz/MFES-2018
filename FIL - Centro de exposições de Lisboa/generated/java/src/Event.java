
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Event {
  private Schedule schedule;
  private VDMSet visitor;
  private Exhibitor exhibitor;

  public Event() {}

  public String toString() {

    return "Event{"
        + "schedule := "
        + Utils.toString(schedule)
        + ", visitor := "
        + Utils.toString(visitor)
        + ", exhibitor := "
        + Utils.toString(exhibitor)
        + "}";
  }
}
