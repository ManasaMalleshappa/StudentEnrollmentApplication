package com.enrollment;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



public class TableFromDatabase extends JFrame {

	private JPanel contentPane;
	Connection conn=null;
	int a;
	int b,sum=0;
	int x,y;
	 ArrayList<String> arr1=null;
	boolean chek=false;
	boolean chek1=false;
	JButton btnNewButton_1;
	protected static String username;
	protected static String password;
	protected static String ab;
int k;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableFromDatabase frame = new TableFromDatabase(username,password,ab);
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
	public TableFromDatabase(final String username,final String password,final String ab) {
		conn=DBConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		
		Vector<Object> columnNames = new Vector<Object>();
        Vector<Object> data = new Vector<Object>();
        
        
        
        
        String sql = "Select s.secNo,s.sem,c.classId,s.timeoffered,c.Description,c.Numofcredits from class c,section s where c.classId=s.classId and sem='"+ab+"'";
        Statement stmt;
		try {
			stmt = conn.createStatement();
			  ResultSet rs = stmt.executeQuery( sql );
		        ResultSetMetaData md = rs.getMetaData();
		        int columns = md.getColumnCount();
		        a=columns;
		        
		       
		        for (int i = 1; i <= columns; i++)
	            {
	                columnNames.addElement( md.getColumnName(i) );
	            }
		        columnNames.addElement("Select");
		        
		        while (rs.next())
	            {
	                Vector<Object> row = new Vector<Object>(columns+1);

	                for (int i = 1; i <= columns; i++)
	                {
	        			
	                    row.addElement( rs.getObject(i) );
	                }
	               row.addElement(false);
	                

	                data.addElement( row );
	            }


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 DefaultTableModel model = new DefaultTableModel(data, columnNames)
	        {
	        	@Override
	            public Class getColumnClass(int column)
	            {
	                for (int row = 0; row < getRowCount(); row++)
	                {
	                    Object o = getValueAt(row, column);

	                    if (o != null)
	                    {
	                        return o.getClass();
	                    }
	                }

	                return Object.class;
	            }
	        };
	        
	        final JTable table = new JTable( model );
	        table.setBackground(new Color(255, 255, 255));
	        JScrollPane scrollPane = new JScrollPane( table );
	        getContentPane().add( scrollPane );
	        
	        final JButton btnLoad = new JButton("Load");
	        arr1=new ArrayList<String>();
	        btnLoad.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		//JOptionPane.showMessageDialog(null, a);
	        		btnLoad.setEnabled(false);
	        		for (int i = 0; i < table.getRowCount(); i++) {
						Boolean chked = Boolean.valueOf(table.getValueAt(i, a)
								.toString());
						
						String dataCol1 = table.getValueAt(i, 2).toString();
						String dataCol2=table.getValueAt(i, 4).toString();
						String dataCol3=table.getValueAt(i, 0).toString();
						String dataCol4=table.getValueAt(i, 5).toString();
						
						
						if (chked) {
							
							/*JOptionPane.showMessageDialog(null, dataCol1);
							JOptionPane.showMessageDialog(null, dataCol2);
							JOptionPane.showMessageDialog(null, dataCol3);
							JOptionPane.showMessageDialog(null, i);*/
							
							
							
							
							arr1.add(dataCol1);
							System.out.println(arr1.size());
							
							System.out.println(arr1);
							
							Iterator<String> itr = arr1.iterator();
							while(itr.hasNext())
							{
								JOptionPane.showMessageDialog(null, itr.next());
							}
							
							
							b=Integer.parseInt(dataCol4);
							sum=sum+b;
							
							JOptionPane.showMessageDialog(null, sum);
							
							/*change*/
							/* String sql1 = "Select * from havepreq where classId='"+dataCol1+"' and succlassId IN (select classId from enrolled where sid='"+password+"')";
						     Statement stmt;
						     try {
								stmt = conn.createStatement();
								ResultSet rs = stmt.executeQuery( sql1 );
								if(!rs.next())
								{
									JOptionPane.showMessageDialog(null, "prerequisite not taken");
								}
								else
								{
									JOptionPane.showMessageDialog(null, "prerequisite taken");
									chek=true;
								}*/
								
								
								/*change*/
								String sql2="select * from havepreq where classId='"+dataCol1+"'";
								String sql3="select * from havepreq where classId='"+dataCol1+"' and succlassId IN (select classId from enrolled where sid='"+password+"' and grade is not NULL)";
								Statement stmt1;
								try
								{
									stmt1 = conn.createStatement();
									ResultSet rs = stmt1.executeQuery( sql2 );
									if(!rs.next())
									{
										chek=true;
									}
									else
									{
										stmt1 = conn.createStatement();
										ResultSet rs1 = stmt1.executeQuery( sql3 );
										if(rs1.next())
										{
											chek=true;
										}
									}
									
								}
										
								
								
								
								
								
								/*change*/
							 catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							

							/*change*/
							}
							}
						
					
	        		
	        		if(sum>18)
	        		{
						JOptionPane.showMessageDialog(null, "credit hours is greater than 18");
						chek1=false;
	        		}
	        		else
	        		{
	        			chek1=true;
	        		}
	        		
	        		if(chek==true && chek1==true)
	        		{
	        			btnNewButton_1.setEnabled(true);
	        			JOptionPane.showMessageDialog(null, "awesome");
	        		}
						
					
	        	}
	        });
	        scrollPane.setRowHeaderView(btnLoad);
	        
	        JButton btnNewButton = new JButton("New button");
	        scrollPane.setColumnHeaderView(btnNewButton);
	        
	        

	        JPanel buttonPanel = new JPanel();
	        getContentPane().add( buttonPanel, BorderLayout.SOUTH );
	        
	         btnNewButton_1 = new JButton("Next");
	         btnNewButton_1.addActionListener(new ActionListener() {
	         	public void actionPerformed(ActionEvent arg0) {
	         		/*finalcourses fc=new finalcourses(username,password,ab,arr1);
	         		fc.setVisible(true);*/
	         		

	         		dispose();
	         		
	         		FinalClass tableview=new FinalClass(username,password,ab,arr1);
	         		tableview.setVisible(true);
	         		

	         		
	         		
	         		
	         	}
	         });
	        buttonPanel.add(btnNewButton_1);
	        
	        btnNewButton_1.setEnabled(false);
	        
	        JButton btnRefresh = new JButton("Refresh");
	        btnRefresh.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        
	        		btnLoad.setEnabled(true);
	        		sum=0;
	        		arr1=null;
	        		btnNewButton_1.setEnabled(false);
	        	}
	        });
	        contentPane.add(btnRefresh, BorderLayout.EAST);
	    
   
     
	}
	}


