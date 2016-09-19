package com.enrollment;
import java.sql.*;

import javax.swing.*;

public class DBConnection {
	Connection conn=null;
	
	public static Connection dbConnector()
	{

	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/admin", "root", "password1");
		//Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:8090/manasamm", "manasamm", "830797507");
		//JOptionPane.showMessageDialog(null, "connection successful");
		return conn;
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null, e);
		return null;
	}
	}
}
