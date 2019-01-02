
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Date {
  private Number year;
  private Number month;
  private Number day;

  public void cg_init_Date_1(final Number y, final Number m, final Number d) {

    year = y;
    month = m;
    day = d;
    return;
  }

  public Date(final Number y, final Number m, final Number d) {

    cg_init_Date_1(y, m, d);
  }

  public Number getYear() {

    return year;
  }

  public Number getMonth() {

    return month;
  }

  public Number getDay() {

    return day;
  }

  public Boolean compareDate(final Date d) {

    if (d.getYear().longValue() < year.longValue()) {
      return true;

    } else {
      if (d.getYear().longValue() > year.longValue()) {
        return false;

      } else {
        if (d.getMonth().longValue() < month.longValue()) {
          return true;

        } else {
          if (d.getMonth().longValue() > month.longValue()) {
            return false;

          } else {
            if (d.getDay().longValue() < day.longValue()) {
              return true;

            } else {
              return false;
            }
          }
        }
      }
    }
  }

  public Date() {}

  private static Boolean IsLeapYear(final Number year_1) {

    Boolean orResult_1 = false;

    Boolean andResult_4 = false;

    if (Utils.equals(Utils.mod(year_1.longValue(), 4L), 0L)) {
      if (!(Utils.equals(Utils.mod(year_1.longValue(), 100L), 0L))) {
        andResult_4 = true;
      }
    }

    if (andResult_4) {
      orResult_1 = true;
    } else {
      orResult_1 = Utils.equals(Utils.mod(year_1.longValue(), 400L), 0L);
    }

    return orResult_1;
  }

  private static Number DaysOfMonth(final Number year, final Number month_1) {

    Number casesExpResult_1 = null;

    Number intPattern_1 = month_1;
    Boolean success_1 = Utils.equals(intPattern_1, 1L);

    if (!(success_1)) {
      Number intPattern_2 = month_1;
      success_1 = Utils.equals(intPattern_2, 3L);

      if (!(success_1)) {
        Number intPattern_3 = month_1;
        success_1 = Utils.equals(intPattern_3, 5L);

        if (!(success_1)) {
          Number intPattern_4 = month_1;
          success_1 = Utils.equals(intPattern_4, 7L);

          if (!(success_1)) {
            Number intPattern_5 = month_1;
            success_1 = Utils.equals(intPattern_5, 8L);

            if (!(success_1)) {
              Number intPattern_6 = month_1;
              success_1 = Utils.equals(intPattern_6, 10L);

              if (!(success_1)) {
                Number intPattern_7 = month_1;
                success_1 = Utils.equals(intPattern_7, 12L);

                if (!(success_1)) {
                  Number intPattern_8 = month_1;
                  success_1 = Utils.equals(intPattern_8, 4L);

                  if (!(success_1)) {
                    Number intPattern_9 = month_1;
                    success_1 = Utils.equals(intPattern_9, 6L);

                    if (!(success_1)) {
                      Number intPattern_10 = month_1;
                      success_1 = Utils.equals(intPattern_10, 9L);

                      if (!(success_1)) {
                        Number intPattern_11 = month_1;
                        success_1 = Utils.equals(intPattern_11, 11L);

                        if (!(success_1)) {
                          Number intPattern_12 = month_1;
                          success_1 = Utils.equals(intPattern_12, 2L);

                          if (success_1) {
                            Number ternaryIfExp_1 = null;

                            if (IsLeapYear(year)) {
                              ternaryIfExp_1 = 29L;
                            } else {
                              ternaryIfExp_1 = 28L;
                            }

                            casesExpResult_1 = ternaryIfExp_1;
                          }

                        } else {
                          casesExpResult_1 = 30L;
                        }

                      } else {
                        casesExpResult_1 = 30L;
                      }

                    } else {
                      casesExpResult_1 = 30L;
                    }

                  } else {
                    casesExpResult_1 = 30L;
                  }

                } else {
                  casesExpResult_1 = 31L;
                }

              } else {
                casesExpResult_1 = 31L;
              }

            } else {
              casesExpResult_1 = 31L;
            }

          } else {
            casesExpResult_1 = 31L;
          }

        } else {
          casesExpResult_1 = 31L;
        }

      } else {
        casesExpResult_1 = 31L;
      }

    } else {
      casesExpResult_1 = 31L;
    }

    return casesExpResult_1;
  }

  public String toString() {

    return "Date{"
        + "year := "
        + Utils.toString(year)
        + ", month := "
        + Utils.toString(month)
        + ", day := "
        + Utils.toString(day)
        + "}";
  }
}
