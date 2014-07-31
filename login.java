import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;

class login {

	Scanner sc,intsc;
	String type, user, pass;
	Connection c;
	Statement s;

	login() throws ClassNotFoundException, SQLException {
		new init_connection(); // init database connection
		c = init_connection.conn; // copy connection reference
		s = init_connection.stmt; // copy statement reference
	}
	
	

	void authenticate() throws SQLException, ParseException {
		System.out.println();
		System.out.println("*************Employee Payroll System**************");
		System.out.println("\n\t\tLogin ");
		System.out.print("\nEnter Employee type(EMP/HR/PR) : ");
		sc = new Scanner(System.in);
		intsc = new Scanner(System.in);

		type = sc.next();

		System.out.print("Enter username : ");

		user = sc.next();

		System.out.print("Enter password : ");

		pass = sc.next();

		if(validate(type, user, pass))
		{
			if(type.equalsIgnoreCase("emp"))
			{
				
				staff s = new staff();
				
				while(true)
				{
				System.out.println();
				System.out.println("1.VIEW INFORMATION");
				System.out.println("2.VIEW PAYSLIP");
				System.out.println("3.VIEW APPRAISALS");
				System.out.println("4.LOGOUT");
				
				System.out.println();
				System.out.print("Enter Choice : ");
				int ch = intsc.nextInt();
				
				if(ch == 1)
				{
					s.view_information(user);
				}
				if(ch == 2)
				{
					s.view_payslip(user);
				}
				if(ch == 3)
				{
					s.view_appraisals(user);
				}
				if(ch == 4)
				{
					System.out.println("You are Logged out!!");
					authenticate();
				}
				
				
				}
			}

			else if(type.equalsIgnoreCase("hr"))
			{
				HR h = new HR();
				
				while(true)
				{
					System.out.println();
					System.out.println("1.ADD EMPLOYEE");
					System.out.println("2.UPDATE EMPLOYEE");
					System.out.println("3.REMOVE EMOLOYEE");
					System.out.println("4.VIEW INFORMATION");
					System.out.println("5.VIEW PAYSLIP");
					System.out.println("6.VIEW APPRAISALS");
					System.out.println("7.VIEW EMPLOYEE REPORT");
					System.out.println("8.ADD SALARY DETAILS");
					System.out.println("9.ADD APPRAISALS");
					System.out.println("10.LOGOUT");
					
					System.out.println();
					System.out.print("Enter Choice : ");
					int ch = intsc.nextInt();
					
					if(ch == 1)
					{
						h.add_employee();
					}
					if(ch == 2)
					{
						h.update_employee();
					}
					if(ch == 3)
					{
						h.remove_employee();
					}
					if(ch == 4)
					{
						h.view_information(user);
					}
					if(ch == 5)
					{
						h.view_payslip(user);
					}
					if(ch == 6)
					{
						h.view_appraisals(user);
					}
					if(ch == 7)
					{
						h.generate_employee_report();
					}
					if(ch == 8)
					{
						h.add_appraisals();
					}
					if(ch == 9)
					{
						h.add_bonus();
					}
					if(ch == 10)
					{
						System.out.println("You are Logged Out!!");
						authenticate();
					}
				}
				
				
			}
			
			else if(type.equalsIgnoreCase("pr"))
			{
				pr p = new pr();
				rg r = new rg();
				
				while(true)
				{
					System.out.println();
					System.out.println("1.CALCULATE SALARY");
					System.out.println("2.CALCULATE TDS");
					System.out.println("3.GENERATE PAYSLIP");
					System.out.println("4.VIEW SALARY REPORT");
					System.out.println("5.VIEW INFORMATION");
					System.out.println("6.VIEW PAYSLIP");
					System.out.println("7.VIEW APPRAISALS");
					System.out.println("8.EXPENSE SHEET");
					System.out.println("9.PROFIT_LOSS SHEET");					
					System.out.println("10.LOGOUT");
					
					System.out.println();
					System.out.print("Enter Choice : ");
					int ch = intsc.nextInt();
					
					if(ch == 1)
					{
						System.out.print("Enter Employee ID : ");
						String empid = sc.next();
						System.out.println("SALARY is : " + p.cal_salary(empid));
					}
					if(ch == 2)
					{
						System.out.print("Enter Amount : ");
						int basic = intsc.nextInt();
						System.out.println("TDS : " + p.cal_tds(basic));
					}
					if(ch == 3)
					{
						p.getPaySlip();
					}
					if(ch == 4)
					{
						p.generate_salary_report();
					}
					if(ch == 5)
					{
						p.view_information(user);
					}
					if(ch == 6)
					{
						p.view_payslip(user);
					}
					if(ch == 7)
					{
						p.view_appraisals(user);
					}
					if(ch == 8)
					{
						r.expense_sheet();
					}
					
					if(ch == 9)
					{
						r.profit_loss_sheet();
					}
					
					if(ch == 10)
					{
						System.out.println("You are Logged Out!!");
						authenticate();
					}
					
				}
				
			}
			
			
		}
		else
		{
			System.out.println(" Authentication Failed ");
			authenticate();
		}
	}

	boolean validate(String type, String user, String pass) throws SQLException {

		s = c.createStatement();
		PreparedStatement p = c
				.prepareStatement("select * from login_details where user_type= ? and username= ? and password= ?");

		p.setString(1, type);
		p.setString(2, user);
		p.setString(3, pass);

		ResultSet rs = p.executeQuery();

		return rs.next();

	}
}
