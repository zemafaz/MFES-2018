
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ExhibitionCentre {
  private VDMSet event;
  private VDMSet rooms;

  public ExhibitionCentre() {}

  public String toString() {

    return "ExhibitionCentre{"
        + "event := "
        + Utils.toString(event)
        + ", rooms := "
        + Utils.toString(rooms)
        + "}";
  }
}
