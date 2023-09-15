import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.text.*;
import java.util.*;

class yearwise extends JFrame implements ActionListener,ItemListener
{
	String colHeads[] = {"Booking Id","Car Id","Cutomer Name", "Date","Contact","City","Amount"};
	String data[][];
	JLabel bill = new JLabel("Yearwise Report");
	JLabel book_id = new JLabel("Select Year");
	JComboBox tbook = new JComboBox();
	JButton b1 = new JButton("SUBMIT");
	JButton b2 = new JButton("PRINT");
    JTable table = new JTable();
	JLabel book_id1 = new JLabel("GrandTotal");
	JTextField tbook1 = new JTextField(20);

    Connection cn;    Statement stm;
    ResultSet rs;    int r_cnt=0,i;

    yearwise()
    {
        super("yearwise Report");
		setLayout(null);
		setSize(2000,800);    
		setVisible(true);
		
		add(book_id);	add(tbook);	add(b1);	add(bill);	add(table);
		add(book_id1);	add(b2);
		add(tbook1);
		b1.addActionListener(this);
		b2.addActionListener(this);
		bill.setBounds(530,10,200,20);
		book_id.setBounds(400,60,200,20);
		tbook.setBounds(510,50,150,40);
		b1.setBounds(510,100,150,40);
		b2.setBounds(510,150,150,40);

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
	public void itemStateChanged(ItemEvent e){}
	
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if(e.getSource()==b1)
			{
				 rs = stm.executeQuery("select count(*) from book where year='"+tbook.getSelectedItem()+"'");
				 rs.next();
				r_cnt=rs.getInt(1);

				data = new String[r_cnt][7];

				rs = stm.executeQuery("select * from book where year='"+tbook.getSelectedItem()+"'");
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
				jsp.setBounds(200,200,800,200);
				
				book_id1.setBounds(800,400,120,20);
				tbook1.setBounds(880,400,120,30);				
				rs = stm.executeQuery("select sum(amount) from book where year='"+tbook.getSelectedItem()+"'");
				rs.next();
				tbook1.setText(rs.getString(1));
			}
			
			if(e.getSource()==b2)
			{
				MessageFormat h1 = new MessageFormat("Yearwise Report");
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
        new yearwise();
    }
}