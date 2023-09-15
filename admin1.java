import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;
import java.text.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;

class admin1 extends JFrame implements ActionListener,KeyListener,ItemListener
{
	String colHeads1[] = {"Customer Id","Cutomer Name"};
	String data1[][];
	JLabel l1 = new JLabel("Billing");
	JLabel book_id = new JLabel("Enter Customer ID");
	JLabel km = new JLabel("Enter KM");
	JLabel book_veh = new JLabel("Booked Vehicles");
	
	JTextField tbook = new JTextField(20);
	JTextField tkm = new JTextField(20);
	JButton b1 = new JButton("CALCULATE");
	JButton b2 = new JButton("SUBMIT");
	JButton b3 = new JButton("AAA");
	JComboBox ch1 = new JComboBox();
    JTable table1 = new JTable();
	Date date = new Date();
	
    Connection cn,cn1;    Statement stm,stm1;
    ResultSet rs,rs1,rs2,rs3,rs4,rs5;    int r_cnt1=0,j;
	PreparedStatement prstm;
	String sql,sql1,sql2,sql3,sql4,sql5;

    admin1()
    {
        super("Billing");
		setLayout(null);
		setSize(2000,800);    
		setVisible(true);
		
		add(l1);	
		add(book_id);
		add(km);
		add(tbook);
		add(tkm);
		add(b1);
		add(b2);
		add(b3);
		add(book_veh);
		add(ch1);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		tbook.addKeyListener(this);
		b2.setEnabled(false);
		l1.setBounds(780,10,150,40);
		book_id.setBounds(600,50,150,40);	
		tbook.setBounds(710,50,200,40);
		book_veh.setBounds(600,100,150,40);	
		ch1.setBounds(710,100,200,40);
		
		km.setBounds(600,150,150,40);	
		tkm.setBounds(710,150,200,40);
		
		b1.setBounds(710,200,200,40);
		b2.setBounds(710,250,200,40);
		b3.setBounds(20,20,150,40);

       /* try
        {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/JavaProject","root", "1234");
            stm = cn.createStatement();
			
			
			
			rs = stm.executeQuery("select distinct count(customer_id) from booking where amount=0");
				 rs.next();
				r_cnt=rs.getInt(1);

				data = new String[r_cnt][2];

				rs = stm.executeQuery("select * from booking where amount=0");
				i=0;
				while(rs.next())
				{
						data[i][0]= rs.getString(3);                
						data[i][1]= rs.getString(10);                
						i++;
				}
				
				table = new JTable(data, colHeads);
				table.setEnabled(false);
				int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				JScrollPane jsp = new JScrollPane(table, v, h);
				add(jsp);
				jsp.setBounds(0,30,500,350);
				
				
        }
        catch (Exception e) 
		{            
			e.printStackTrace();        
		}*/

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
	
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e){}
	public void itemStateChanged(ItemEvent e){}
	public void keyTyped(KeyEvent e)
	{
		tbook.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent a)
			{
				if(a.getKeyCode()==KeyEvent.VK_ENTER)
				{
					search();
				}
			}
		});
	}
	
	public void search()
	{
		try
					{
						cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/JavaProject","root", "1234");
						stm = cn.createStatement();
						rs5 = stm.executeQuery("select * from booking where customer_id="+tbook.getText()+" and status='"+"booked"+"'");
						ch1.removeAllItems();
						while(rs5.next())
						{
							ch1.addItem(rs5.getString(2));
						}
						//System.out.println("hello");
						/*int book = Integer.parseInt(tbook.getText());
						rs = stm.executeQuery("select * from book where book_id="+book+"");
						rs.next();
						tname.setText(rs.getString(3));
						tcnt.setText(rs.getString(8));
						tcarid.setText(rs.getString(2));
						tbookdate.setText(rs.getString(4));*/
					}
					catch(Exception e1)
					{
						System.out.println(e1);
					}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			try
			{
				String date1="";
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();
				date1 = dtf.format(now);
				
				rs4 = stm.executeQuery
				("select * from vehicle where veh_id='"+ch1.getSelectedItem()+ "'");
				rs4.next();
				String vname=rs4.getString(2);
				int price = rs4.getInt(5);
				int km = Integer.parseInt(tkm.getText());
				int amount = price*km;
				
				int a = rs4.getInt(6);
				int prevkm = a+km;
				//System.out.println(prevkm);
sql3 ="update vehicle set status='available',prev_km="+prevkm+" where veh_id="+ch1.getSelectedItem();
                prstm = cn.prepareStatement(sql3);
                prstm.execute() ;
                prstm.close();
				
sql ="update booking set amount="+amount+",km="+km+",return_date='"+date1+"',status='"+"returned"+"' where vehicle_id="+ch1.getSelectedItem()+" and status='"+"booked"+"' and customer_id="+tbook.getText()+"";
                prstm = cn.prepareStatement(sql);
                prstm.execute() ;
                prstm.close();
				JOptionPane.showMessageDialog(null, "Inserted Successfully!");
	
rs5 = stm.executeQuery
				("select * from customer where cust_id="+tbook.getText()+"");
				rs5.next();
				String cname = rs5.getString(2);
sql4 = "insert into cust_vehicle(cust_id,cust_name,veh_id,veh_name,km,amount,status,bill_id,status1)values("+tbook.getText()+ ",'"+cname+"',"+ch1.getSelectedItem()+",'"+vname+"',"+km+","+amount+",'"+"unpaid"+"',"+0+",'"+"allocate"+"')";
                prstm = cn.prepareStatement(sql4);
                prstm.execute() ;
                prstm.close();
				tkm.setText("");
				
				
				int count = ch1.getItemCount();
				int c=0;
				c++;
				if(count==c)
				{
					b2.setEnabled(true);
				}
				ch1.removeItemAt(ch1.getSelectedIndex());
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		}
		if(e.getSource()==b2)
		{
		  try
		  {
				rs2 = stm.executeQuery
				("select sum(amount) from cust_vehicle where cust_id="+tbook.getText()+" and status='unpaid'");
				rs2.next();

sql1 = "insert into bill1(cust_id,amount,status)values("+tbook.getText()+ ","+rs2.getString(1)+",'"+"unpaid"+"')";
                prstm = cn.prepareStatement(sql1);
                prstm.execute() ;
                prstm.close();
			
				rs3 = stm.executeQuery
				("select * from bill1 where cust_id="+tbook.getText()+" and status='unpaid'");
				rs3.next();
				
sql2 ="update cust_vehicle set bill_id="+rs3.getString(1)+",status='paid' where cust_id="+tbook.getText()+" and status='unpaid'";
                prstm = cn.prepareStatement(sql2);
                prstm.execute() ;
                prstm.close();
								
				JOptionPane.showMessageDialog(null, "Inserted Successfully!");
		}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		}
		
		if(e.getSource()==b3)
		{
			try
				{
					cn1 = DriverManager.getConnection("jdbc:mysql://localhost:3307/JavaProject","root", "1234");
					stm1 = cn1.createStatement();
								
					rs1 = stm1.executeQuery("select count(distinct customer_id) from booking where amount=0");
						 rs1.next();
						int r_cnt1=rs1.getInt(1);

						data1 = new String[r_cnt1][2];

						rs1 = stm1.executeQuery("select distinct customer_id,customer_name from booking where amount=0");
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
	
    public static void main(String args[])
    {
        new admin1();
    }
}