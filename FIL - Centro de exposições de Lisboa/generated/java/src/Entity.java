
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Entity {
  protected Number id;
  protected String name;

  public void cg_init_Entity_1(final Number i, final String n) {

    id = i;
    name = n;
    return;
  }

  public Entity(final Number i, final String n) {

    cg_init_Entity_1(i, n);
  }

  public Number getId() {

    return id;
  }

  public String getName() {

    return name;
  }

  public Entity() {}

  public String toString() {

    return "Entity{" + "id := " + Utils.toString(id) + ", name := " + Utils.toString(name) + "}";
  }
}
