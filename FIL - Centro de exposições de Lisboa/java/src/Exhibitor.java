
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Exhibitor extends Entity {
  private static Number nextId = 1L;

  public void cg_init_Exhibitor_1(final String n) {

    id = Exhibitor.nextId;
    nextId = Exhibitor.nextId.longValue() + 1L;
    name = n;
    return;
  }

  public Exhibitor(final String n) {

    cg_init_Exhibitor_1(n);
  }

  public Exhibitor() {}

  public String toString() {

    return "Exhibitor{" + "nextId := " + Utils.toString(nextId) + "}";
  }
}
