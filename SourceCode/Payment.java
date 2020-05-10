//package Payroll;

import java.util.Scanner;
import java.sql.*;
//import connectDB.;

interface PayProcess{
	double calculate(Date d);
}

class Hourly implements PayProcess{
	private double hourlyRate;
	private int id;

	Hourly(double hourlyRate, int id){
		this.hourlyRate = hourlyRate;
		this.id = id;
	}

	public double get_hourly_rate()
	{
		return hourlyRate;
	}
	public void update_hourly_rate(double hourlyRate)
	{
		this.hourlyRate = hourlyRate;
		//Update DB
		connectDB.update_hourly_rate(id, hourlyRate);
	}
	public double calculate(Date d)
	{
		//Query to calculate hours from DB
		double hours = connectDB.calculate_hours(id, d);
		return hours*hourlyRate;
	}
}

class Monthly implements PayProcess{
	private double monthlyRate;
	private int id;

	Monthly(double monthlyRate, int id){
		this.monthlyRate = monthlyRate;
		this.id = id;
	}

	public double get_monthly_rate()
	{
		return monthlyRate;
	}
	public void update_monthly_rate(double monthlyRate)
	{
		connectDB.update_monthly_rate(id, monthlyRate);
	}
	public double calculate(Date d)
	{
		//Condition for checking last working day

		return monthlyRate;
	}
}

class commission implements PayProcess{
	private double commissionRate;
	private int id;

	commission(double commissionRate, int id){
		this.commissionRate = commissionRate;
		this.id = id;
	}

	public double get_commission_rate()
	{
		return commissionRate;
	}
	public void update_commission_rate(double commissionRate)
	{
		this.commissionRate = commissionRate;
		//UPDATE DB
		connectDB.update_commission_rate(id, commissionRate);
	}
	public double calculate(Date d)
	{
		//Query to calculate sales from DB
		double totalSale = connectDB.calculate_total_sale(id,d);
			
		return (totalSale*commissionRate/100);
	}
}

class unionCharges implements PayProcess{
	private int id;

	unionCharges(int id){
		this.id = id;
	}

	public double calculate(Date d)
	{
		//Query to calculate union dues
		double dues = connectDB.calculate_dues(id,d);
		return -1*dues;
	}
}