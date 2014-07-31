import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class pr extends staff 
{
	Connection c;
	Statement s;
	
	int basic=0,ta=0,da = 0,hra=0,bonus=0;

	
	pr()
	{
			c = init_connection.conn; // copy connection reference
			s = init_connection.stmt; // copy statement reference	
	}
	
	Scanner sc = new Scanner(System.in);
	
	int cal_tds(int basic) throws SQLException
	{
		int salary = basic;
		int tds = 0;
		
		if(salary <= 10000)
			 tds = (int) (salary*0.05);
		
		if(salary <= 50000)
			 tds = (int) (salary*0.07);
		
		if(salary > 50000)
			 tds = (int) (salary*0.10);
		
		//System.out.println("TDS is : " + tds);

		return tds;
	}
	
	
	int cal_salary(String empid) throws SQLException
	{
		String emp_id;
		
		if(empid.equalsIgnoreCase(""))
		{
			System.out.print("Enter Emp id : ");
			emp_id = sc.next();
		}
		else
		{
			emp_id = empid;
		}
		
		
		
		check ch = new check();
		
		if(ch.emp_check(emp_id))
		{
			PreparedStatement p6 = c.prepareStatement("select b_salary from emp_professional where empid = ?");
			p6.setString(1, emp_id);
			
			ResultSet r = p6.executeQuery();
			
			if(r.next())
				basic = r.getInt(1);
			
			r = null;
			
			p6 = c.prepareStatement("select ta,da,hra,bonus from salary_details where empid = ?");
			p6.setString(1, emp_id);
			
			ResultSet r1 = p6.executeQuery();
			
			if(r1.next())
			{
				ta = r1.getInt(1);
				da = r1.getInt(2);
				hra = r1.getInt(3);
				bonus = r1.getInt(4);
			}
			
			return basic+ta+da+hra+bonus-cal_tds(basic);
		
		}
		else
		{
			System.out.println("Invalid Employee ID ");
			this.cal_salary("");
			
		}
		return basic;
		
		
		
		
	
	}
	
	void getPaySlip() throws SQLException, ParseException
	{
		String emp_id,name = null,desg = null,temp;
		int basic,total,bonus;
		
		java.util.Date d1 = null;
		SimpleDateFormat fr = new SimpleDateFormat("yyyy-mm-dd");
		java.sql.Date d;

		
		System.out.print("Enter Emp id : ");
		emp_id = sc.next();
		
		check ch = new check();
		
		if(ch.emp_check(emp_id))
		{
			System.out.print("Enter Date : ");
			
			temp = sc.next();	
			d1 = fr.parse(temp);
			d = new java.sql.Date(d1.getTime());
			
			PreparedStatement p = c.prepareStatement("select emp_name,designation,b_salary from emp_professional where empid = ?");
			p.setString(1, emp_id);
			
			ResultSet r = p.executeQuery();
			
			if(r.next())
			{
				name = r.getString(1);
				desg = r.getString(2);
				basic = r.getInt(3);
			}
			
			total = cal_salary(emp_id);
			basic = this.basic;
			bonus = this.bonus;
			
			System.out.println("*************Monthly Pay_Slip****************");
			System.out.print("\t\t EMPID : " + emp_id + " \n\t\tEMP Name : " + name + " \n\t\tDesignation : " + desg + " \n\t\tBasic Salary : " +  basic + " \n\t\tTotal Salary : " + total + " \n\t\tDate :" + d + " \n\t\tBonus : " +  bonus );
					
			PreparedStatement p1 = c.prepareStatement("insert into pay_slip values(?,?,?,?,?,?,?)");
			p1.setString(1,emp_id);
			p1.setString(2, name);
			p1.setString(3, desg);
			p1.setInt(4, basic);
			p1.setInt(5, total);
			p1.setDate(6, d);
			p1.setInt(7, bonus);
			
			p1.executeUpdate();
				
		}
	}
	
	void generate_salary_report() throws SQLException
	{
		rg r = new rg();
		r.generate_salary_report();
	}
	
	
	
}