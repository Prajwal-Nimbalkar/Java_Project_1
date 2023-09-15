import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;
import java.text.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;

class admin extends JFrame implements ActionListener,KeyListener,ItemListener
{
	String colHeads[] = {"Customer Id","Cutomer Name"};
	String data[][];
	JLabel l1 = new JLabel("Billing");
	JLabel book_id = new JLabel("Enter Customer ID");
	JLabel km = new JLabel("Enter KM");
	JLabel book_veh = new JLabel("Booked Vehicles");
	
	JTextField tbook = new JTextField(20);
	JTextField tkm = new JTextField(20);
	JButton b1 = new JButton("CALCULATE");
	JButton b2 = new JButton("SUBMIT");
	JComboBox ch1 = new JComboBox();
    JTable table = new JTable();
	Date date = new Date();
	
    Connection cn;    Statement stm;
    ResultSet rs,rs1,rs2,rs3,rs4,rs5;    int r_cnt=0,i;
	PreparedStatement prstm;
	String sql,sql1,sql2,sql3,sql4,sql5;

    admin()
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
		add(book_veh);
		add(ch1);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		tbook.addKeyListener(this);
		l1.setBounds(780,10,150,40);
		book_id.setBounds(600,50,150,40);	
		tbook.setBounds(710,50,200,40);
		book_veh.setBounds(600,100,150,40);	
		ch1.setBounds(710,100,200,40);
		
		km.setBounds(600,150,150,40);	
		tkm.setBounds(710,150,200,40);
		
		b1.setBounds(710,200,200,40);
		b2.setBounds(710,250,200,40);

        try
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
					try
					{
						rs5 = stm.executeQuery("select * from booking where customer_id="+tbook.getText()+" and status='"+"booked"+"'");
						while(rs5.next())
						{
							ch1.addItem(rs5.getString(2));
						}
						System.out.println("hello");
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
			}
		});
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
				tkm.setText("");
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
				("select sum(amount) from booking where customer_id="+tbook.getText()+"");
				rs2.next();

sql1 = "insert into bill(cust_id,total_amt,status)values("+tbook.getText()+ ","+rs2.getString(1)+",'"+"unpaid"+"')";
                prstm = cn.prepareStatement(sql1);
                prstm.execute() ;
                prstm.close();
			
				rs3 = stm.executeQuery
				("select * from bill where cust_id='"+tbook.getText()+ "'");
				rs3.next();
				
/*sql2 ="update booking set bill_id="+rs3.getString(1)+" where customer_id="+tbook.getText()+" and total_amt="+rs2.getInt(3)+"";
                prstm = cn.prepareStatement(sql2);
                prstm.execute() ;
                prstm.close();*/
								
				JOptionPane.showMessageDialog(null, "Inserted Successfully!");
		}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		}
	}
	
    public static void main(String args[])
    {
        new admin();
    }
}