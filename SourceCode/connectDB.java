//package Payroll;

import java.sql.*;

public class connectDB{
	public static Statement stmt = null;
	public static Connection con = null;
	public static void connect_db()
	{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/testdb?SSL=false","root", "");  
			//here sonoo is database name, root is username and password  
			stmt=con.createStatement();  
		}
		catch(Exception e)
		{
			System.out.println();
		}
	}
	public static void close_db()
	{
		try{
			con.close();
		}
		catch(Exception e)
		{
			System.out.println();
		}
	}
	public static void update_hourly_rate(int id, double hourlyRate)
	{
		try{
				int rs=stmt.executeUpdate("UPDATE `Employee` SET `hourlyRate` = " + hourlyRate + "WHERE `id` =" + id);  
				System.out.println(rs);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public static double calculate_hours(int id, Date d)
	{
		int totalHours = 0 ;
		double hours =0;
		try{
			ResultSet rs=connectDB.stmt.executeQuery("SELECT SUM(`hours`) FROM `timecard` WHERE `emp_id` =" + id + "&& `date`>" +d );  
			while (rs.next()) {
				totalHours = rs.getInt(1);   
			}

			ResultSet res=connectDB.stmt.executeQuery("SELECT SUM(`hours`), COUNT(`hours`) FROM `timecard` WHERE `emp_id` =" + id + "&& `date`>" +d +"&& `hours`>8");  
			while (res.next()) {
				int extraHours = res.getInt(1) - (res.getInt(2)*8);
				hours = totalHours + extraHours * 0.5; 
			}
			System.out.println("hrs" + totalHours);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return hours;
	}
	public static void update_monthly_rate(int id, double monthlyRate)
	{
		try{
				int rs=stmt.executeUpdate("UPDATE `Employee` SET `monthlyRate` = " + monthlyRate + "WHERE `id` =" + id);  
				System.out.println(rs);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public static void update_commission_rate(int id, double commission)
	{
		
		try{
				int rs=connectDB.stmt.executeUpdate("UPDATE `Employee` SET `commissionRate` = " + commission + "WHERE `id` =" + id);  
				System.out.println(rs);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public static double calculate_total_sale(int id, Date d)
	{
		double totalSale =0;
		try{
				ResultSet rs=connectDB.stmt.executeQuery("SELECT SUM(`amount`) FROM `sales` WHERE `emp_id` =" + id + "&& `date` > " +d);  
				while (rs.next()) {
					totalSale = rs.getInt(1);   
				}
				System.out.println("sle" + totalSale);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		return totalSale;
	}
	public static double calculate_dues(int id, Date d)
	{
		double dues = 0;
		try{
			ResultSet rs=connectDB.stmt.executeQuery("SELECT SUM(`charges`) FROM `unionCharges` WHERE `date`>" + d + "&& `emp_id`=" + id);  
			while (rs.next()) {
				dues = rs.getInt(1);   
			}
			System.out.println(dues);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return dues;
	}

	public static void add_timecard(int employeeId, Date date, int hours)
	{
		try{
				String query = "INSERT INTO `timecard`(`emp_id`, `date`,`hours`) VALUES ("+ employeeId + ", '"+ date + "' , " + hours + ");";

				int rs=connectDB.stmt.executeUpdate(query);  
				System.out.println(rs);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public static void update_modeOfPayment(int id, String s)
	{

		try{
				int rs=connectDB.stmt.executeUpdate("UPDATE `Employee` SET `modeOfPayment` = '" + s + "'' WHERE `id` =" + id);  
				System.out.println(rs);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public static void update_paymentDate(int id, Date d)
	{
		try{
				int rs=connectDB.stmt.executeUpdate("UPDATE `Employee` SET `lastPayment` = " + d + "WHERE `id` =" + id);  
				System.out.println(rs);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public static void add_sale(int id, Date date, double receipt_no, double amount)
	{
		try{
				String query = "INSERT INTO `sales`(`emp_id`, `receipt_no`, `date`,`amount`) VALUES ("+ id + ", "+ receipt_no + ", '" + date + "' , " + amount + ");";

				int rs=connectDB.stmt.executeUpdate(query);  
				System.out.println(rs);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public static int max_id()
	{
		int id=0;
		try{
				ResultSet res=connectDB.stmt.executeQuery("select MAX(id) from Employee");  
				while (res.next()) {
					id = res.getInt(1);   
				}
				System.out.println(id);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return id+1;
	}
	public static int addEmp(double hourlyRate, double monthlyRate, double commissionRate, String modeOfPayment )
	{
		//Query to add employee
		int id = 0;
		try{
				id=max_id();
				String query = "INSERT INTO `Employee`(`id`, `hourlyRate`, `monthlyRate`, `commissionRate`, `paymentDue`, `modeOfPayment`, `lastPayment`) VALUES ("+ id + ", "+ hourlyRate + " , " + monthlyRate + ", " + commissionRate + ", 0, '" + modeOfPayment + "',  '2000-01-01')";

				int rs=connectDB.stmt.executeUpdate(query);  
				System.out.println(rs);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		return id;
	}

	public static void delEmp(int id)
	{
		try{
				String query = "DELETE FROM `Employee` WHERE `id`=" + id +";";
				System.out.println(query);
				int rs=connectDB.stmt.executeUpdate(query);  
				System.out.println(rs);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public static void add_union_charges(int id, double charges)
	{
		try{
			long millis=System.currentTimeMillis();  
			Date today = new Date(millis);
			String query = "INSERT INTO `unionCharges`(`emp_id`, `date`,`charges`) VALUES ("+ id + ", '"+ today + "' , " + charges + ");";
			int rs=connectDB.stmt.executeUpdate(query);  
			System.out.println(rs);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}