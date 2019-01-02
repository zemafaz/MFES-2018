
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestDate extends MyTestCase {
  private Date date1 = new Date(2019L, 1L, 31L);
  private Date date2 = new Date(2019L, 2L, 28L);
  private Date date3 = new Date(2020L, 2L, 29L);
  private Date date4 = new Date(2019L, 4L, 1L);
  private Date date5 = new Date(2019L, 1L, 1L);
  private Date date6 = new Date(2019L, 1L, 31L);

  private void testGets() {

    assertEqual(2019L, date1.getYear());
    assertEqual(1L, date1.getMonth());
    assertEqual(31L, date1.getDay());
    assertEqual(2019L, date2.getYear());
    assertEqual(2L, date2.getMonth());
    assertEqual(28L, date2.getDay());
    assertEqual(2020L, date3.getYear());
    assertEqual(2L, date3.getMonth());
    assertEqual(29L, date3.getDay());
    assertEqual(2019L, date4.getYear());
    assertEqual(4L, date4.getMonth());
    assertEqual(1L, date4.getDay());
  }

  private void testCompareDate() {

    assertTrue(!(date1.compareDate(date3)));
    assertTrue(date3.compareDate(date1));
    assertTrue(date1.compareDate(date5));
    assertTrue(!(date5.compareDate(date1)));
    assertTrue(date4.compareDate(date1));
    assertTrue(!(date1.compareDate(date4)));
    assertTrue(!(date1.compareDate(date6)));
    assertTrue(!(date6.compareDate(date1)));
  }

  private void testPreConditionYearDate() {

    {
      Date wrongYear = new Date(2018L, 1L, 1L);
      assertEqual(2018L, wrongYear.getYear());
    }
  }

  private void testPreConditionMonthDate() {

    {
      Date wrongMonth = new Date(2019L, 13L, 1L);
      assertEqual(13L, wrongMonth.getMonth());
    }
  }

  private void testPreConditionDayDate() {

    {
      Date wrongDay = new Date(2019L, 2L, 29L);
      assertEqual(29L, wrongDay.getDay());
    }
  }

  public void test() {

    IO.println("\tDate Tests");
    testGets();
    testCompareDate();
    IO.println("\tTestes das datas terminados com sucesso!");
  }

  public TestDate() {}

  public String toString() {

    return "TestDate{"
        + "date1 := "
        + Utils.toString(date1)
        + ", date2 := "
        + Utils.toString(date2)
        + ", date3 := "
        + Utils.toString(date3)
        + ", date4 := "
        + Utils.toString(date4)
        + ", date5 := "
        + Utils.toString(date5)
        + ", date6 := "
        + Utils.toString(date6)
        + "}";
  }
}
