import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class JCAL extends JFrame implements ActionListener
{   JLabel l1;    JButton b1;
    DateButton calb;
    JCAL(String t)
    {   super(t);      setSize(190,150);
        setLocation(400,400);
        setLayout(new FlowLayout());
        l1 = new JLabel("Select Date");    add(l1);        
        calb = new DateButton();		   add(calb);

        b1= new JButton("Show Date");
        add(b1);     b1.addActionListener(this);
     	setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    {
        JOptionPane .showMessageDialog(null,calb.getText());
    }    
    public static void main(String args[])
    {
        new JCAL("JCalendar");
    }
}