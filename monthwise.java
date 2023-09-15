import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.text.*;
import java.util.*;

class monthwise extends JFrame implements ActionListener,ItemListener
{
	String colHeads[] = {"Booking Id","Car Id","Cutomer Name", "Date","Contact","City","Amount"};
	String data[][];
	JLabel bill = new JLabel("Monthwise Report");
	JLabel book_id = new JLabel("Select Month");
	JLabel book_id1 = new JLabel("Select Year");
	JComboBox tbook = new JComboBox();
	JComboBox tbook1 = new JComboBox();
	JButton b1 = new JButton("SUBMIT");
	JButton b2 = new JButton("PRINT");
    JTable table = new JTable();
	JLabel book_id2 = new JLabel("GrandTotal");
	JTextField tbook2 = new JTextField(20);

    Connection cn;    Statement stm;
    ResultSet rs;    int r_cnt=0,i;

    monthwise()
    {
        super("monthwise Report");
		setLayout(null);
		setSize(2000,800);    
		setVisible(true);
		
		add(book_id);	add(tbook);	add(b1);	add(bill);	add(table);
		add(book_id1);	add(book_id2);	add(b2);
		add(tbook1);	add(tbook2);
		b1.addActionListener(this);
		b2.addActionListener(this);
		bill.setBounds(530,10,200,20);
		book_id.setBounds(400,110,200,20);
		book_id1.setBounds(400,60,200,20);
		tbook.setBounds(510,50,150,40);
		tbook1.setBounds(510,100,150,40);
		b1.setBounds(510,150,150,40);
		b2.setBounds(510,200,150,40);
		
		tbook1.addItem("-Select-");
		tbook1.addItem("January");
		tbook1.addItem("February");
		tbook1.addItem("March");
		tbook1.addItem("April");
		tbook1.addItem("May");
		tbook1.addItem("June");
		tbook1.addItem("July");
		tbook1.addItem("August");
		tbook1.addItem("September");
		tbook1.addItem("October");
		tbook1.addItem("November");
		tbook1.addItem("December");

        try
        {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/javadata","root", "1234");
            stm = cn.createStatement();
			
			rs = stm.executeQuery("select distinct year from book");
			while(rs.next())
			{
				tbook.addItem(rs.getString(1));
			}
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
	public void itemStateChanged(ItemEvent e)
	{
		//tbook.getSelectedItem();
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if(e.getSource()==b1)
			{
				 int str =tbook1.getSelectedIndex();
				 rs = stm.executeQuery("select count(*) from book where year='"+tbook.getSelectedItem()+"'and month="+str+"");
				 rs.next();
				r_cnt=rs.getInt(1);

				data = new String[r_cnt][7];

				rs = stm.executeQuery("select * from book where year='"+tbook.getSelectedItem()+"'and month="+str+"");
				i=0;
				while(rs.next())
				{
						data[i][0]= rs.getString(1);                
						data[i][1]= rs.getString(2);                
						data[i][2]= rs.getString(3);
						data[i][3]= rs.getString(4);
						data[i][4]= rs.getString(8);
						data[i][5]= rs.getString(9);
						data[i][6]= rs.getString(10);
						i++;
				}
				table = new JTable(data, colHeads);
				table.setEnabled(false);
				int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				JScrollPane jsp = new JScrollPane(table, v, h);
				add(jsp);
				jsp.setBounds(200,250,800,320);
				
				
				book_id2.setBounds(810,566,120,20);
				tbook2.setBounds(880,566,120,30);				
				rs = stm.executeQuery("select sum(amount) from book where year='"+tbook.getSelectedItem()+"'and month="+str+"");
				rs.next();
				tbook2.setText(rs.getString(1));
			}
			
			if(e.getSource()==b2)
			{
				MessageFormat h1 = new MessageFormat("Monthwise Report");
				MessageFormat f1 = new MessageFormat("");
				try
				{
					table.print(JTable.PrintMode.FIT_WIDTH,h1,f1);
				}
				catch(Exception e1)
				{
					System.out.println(e1);
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
        new monthwise();
    }
}