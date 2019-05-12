import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotePadTest {

    private CalendarPad calendarPad;
    private NotePad notePad;

    @Before
    public void setUp() throws Exception {
        calendarPad=new CalendarPad(2019,5,1);
        notePad = new NotePad(calendarPad);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setYear() {
        assertEquals(2019,notePad.year);
    }

    @Test
    public void setMonth() {
        assertEquals(5,notePad.month);
    }

    @Test
    public void setDay() {
        assertEquals(1,notePad.day);
    }

    @Test
    public void setText() {
        assertEquals("",notePad.text.getText());
    }

    @Test
    public void setIFLine(){
        assertEquals("2019年5月1日",notePad.IFLine.getText());
    }

    @Test
    public void lookCalendar() {
        notePad.lookCalendar(2019,5,1);
        assertEquals("无记录",notePad.text.getText());//空记录

        notePad.lookCalendar(2019,5,12);
        assertEquals("123",notePad.text.getText());//有记录 但是需要额外手点操作

    }

    @Test
    public void save_text() {
    }

    @Test
    public void rm_text() {
    }
}