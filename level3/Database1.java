package level3;
import java.sql.*;

public class Database1 {

	public static void main(String[] args) {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver accepted ");
			Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/chettinad","root","12345");
			System.out.println("Connection Success");
			//--------Statement--> purpose --> to write sql queries
			Statement st=con.createStatement();
			//-----ResultSet --> purpose --> to store sql queries
			ResultSet rs=st.executeQuery("select * from student");
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("Error Reason:"+e.toString());
			}
		

	}

}
