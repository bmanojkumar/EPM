import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;


class check
{
	Connection c;
	Statement s;
	
	check()
	{
		
		c = init_connection.conn; // copy connection reference
		s = init_connection.stmt; // copy statement reference
	}
	
	boolean emp_check(String empid) throws SQLException
	{

		PreparedStatement p3 = c.prepareStatement("select * from emp_personal where empid = ?");
		
		p3.setString(1, empid);
		
		ResultSet r = p3.executeQuery();
		
		if(r.next()) return true;
		
		return false;
		
	}
}

class HR extends staff
{
	Connection c;
	Statement s;
	
	HR()
	{
		c = init_connection.conn; // copy connection reference
		s = init_connection.stmt; // copy statement reference
	}
	
	Scanner cs = new Scanner(System.in);
	Scanner sc = new Scanner(System.in);
	String temp;
	int t;
	java.util.Date d1 = null;
	SimpleDateFormat fr = new SimpleDateFormat("yyyy-mm-dd");
	java.sql.Date d;
	
	
	@SuppressWarnings("deprecation")
	void add_employee() throws ParseException, SQLException
	{
		System.out.println("\nCollecting Basic Employee Information");
		/* set name */
		System.out.print("Enter name :");
		temp = cs.next();	
		this.setName(temp);
		
		/* set address */
		System.out.print("Enter address :");
		temp = cs.next();	
		this.setAddress(temp);
		
		/* set martial status */
		System.out.print("Enter marital_status :");
		temp = cs.next();
		this.setMarital_status(temp);
		
		/* set blood group */
		System.out.print("Enter blood group :");
		temp = cs.next();	
		this.setBlood_group(temp);
		
		/* set phone */
		System.out.print("Enter phone :");
		t = sc.nextInt();
		this.setPhone_number(t);
		
		/* set dob */
		System.out.print("Enter date of birth (YYYY-MM-DD) :");
		temp = cs.next();	
		d1 = fr.parse(temp);
		d = new java.sql.Date(d1.getTime());
		this.setDob(d);
		
		if(add_basic_employee()) System.out.println("\nBasic Details Updated");
		
		System.out.println("\nCollecting Professional Employee Information");
		
		System.out.print("Enter Designation :");
		temp = cs.next();
		this.setDesignation(temp);
		
		
		System.out.print("Enter Qualification :");
		temp = cs.next();	
		this.setQualification(temp);
		
		
		System.out.print("Enter Department :");
		temp = cs.next();	
		this.setDepartment(temp);
		
		System.out.print("Enter Status :");
		temp = cs.next();	
		this.setStatus(temp);
		
		System.out.print("Enter Basic salary :");
		t = sc.nextInt();	
		this.setB_salary(t);
		
		System.out.print("Enter Expenses :");
		t = sc.nextInt();
		this.setExpenses(t);
		
		if(add_professional_employee()) System.out.println("\nProfessional Details Updated");
		
		String pass = generateActivationCode();
		
		PreparedStatement pp = c.prepareStatement("insert into login_details(user_type,username,password) values(?,?,?)");
		pp.setString(1, "EMP");
		pp.setString(2, this.id);
		pp.setString(3, pass);
		
		int i = pp.executeUpdate();
		
		if(i == 1) 
			{
				System.out.println("Login Details Updated ");
				System.out.println("Password : " + pass);
			}
		
		
		
	}
	
	
	public static String generateActivationCode() 
	{
		Random randomGenerator = new Random();
		Character ch;
		String str="",s;
		int k;
		for(int i=0;i<6;i++)
		{
		do
			{
		k=randomGenerator.nextInt(100);
			}while(!((k>96&&k<123)||((k>47&&k<58)&&i!=0)));
		ch = new Character((char)k);
		s=ch.toString();
		str=str+s;
		}
		return str;
		//return "a23j5h";
	}
	
	boolean add_professional_employee() throws SQLException
	{
		
		PreparedStatement p2 = c
				.prepareStatement("insert into emp_professional(empid,emp_name,designation,b_salary,qualification,department,status,expenses) values (?,?,?,?,?,?,?,?)");
		
		p2.setString(1, this.id);
		p2.setString(2, this.name);
		p2.setString(3, this.designation);
		p2.setInt(4, this.b_salary);
		p2.setString(5, this.qualification);
		p2.setString(6, this.department);
		p2.setString(7,this.status);
		p2.setInt(8, this.expenses);
		
		
		int i = p2.executeUpdate();
		
		if(i==1) return true;
		return false;

	}
	
