//package Payroll;

import java.util.Scanner;
import java.util.ArrayList;
import java.sql.*;

public class Driver{
	static void displayHome()
	{
		System.out.println("Payroll Application!");
		System.out.println("1. Admin");
		System.out.println("2. Employee");
		System.out.println("3. Employee Union");
	}
	static void displayAdmin(){
		System.out.println("1. Add Employee");
		System.out.println("2. Remove Employee");
		System.out.println("3. Calculate Payroll");
	}
	static void displayEmployee(){
		System.out.println("1. Post Timecard");
		System.out.println("2. Post Sales Reciept");
		System.out.println("3. Update Hourly Rate");
		System.out.println("4. Update Monthly Rate");
		System.out.println("5. Update Commission Rate");
		System.out.println("6. Update modeOfPayment");
	}
	static void displayEmployeeUnion(){
		System.out.println("1. Post charges for employee");
		System.out.println("2. Add Member");
	}

	static void do_admin(int ch, Scanner scan)
	{
		switch(ch)
		{
			case 1 : System.out.print("Enter Hourly Rate :");
					double hourlyRate = scan.nextDouble();
					System.out.print("Enter Monthly Fixed Salary :");
					double monthlyRate = scan.nextDouble();				
					System.out.print("Enter rate of commission in %: ");
					double commission = scan.nextDouble();
					System.out.print("Enter mode of Payment :");
					String s = scan.next();
					int id = Admin.addEmployee(hourlyRate, monthlyRate, commission, s);
					System.out.print("Employee added with employee id: " + id);
					break;

			case 2 : System.out.print("Enter employee id: ");
					int empId = scan.nextInt();
					Admin.delEmployee(empId);
					System.out.println("Removed!");
					break;

			case 3 : System.out.print("Enter employee id: ");
					int emp_id = scan.nextInt();
					System.out.println(Admin.calulatePayroll(emp_id));
					break;

			default : System.out.println("INVALID CHOIE!");
					  break;

		}
	}

	static void do_employee(int ch, Scanner scan, Employee emp)
	{
		switch(ch)
		{
			case 1 : System.out.print("Enter Date :");
					Date date = Date.valueOf(scan.next());	
					System.out.print("Enter Hours :");
					int hours = scan.nextInt();
					emp.post(date, hours);
					break;

			case 2 : System.out.print("Enter Date :");
					Date dt = Date.valueOf(scan.next());	
					System.out.print("Enter Reciept No. :");
					int reciept_no = scan.nextInt();
					System.out.print("Enter sales amount: ");
					double amt = scan.nextDouble();
					emp.post(dt, reciept_no, amt);
					break;

			case 3 : System.out.print("Enter Hourly Rate :");
					double hourlyRate = scan.nextDouble();
					emp.update_hourly_rate(hourlyRate);
					break;

			case 4 : System.out.print("Enter Monthly Fixed Salary :");
					double monthlyRate = scan.nextDouble();		
					emp.update_monthly_rate(monthlyRate);
					break;

			case 5 : System.out.print("Enter rate of commission in %: ");
					double commission = scan.nextDouble();
					emp.update_commission_rate(commission);
					break;

			case 6 : System.out.print("Enter mode of Payment :");
					String s = scan.next();
					emp.update_modeOfPayment(s);
					break;

			default : System.out.println("INVALID CHOIE!");
					  break;


		}
	}

	static void do_employeeUnion(int ch, Scanner scan)
	{
		switch(ch)
		{
			case 1 : System.out.print("Enter employee id: ");
					int id = scan.nextInt();
					System.out.print("Employee Charges: ");
					double charges = scan.nextDouble();
					employeeUnion.unionCharges(id, charges);
					break;

			case 2: System.out.println("Enter new member id:");
					int emp_id = scan.nextInt();
					employeeUnion.addMember(emp_id);

			default : System.out.println("INVALID CHOIE!");
					  break;

		}
	}
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		connectDB.connect_db();
		displayHome();
		int choice = scan.nextInt();
		switch(choice)
		{
			case 1 : displayAdmin();
				int ch = scan.nextInt();
				do_admin(ch, scan);
				break;

			case 2 : System.out.print("Enter employee id: ");
				int id = scan.nextInt();
				Employee emp = new Employee(id);
				displayEmployee();
				int chc = scan.nextInt();
				do_employee(chc, scan, emp);
				break;

			case 3: displayEmployeeUnion();
				int chy = scan.nextInt();
				do_employeeUnion(chy, scan);
				break;

			default : System.out.println("INVALID CHOIE!");
					  break;				
		}
		connectDB.close_db();
	}
}