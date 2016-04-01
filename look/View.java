package look;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.List;
import javax.swing.JScrollPane;

public class View {

	private JFrame frmTripsInIceland;
	private JTextField txtEnterTrip;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frmTripsInIceland.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTripsInIceland = new JFrame();
		frmTripsInIceland.setTitle("Trips in Iceland");
		frmTripsInIceland.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmTripsInIceland.getContentPane().setForeground(Color.BLACK);
		frmTripsInIceland.setBounds(100, 100, 450, 300);
		frmTripsInIceland.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTripsInIceland.getContentPane().setLayout(null);
		
		txtEnterTrip = new JTextField();
		txtEnterTrip.setText("Enter Trip...");
		txtEnterTrip.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		txtEnterTrip.setBounds(6, 53, 300, 82);
		frmTripsInIceland.getContentPane().add(txtEnterTrip);
		txtEnterTrip.setColumns(1);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Monaco", Font.BOLD, 14));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			//setja inn hérna tenginu við leitarorð í gagnagrunni, þegar ytt er á takk gerist þetta
			public void actionPerformed(ActionEvent ars0) {
				// a eða args0 þarna uppi
				DefaultListModel DLM=new DefaultListModel();
				DLM.addElement("Hestaferðir");
				DLM.addElement("Gönguferðir");
				DLM.addElement("Hestaferðir");
				DLM.addElement("Hestaferðir");
				
				
			}
		});
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setBounds(318, 77, 117, 36);
		frmTripsInIceland.getContentPane().add(btnNewButton);
		
		table = new JTable();
		table.setBounds(352, 97, 1, 1);
		frmTripsInIceland.getContentPane().add(table);
		
		List list = new List();
		
		
		list.setBounds(91, 158, 138, 82);
		frmTripsInIceland.getContentPane().add(list);
	}
}
