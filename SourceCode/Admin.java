//package Payroll;

import java.util.Scanner;
import java.util.ArrayList;
import java.sql.*;

class Admin{

	public static int addEmployee(double hourlyRate, double monthlyRate, double commissionRate, String modeOfPayment )
	{
		//Query to add employee
		int id = connectDB.addEmp(hourlyRate, monthlyRate, commissionRate, modeOfPayment);		
		
		return id;
	}
	public static void delEmployee(int id)
	{
		//Query DB to remove Employee
		connectDB.delEmp(id);
		
	}
	public static double calulatePayroll(int employeeId)
	{
		//Calculating Payroll
		Employee emp = new Employee(employeeId);
		double pay = emp.CalculatePay();
		return pay;
	}
}

class employeeUnion{
	private static ArrayList<Integer> members;

	public static void unionCharges(int id, double charges)
	{
		//Query to add charges;
		connectDB.add_union_charges(id, charges);
		
	}
	public static void addMember(int emp_id)
	{
		members.add(emp_id);
	}
}