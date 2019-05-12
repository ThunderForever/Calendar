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
    public void set_calendar() {

        //大月31天测试
        calendarPad.set_calendar(2019,5);
        assertEquals(31,calendarPad.daynum);

        //小月30天测试
        calendarPad.set_calendar(2019,4);
        assertEquals(30,calendarPad.daynum);

        //平年二月测试
        calendarPad.set_calendar(2019,2);
        assertEquals(28,calendarPad.daynum);
        calendarPad.set_calendar(1800,2);
        assertEquals(28,calendarPad.daynum);

        //闰年二月测试
        calendarPad.set_calendar(2016,2);
        assertEquals(29,calendarPad.daynum);

        calendarPad.set_calendar(2000,2);
        assertEquals(29,calendarPad.daynum);

        //年份出范围测试
        calendarPad.set_calendar(4500,5);
        assertEquals(31,calendarPad.daynum);
        calendarPad.set_calendar(4500,4);
        assertEquals(30,calendarPad.daynum);
        calendarPad.set_calendar(4500,2);
        assertEquals(28,calendarPad.daynum);



    }

    @Test
    public void arraynum() {
        calendarPad.arraynum(3,31);
        assertEquals(Color.blue,calendarPad.showDay[3].getBackground()); //5月1日为目标日 为蓝色背景
        assertEquals(Color.white,calendarPad.showDay[4].getBackground()); //5月2日不是目标日 为白色背景
        assertEquals(Color.red,calendarPad.showDay[6].getForeground()); //周六红色字体
        assertEquals(Color.red,calendarPad.showDay[7].getForeground()); //周日红色字体\
        assertEquals("",calendarPad.showDay[2].getText());


    }


    @Test
    public void setYear() {
        calendarPad.setYear(2000);
        assertEquals(2000,calendarPad.year);
    }

    @Test
    public void setMonth() {
        calendarPad.setMonth(10);
        assertEquals(10,calendarPad.month);
    }

    @Test
    public void setDay() {
        calendarPad.setDay(30);
        assertEquals(30,calendarPad.day);
    }
}