	boolean add_basic_employee() throws SQLException
	{
		int count = 0;
		
		PreparedStatement p = c
				.prepareStatement("select count(*) as ct from emp_personal");
		
		ResultSet r = p.executeQuery();
		
		if(r.next()) 
			count  = r.getInt(1);
		
		count++;
		
		PreparedStatement p1 = c
				.prepareStatement("insert into emp_personal(emp_name,empid,address,marital_status,bloodGroup,phone,DOB) values (?,?,?,?,?,?,?)");

		this.id =  "EMP" + count;
		p1.setString(1, this.name);
		p1.setString(2, this.id);
		p1.setString(3, this.address);
		p1.setString(4, this.marital_status);
		p1.setString(5, this.blood_group);
		p1.setInt(6, this.phone_number);
		p1.setDate(7, this.dob);
		
		int i = p1.executeUpdate();
		
		if(i==1) return true;
		return false;

		
	}
	
	boolean update_basic_employee() throws SQLException
	{
		PreparedStatement p1 = c
				.prepareStatement("update emp_personal set emp_name = ?,address = ? ,marital_status = ?,bloodGroup = ?,phone = ? ,DOB = ? where empid=?");

		p1.setString(1, this.name);
		p1.setString(2, this.address);
		p1.setString(3, this.marital_status);
		p1.setString(4, this.blood_group);
		p1.setInt(5, this.phone_number);
		p1.setDate(6, this.dob);
		
		p1.setString(7, this.id);

		
		int i = p1.executeUpdate();
		
		if(i==1) return true;
		return false;
	}
	
	
	boolean update_professional_employee() throws SQLException
	{
		
		PreparedStatement p2 = c
				.prepareStatement("update emp_professional set emp_name = ?,designation = ?,b_salary = ?,qualification = ?,department = ?,status = ? ,expenses = ? where empid = ?");
		
		
		p2.setString(1, this.name);
		p2.setString(2, this.designation);
		p2.setInt(3, this.b_salary);
		p2.setString(4, this.qualification);
		p2.setString(5, this.department);
		p2.setString(6,this.status);
		p2.setInt(7, this.expenses);
		
		p2.setString(8, this.id);
		
		
		int i = p2.executeUpdate();
		
		if(i==1) return true;
		return false;

	}
	
	void update_employee() throws SQLException, ParseException
	{
		
		check ch = new check();
		System.out.print("Enter the employee :");
		this.id  = cs.next();
		
		if(ch.emp_check(id))
		{
			System.out.println("=============Collecting Basic Employee Information============");
			/* update name */
			System.out.print("Enter name :");
			temp = cs.next();	
			this.setName(temp);
			
			/* update address */
			System.out.print("Enter address :");
			temp = cs.next();	
			this.setAddress(temp);
			
			/* update martial status */
			System.out.print("Enter marital_status :");
			temp = cs.next();
			this.setMarital_status(temp);
			
			/* update blood group */
			System.out.print("Enter blood group :");
			temp = cs.next();	
			this.setBlood_group(temp);
			
			/* update phone */
			System.out.print("Enter phone :");
			t = sc.nextInt();
			this.setPhone_number(t);
			
			/* update dob */
			System.out.print("Enter date of birth (YYYY-MM-DD) :");
			temp = cs.next();	
			d1 = fr.parse(temp);
			d = new java.sql.Date(d1.getTime());
			this.setDob(d);
			
			if(update_basic_employee()) System.out.println("Basic Details Updated");
			
			System.out.println("===========Collecting Professional Employee Information============");
			
			System.out.print("Enter Designation :");
			temp = cs.next();
			this.setDesignation(temp);
			
			
			System.out.print("Enter Qualification :");
			temp = cs.next();	
			this.setQualification(temp);
			
			
			System.out.print("Enter Department :");
			temp = cs.next();	
			this.setDepartment(temp);
			
			System.out.print("Enter Status :");
			temp = cs.next();	
			this.setStatus(temp);
			
			System.out.print("Enter Basic salary :");
			t = sc.nextInt();	
			this.setB_salary(t);
			
			System.out.print("Enter Expenses :");
			t = sc.nextInt();
			this.setExpenses(t);
			
			if(update_professional_employee()) System.out.println("Professional Details Updated");
			
			
			
		}
		else
			System.out.println("Employee Doesnt exist");
		
	}
	
