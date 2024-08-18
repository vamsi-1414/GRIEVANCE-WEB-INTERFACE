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

@WebServlet("/viewReports")
public class ViewReports extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection  conn;
	static PreparedStatement stmt;
	static ResultSet rs;
	static String dburl="jdbc:mysql://localhost/db_1";
	static String user="root";
	static String pass ="root";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			String nameuser=req.getParameter("nameuser");
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dburl, user, pass);
			String sql="select issue from reports where roll = (select roll from login where username = ? )";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,nameuser);
			rs=stmt.executeQuery();
			while(rs.next()) {
				PrintWriter pw=res.getWriter();
				res.setContentType("text/html");
				String pr=rs.getString("issue");
				System.out.println(pr);
				pw.println("<html><body><h3>"
						+ pr
						+ "</h3></body></html>");
				
			}
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
