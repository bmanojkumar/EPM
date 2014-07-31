import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class rg
{
	Connection c;
	Statement s;
	
	int basic=0,ta=0,da = 0,hra=0,bonus=0;

	
	rg()
	{
			c = init_connection.conn; // copy connection reference
			s = init_connection.stmt; // copy statement reference
	}
	
	
	void generate_salary_report() throws SQLException
	{
		PreparedStatement p = c.prepareStatement("select e1.empid,e1.emp_name,e2.b_salary from emp_personal e1,emp_professional e2 where e1.empid=e2.empid");
		ResultSet r = p.executeQuery();
		
		System.out.println("\n=====================SALARY REPORT=====================");
		System.out.println("| EMPID \t\t| EMP NAME \t\t| SALARY |");
		System.out.println("\n=======================================================");
		
		while(r.next())
		{
			System.out.print("| " + r.getString(1) + "\t\t\t|  ");
			System.out.print(r.getString(2) + "\t\t|  ");
			System.out.print(r.getInt(3) + "|  ");
			 System.out.println();

			System.out.println("-----------------------------------------------------------");
			
		}

	}
	
	
	void generate_employee_report() throws SQLException
	{
		
		PreparedStatement p = c.prepareStatement("select e1.empid,e1.emp_name,e1.phone,e2.status,e2.department from emp_personal e1,emp_professional e2 where e1.empid=e2.empid"); 
		ResultSet r = p.executeQuery();
		
		System.out.println("\n=============================================EMPLOYEE REPORT==================================");
		System.out.println("| EMP ID \t| EMP NAME \t| PHONE \t\t| STATUS \t| DEPARTMENT |");
		System.out.println("\n==============================================================================================");

		while(r.next())
		{
			System.out.print("| " + r.getString(1) + " \t\t| ");
			System.out.print(r.getString(2) + " \t| ");
			System.out.print(r.getInt(3) + " \t\t| ");
			System.out.print(r.getString(4) + " \t| ");
			System.out.print(r.getString(5) + " \t| ");
			 System.out.println();

			System.out.println("----------------------------------------------------------------------------------------------");
		}
		
	}
	
	
	void generate_performance_report()
	{
		//	PreparedStatement p1=c.prepareStatement("select empid,emp_name ")
	}
	
	void expense_sheet() throws SQLException
	{
		
		PreparedStatement p1=c.prepareStatement("select e1.empid, e1.emp_name,e2.expenses from emp_personal e1,emp_professional e2 where e1.empid = e2.empid");
		ResultSet r=p1.executeQuery();
		
		System.out.println("===================EXPENSE SHEET=================");
		System.out.println("| EMP ID | EMP NAME | EXPENSES |");
		System.out.println("=================================================");

		
		while(r.next())
		{
			  System.out.print("| " + r.getString(1) + " | ");
			  System.out.print(r.getString(2) + " | ");
			  System.out.print(r.getString(3) + " | ");
				 System.out.println();

			  System.out.println("-----------------------------------------------------------------------------");
		}
	}
	
	
	void profit_loss_sheet() throws SQLException
	{
		 int total_budget=10000000, total_expenses=0,total_salaries=0;
		 int total_sum;
		 PreparedStatement p2=c.prepareStatement("select empid from emp_personal");
		 ResultSet r=p2.executeQuery();
		 
		 pr p=new pr();
		 
		 while(r.next())
		 {
			  total_salaries=total_salaries+p.cal_salary(r.getString(1));
			  
		 }
		 
		 PreparedStatement p3=c.prepareStatement("select expenses from emp_professional");
		 ResultSet r1=p3.executeQuery();
		 
		 while(r1.next())
		 {
			 total_expenses=total_expenses+r1.getInt(1);
		 }
		 
		 total_sum=total_budget-(total_expenses+total_salaries);
		 
		 
		 System.out.println("==================PROFIT/LOSS SHEET===================");
		 System.out.println("Total Budget : " + total_budget);
		 System.out.println("Total Expenses : " + total_expenses);
		 System.out.println("Total Salaries : " + total_salaries);
		 
		 System.out.println("Net Profit/Loss : " + total_sum);
		 //System.out.println("Profit/Loss Percentage : " + ((total_budget-total_sum)/total_budget)*100);
		 
		 if(total_sum > 0)
			 System.out.println("profit");
		 else
			 System.out.println("loss");
		 
		 System.out.println();
		System.out.println("-----------------------------------------------------------------------------");

	}
	
}