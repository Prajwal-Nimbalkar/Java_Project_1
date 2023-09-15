import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class frm13 extends Frame implements ActionListener
{
	int i;
	Panel p1;
	Button b1,b2;
	TextField t1;
	Button btn[];
	
	frm13()
	{
		setTitle("My First AWT Frame");
		setSize(500,500);
		setLocation(350,250);
		setLayout(null);
		
		p1 = new Panel();
		b1 = new Button("Hide");
		b2 = new Button("Show");
		t1 = new TextField("");
		
		btn = new Button[3];		// Array of ref.
		
		/*for(i=0;i<3;i++)
		{
			btn[i] = new Button(""+(i+1));	// Array of Obj
			p1.add(btn[i]);
			btn[i].addActionListener(this);
		}*/
		p1.add(t1);
		add(p1);
		add(b1);
		add(b2);
		//add(t1);

		p1.setLayout(null);
		p1.setBackground(Color.YELLOW);
		p1.setFont(new Font("Arial",Font.BOLD,20));
		
		p1.setBounds(100,100,200,200);
		b1.setBounds(100,310,200,20);
		b2.setBounds(100,340,200,20);
		t1.setBounds(100,370,200,20);

		b1.addActionListener(this);
		b2.addActionListener(this);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			p1.setVisible(false);
		}
		else if(e.getSource()==b2)
		{
			p1.setVisible(true);
		}
		else
		{
			System.out.println(e.getActionCommand());
		}
	}
		
	public static void main(String args[])
	{
		new frm13();
	}
}