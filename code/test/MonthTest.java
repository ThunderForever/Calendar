import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonthTest {

    private CalendarPad calendarPad;
    private Month changemonth;
    @Before
    public void setUp() throws Exception {
        calendarPad=new CalendarPad(2019,5,1);
        changemonth = new Month(calendarPad);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getMonth() {
        assertEquals(5,changemonth.month);
    }

    @Test
    public void setMonth() {
        changemonth.setMonth(4);
        assertEquals(4,changemonth.month);
    }


    @Test
    public void actionPerformed() {
    }
}