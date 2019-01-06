
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Event {
  private static final String forceClosePassWord = "password";
  public static Number nextId = 1L;
  private Number id_event;
  private String name;
  private Schedule schedule;
  private VDMSet participants;
  private Exhibitor exhibitor;
  private Number pricePerParticipant;
  private Boolean closed;

  public void cg_init_Event_1(
      final Schedule sch, final Exhibitor exhi, final Number price, final String n) {

    schedule = sch;
    exhibitor = exhi;
    pricePerParticipant = price;
    closed = false;
    participants = SetUtil.set();
    id_event = Event.nextId;
    nextId = Event.nextId.longValue() + 1L;
    name = n;
  }

  public Event(final Schedule sch, final Exhibitor exhi, final Number price, final String n) {

    cg_init_Event_1(sch, exhi, price, n);
  }

  public void closeEvent(final Date currentDate) {

    closed = true;
  }

  public Boolean has_started() {

    return !(schedule
        .getInitialDate()
        .compareDate(CurrentTime.getYear(), CurrentTime.getMonth(), CurrentTime.getDay()));
  }

  public Boolean has_ended() {

    return !(schedule
        .getFinalDate()
        .compareDate(CurrentTime.getYear(), CurrentTime.getMonth(), CurrentTime.getDay()));
  }

  public Number getId() {

    return id_event;
  }

  public String getName() {

    return name;
  }

  public void forceCloseEvent(final String password) {

    closed = true;
    participants = SetUtil.set();
  }

  public Number getPricePerParticipant() {

    return pricePerParticipant;
  }

  public Boolean isClosed() {

    return closed;
  }

  public Number getCostOfEvent() {

    return schedule.getPricePerDay().longValue() * schedule.getNumberOfDays().longValue();
  }

  public Number getSalesValueOfEvent() {

    return participants.size() * pricePerParticipant.longValue();
  }

  public Number profit() {

    return getSalesValueOfEvent().longValue() - getCostOfEvent().longValue();
  }

  public Exhibitor getExhibitor() {

    return exhibitor;
  }

  public VDMSet getParticipants() {

    return Utils.copy(participants);
  }

  public void addParticipant(final Visitor participant) {

    participants = SetUtil.union(Utils.copy(participants), SetUtil.set(participant));
  }

  public void removeParticipant(final Visitor participant) {

    participants = SetUtil.diff(Utils.copy(participants), SetUtil.set(participant));
  }

  public Boolean has_participant(final Visitor participant) {

    return SetUtil.inSet(participant, participants);
  }

  public Boolean overlaps(final Date iDate, final Date fDate, final Number idRoom) {

    return schedule.overlaps(iDate, fDate, idRoom);
  }

  public Event() {}

  public String toString() {

    return "Event ID: " + Utils.toString(id_event) +
    		"\n\t Schedule: " + schedule.toString() + 
    		"\t Name: " + name + 
    		"\n\t Exhibitor: " + exhibitor.toString()+
    		"\t Price To Participate: " + Utils.toString(pricePerParticipant) +"\n";
  }
  
  public String toStringComplete() {
	  String stringtoreturn=toString() +
			  "\t Closed: "+ closed.toString() + 
			  "\n\t Cost for the Exhibitor: "+ Utils.toString(getCostOfEvent())+
			  "\n\t Total Sales: " + Utils.toString(getSalesValueOfEvent()) + 
			  "\n\t Profit for the exhibitor: " + Utils.toString(profit()) + 
			  "\n\tParticipants:\n";
	  for (Iterator iterator_24 = participants.iterator(); iterator_24.hasNext(); ) {
	        Visitor x = (Visitor) iterator_24.next();
	        stringtoreturn+="\t"+x.toString();
	      }
	  return stringtoreturn+"\n";
  }
}
