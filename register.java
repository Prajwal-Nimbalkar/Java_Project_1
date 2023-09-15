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

class register extends javax.swing.JFrame implements ActionListener
{
	Panel p1,p2,p3;
	String colHeads[] = {"Owner Id","Owner Name", " Age",
	"Contact","City","Registration Date"};
	String colHeads1[] = {"Owner Id","Owner Name", " Age",
	"Contact","City","Registration Date"};
	String data[][],data1[][];
	JLabel book_id = new JLabel("GrandTotal");
	JTextField tbook = new JTextField(20);
    JTable table = new JTable();
    JTable table1 = new JTable();
	int r_cnt=0,r_cnt1=0,i,j;
	
    JLabel register,name,vname,vnum,oname,age,contact,city,rate,prevkm;
    JTextField tname,tvnum,tvname,trate,tprevkm,toname,tage,tcontact,tcity;
    JButton reg,add,cls,updt,ext;
	JButton submit,submit1;
	
	
    Connection cn,cn1;
    String sql,sql1,sql2;
	Statement stm,stm1;
	ResultSet rs,rs1;
    PreparedStatement prstm;
	
    register()
    {
        setTitle("Menu Program");
		setLayout(null);
        setSize(2000,2000);
		setVisible(true);
     
		p1 = new Panel();
		p2 = new Panel();
		p3 = new Panel();
		
        register = new JLabel("REGISTER A CAR");    
        name = new JLabel("Enter Owner Id:");    
		vname = new JLabel("Vehicle Name:");
        vnum = new JLabel("Vehicle Passing No:");
        oname = new JLabel("Owner Name:");
        age = new JLabel("Age:");
        contact = new JLabel("Contact:");
        city = new JLabel("City:");
        rate = new JLabel("Rate/KM:");        
        prevkm = new JLabel("Previous KM:");        
		
        tname = new JTextField("");        
		tvname = new JTextField("");
        tvnum = new JTextField("");
        toname = new JTextField("");
        tage = new JTextField("");
        tcontact = new JTextField("");
        tcity = new JTextField("");
        trate = new JTextField("");
        tprevkm = new JTextField("");
        
        reg = new JButton("CONTINUE"); 
		add = new JButton("ADD OWNER");
        cls = new JButton("CLEAR");       
		ext = new JButton("EXIT");
        updt = new JButton("UPDT");
        submit = new JButton("SUBMIT");
        submit1 = new JButton("SUBMIT");
			
        add(name);  	add(register);	add(tname);
        add(reg);    add(add);  add(cls);  add(updt);
		
		
		
/*Panel 2 work*/
		
		add(p2);
		p2.setLayout(null);
		p2.add(vname);
		p2.add(vnum);	
		p2.add(rate);	
		p2.add(prevkm);	
		p2.add(tvname);
		p2.add(tvnum);	
		p2.add(trate);	
		p2.add(tprevkm);	
		p2.add(submit);	
		
		p2.setBounds(300,200,500,250);
		vname.setBounds(40,20,100,20);
		tvname.setBounds(160,20,220,30);	
        vnum.setBounds(40,60,150,20);
		tvnum.setBounds(160,60,220,30);
		rate.setBounds(40,100,150,20);
		trate.setBounds(160,100,220,30);
		prevkm.setBounds(40,140,150,20);
		tprevkm.setBounds(160,140,220,30);
		
		submit.setBounds(160,180,120,30);
		p2.setBackground(Color.ORANGE);
		
/*Panel 2 work*/

/*Panel 3 work*/
		
		add(p3);
		p3.setLayout(null);
		p3.add(oname);
		p3.add(age);	
		p3.add(contact);
		p3.add(city);
			
		p3.add(toname);
		p3.add(tage);	
		p3.add(tcontact);
		p3.add(tcity);	
		
		p3.add(submit1);	
		
		p3.setBounds(300,200,500,260);
		oname.setBounds(40,20,100,20);
		toname.setBounds(160,20,220,30);
        age.setBounds(40,60,150,20);
		tage.setBounds(160,60,220,30);
		contact.setBounds(40,100,150,20);
		tcontact.setBounds(160,100,220,30);
		city.setBounds(40,140,150,20);
		tcity.setBounds(160,140,220,30);
		
		submit1.setBounds(160,180,120,30);
		p3.setBackground(Color.ORANGE);
		
/*Panel 3 work*/
		
		
        reg.addActionListener(this);        
		add.addActionListener(this);
		submit.addActionListener(this);
		submit1.addActionListener(this);
		
        cls.addActionListener(this);
        ext.addActionListener(this);
		
		register.setBounds(500,10,200,20);
        name.setBounds(400,50,150,20);     
		tname.setBounds(500,50,180,30);		
        reg.setBounds(420,90,120,30);
		add.setBounds(560,90,120,30);
		
      
		
		
        try
        {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/JavaProject","root", "1234");
			
			stm = cn.createStatement();
			rs = stm.executeQuery("select count(*) from owner");
				 rs.next();
				r_cnt=rs.getInt(1);

				data = new String[r_cnt][6];

				rs = stm.executeQuery("select * from owner");
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
								
				jsp.setBounds(0,0,800,380);
				p1.setBounds(200,200,800,400);
				p1.setVisible(true);
				p1.setBackground(Color.ORANGE);
				
		/*Panel 1 work*/
		}
        catch(Exception e)
        { e.printStackTrace();
		}
		
        setVisible(true);
        p2.setVisible(false);
        p3.setVisible(false);
		
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
				rs = stm.executeQuery("select count(*) from owner where owner_id="+tname.getText());
				 rs.next();
				r_cnt=rs.getInt(1);
				if(r_cnt == 0)
				{
					JOptionPane.showMessageDialog(null,"Owner Id Not Found!");
				}
				else		  
				{
					JOptionPane.showMessageDialog(null,"Owner Id Verified!");
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
			
            if(e.getSource() == cls)
            {
                tname.setText("");
                tvname.setText("");
                tvnum.setText("");
			}
			
            if(e.getSource() ==updt)
            {
                //sql = "update mytable set name='" +tcnt.getText()+"', age="
				//+tage.getText()+" where roll = "+tcnt.getText();
                //prstm = cn.prepareStatement(sql);
                prstm.execute() ;
                /*prstm.close();
                JOptionPane.showMessageDialog(null, "Record Updated Successfully !!!");*/
			}
		
			if(e.getSource() ==submit)
            {
				String date1="";
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();
				date1 = dtf.format(now);
sql = "insert into vehicle(veh_name,veh_no,veh_reg_date,rate_km,prev_km,status,owner_id)values('" +tvname.getText()+ "','"+tvnum.getText()+"','"+date1+"'," +trate.getText()+","+tprevkm.getText()+",'"+"available"+"',"+tname.getText()+")";

                prstm = cn.prepareStatement(sql);
                prstm.execute() ;
                prstm.close();
				JOptionPane.showMessageDialog(null, "Vehicle Information Inserted Successfully!!!");
				tvname.setText("");
				tvnum.setText("");
				trate.setText("");
				tprevkm.setText("");
                p2.setVisible(false);
                p3.setVisible(false);
				p1.setVisible(true);
			}
		
			if(e.getSource() ==submit1)
            {
				String date1="";
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();
				date1 = dtf.format(now);
sql = "insert into owner(own_name,age,contact,city,reg_date)values('" +toname.getText()+ "',"+tage.getText()+",'"+tcontact.getText()+"','" +tcity.getText()+"','"+date1+"')";

                prstm = cn.prepareStatement(sql);
                prstm.execute() ;
                prstm.close();
				rs1 = stm.executeQuery
				("select * from owner where own_name='"+toname.getText()+ "'");
				rs1.next();
				JOptionPane.showMessageDialog(null, "Owner Information Inserted Successfully!!!Registration ID:"+rs1.getInt(1));
				toname.setText("");
				tage.setText("");
				tcontact.setText("");
				tcity.setText("");


/*try
        {
			cn1 = DriverManager.getConnection("jdbc:mysql://localhost:3307/JavaProject","root", "1234");
			
			stm1 = cn1.createStatement();
			rs1 = stm1.executeQuery("select count(*) from owner");
				 rs1.next();
				r_cnt1=rs1.getInt(1);

				data1 = new String[r_cnt1][6];

				rs1 = stm1.executeQuery("select * from owner");
				j=0;
				while(rs1.next())
				{
						data[j][0]= rs1.getString(1);                
						data[j][1]= rs1.getString(2);                
						data[j][2]= rs1.getString(3);
						data[j][3]= rs1.getString(4);
						data[j][4]= rs1.getString(5);
						data[j][5]= rs1.getString(6);
						j++;
				}
				
				table1 = new JTable(data1, colHeads1);
				table1.setEnabled(false);
				int v1 = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				int h1 = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				JScrollPane jsp1 = new JScrollPane(table1, v1, h1);
		
				
				add(p1);
				p1.setLayout(null);
				p1.add(jsp1);
								
				jsp1.setBounds(0,0,600,180);
				p1.setBounds(300,200,600,200);
				p1.setVisible(true);
				p1.setBackground(Color.ORANGE);
				
		
		}
        catch(Exception e1)
        { e1.printStackTrace();
		}*/
                p2.setVisible(false);
                p3.setVisible(false);
				p1.setVisible(true);
			}
			
            if(e.getSource() == ext)
			System.exit(0);
		}
		catch (SQLException e1)
		{    System.out.println(e1);
		}
	}
	
    public static void main(String args[])
    {			
		new register();
	}
}