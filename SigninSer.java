package sign;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/signin")

public class SigninSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String dburl="jdbc:mysql://localhost:3306/db_1";
	static String user="root";
	static String pass="root";
	static Connection conn;
	static PreparedStatement stmt;
	String sql="insert into login values(?,?,?);";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String roll=req.getParameter("roll");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dburl, user, pass);
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,username);;
			stmt.setString(2,password);
			stmt.setString(3,roll);
			int rs=stmt.executeUpdate();
			System.out.println(rs);
			res.sendRedirect("login.html");
		} catch (SQLException e) {
			req.setAttribute("error","invalid data");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
