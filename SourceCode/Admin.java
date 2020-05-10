import java.util.Scanner;
import java.util.ArrayList;
import java.sql.*;

class Admin{
	static int id=0;

	//Getting unique Id
	Admin(){
		//Query to get max emp id
	}

	int addEmployee(double hourlyRate, double monthlyRate, double commissionRate, String modeOfPayment )
	{
		//Query to add employee
		id++;
		
		return id;
	}
	void delEmployee(int id)
	{
		//Query DB to remove Employee
		
	}
	double calulatePayroll(int employeeId)
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
		
	}
	public static void addMember(int emp_id)
	{
		members.add(emp_id);
	}
}