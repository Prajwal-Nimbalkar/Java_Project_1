import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

class book extends javax.swing.JFrame implements ActionListener
{
	Panel p1,p2,p3;
	String colHeads[] = {"Customer Id","Customer Name", " Address",
	"Contact","Age","Registration Date"};
	String colHeads1[] = {"Vehicle Id","Vehicle Name", "Vehicle Number",
	"Rate/KM"};
	String data[][],data1[][];
	JLabel book_id = new JLabel("GrandTotal");
	JTextField tbook = new JTextField(20);
    JTable table = new JTable();
    JTable table1 = new JTable();
	int r_cnt=0,r_cnt1=0,i,j;
	
    JLabel book,id,id1,name,age,contact,address;
    JTextField tid,tid1,tname,tage,tcontact,taddress;
    JButton reg,add,back;
	JButton submit,submit1;
	
	
    Connection cn,cn1;
    String sql,sql1,sql2;
	Statement stm,stm1;
	ResultSet rs,rs1;
    PreparedStatement prstm;
	
    book()
    {
        setTitle("Menu Program");
		setLayout(null);
        setSize(2000,2000);
		setVisible(true);
     
		p1 = new Panel();
		p2 = new Panel();
		p3 = new Panel();
		
        book = new JLabel("BOOK A CAR");    
        id = new JLabel("Enter Customer Id:");    
        id1 = new JLabel("Enter Vehicle Id:");    
		name = new JLabel("Customer Name:"); 
        age = new JLabel("Age:");
        contact = new JLabel("Contact:");
        address = new JLabel("Address:");     
		
        tid = new JTextField("");        
		tid1 = new JTextField("");
        tname = new JTextField("");
        tage = new JTextField("");
        tcontact = new JTextField("");
        taddress = new JTextField("");
        
        reg = new JButton("CONTINUE"); 
		add = new JButton("ADD CUSTOMER");
		back = new JButton("BACK");
        submit = new JButton("SUBMIT");
        submit1 = new JButton("SUBMIT");
			
        add(id);  	add(book);	add(tid);
        add(reg);    add(add);
		
		
		
/*Panel 2 work*/		
		try
        {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/JavaProject","root", "1234");
			
			stm = cn.createStatement();
			rs = stm.executeQuery("select count(*) from vehicle where status='available'");
				 rs.next();
				r_cnt=rs.getInt(1);

				data = new String[r_cnt][4];

				rs = stm.executeQuery("select * from vehicle where status='available'");
				i=0;
				while(rs.next())
				{
						data[i][0]= rs.getString(1);                
						data[i][1]= rs.getString(2);                
						data[i][2]= rs.getString(3);
						data[i][3]= rs.getString(5);
						i++;
				}
				
				table = new JTable(data, colHeads1);
				table.setEnabled(false);
				int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				JScrollPane jsp = new JScrollPane(table, v, h);
		
				
				add(p2);
				p2.setLayout(null);
				p2.add(id1);
				p2.add(tid1);		
				p2.add(submit);	
				p2.add(jsp);
								
				jsp.setBounds(0,100,600,180);
				p2.setBounds(300,200,600,300);
				id1.setBounds(40,20,160,20);
				tid1.setBounds(140,20,140,30);		
				submit.setBounds(140,60,120,30);
				p2.setVisible(true);
				p2.setBackground(Color.ORANGE);
				//setVisible(false);
		}
        catch(Exception e)
        { 
			e.printStackTrace();
		}		
/*Panel 2 work*/

/*Panel 3 work*/
		add(p3);
		p3.setLayout(null);
		p3.add(name);
		p3.add(age);	
		p3.add(contact);
		p3.add(address);
			
		p3.add(tname);
		p3.add(tage);	
		p3.add(tcontact);
		p3.add(taddress);	
		
		p3.add(submit1);	
		p3.add(back);	
		
		p3.setBounds(300,200,500,260);
		name.setBounds(40,20,100,20);
		tname.setBounds(160,20,220,30);
        age.setBounds(40,60,150,20);
		tage.setBounds(160,60,220,30);
		contact.setBounds(40,100,150,20);
		tcontact.setBounds(160,100,220,30);
		address.setBounds(40,140,150,20);
		taddress.setBounds(160,140,220,30);
		
		submit1.setBounds(160,180,100,30);
		back.setBounds(280,180,100,30);
		p3.setBackground(Color.ORANGE);
		
/*Panel 3 work*/
		
		
        reg.addActionListener(this);        
		add.addActionListener(this);
		submit.addActionListener(this);
		submit1.addActionListener(this);
		back.addActionListener(this);
		
		book.setBounds(500,10,200,20);
        id.setBounds(400,50,180,20);     
		tid.setBounds(510,50,180,30);		
        reg.setBounds(420,90,120,30);
		add.setBounds(560,90,140,30);
		
      
		
		
        try
        {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/JavaProject","root", "1234");
			
			stm = cn.createStatement();
			rs = stm.executeQuery("select count(*) from customer");
				 rs.next();
				r_cnt=rs.getInt(1);

				data = new String[r_cnt][6];

				rs = stm.executeQuery("select * from customer");
				i=0;
				while(rs.next())
				{
						data[i][0]= rs.getString(1);                
						data[i][1]= rs.getString(2);                
						data[i][2]= rs.getString(3);
						data[i][3]= rs.getString(4);
						data[i][4]= rs.getString(5);
						data[i][5]= rs.getString(6);
						i++;
				}
				
				table = new JTable(data, colHeads);
				table.setEnabled(false);
				int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				JScrollPane jsp = new JScrollPane(table, v, h);
		/*Panel 1 work*/
				
				add(p1);
				p1.setLayout(null);
				p1.add(jsp);
								
				jsp.setBounds(0,0,1250,180);
				p1.setBounds(20,200,1250,200);
				p1.setVisible(true);
				p1.setBackground(Color.ORANGE);
				setVisible(true);
				p2.setVisible(false);
				p3.setVisible(false);
				
		/*Panel 1 work*/
		}
        catch(Exception e)
        { e.printStackTrace();
		}
		
        
		
         addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				new home();
				setVisible(false);
			}
		});
	}
	
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            if(e.getSource() == reg)
            {
				rs = stm.executeQuery("select count(*) from customer where cust_id="+tid.getText());
				 rs.next();
				r_cnt=rs.getInt(1);
				if(r_cnt == 0)
				{
					JOptionPane.showMessageDialog(null,"Customer Id Not Found!");
				}
				else		  
				{
					JOptionPane.showMessageDialog(null,"Customer Id Verified!");
					p1.setVisible(false);
					p3.setVisible(false);
					p2.setVisible(true);
				}
			}
			
            if(e.getSource() == add)
            {
				p1.setVisible(false);
				p2.setVisible(false);
				p3.setVisible(true);
			}
				
			if(e.getSource() ==submit)
            {
				String date1="";
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();
				date1 = dtf.format(now);
		
rs = stm.executeQuery("select count(*) from vehicle where veh_id="+tid1.getText());
				 rs.next();
				int r_cnt=rs.getInt(1);
				if(r_cnt == 0)
				{
					JOptionPane.showMessageDialog(null,"Vehicle Id not found!");
				}
				else
				{
					rs = stm.executeQuery("select * from vehicle where veh_id="+tid1.getText()+"");
					rs.next();
					if(rs.getString(7).equals("booked"))
					{
						JOptionPane.showMessageDialog(null,"Vehicle is already booked!");
					}
					else
					{	
rs = stm.executeQuery("select * from customer where cust_id="+tid.getText());
				 rs.next();
				String n=rs.getString(2);
				
sql = "insert into booking(vehicle_id,customer_id,booking_date,amount,km,return_date,status,bill_id,customer_name)values('" +tid1.getText()+ "','"+tid.getText()+"','"+date1+"',"+0+","+0+",'"+"0000-00-00"+"','"+"booked"+"',"+0+",'"+n+"')";

                prstm = cn.prepareStatement(sql);
                prstm.execute() ;
                prstm.close();
				JOptionPane.showMessageDialog(null, "Vehicle Booked Successfully!!!");
				
				sql1 ="update vehicle set status='booked' where veh_id="+tid1.getText();
				prstm = cn.prepareStatement(sql1);
				prstm.execute();
				
				tid1.setText("");
                p2.setVisible(false);
                p3.setVisible(false);
				p1.setVisible(true);
					}
				}
			}
			if(e.getSource() ==submit1)
            {
				String date1="";
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();
				date1 = dtf.format(now);
sql = "insert into customer(name,address,contact,age,reg_date)values('" +tname.getText()+ "','"+taddress.getText()+"','"+tcontact.getText()+"',"+tage.getText()+",'"+date1+"')";

                prstm = cn.prepareStatement(sql);
                prstm.execute() ;
                prstm.close();
				rs1 = stm.executeQuery
				("select * from customer where name='"+tname.getText()+ "'");
				rs1.next();
				JOptionPane.showMessageDialog(null, "Customer Information Inserted Successfully!!!Registration ID:"+rs1.getInt(1));
				tname.setText("");
				tage.setText("");
				tcontact.setText("");
				taddress.setText("");
                //p2.setVisible(false);
                //p3.setVisible(false);
				//p1.setVisible(true);
			}
			
			if(e.getSource()==back)
			{
				p1.setVisible(true);
				p3.setVisible(false);
				p2.setVisible(false);
			}
		}
		catch (SQLException e1)
		{    System.out.println(e1);
		}
	}
	
    public static void main(String args[])
    {			
		new book();
	}
}