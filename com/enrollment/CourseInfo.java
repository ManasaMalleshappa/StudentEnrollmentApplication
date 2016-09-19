package com.enrollment;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CourseInfo extends JFrame {

	protected static final String username = null;
	protected static final String Password = null;
	private JPanel contentPane;
	Connection conn=null;
	
	JComboBox SemSelectcomboBox;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseInfo frame = new CourseInfo(username,Password);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void fillComboBox()
	{
		try
		{
		//String query="select distinct(classID) from section";
			String query="select distinct(sem) from section where sem='SP16' or sem='FA15'";
		PreparedStatement pst=conn.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		
		while(rs.next())
		{
			SemSelectcomboBox.addItem(rs.getString("sem"));
		}
		
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			
		}
	}

	/**
	 * Create the frame.
	 */
	public CourseInfo(final String username,final String Password) {
		conn=DBConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		
		JLabel SemInfoLabel = new JLabel("Enter the semester which you want to enroll for");
		SemInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		SemInfoLabel.setBounds(128, 119, 397, 22);
		contentPane.add(SemInfoLabel);
		
		SemSelectcomboBox = new JComboBox();
		SemSelectcomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String a=(String)SemSelectcomboBox.getSelectedItem();
				TableFromDatabase classinfo=new TableFromDatabase(username,Password,a);
				classinfo.setVisible(true);
				dispose();
				
				
		        
			}
		});
		SemSelectcomboBox.setBounds(262, 164, 91, 20);
		contentPane.add(SemSelectcomboBox);
		
		fillComboBox();
	}
}
