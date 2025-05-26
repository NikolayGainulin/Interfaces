import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AviaSoulsTest {

    @Test
    public void testTicketCompareTo() {
        Ticket ticket1 = new Ticket("A", "B", 100, 1000, 1200);
        Ticket ticket2 = new Ticket("A", "B", 150, 1100, 1300);
        assertTrue(ticket1.compareTo(ticket2) < 0);
        assertEquals(0, ticket1.compareTo(ticket1));
        assertTrue(ticket2.compareTo(ticket1) > 0);
    }

    @Test
    public void testSearchSortsByPrice() {
        AviaSouls souls = new AviaSouls();
        souls.add(new Ticket("A", "B", 200, 1000, 1200));
        souls.add(new Ticket("A", "B", 100, 900, 1100));
        souls.add(new Ticket("A", "B", 300, 800, 1000));

        Ticket[] result = souls.search("A", "B");
        assertEquals(3, result.length);
        assertEquals(100, result[0].getPrice());
        assertEquals(200, result[1].getPrice());
        assertEquals(300, result[2].getPrice());
    }

    @Test
    public void testTicketTimeComparator() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("A", "B", 100, 1000, 1200); // 120 минут
        Ticket t2 = new Ticket("A", "B", 200, 900, 1000);  // 60 минут

        assertTrue(comparator.compare(t1, t2) > 0);
        assertEquals(60, comparator.compare(t2, t1) * -1);
    }

    @Test
    public void testSearchAndSortByTime() {
        AviaSouls souls = new AviaSouls();
        souls.add(new Ticket("A", "B", 200, 1000, 1200)); // 120 минут
        souls.add(new Ticket("A", "B", 100, 900, 1000));  // 60 минут
        souls.add(new Ticket("A", "B", 300, 2200, 200));   // 240 минут

        Ticket[] result = souls.searchAndSortBy("A", "B", new TicketTimeComparator());
        assertEquals(3, result.length);
        assertEquals(100, result[0].getPrice());
        assertEquals(200, result[1].getPrice());
        assertEquals(300, result[2].getPrice());
    }

    @Test
    public void testFlightTimeOvernight() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t = new Ticket("A", "B", 100, 2300, 100); // 120 минут
        assertEquals(120, comparator.getFlightTime(t));
    }
}