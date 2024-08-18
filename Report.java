package sign;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet("/issue")

public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection  conn;
	static PreparedStatement stmt;
	
	static String dburl="jdbc:mysql://localhost/db_1";
	static String user="root";
	static String pass ="root";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		try {
			String nameuser=req.getParameter("nameuser");
			String issue=req.getParameter("textfield");
			String sector =req.getParameter("sector");
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dburl, user, pass);
			String sql="insert into reports values((select roll from login where username = ?) ,?,?)";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,nameuser);
			stmt.setString(2,issue);
			stmt.setString(3,sector);
			int rs;
			rs=stmt.executeUpdate();
			
			PrintWriter pw=res.getWriter();
			if(rs!=-1) {
				pw.println("data inserted successfully!!");

			}else {
				pw.println("insertion failed!!!");
				System.out.println("insertion failed!!!");
			}
			System.out.println(stmt);
		} catch (SQLException e) {
			System.out.println(stmt);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
