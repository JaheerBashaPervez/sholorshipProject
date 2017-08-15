package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseAccess {
	boolean flag = false;

	Connection getDbConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
		con.commit();
		return con;
	}

	public int studentRegistration(long rollno, String email, String fname, String lname, String gender, String city,
			String password) throws ClassNotFoundException, SQLException {
		int defaultmoney=500;
		Connection con = getDbConnection();
		Statement stmt = con.createStatement();
		int x = stmt.executeUpdate("INSERT INTO STUDENT_REGISTRATION VALUES(S_MYTABLE.NEXTVAL," + rollno + ",'" + fname
				+ "','" + lname + "','" + gender + "','" + city + "','" + "" + password + "','" + email + "','"+defaultmoney+"')");
		con.close();
		return x;
	}

	public boolean studentLogin(String username, String password) throws ClassNotFoundException, SQLException {

		Connection con = getDbConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT_REGISTRATION");
		while (rs.next()) {
			String usernamedb = rs.getString(8);
			String passworddb = rs.getString(7);
			if (usernamedb!=null && passworddb!=null &&usernamedb.trim().equals(username.trim()) && passworddb.trim().equals(password.trim())) 
				{
					flag = true;
				}
			
		}
		con.close();
		return flag;
	}

	public boolean adminLogin(String username, String password) throws ClassNotFoundException, SQLException {

		Connection con = getDbConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ADMIN_TABLE");
		while (rs.next()) {
			String usernamedb = rs.getString(1);
			String passworddb = rs.getString(2);
			if (usernamedb!=null && passworddb!=null && usernamedb.trim().equals(username.trim()) && passworddb.trim().equals(password.trim())) {
				flag = true;
			}
		}
		con.close();
		return flag;
	}

	public boolean instructorLogin(String username, String password) throws ClassNotFoundException, SQLException {

		Connection con = getDbConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM INSTRUCTOR_TABLE");
		while (rs.next()) {
			String usernamedb = rs.getString(2);
			String passworddb = rs.getString(6);
			if (usernamedb.trim().equals(username.trim()) && passworddb.trim().equals(password.trim())) {
				flag = true;
			}
		}
		con.close();
		return flag;
	}

	public int instructorRegistration(String email, String name, String gender, String city, String password) throws ClassNotFoundException, SQLException {
		Connection con = getDbConnection();
		Statement stmt = con.createStatement();
		int x = stmt.executeUpdate("INSERT INTO INSTRUCTOR_TABLE VALUES(INSTRUCTOR_SEQUENCE.NEXTVAL,'"  + email
				+ "','"+ name + "','" + gender + "','" + "" + city + "','" + password + "')");
		con.close();
		return x;
		
	}

	public int transaction(int amount, String username) throws ClassNotFoundException, SQLException {
		Connection con = getDbConnection();
		int value=0;
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT_REGISTRATION");
		
		while (rs.next()) 
		{
			if(username.equals(rs.getString(8)))
			{
				int balance =rs.getInt(9);
				if(balance>amount)
				{	
					value=balance-amount;
					stmt.execute("update STUDENT_REGISTRATION set Amount="+value+" where email='"+username+"'");
					
				}
				else
					return -1;
			}
		}
		con.close();
		return value;
		
	}

	public int addMoney(int rollno, double amount) throws ClassNotFoundException, SQLException {
		float balance=0,balance2=0;
		Connection con = getDbConnection();
		Statement stmt = con.createStatement();
		ResultSet rs1=stmt.executeQuery("select \"Amount\" from Budget");
		while(rs1.next())
		{
			balance2=(float)rs1.getInt(1);
		}
		ResultSet rs=stmt.executeQuery("select amount from student_registration where roll_no="+rollno);
		while(rs.next())
		{
			balance=(float) (rs.getInt(1)+amount);
		}
		stmt.executeUpdate("update Budget set \"Amount\"="+(balance2-amount));
		int status=stmt.executeUpdate("update STUDENT_REGISTRATION set Amount="+balance+" where ROLL_NO='"+rollno+"'");
		con.close();
		return status;
	}

	public int removeMoney(int rollno, double amount) throws SQLException, ClassNotFoundException {
		float balance=0;
		int status=0;
		Connection con = getDbConnection();
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select amount from student_registration where roll_no="+rollno);
		while(rs.next())
		{
			balance=(float) (rs.getInt(1)-amount);
		}
		if(balance>0)
		 status=stmt.executeUpdate("update STUDENT_REGISTRATION set Amount="+balance+" where ROLL_NO='"+rollno+"'");
		else
		status=stmt.executeUpdate("update STUDENT_REGISTRATION set Amount="+0+" where ROLL_NO='"+rollno+"'");
		con.close();
		return status;
	}

}
