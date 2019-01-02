
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestMain {
  public static void main() {

    IO.println("Inicializar testes...");
    new TestDate().test();
    new TestRoom().test();
    IO.println("Testes terminados com sucesso!");
  }

  public TestMain() {}

  public String toString() {

    return "TestMain{}";
  }
}
