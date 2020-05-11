import java.util.*;
import java.time.*;
import java.util.concurrent.*;


class MyRunnableTask implements Runnable {

    private int id;

    public MyRunnableTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Running Payroll Evaluation ...: ");
        Admin.payroll();
    }
}
public class PayrollScheduler {
 
	public static void main(String[] args) {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		ZonedDateTime nextRun = now.withHour(6).withMinute(10).withSecond(0);
		if(now.compareTo(nextRun) > 0)
		    nextRun = nextRun.plusDays(1);

		Duration duration = Duration.between(now, nextRun);
		long initalDelay = duration.getSeconds();

		System.out.println(initalDelay);
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);            
					scheduler.scheduleAtFixedRate(new MyRunnableTask(9),
					    initalDelay,
					    60,
					    TimeUnit.SECONDS);
		//timer.schedule(tt, initalDelay, 1000);//	delay the task 1 second, and then run task every five seconds
	}
}