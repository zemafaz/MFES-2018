
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Entity {
  protected Number id;
  protected String name;

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
