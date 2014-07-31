//STEP 1. Import required packages
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;



// Login Class for Authentication

class staff {
	
	
	/* Basic Details */
	
	String id,name,address,blood_group,marital_status;
	java.sql.Date dob;
	int phone_number;
	
	/*Professional Details */
	
	String designation,qualification,department,status;
	int b_salary,expenses;
	
	Connection c;
	Statement s;
	
	staff()
	{
		c = init_connection.conn; // copy connection reference
		s = init_connection.stmt; // copy statement reference
	}
	
	
	
	void view_information(String empid) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("select * from emp_personal where empid = ?");
		p.setString(1, empid);
		
		ResultSet r = p.executeQuery();
		
		System.out.println("\n=======================EMPLOYEE INFORMATION======================================================================================");
		System.out.println("\n| EMP NAME \t| EMP ID \t| ADDRESS \t| MARITAL STATUS \t| BLOOD GROUP \t| PHONE \t| DOB |");
		System.out.println("\n=================================================================================================================================");

		
		while(r.next())
		{
			System.out.print("| " + r.getString(1) + "\t\t| " + r.getString(2) + "\t\t| " + r.getString(3) + "\t\t| " + r.getString(4) + "\t\t\t| " + r.getString(5) + "\t\t| " + r.getInt(6) + "\t\t| " + r.getDate(7) + " | ");
			System.out.println();

			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		}
		
	}
	
	void view_payslip(String empid) throws SQLException
	{
		System.out.println("=====================================PAY SLIP INFORMATION=============================================================");
		PreparedStatement p = c.prepareStatement("select * from pay_slip where empid = ?");
		p.setString(1, empid);
		
		ResultSet r = p.executeQuery();
		
		System.out.println("\n| EMP ID \t| EMP NAME \t| DESIGNATION \t| BASIC SALARY \t| TOTAL SALARY \t| DATE \t\t\t| BONUS |");
		System.out.println("=======================================================================================================================");

		int i = 0;
		while(r.next())
		{
			i = 1;
			System.out.println("| " + r.getString(1) + "\t\t| " + r.getString(2) + "\t\t| " + r.getString(3) + "\t\t| " + r.getInt(4) + "\t\t| " + r.getInt(5) + "\t\t| " + r.getDate(6) + "\t\t| " + r.getInt(7) + "\t|");

			System.out.println("-------------------------------------------------------------------------------------------------------------------");
		}
		
		if(i == 0)
			System.out.println("====Pay Slip Information is not ready====");
		
	}
	
	void view_appraisals(String empid) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("select * from appraisals where empid = ?");
		p.setString(1, empid);
		
		ResultSet r = p.executeQuery();
		
		System.out.println("\n=====================APPRAISALS==================");
		System.out.println("\n| EMP ID \t| EMP NAME \t| AMOUNT |");
		System.out.println("=================================================");
		
		int i = 0;
		while(r.next())
		{
			i = 1;
			System.out.print("| " + r.getString(1) + "\t\t| " + r.getDate(2) + "\t| " + r.getInt(3) + " |");
			System.out.println();

			System.out.println("--------------------------------------------------");
		}
		
		if(i == 0)
			System.out.println("====No Information to Display====");
	}
	
	
}



class employee extends staff 
{
	
	
	
	
	
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
	
	public String getMarital_status() {
		return marital_status;
	}
	
	public String getBlood_group() {
		return blood_group;
	}

	public Date getDob() {
		return dob;
	}
	
	public int getPhone_number() {
		return phone_number;
	}
	
}




public class db {
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, ParseException {

		login l = new login();
		l.authenticate();
	}
}

// public class db extends init_connection{
// public static void main(String[] args) {
//
//
//
// try{
//
//
// System.out.println("Creating statement...");
// stmt = conn.createStatement();
// String sql;
// sql = "desc emp_personal";
// ResultSet rs = stmt.executeQuery(sql);
//
// //STEP 5: Extract data from result set
// while(rs.next()){
// //Retrieve by column name
//
// String fid = rs.getString("Field");
//
// //Display values
// System.out.println("ID: " + fid);
//
// }
// //STEP 6: Clean-up environment
// rs.close();
// stmt.close();
// conn.close();
// }catch(SQLException se){
// //Handle errors for JDBC
// se.printStackTrace();
// }catch(Exception e){
// //Handle errors for Class.forName
// e.printStackTrace();
// }finally{
// //finally block used to close resources
// try{
// if(stmt!=null)
// stmt.close();
// }catch(SQLException se2){
// }// nothing we can do
// try{
// if(conn!=null)
// conn.close();
// }catch(SQLException se){
// se.printStackTrace();
// }//end finally try
// }//end try
// System.out.println("Goodbye!");
// }//end main
// }//end FirstExample