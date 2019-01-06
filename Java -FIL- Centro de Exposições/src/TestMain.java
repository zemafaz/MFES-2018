
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestMain {
  public static void main(String args[]) {

    IO.println("Inicializar testes...");
    new TestDate().test();
    new TestRoom().test();
    new TestEntity().test();
    new TestSchedule().test();
    new TestEvent().test();
    new TestExhibitionCentre().test();
    IO.println("Testes terminados com sucesso!");
  }

  public TestMain() {}

  public String toString() {

    return "TestMain{}";
  }
}
