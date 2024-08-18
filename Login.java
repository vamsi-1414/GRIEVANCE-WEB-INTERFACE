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

@WebServlet("/login")

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Connection conn;
	static PreparedStatement stmt;
	static ResultSet rs;
	String sql=" select *from login where username =? and password=?";
	static String dburl="jdbc:mysql://localhost:3306/db_1";
	static String user="root";
	static String pass="root";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	String username=req.getParameter("username");
	String password =req.getParameter("password");
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection(dburl,user,pass);
		stmt=conn.prepareStatement(sql);
		stmt.setString(1,username);
		stmt.setString(2, password);
		
		rs=stmt.executeQuery();
		
		if(rs.next()) {
			
			res.sendRedirect("logged.html");

		}
		else {
			PrintWriter ps=res.getWriter();
			res.setContentType("text/html");
			ps.write("invalid username or password!!");
			
		}

		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	catch(ClassNotFoundException e) {
		e.printStackTrace();
	}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
