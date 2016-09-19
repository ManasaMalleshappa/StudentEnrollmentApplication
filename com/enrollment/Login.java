package com.enrollment;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Login extends JFrame {

	JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	Connection connection=null; 
	String username;
	String password;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection=DBConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 594, 382);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel LoginLabel = new JLabel("Login Page");
		LoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		LoginLabel.setBounds(141, 11, 185, 55);
		frame.getContentPane().add(LoginLabel);
		
		JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UsernameLabel.setBounds(100, 90, 103, 23);
		frame.getContentPane().add(UsernameLabel);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		PasswordLabel.setBounds(100, 153, 92, 23);
		frame.getContentPane().add(PasswordLabel);
		
		textField = new JTextField();
		textField.setBounds(244, 90, 143, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(244, 153, 143, 24);
		frame.getContentPane().add(passwordField);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					String query="select * from students where fname=? and sid=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, passwordField.getText());
					username=textField.getText();
					password=passwordField.getText();
					
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next())
					{
						count++;
					}
					
					if(count==1)
					{
						JOptionPane.showMessageDialog(null, "User Name and Password is Correct");
						frame.dispose();
						CourseInfo info=new CourseInfo(username,password);
						
						info.setVisible(true);
					}
					else if(count>1)
					{
						JOptionPane.showMessageDialog(null, "Duplicate User Name and Password is Correct");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "User Name and Password is not Correct");
						frame.dispose();
						StudentInfo studinfo=new StudentInfo();
						studinfo.setVisible(true);
						
						
					}
					rs.close();
					pst.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, e);
					
				}
				
			}
				
			
		});
		LoginButton.setBounds(202, 207, 89, 23);
		frame.getContentPane().add(LoginButton);
	}

}
