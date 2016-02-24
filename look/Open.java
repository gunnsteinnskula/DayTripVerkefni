package look;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Open {

	private JFrame frame;
	private JTextField txtEnterTrip;
	private String trip;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Open window = new Open();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Open() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		trip = null;
		
		txtEnterTrip = new JTextField();
		txtEnterTrip.setText("Enter trip");
		frame.getContentPane().add(txtEnterTrip, BorderLayout.CENTER);
		txtEnterTrip.setColumns(1);
		
		JLabel lblSearchForTrip = new JLabel("Search for trip");
		frame.getContentPane().add(lblSearchForTrip, BorderLayout.NORTH);
		
		JButton btnSearch = new JButton("Search");
		frame.getContentPane().add(btnSearch, BorderLayout.EAST);
		
		btnSearch.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				trip = txtEnterTrip.getText().toString();
				System.out.println(trip);
			}
		});
	}

}
