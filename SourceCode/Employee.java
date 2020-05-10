import java.util.Scanner;
import java.util.ArrayList;
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
	private Hourly hourlyPay;
	private Monthly monthlyPay;
	private commission commissionPay;
	private unionCharges dues;
	ArrayList<PayProcess> modes;
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

	//Calculate income of Employee
	double CalculatePay()
	{
		double pay = 0;
		for(PayProcess p: modes)
		{
			pay += p.calculate(lastPayment);
		}
		return pay;
	}

	//Update Details Of Employee
	public void update_hourly_rate(double hourlyRate)
	{
		hourlyPay.update_hourly_rate(hourlyRate);
	}
	public void update_monthly_rate(double monthlyRate)
	{
		monthlyPay.update_monthly_rate(monthlyRate);
	}
	public void update_commission_rate(double commissionRate)
	{
		commissionPay.update_commission_rate(commissionRate);
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
		return hourlyPay.get_hourly_rate();
	}
	public double get_monthly_rate()
	{
		return monthlyPay.get_monthly_rate();
	}
	public double get_commission_rate()
	{
		return commissionPay.get_commission_rate();
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
