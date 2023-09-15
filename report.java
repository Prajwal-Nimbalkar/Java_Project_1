import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class report extends JFrame
{
	String colHeads[] = {"Booking Id","Cutomer Name", "Date","Contact","City","Kilometer","Amount"};
	String data[][];
	JLabel book_id = new JLabel("GrandTotal");
	JTextField tbook = new JTextField(20);
    JTable table = new JTable();

    Connection cn;    Statement stm;
    ResultSet rs,rs1;    int r_cnt=0,i;

    report()
    {
        super("Report");
		setLayout(null);
		setSize(2000,800);    
		setVisible(true);
		
		add(book_id);	
		add(tbook);

        try
        {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/javadata","root", "1234");
            stm = cn.createStatement();
			
			rs = stm.executeQuery("select count(*) from booking where status='booked'");
				 rs.next();
				r_cnt=rs.getInt(1);

				data = new String[r_cnt][7];

				rs = stm.executeQuery("select * from booking where status='booked' ");
				while(rs.next())
				{
						data[i][0]= rs.getString(1);                
						data[i][1]= rs.getString(2);                
						data[i][2]= rs.getString(3);
						data[i][3]= rs.getString(4);
						data[i][4]= rs.getString(5);
						data[i][5]= rs.getString(6);
						data[i][6]= rs.getString(7);
						i++;
				}
				
				table = new JTable(data, colHeads);
				table.setEnabled(false);
				int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				JScrollPane jsp = new JScrollPane(table, v, h);
				add(jsp);
				jsp.setBounds(0,0,1270,150);
				tbook.setBounds(1075,150,185,30);
				book_id.setBounds(1000,150,185,20);
				rs = stm.executeQuery("select sum(amount) from booking where status='booked'");
				rs.next();
				tbook.setText(rs.getString(1));
        }
        catch (Exception e) 
		{            
			e.printStackTrace();        
		}

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[])
    {
        new report();
    }
}