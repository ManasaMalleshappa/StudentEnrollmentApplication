package com.enrollment;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;




public class FinalClass extends JFrame {
Connection conn=null;
	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldClassId;
	private JTextField textSecNo;
	private JTextField textFieldSem;
	private JLabel lblNewLabel;
	private JLabel SecNoLabel;
	private JLabel NumofCredits;
	private JButton EnrolledButton;
	protected static final String username = null;
	protected static final String password = null;
	protected static final String ab = null;
	protected static final ArrayList<String> arr1=null;
	int pswd;
	private JButton btnBackButton;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalClass frame = new FinalClass(username,password,ab,arr1);
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
	public FinalClass(String username,String password,final String ab,ArrayList<String> arr1) {
		conn=DBConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		System.out.println(arr1.size());
		
		Vector<Object> columnNames = new Vector<Object>();
        Vector<Object> data = new Vector<Object>();
        PreparedStatement stmt;
        
       // String sql = "Select * from class c,section s where c.classId=s.classId and sem='"+ab+"'";
      
        /*****/
        
      try
        {
        	Iterator iterator=arr1.iterator();
    		while(iterator.hasNext())
    		{
    			String classId=(String)iterator.next();
    			stmt=conn.prepareStatement("select c.classId,s.secNo,s.sem,s.timeoffered,s.Emp_no,c.Description,c.Numofcredits from class c,section s where c.classId=s.classId and c.classId='"+classId+"' and sem='"+ab+"'");
    		
			  ResultSet rs = stmt.executeQuery( );
		        ResultSetMetaData md = rs.getMetaData();
		        int columns = md.getColumnCount();
		        
		        
		       
		        for (int i = 1; i <= columns; i++)
	            {
	                columnNames.addElement( md.getColumnName(i) );
	            }
		       
		        
		        while (rs.next())
	            {
	                Vector<Object> row = new Vector<Object>(columns);

	                for (int i = 1; i <= columns; i++)
	                {
	        			
	                    row.addElement( rs.getObject(i) );
	                }
	               

	                data.addElement( row );
	            }

    		}

        	
        	
        	
        }
       /****/
        
        /*try {
			stmt = conn.createStatement();
			  ResultSet rs = stmt.executeQuery( sql );
		        ResultSetMetaData md = rs.getMetaData();
		        int columns = md.getColumnCount();
		       
		        
		       
		        for (int i = 1; i <= columns; i++)
	            {
	                columnNames.addElement( md.getColumnName(i) );
	            }
		        
		        
		        while (rs.next())
	            {
	                Vector<Object> row = new Vector<Object>(columns+1);

	                for (int i = 1; i <= columns; i++)
	                {
	        			
	                    row.addElement( rs.getObject(i) );
	                }
	               
	                

	                data.addElement( row );
	            }


		} */
        
       catch (SQLException e) {
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(142, 66, 530, 344);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try
				{
					
					int row=table.getSelectedRow();
					String classID_=(table.getModel().getValueAt(row, 0).toString());
					String query="Select c.classId,s.secNo,s.sem,s.timeoffered,s.Emp_no,c.Description,c.Numofcredits from class c,section s where c.classId=s.classId and sem='"+ab+"' and c.classId='"+classID_+"'";
					//String query="select * from class where classID='"+classID_+"'";
					PreparedStatement pst=conn.prepareStatement(query);
					//pst.setString(1,(String)comboBoxName.getSelectedItem() );
					ResultSet rs= pst.executeQuery();
					while(rs.next())
					{
						textFieldClassId.setText(rs.getString("classId"));
						textSecNo.setText(rs.getString("secNo"));
						textFieldSem.setText(rs.getString("sem"));
						
					}
					pst.close();
					
				}
				catch(Exception e)
				{
				}
			}
		});
		scrollPane.setViewportView(table);
		
		textFieldClassId = new JTextField();
		textFieldClassId.setBounds(27, 64, 86, 20);
		contentPane.add(textFieldClassId);
		textFieldClassId.setColumns(10);
		
		textSecNo = new JTextField();
		textSecNo.setBounds(27, 112, 86, 20);
		contentPane.add(textSecNo);
		textSecNo.setColumns(10);
		
		textFieldSem = new JTextField();
		textFieldSem.setBounds(27, 168, 86, 20);
		contentPane.add(textFieldSem);
		textFieldSem.setColumns(10);
		
		lblNewLabel = new JLabel("classId");
		lblNewLabel.setBounds(26, 39, 46, 14);
		contentPane.add(lblNewLabel);
		
		SecNoLabel = new JLabel("SecNo");
		SecNoLabel.setBounds(27, 87, 70, 14);
		contentPane.add(SecNoLabel);
		
		NumofCredits = new JLabel("Sem");
		NumofCredits.setBounds(27, 143, 86, 14);
		contentPane.add(NumofCredits);
		
		pswd=Integer.parseInt(password);
		
		EnrolledButton = new JButton("Enrolled");
		EnrolledButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query="insert into enrolled (grade,Dropdate,sid,secNo,sem,classId) values (?,?,?,?,?,?)";
				PreparedStatement pst;
				try {
					pst = conn.prepareStatement(query);
					//pst.setInt(1, sid);
					pst.setNull(1, java.sql.Types.VARCHAR);
					pst.setNull(2,java.sql.Types.TIMESTAMP);
					pst.setInt(3, pswd);
					
					/*pst.setString(2, lasttextField.getText());
					pst.setInt(3, Integer.parseInt(sidtextField.getText()));*/
					//pst.setString(4,dobtextField.getText() );
					pst.setString(4,textSecNo.getText() );
					pst.setString(5,textFieldSem.getText() );
					pst.setString(6,textFieldClassId.getText() );
					
					
					pst.execute();
					JOptionPane.showMessageDialog(null, "Enrolled Data Saved");
				
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "You have already enrolled for this class");
					e.printStackTrace();
				}
			}
		});
		EnrolledButton.setBounds(24, 236, 89, 23);
		contentPane.add(EnrolledButton);
		
		btnBackButton = new JButton("Close");
		btnBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TableFromDatabase tabel=new TableFromDatabase(null, null, null);
				tabel.setVisible(false);
				
				
				
				dispose();
				Login window = new Login();
				window.frame.setVisible(true);
			}
		});
		btnBackButton.setBounds(24, 313, 89, 23);
		contentPane.add(btnBackButton);
	}
}
