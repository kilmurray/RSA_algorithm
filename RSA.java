import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Arrays;

public class RSA extends JFrame
{//create buttons and textfield for gui
	private JButton ran_button1,ran_button2,prime_button1,prime_button2,button_n,get_message,encrypt,decrypt;
	private JTextField ran_num1, ran_num2,message,encrypt_message,decrypted_message;
	private JLabel label1,label2,label3;
	private JTextField num1,num2,prime1,prime2,prod_n;
	
	public RSA()
	{
		setLayout(new GridLayout(0,2));//set layout
		event e = new event();//instantiate event for gui
		
		label1 = new JLabel("First random number is ");//text for user
		add(label1);
		
		label2 = new JLabel("Second random number is ");//text for user
		add(label2);
		
		num1 = new JTextField(10);//show prime random number here
		add(num1);
		
		num2 = new JTextField(10);//show prime random number here
		add(num2);

		ran_button1 = new JButton("Generate random number");//click button to show first random prime
		add(ran_button1);
		ran_button1.addActionListener(e);
		
		ran_button2 = new JButton("Generate random number");//click this button to show second random prime
		add(ran_button2);
		ran_button2.addActionListener(e);
		
		prime_button1 = new JButton("Is first number prime");//button to test for prime
		add(prime_button1);
		prime_button1.addActionListener(e);

		prime1 = new JTextField(10);//show result in this field
		add(prime1);

		prime_button2 = new JButton("Is second number prime");//button to test for prime
		add(prime_button2);
		prime_button2.addActionListener(e);

		prime2 = new JTextField(10);//show result in this field
		add(prime2);
		
		button_n = new JButton("N = ");//button to calculate N
		add(button_n);
		button_n.addActionListener(e);
		
		prod_n = new JTextField(10);//show N value here
		add(prod_n);
		
		get_message = new JButton("Enter message");//display enter message text
		add(get_message);
		get_message.addActionListener(e);
		
		message = new JTextField(10);//field for user entered message
		add(message);

		encrypt = new JButton("Encrypted message");//button to encrypt message
		add(encrypt);
		encrypt.addActionListener(e);
		
		encrypt_message = new JTextField(10);//show encrypted message here
		add(encrypt_message);
		
		decrypt = new JButton("Decrypt message");//button to decrypt message
		add(decrypt);
		decrypt.addActionListener(e);
		
		decrypted_message = new JTextField(10);//show decrypted message here
		add(decrypted_message);
	}
	
	public static void main(String[] Args)
	{
		RSA gui = new RSA();//instantiate RSA gui object
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set close on exit
		gui.setLocation(100,200); //set gui position
		gui.setVisible(true);//make viewable
		gui.pack();
		gui.setTitle("RSA");//give name
	}
	
	public class event implements ActionListener
	{
		Algorithm al = new Algorithm();//instantiate algorithm
		BigInteger e = al.generate_E();
		
		long p = al.ran_gen();//generate random numbers
		long q = al.ran_gen();
		long t; //totient
		String show_p,show_q,show_n;
		boolean test;
		long n;//product of randoms
		public byte[] encrypted;

		public void actionPerformed(ActionEvent arg0)
		{
			show_p = Long.toString(p);//change numbers to string for printing
			show_q = Long.toString(q);
			
			if(arg0.getSource() == ran_button1 )//if button1 is pressed show random prime number
			{
				num1.setText(show_p);
			}
			else if(arg0.getSource() == ran_button2 )//if button2 is pressed show random prime number
			{
				num2.setText(show_q);
			}
			else if(arg0.getSource() == prime_button1)//if prime button1 is pressed call test_prime method
			{
				test = al.test_prime(p);
				
				if(test == true)//print result of test_prime for random number 1
				{
					prime1.setText("Yes");
				}
				else
				{
					prime1.setText("No");
				}
			}
			else if(arg0.getSource() == prime_button2)//if prime button2 is pressed call test_prime method
			{
				test = al.test_prime(q);
				
				if(test == true)//print result of test_prime for random number 2
				{
					prime2.setText("Yes");
				}
				else
				{
					prime2.setText("No");
				}
			}
			else if(arg0.getSource() == button_n)//calculate N
			{
				n = al.product(p, q);//nN is product of p and q
				show_n = Long.toString(n);//parse n for printing
				prod_n.setText(show_n);//print n to gui
			}
			else if(arg0.getSource() == encrypt)//if encrypt button push
			{
				String msg = message.getText();//get message to encrypt 
				encrypted = al.encrypt(msg.getBytes(),e,n);//return encrypted message in byte array
				String encrypted_message = al.bytesToString(encrypted);//parse byte array to string
				encrypt_message.setText(encrypted_message);//show encrypted message
			}
			else if(arg0.getSource() == decrypt)
			{
				String hidden = encrypt_message.getText();//commented out as causing crash
				String myHidden = hidden.trim();
				BigInteger d = al.RSA(p, q, e);
				byte[] decrypt = al.decrypt(encrypted,d,n);
				System.out.println(decrypt);
				String hidden_message = al.bytesToString(decrypt);
				decrypted_message.setText(new String(hidden_message));
			}
			
		}
		
		
		
	}
}