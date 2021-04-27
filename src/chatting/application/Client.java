package chatting.application;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client extends JFrame implements ActionListener {
    
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    Client()
    {
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,350,55);
        add(p1);
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(8,15,25,25);
        p1.add(l1);
        
        l1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae)
            {
                System.exit(0);
            }
        });
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/2.png"));
        Image i5 = i4.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(38,10,40,40);
        p1.add(l2);
        
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 35, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l3 = new JLabel(i9);
        l3.setBounds(230,12,35,35);
        p1.add(l3);
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel l4 = new JLabel(i12);
        l4.setBounds(270,15,30,30);
        p1.add(l4);
        
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel l5 = new JLabel(i15);
        l5.setBounds(300,8,40,40);
        p1.add(l5);
        
        JLabel l6 = new JLabel("Bunty");
        l6.setFont(new Font("SEN_SERIF", Font.BOLD, 14));
        l6.setForeground(Color.white);
        l6.setBounds(38+50,8,70,30);
        p1.add(l6);
        
        
        JLabel l7 = new JLabel("Active Now");
        l7.setFont(new Font("SEN_SERIF", Font.PLAIN, 12));
        l7.setForeground(Color.white);
        l7.setBounds(38+50,25,70,30);
        p1.add(l7);
        
        a1 = new JTextArea();
        a1.setBounds(5, 60 , 340, 400);
        a1.setFont(new Font("SEN_SERIF",Font.PLAIN,14));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);
        
        t1 = new JTextField();
        t1.setBounds(5,465,253,30);
        t1.setFont(new Font("SEN_SERIF",Font.PLAIN,14));
        add(t1);
        
        b1 = new JButton("Send");
        b1.setBounds(262,465,85,30);
        b1.setBackground(new Color(7,94,84));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SEN_SERIF",Font.PLAIN,14));
        b1.addActionListener(this);
        add(b1);
        
        
//        getContentPane().setBackground(Color.PINK);
        setLayout(null);
        setSize(350,500);
        setLocation(600,100);
        setUndecorated(true);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        try{
            String out = t1.getText();
            a1.setText(a1.getText()+"\n\t\t\t"+out);
            t1.setText("");
            
            dout.writeUTF(out);
        }catch(Exception e){}
    }
    public static void main(String[] args)
    {
        new Client().setVisible(true);
        String msgInput;
        try{
            s = new Socket("127.0.0.1", 6001);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            
            while(true)
            {
                msgInput = din.readUTF();
                a1.setText(a1.getText() + "\n" + msgInput); 
            }
        }catch(Exception e)
        {
            
        }
    }
    
}
