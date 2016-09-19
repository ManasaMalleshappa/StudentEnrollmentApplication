package com.enrollment;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class StudentInfo extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField firsttextField;
	private JTextField lasttextField;
	private JTextField sidtextField;
	private JTextField dobtextField;
	private JTextField majortextField;
	JButton RegisterButton;
	Connection conn=null;
	private JButton CloseButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentInfo frame = new StudentInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentInfo() {
		conn=DBConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel StudentLabel = new JLabel("Student Information");
		StudentLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		StudentLabel.setBounds(258, 11, 200, 22);
		getContentPane().add(StudentLabel);
		
		JLabel FirstLabel = new JLabel("First Name");
		FirstLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		FirstLabel.setBounds(99, 68, 103, 22);
		getContentPane().add(FirstLabel);
		
		JLabel LastLabel = new JLabel("Last Name");
		LastLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LastLabel.setBounds(99, 120, 103, 22);
		getContentPane().add(LastLabel);
		
		JLabel SidLabel = new JLabel("Student ID");
		SidLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		SidLabel.setBounds(99, 174, 103, 22);
		getContentPane().add(SidLabel);
		
		JLabel DOBLabel = new JLabel("DOB");
		DOBLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		DOBLabel.setBounds(99, 221, 46, 22);
		getContentPane().add(DOBLabel);
		
		JLabel MajorLabel = new JLabel("Major");
		MajorLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		MajorLabel.setBounds(99, 263, 60, 29);
		getContentPane().add(MajorLabel);
		
		firsttextField = new JTextField();
		firsttextField.setBounds(276, 68, 136, 24);
		getContentPane().add(firsttextField);
		firsttextField.setColumns(10);
		
		lasttextField = new JTextField();
		lasttextField.setBounds(276, 120, 136, 24);
		getContentPane().add(lasttextField);
		lasttextField.setColumns(10);
		
		sidtextField = new JTextField();
		sidtextField.setBounds(276, 174, 136, 24);
		getContentPane().add(sidtextField);
		sidtextField.setColumns(10);
		
		dobtextField = new JTextField();
		dobtextField.setBounds(276, 221, 136, 24);
		getContentPane().add(dobtextField);
		dobtextField.setColumns(10);
		
		majortextField = new JTextField();
		majortextField.setBounds(276, 272, 136, 22);
		getContentPane().add(majortextField);
		majortextField.setColumns(10);
		
		RegisterButton = new JButton("Register");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query="insert into students (fname,lname,sid,major) values (?,?,?,?)";
				PreparedStatement pst;
				try {
					pst = conn.prepareStatement(query);
					pst.setString(1, firsttextField.getText());
					pst.setString(2, lasttextField.getText());
					pst.setInt(3, Integer.parseInt(sidtextField.getText()));
					//pst.setString(4,dobtextField.getText() );
					pst.setString(4,majortextField.getText() );
					pst.execute();
					JOptionPane.showMessageDialog(null, "Student Data Saved");
					RegisterButton.setEnabled(false);
					CloseButton.setEnabled(true);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				
			}
		});
		RegisterButton.setBounds(146, 343, 89, 23);
		getContentPane().add(RegisterButton);
		
		CloseButton = new JButton("Close");
		CloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				Login window = new Login();
				
				window.frame.setVisible(true);
				
			}
		});
		CloseButton.setBounds(411, 343, 89, 23);
		getContentPane().add(CloseButton);
	}

}
