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
	public static void payroll()
	{
		try{
			ResultSet res=connectDB.stmt.executeQuery("select id, modeOfPayment from Employee");  
				while (res.next()) {
					System.out.println("lawde ho tum");   
					int id = res.getInt(1);
					String s = res.getString(2);
					double d = Admin.calulatePayroll(id);
					System.out.println("Transfered "+ d+ " amount to " + id+"via "+ s);   
				}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
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