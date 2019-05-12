import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

import static org.junit.Assert.*;

public class YearTest {

    private CalendarPad calendarPad;
    private Year changeyear;

    @Before
    public void setUp() throws Exception {
        calendarPad=new CalendarPad(2019,5,1);
        changeyear = new Year(calendarPad);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getYear() {
        assertEquals(2019,changeyear.year);
    }

    @Test
    public void setYear() {
        changeyear.setYear(2018);
        assertEquals(2018,changeyear.year);
    }

    @Test
    public void actionPerformed() {

    }
}