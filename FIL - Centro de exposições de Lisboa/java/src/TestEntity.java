
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestEntity extends MyTestCase {
  private Visitor visitor1 = new Visitor("João");
  private Visitor visitor2 = new Visitor("José");
  private Visitor visitor3 = new Visitor("Maria");
  private Exhibitor exhibitor1 = new Exhibitor("Paulo");
  private Exhibitor exhibitor2 = new Exhibitor("Ana");
  private Exhibitor exhibitor3 = new Exhibitor("Sofia");

  private void testGets() {

    assertEqual(1L, visitor1.getId());
    assertEqual(2L, visitor2.getId());
    assertEqual(3L, visitor3.getId());
    assertEqual(1L, exhibitor1.getId());
    assertEqual(2L, exhibitor2.getId());
    assertEqual(3L, exhibitor3.getId());
    assertEqual("João", visitor1.getName());
    assertEqual("José", visitor2.getName());
    assertEqual("Maria", visitor3.getName());
    assertEqual("Paulo", exhibitor1.getName());
    assertEqual("Ana", exhibitor2.getName());
    assertEqual("Sofia", exhibitor3.getName());
  }

  private void testNameTooBig() {

    {
      Exhibitor NameTooBig =
          new Exhibitor("O meu nome tem mais de cinquenta caracteres por isso dá erro");
      assertEqual(
          "O meu nome tem mais de cinquenta caracteres por isso dá erro", NameTooBig.getName());
    }
  }

  public void test() {

    IO.println("\tEntity Tests");
    testGets();
    IO.println("\tTestes das entidades terminados com sucesso!");
  }

  public TestEntity() {}

  public String toString() {

    return "TestEntity{"
        + "visitor1 := "
        + Utils.toString(visitor1)
        + ", visitor2 := "
        + Utils.toString(visitor2)
        + ", visitor3 := "
        + Utils.toString(visitor3)
        + ", exhibitor1 := "
        + Utils.toString(exhibitor1)
        + ", exhibitor2 := "
        + Utils.toString(exhibitor2)
        + ", exhibitor3 := "
        + Utils.toString(exhibitor3)
        + "}";
  }
}
