
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ExhibitionCentre {
  private static ExhibitionCentre centre = new ExhibitionCentre();
  private VDMSet events;
  private VDMSet rooms;
  private VDMSet visitors;
  private VDMSet exhibitors;

  private void cg_init_ExhibitionCentre_1() {

    events = SetUtil.set();
    rooms = SetUtil.set();
    visitors = SetUtil.set();
    exhibitors = SetUtil.set();
    return;
  }

  private ExhibitionCentre() {

    cg_init_ExhibitionCentre_1();
  }

  public static ExhibitionCentre getInstance() {

    return ExhibitionCentre.centre;
  }

  public VDMSet getVisitors() {

    return Utils.copy(visitors);
  }

  public VDMSet getExhibitors() {

    return Utils.copy(exhibitors);
  }

  public VDMSet getEvents() {

    return Utils.copy(events);
  }

  public VDMSet getRooms() {

    return Utils.copy(rooms);
  }

  public Event getEvent(final Number id) {

    {
      Event event = null;
      for (Iterator iterator_24 = events.iterator(); iterator_24.hasNext(); ) {
        Event x = (Event) iterator_24.next();
        if (Utils.equals(x.getId(), id)) {
          event = x;
        }
      }
      return event;
    }
  }

  public Room getRoom(final Number id) {

    {
      Room room = null;
      for (Iterator iterator_25 = rooms.iterator(); iterator_25.hasNext(); ) {
        Room x = (Room) iterator_25.next();
        if (Utils.equals(x.getId(), id)) {
          room = x;
        }
      }
      return room;
    }
  }

  public Visitor getVisitor(final Number id) {

    {
      Visitor visitor = null;
      for (Iterator iterator_26 = visitors.iterator(); iterator_26.hasNext(); ) {
        Visitor x = (Visitor) iterator_26.next();
        if (Utils.equals(x.getId(), id)) {
          visitor = x;
        }
      }
      return visitor;
    }
  }

  public Exhibitor getExhibitor(final Number id) {

    {
      Exhibitor exhibitor = null;
      for (Iterator iterator_27 = exhibitors.iterator(); iterator_27.hasNext(); ) {
        Exhibitor x = (Exhibitor) iterator_27.next();
        if (Utils.equals(x.getId(), id)) {
          exhibitor = x;
        }
      }
      return exhibitor;
    }
  }

  public Visitor registerVisitor(final String v) {

    {
      Visitor visitor = new Visitor(v);
      visitors = SetUtil.union(Utils.copy(visitors), SetUtil.set(visitor));
      return visitor;
    }
  }

  public Exhibitor registerExhibitor(final String e) {

    {
      Exhibitor exhibitor = new Exhibitor(e);
      exhibitors = SetUtil.union(Utils.copy(exhibitors), SetUtil.set(exhibitor));
      return exhibitor;
    }
  }

  public void removeVisitor(final Visitor visitor) {

    visitors = SetUtil.diff(Utils.copy(visitors), SetUtil.set(visitor));
    for (Iterator iterator_28 = events.iterator(); iterator_28.hasNext(); ) {
      Event event = (Event) iterator_28.next();
      Boolean andResult_15 = false;

      if (event.has_participant(visitor)) {
        Boolean andResult_16 = false;

        if (!(event.has_started())) {
          if (!(event.isClosed())) {
            andResult_16 = true;
          }
        }

        if (andResult_16) {
          andResult_15 = true;
        }
      }

      if (andResult_15) {
        event.removeParticipant(visitor);
      }
    }
  }

  public void removeExhibitor(final Exhibitor exhibitor) {

    exhibitors = SetUtil.diff(Utils.copy(exhibitors), SetUtil.set(exhibitor));
    for (Iterator iterator_29 = events.iterator(); iterator_29.hasNext(); ) {
      Event event = (Event) iterator_29.next();
      Boolean andResult_20 = false;

      if (!(event.isClosed())) {
        Boolean andResult_21 = false;

        if (Utils.equals(event.getExhibitor(), exhibitor)) {
          if (!(event.has_started())) {
            andResult_21 = true;
          }
        }

        if (andResult_21) {
          andResult_20 = true;
        }
      }

      if (andResult_20) {
        removeEvent(event);
      }
    }
  }

  public void removeEvent(final Event event) {

    events = SetUtil.diff(Utils.copy(events), SetUtil.set(event));
  }

  public Event createEvent(
      final Date initialDate,
      final Date finalDate,
      final Room room,
      final Exhibitor exhibitor,
      final Number price,
      final String name) {

    {
      Schedule shcedule = new Schedule(room, initialDate, finalDate);
      Event eventtoadd = new Event(shcedule, exhibitor, price, name);
      events = SetUtil.union(Utils.copy(events), SetUtil.set(eventtoadd));
      return eventtoadd;
    }
  }

  public void closeCompletedEvents() {

    {
      Date currentDate =
          new Date(CurrentTime.getYear(), CurrentTime.getMonth(), CurrentTime.getDay());
      for (Iterator iterator_30 = events.iterator(); iterator_30.hasNext(); ) {
        Event event = (Event) iterator_30.next();
        Boolean andResult_28 = false;

        if (event.has_ended()) {
          if (!(event.isClosed())) {
            andResult_28 = true;
          }
        }

        if (andResult_28) {
          event.closeEvent(currentDate);
        }
      }
    }
  }

  public void forceCloseEvent(final Event event, final String password) {

    event.forceCloseEvent(password);
  }

  public void addParticipantToEvent(final Event event, final Visitor participant) {

    event.addParticipant(participant);
  }

  public void removeParticipantFromEvent(final Event event, final Visitor participant) {

    event.removeParticipant(participant);
  }

  public void addParticipantsToEvent(final Event event, final VDMSet participants) {

    for (Iterator iterator_31 = participants.iterator(); iterator_31.hasNext(); ) {
      Visitor participant = (Visitor) iterator_31.next();
      event.addParticipant(participant);
    }
  }

  public void removeParticipantsFromEvent(final Event event, final VDMSet participants) {

    for (Iterator iterator_32 = participants.iterator(); iterator_32.hasNext(); ) {
      Visitor participant = (Visitor) iterator_32.next();
      event.removeParticipant(participant);
    }
  }

  public Room addRoom(final Number priceToSchedule, final Number space) {

    {
      Room room = new Room(space, priceToSchedule);
      rooms = SetUtil.union(Utils.copy(rooms), SetUtil.set(room));
      return room;
    }
  }

  public void removeRoom(final Room room) {

    rooms = SetUtil.diff(Utils.copy(rooms), SetUtil.set(room));
  }

  public VDMSet getAvailableRooms(final Date iDate, final Date fDate) {

    VDMSet setCompResult_1 = SetUtil.set();
    VDMSet set_12 = Utils.copy(rooms);
    for (Iterator iterator_12 = set_12.iterator(); iterator_12.hasNext(); ) {
      Room room = ((Room) iterator_12.next());
      Boolean forAllExpResult_2 = true;
      VDMSet set_13 = Utils.copy(events);
      for (Iterator iterator_13 = set_13.iterator(); iterator_13.hasNext() && forAllExpResult_2; ) {
        Event event = ((Event) iterator_13.next());
        Boolean orResult_7 = false;

        if (!(event.overlaps(iDate, fDate, room.getId()))) {
          orResult_7 = true;
        } else {
          orResult_7 = event.isClosed();
        }

        forAllExpResult_2 = orResult_7;
      }
      if (forAllExpResult_2) {
        setCompResult_1.add(room);
      }
    }
    return Utils.copy(setCompResult_1);
  }

  public Boolean overlapsEvents(final Date iDate, final Date fDate, final Number idRoom) {

    Boolean existsExpResult_6 = false;
    VDMSet set_15 = Utils.copy(events);
    for (Iterator iterator_15 = set_15.iterator();
        iterator_15.hasNext() && !(existsExpResult_6);
        ) {
      Event event = ((Event) iterator_15.next());
      Boolean andResult_34 = false;

      if (event.overlaps(iDate, fDate, idRoom)) {
        if (!(event.isClosed())) {
          andResult_34 = true;
        }
      }

      existsExpResult_6 = andResult_34;
    }
    return existsExpResult_6;
  }

  public VDMSet getEventsFromVisitor(final Visitor visitor) {

    VDMSet setCompResult_2 = SetUtil.set();
    VDMSet set_16 = Utils.copy(events);
    for (Iterator iterator_16 = set_16.iterator(); iterator_16.hasNext(); ) {
      Event event = ((Event) iterator_16.next());
      if (event.has_participant(visitor)) {
        setCompResult_2.add(event);
      }
    }
    return Utils.copy(setCompResult_2);
  }

  public VDMSet getNotClosedEvents() {

    VDMSet setCompResult_3 = SetUtil.set();
    VDMSet set_17 = Utils.copy(events);
    for (Iterator iterator_17 = set_17.iterator(); iterator_17.hasNext(); ) {
      Event event = ((Event) iterator_17.next());
      if (!(event.isClosed())) {
        setCompResult_3.add(event);
      }
    }
    return Utils.copy(setCompResult_3);
  }

  public VDMSet getEventsFromExhibitor(final Exhibitor exhibitor) {

    VDMSet setCompResult_4 = SetUtil.set();
    VDMSet set_18 = Utils.copy(events);
    for (Iterator iterator_18 = set_18.iterator(); iterator_18.hasNext(); ) {
      Event event = ((Event) iterator_18.next());
      if (Utils.equals(event.getExhibitor(), exhibitor)) {
        setCompResult_4.add(event);
      }
    }
    return Utils.copy(setCompResult_4);
  }

  public VDMSeq getEventStatistics(final Event event) {

    return SeqUtil.seq(
        event.getPricePerParticipant(),
        event.getCostOfEvent(),
        event.getSalesValueOfEvent(),
        event.profit(),
        event.getParticipants().size());
  }

  public Number totalMoneySpent(final Visitor visitor) {

    {
      Number total = 0L;
      for (Iterator iterator_33 = events.iterator(); iterator_33.hasNext(); ) {
        Event event = (Event) iterator_33.next();
        if (event.has_participant(visitor)) {
          total = total.longValue() + event.getPricePerParticipant().longValue();
        }
      }
      return total;
    }
  }

  public Number totalProfit(final Exhibitor exhibitor) {

    {
      Number total = 0L;
      for (Iterator iterator_34 = events.iterator(); iterator_34.hasNext(); ) {
        Event event = (Event) iterator_34.next();
        if (Utils.equals(event.getExhibitor(), exhibitor)) {
          total = total.longValue() + event.profit().longValue();
        }
      }
      return total;
    }
  }

  public String toString() {

    return "ExhibitionCentre{"
        + "centre := "
        + Utils.toString(centre)
        + ", events := "
        + Utils.toString(events)
        + ", rooms := "
        + Utils.toString(rooms)
        + ", visitors := "
        + Utils.toString(visitors)
        + ", exhibitors := "
        + Utils.toString(exhibitors)
        + "}";
  }
}
