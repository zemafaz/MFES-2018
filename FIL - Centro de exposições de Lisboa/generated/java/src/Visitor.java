
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Visitor extends Entity {
  private static Number nextId = 1L;

  public void cg_init_Visitor_1(final String n) {

    id = Visitor.nextId;
    nextId = Visitor.nextId.longValue() + 1L;
    name = n;
    return;
  }

  public Visitor(final String n) {

    cg_init_Visitor_1(n);
  }

  public Visitor() {}

  public String toString() {

    return "Visitor{" + "nextId := " + Utils.toString(nextId) + "}";
  }
}
