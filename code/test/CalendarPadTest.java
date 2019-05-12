import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.awt.*;

import static org.junit.Assert.*;

public class CalendarPadTest {

    private CalendarPad calendarPad;


    @Before
    public void setUp() throws Exception {
        calendarPad=new CalendarPad(2019,5,1);

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void arraynum() {
        assertEquals(Color.blue,calendarPad.showDay[3].getBackground()); //5月1日为目标日 为蓝色背景
        assertEquals(Color.white,calendarPad.showDay[4].getBackground()); //5月2日不是目标日 为白色背景
        assertEquals(Color.red,calendarPad.showDay[6].getForeground()); //周六红色字体
        assertEquals(Color.red,calendarPad.showDay[7].getForeground()); //周日红色字体

    }

    @Test
    public void setYear(){
        assertEquals(2019,calendarPad.notepad.getYear());
    }

    @Test
    public void setMonth(){
        assertEquals(5,calendarPad.notepad.getMonth());
    }

    @Test
    public void setDay(){
        assertEquals(1,calendarPad.notepad.getDay());
    }

}