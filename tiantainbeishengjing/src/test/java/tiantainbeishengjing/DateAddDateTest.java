package tiantainbeishengjing;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateAddDateTest {

	@Test
	public void test() throws ParseException {
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         Date currdate;
         currdate = format.parse("2017-11-2");
         System.out.println("现在的日期是：" + currdate);
         Calendar ca = Calendar.getInstance();
         ca.setTime(currdate);
         ca.add(Calendar.DATE, 1);// num为增加的天数，可以改变的
         currdate = ca.getTime();
         String enddate = format.format(currdate);
         System.out.println("增加天数以后的日期：" + enddate);
	}
	@Test
	public void week(){
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		int week = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println(week);
		
	}

}
