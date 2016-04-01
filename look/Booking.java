package look;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class Booking extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmailAddress;
	private JTextField txtCountry;
	private JTextField txtAge;
	private JTextField txtGroupSize;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Booking frame = new Booking();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Booking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setText("Name...");
		txtName.setBounds(26, 74, 193, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtEmailAddress = new JTextField();
		txtEmailAddress.setText("Email address...");
		txtEmailAddress.setBounds(26, 112, 195, 26);
		contentPane.add(txtEmailAddress);
		txtEmailAddress.setColumns(10);
		
		txtCountry = new JTextField();
		txtCountry.setText("Country...");
		txtCountry.setBounds(26, 152, 196, 26);
		contentPane.add(txtCountry);
		txtCountry.setColumns(10);
		
		JButton btnNewButton = new JButton("Bóka");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(165, 227, 132, 29);
		contentPane.add(btnNewButton);
		
		txtAge = new JTextField();
		txtAge.setText("Age...");
		txtAge.setBounds(297, 91, 99, 26);
		contentPane.add(txtAge);
		txtAge.setColumns(10);
		
		txtGroupSize = new JTextField();
		txtGroupSize.setText("Group size...");
		txtGroupSize.setBounds(297, 129, 99, 26);
		contentPane.add(txtGroupSize);
		txtGroupSize.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("    Please fill in the information down here for booking");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel.setBounds(6, 16, 422, 16);
		contentPane.add(lblNewLabel);
	}
	
	public String getInputEmail() {
		return txtEmailAddress.getText().toString();
	}
	
	public String getInputName() {
		return txtName.getText().toString();
	}
	
	public String getInputCountry() {
		return txtCountry.getText().toString();
	}
	
	public int getInputAge() {
		return Integer.parseInt(txtAge.getText().toString());
	}
	
	public int getInputGroupSize() {
		return Integer.parseInt(txtGroupSize.getText().toString());
	}
	
	//public void show() {
		//this.setVisible(true);
	//}
}