	void remove_employee() throws SQLException
	{
		check ch = new check();
		System.out.print("Enter the employee :");
		this.id  = cs.next();
		
		if(ch.emp_check(id))
		{
			PreparedStatement p2 = c
					.prepareStatement("update emp_professional set status = ? where empid = ?");
			
			
			p2.setString(1, "Resigned");
			p2.setString(2, this.id);
			
			int i = p2.executeUpdate();
			
			if(i == 1)
				System.out.println("Status Updated");
		}
		else
			System.out.println("Employee does'nt exist");
	}
	
	void generate_employee_report() throws SQLException
	{
		rg r = new rg();
		r.generate_employee_report();
	}
	
	void add_bonus() throws SQLException, ParseException
	{
		check ch = new check();
		System.out.print("Enter the employee :");
		String id  = cs.next();
		
		int bonus;
		
		
		
		
		if(ch.emp_check(id))
		{
			System.out.print("Enter Bonus :");
			bonus = sc.nextInt();
			
			System.out.print("Enter date (YYYY-MM-DD) :");
			temp = cs.next();	
			d1 = fr.parse(temp);
			d = new java.sql.Date(d1.getTime());
			
			PreparedStatement p8 = c.prepareStatement("insert into appraisals values(?,?,?)");
			p8.setString(1, id);
			p8.setInt(3, bonus);
			p8.setDate(2, d);
			
			int i = p8.executeUpdate();
			
			if(i == 1) System.out.println("Database Updated");
			
		}
		else
		{
			System.out.println("Employee does'nt exist");
		}
	}
	
	void add_appraisals() throws SQLException, ParseException
	{
		check ch = new check();
		System.out.print("Enter the employee :");
		String id  = cs.next();
		int t;
		
		int ta,da,hra,health_ins,edu_allowance,bonus;
		
		
		if(ch.emp_check(id))
		{
			
			PreparedStatement p4 = c.prepareStatement("select * from emp_professional where empid = ?");
			
			p4.setString(1, id);
			
			ResultSet r = p4.executeQuery();
			
			if(r.next())
				this.b_salary = r.getInt("b_salary");
			
			System.out.print("Enter TA : ");
			ta = sc.nextInt();
			
			System.out.print("Enter DA : ");
			da = sc.nextInt();
			
			System.out.print("Enter HRA : ");
			hra = sc.nextInt();
			
	
			
			System.out.print("Enter health insurance : ");
			health_ins = sc.nextInt();
			
			System.out.print("Enter educational allowance : ");
			edu_allowance = sc.nextInt();
			
			System.out.print("Enter bonus : ");
			bonus = sc.nextInt();
			
			System.out.print("Enter date (YYYY-MM-DD) :");
			String temp = cs.next();	
			d1 = fr.parse(temp);
			d = new java.sql.Date(d1.getTime());
			
			
			PreparedStatement p5 = c.prepareStatement("insert into salary_details values(?,?,?,?,?,?,?,?,?)");
			
			p5.setString(1, id);
			p5.setInt(2, this.b_salary);
			p5.setInt(3, ta);
			p5.setInt(4, da);
			p5.setInt(5, hra);
			p5.setInt(6, health_ins);
			p5.setInt(7, edu_allowance);
			p5.setInt(8, bonus);
			p5.setDate(9, d);
			
			int i = p5.executeUpdate();
			
			if(i==1)
				System.out.println("Salary Details Updated");

			
		}
		else
			System.out.println("Employee does'nt exist");
		
	}
	
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
	
	public void setId(String id) {
		this.id = id;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public void setAddress(String address) {
		this.address = address;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	
	public void setDob(Date dob) {
		this.dob = dob;
	}

	
	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getB_salary() {
		return b_salary;
	}

	public void setB_salary(int b_salary) {
		this.b_salary = b_salary;
	}

	public int getExpenses() {
		return expenses;
	}

	public void setExpenses(int expenses) {
		this.expenses = expenses;
	}
	
}
