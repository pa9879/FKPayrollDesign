//package Payroll;

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
		connectDB.add_timecard(employeeId, date, hours);
	}
}

public class Employee{
	private final int id;
	private Hourly hourlyPay;
	private Monthly monthlyPay;
	private commission commissionPay;
	private unionCharges dues;
	ArrayList<PayProcess> modes = new ArrayList<>();
	Timecard t1;
	String modeOfPayment;
	Date lastPayment;

	Employee(int emp_id)
	{
		id=emp_id;
		//Fetch Employee Details From Database using id
		try{
				ResultSet res=connectDB.stmt.executeQuery("select * from Employee where id =" + id);  
				while (res.next()) {
					hourlyPay = new Hourly(res.getDouble(2), id); 
					modes.add(hourlyPay); 

					monthlyPay = new Monthly(res.getDouble(3), id);
					modes.add(monthlyPay);
					
					commissionPay = new commission(res.getDouble(4), id);
					modes.add(commissionPay);
					
					dues = new unionCharges(id);
					modes.add(dues);
					
					modeOfPayment = res.getString(6);
					lastPayment = res.getDate(7);
					System.out.println(lastPayment);
				}
				System.out.println(res);
				
		}
		catch(Exception e)
		{
			System.out.println(e);
		}


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
		connectDB.add_sale(id, date, receipt_no, amount);
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

		connectDB.update_modeOfPayment(id, s);
	}
	public void update_PaymentDate(Date d)
	{
		lastPayment = d;
		
		connectDB.update_paymentDate(id, d);
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
