package day09;

import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	public static void main(String[] args) {
		Timee t=new Timee();
		Timer tt=new Timer();
		tt.schedule(t, new Date(), 1000);
	}
}
class Timee extends TimerTask{
	 int second=60;
	@Override
	public void run() {
		second--;
		System.out.println(second);
	}
}
