import java.util.Scanner;
import java.sql.*;

class Timecard{
	private int employeeId;
	private Date date;
	private int hours;

	Timecard(int id, Date d, int hrs){
		this.employeeId = id;
		this.date = d;
		this.hours = hrs;
	}

	void add(){
		
		//Query to add to DB

		
	}
}

public class Employee{
	private final int id;
	private double hourlyRate;
	private double monthlyRate;
	private double dues;
	private double commissionRate;
	Timecard t1;
	String modeOfPayment;
	Date lastPayment;

	Employee(int emp_id)
	{
		id=emp_id;
		//Fetch Employee Details From Database using id
		
	}

	//Post Timecard
	void post(Date d, int hours)
	{
		t1 = new Timecard(this.id, d, hours);
		t1.add();
	}

	//Post Sales Reciept
	void post(Date date, double receipt_no, double amount)
	{
		//Query to Database for adding sales 
		
	}

	//Update Details Of Employee
	public void update_hourly_rate(double hourlyRate)
	{
		this.hourlyRate = hourlyRate;
	}
	public void update_monthly_rate(double monthlyRate)
	{
		this.monthlyRate = monthlyRate;
	}
	public void update_commission_rate(double commissionRate)
	{
		this.commissionRate = commissionRate;
	}
	public void update_dues(double dues)
	{
		this.dues = dues;
	}
	public void update_modeOfPayment(String s)
	{
		this.modeOfPayment = s;
	}
	public void update_PaymentDate(Date d)
	{
		lastPayment = d;
	}

	//Get values
	public double get_hourly_rate()
	{
		return hourlyRate;
	}
	public double get_monthly_rate()
	{
		return monthlyRate;
	}
	public double get_commission_rate()
	{
		return commissionRate;
	}
	public double get_dues()
	{
		 return dues;
	}
	public String get_modeOfPayment()
	{
		return modeOfPayment;
	}
	public Date get_lastPaymentDate()
	{
		return lastPayment;
	}
}
