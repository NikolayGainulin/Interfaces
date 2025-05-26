import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2) {
        int time1 = getFlightTime(t1);
        int time2 = getFlightTime(t2);
        return time1 - time2;
    }

    int getFlightTime(Ticket ticket) {
        int from = ticket.getTimeFrom();
        int to = ticket.getTimeTo();

        int fromHours = from / 100;
        int fromMinutes = from % 100;
        int totalFrom = fromHours * 60 + fromMinutes;

        int toHours = to / 100;
        int toMinutes = to % 100;
        int totalTo = toHours * 60 + toMinutes;

        if (totalTo < totalFrom) {
            totalTo += 24 * 60;
        }

        return totalTo - totalFrom;
    }
}