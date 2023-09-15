import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
class home extends JFrame implements ActionListener
{
	JMenuBar mbr;
	JMenu m1,m2,m3,m4,m5,m6;
	JMenuItem mi1,mi2,mi3,mi4,mi5,mi6,mi7,mi8,mi9;
	JRadioButtonMenuItem mr1,mr2;
	ButtonGroup grp;
	TextArea t1;
	Random r;
	int n[] = new int[50];
	int i,sum,avg,max,min,med,flag;
	String s1,s2,s3;
	int m[] = new int[50];
	
	home()
	{
		setTitle("Home");
		setSize(2000,800);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mbr = new JMenuBar();
		m1 = new JMenu("HOME");
		m2 = new JMenu("REGISTER");
		m3 = new JMenu("BOOK");
		m4 = new JMenu("RECEIPT");
		m5 = new JMenu("REPORT");
		m6 = new JMenu("BILLING");
		
		mi1 = new JMenuItem("REGISTER");
		mi2 = new JMenuItem("BOOK");
		mi3 = new JMenuItem("RECEIPT");
		mi4 = new JMenuItem("REPORT");
		//mi5 = new JMenuItem("MONTHWISE");
		//mi6 = new JMenuItem("YEARWISE");
		mi7 = new JMenuItem("BILLING");
		
		m2.add(mi1);	m3.add(mi2);	m4.add(mi3);	m5.add(mi4);	m6.add(mi7);	//m5.add(mi5);	m5.add(mi6);
		mbr.add(m1);	mbr.add(m2);	mbr.add(m3);		mbr.add(m6);
		mbr.add(m4);	mbr.add(m5);	add(mbr);
		mbr.setBounds(0,0,2000,50);
	
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi4.addActionListener(this);
		//mi5.addActionListener(this);
		//mi6.addActionListener(this);
		mi7.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==mi1)
		{
			new register();
			this.setVisible(false);
		}
		else if(e.getSource()==mi2)
		{
			new book();
			this.setVisible(false);
		}
		else if(e.getSource()==mi3)
		{
			new bill();
			this.setVisible(false);
		}
		else if(e.getSource()==mi4)
		{
			new datewise();
			this.setVisible(false);
		}
		/*else if(e.getSource()==mi5)
		{
				new monthwise();
				this.setVisible(false);
		}
		else if(e.getSource()==mi6)
		{
				new yearwise();
				this.setVisible(false);
		}*/
		else if(e.getSource()==mi7)
		{
				new admin1();
				this.setVisible(false);
		}
	}
	
	public static void main(String args[])
	{
		new home();
	}
}