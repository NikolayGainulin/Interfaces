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
        Ticket ticket1 = new Ticket("A", "B", 200, 1000, 1200);
        Ticket ticket2 = new Ticket("A", "B", 100, 900, 1100);
        Ticket ticket3 = new Ticket("A", "B", 300, 800, 1000);

        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);

        Ticket[] result = souls.search("A", "B");
        Ticket[] expected = {ticket2, ticket1, ticket3};

        assertArrayEquals(expected, result);
    }

    @Test
    public void testTicketTimeComparator() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("A", "B", 100, 1000, 1200); // 120 мин
        Ticket t2 = new Ticket("A", "B", 200, 900, 1000);  // 60 мин

        assertTrue(comparator.compare(t1, t2) > 0);
        assertEquals(60, comparator.getFlightTime(t2));
    }

    @Test
    public void testSearchAndSortByTime() {
        AviaSouls souls = new AviaSouls();
        Ticket ticket1 = new Ticket("A", "B", 200, 1000, 1200); // 120 мин
        Ticket ticket2 = new Ticket("A", "B", 100, 900, 1000);   // 60 мин
        Ticket ticket3 = new Ticket("A", "B", 300, 2200, 200);   // 240 мин

        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);

        Ticket[] result = souls.searchAndSortBy("A", "B", new TicketTimeComparator());
        Ticket[] expected = {ticket2, ticket1, ticket3};

        assertArrayEquals(expected, result);
    }

    @Test
    public void testFlightTimeOvernight() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t = new Ticket("A", "B", 100, 2300, 100); // 120 минут
        assertEquals(120, comparator.getFlightTime(t));
    }

    @Test
    public void testNoTicketsFound() {
        AviaSouls souls = new AviaSouls();
        souls.add(new Ticket("A", "B", 100, 900, 1000));

        Ticket[] result = souls.search("C", "D");
        assertArrayEquals(new Ticket[0], result);
    }
}