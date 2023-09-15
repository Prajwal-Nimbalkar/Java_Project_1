import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.text.*;
import java.util.*;

class datewise extends JFrame implements ActionListener
{
	String colHeads[] = {"Customer Id","Customer Name","KM", "Amount","Booking Date"};
	String data[][];
	JLabel bill = new JLabel("Report");
	JLabel book_id = new JLabel("Enter Date");
	JLabel upto = new JLabel("upto");
	JTextField tbook = new JTextField(20);
	JTextField tupto = new JTextField(20);
	JButton b1 = new JButton("SUBMIT");
	JButton b2 = new JButton("PRINT");
    JTable table = new JTable();
	JLabel book_id1 = new JLabel("GrandTotal");
	JTextField tbook1 = new JTextField(20);

    Connection cn;    Statement stm;
    ResultSet rs,rs1;    int r_cnt=0,i;

    datewise()
    {
        super("Datewise Report");
		setLayout(null);
		setSize(2000,800);    
		setVisible(true);
		
		add(book_id);	add(tbook);	add(b1);	add(bill);	add(table);
		add(book_id1);	add(b2);
		add(tbook1);
		add(upto);
		add(tupto);
		b1.addActionListener(this);
		b2.addActionListener(this);
		bill.setBounds(530,10,200,20);
		book_id.setBounds(340,60,200,20);
		tbook.setBounds(410,50,150,40);
		upto.setBounds(570,60,200,20);
		tupto.setBounds(610,50,150,40);
		b1.setBounds(510,100,150,40);
		b2.setBounds(510,150,150,40);

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
				 rs = stm.executeQuery("select count(*) from booking where booking_date between '"+tbook.getText()+"' and '"+tupto.getText()+"'");
				 rs.next();
				r_cnt=rs.getInt(1);

				data = new String[r_cnt][5];

				rs = stm.executeQuery("select * from booking where booking_date between '"+tbook.getText()+"' and '"+tupto.getText()+"'");
				i=0;
				while(rs.next())
				{
						data[i][0]= rs.getString(3);                
						data[i][1]= rs.getString(10);                
						data[i][2]= rs.getString(6);
						data[i][3]= rs.getString(5);
						data[i][4]= rs.getString(4);
						//data[i][5]= rs.getString(9);
						//data[i][6]= rs.getString(2);
						i++;
				}
				table = new JTable(data, colHeads);
				table.setEnabled(false);
				int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				JScrollPane jsp = new JScrollPane(table, v, h);
				add(jsp);
				jsp.setBounds(200,250,800,320);				
				book_id1.setBounds(810,566,120,20);
				tbook1.setBounds(880,566,120,30);			
				rs1 = stm.executeQuery("select sum(amount) from booking where booking_date between '"+tbook.getText()+"' and '"+tupto.getText()+"'");
				rs1.next();
				tbook1.setText(rs1.getString(1));
			
			}
			if(e.getSource()==b2)
			{
				MessageFormat h1 = new MessageFormat("Datewise Report");
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
        new datewise();
    }
}