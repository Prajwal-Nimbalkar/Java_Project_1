import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.text.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

class bill extends JFrame implements ActionListener
{
	String colHeads[] = {"Vehicle Id","Vehicle Name","Kilometers","Amount"};
	String colHeads1[] = {"Bill Id","Customer Name"};
	String data[][];
	String data1[][];
	JLabel book_id = new JLabel("Enter Booking ID");
	JLabel bill = new JLabel("Print Bill");
	JTextField tbook = new JTextField(20);
	JLabel l1 = new JLabel("RENTO");
	JLabel l2 = new JLabel("Name:");
	JLabel l3;
	JLabel l4 = new JLabel("Date:");
	JLabel l5;
	JLabel l6 = new JLabel("Grand Total:");;
	JLabel l7;
	JButton b1 = new JButton("Generate Bill");
	JButton b2 = new JButton("Print");
	JButton b3 = new JButton("AA");
    JTable table = new JTable();
    JTable table1 = new JTable();

    Connection cn,cn1;    Statement stm,stm1;
    ResultSet rs,rs2,rs1;    int r_cnt=0,i,j;
	String sql1,sql2;
	PreparedStatement prstm;

    bill()
    {
        super("Bill");
		setLayout(null);
		setSize(2000,800);    
		setVisible(true);
			add(l1);	add(l2);	add(l4);	
				add(l6);	
		add(book_id);	add(tbook);	add(b1);	add(bill);	add(table);
		add(b2);
		add(b3);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		bill.setBounds(550,10,200,20);
		book_id.setBounds(400,60,200,20);
		tbook.setBounds(510,50,150,40);
		b1.setBounds(510,100,150,40);
		b2.setBounds(510,150,150,40);
		b3.setBounds(20,20,150,40);
		
		

        try
        {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/JavaProject","root", "1234");
            stm = cn.createStatement();
        }
        catch (Exception e) 
		{            
			e.printStackTrace();        
		}

         addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				new home();
				setVisible(false);
			}
		});
        setVisible(true);
    }
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if(e.getSource()==b1)
			{
				String date1="";
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();
				date1 = dtf.format(now);
				
sql1 ="update bill1 set status='paid' where bill_id="+tbook.getText();
                prstm = cn.prepareStatement(sql1);
                prstm.execute() ;
                prstm.close();
				
sql2 ="update cust_vehicle set status1='deallocate' where bill_id="+tbook.getText();
                prstm = cn.prepareStatement(sql2);
                prstm.execute() ;
                prstm.close();

			
				 rs = stm.executeQuery("select count(*) from cust_vehicle where bill_id="+tbook.getText());
				 rs.next();
				r_cnt=rs.getInt(1);
				if(r_cnt == 0)
				{
					JOptionPane.showMessageDialog(null,"Bill Id not found");
				}
				else
				{
				data = new String[r_cnt][4];
				rs = stm.executeQuery("select * from cust_vehicle where bill_id="+tbook.getText());
				i=0;
				while(rs.next())
				{
						data[i][0]= rs.getString(4);                
						data[i][1]= rs.getString(5);
						data[i][2]= rs.getString(6);
						data[i][3]= rs.getString(7);
						l3 = new JLabel(rs.getString(3));
						l5 = new JLabel(date1);
						
						i++;
				}
				
				table = new JTable(data, colHeads);
				table.setEnabled(false);
				int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				JScrollPane jsp = new JScrollPane(table, v, h);
				add(jsp);
				jsp.setBounds(400,260,400,300);	
				
				add(l3);add(l5);
				l1.setBounds(560,200,150,40);
				
				l2.setBounds(400,230,150,40);
				l3.setBounds(440,230,150,40);
				
				l4.setBounds(700,230,150,40);
				l5.setBounds(732,230,150,40);
rs2 = stm.executeQuery
				("select sum(amount) from cust_vehicle where bill_id="+tbook.getText());
				rs2.next();
				l7 = new JLabel(rs2.getString(1));
				add(l7);
				l6.setBounds(675,550,150,40);
				l7.setBounds(750,550,150,40);
				
			}
			}
			if(e.getSource()==b2)
			{
				MessageFormat h1 = new MessageFormat("Bill");
				MessageFormat f1 = new MessageFormat("Vehicle Booking");
				try
				{
					table.print(JTable.PrintMode.FIT_WIDTH,h1,f1);
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
				l3.setText("");
				l5.setText("");
				l7.setText("");
				//l5 = new JLabel("");
				//l7 = new JLabel("");
			}
			
			if(e.getSource()==b3)
			{
				try
				{
					cn1 = DriverManager.getConnection("jdbc:mysql://localhost:3307/JavaProject","root", "1234");
					stm1 = cn1.createStatement();
								
					rs1 = stm1.executeQuery("select count(distinct bill_id) from cust_vehicle where status1='allocate'");
						 rs1.next();
						int r_cnt1=rs1.getInt(1);

						data1 = new String[r_cnt1][2];

						rs1 = stm1.executeQuery("select distinct bill_id,cust_name from cust_vehicle where status1='allocate'");
						j=0;
						while(rs1.next())
						{
							data1[j][0]= rs1.getString(1);              
							data1[j][1]= rs1.getString(2);
							j++;
						}
				
						table1 = new JTable(data1, colHeads1);
						table1.setEnabled(false);
						int v1 = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
						int h1 = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
						JScrollPane jsp1 = new JScrollPane(table1, v1, h1);
						add(jsp1);
						jsp1.setBounds(10,80,200,350);				
						}
						catch (Exception e1) 
						{            
							e1.printStackTrace();        
						}
			}
		}
		catch(SQLException e1)
		{
			System.out.println(e1);
		}
	}

    public static void main(String args[])
    {
        new bill();
    }
}