package ogloszenia;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OknoOgloszen {

	private JFrame frame;
	private JTextField textField;
	private List<Ogloszenie> ogloszenia;
	private JLabel lblStatus;
	private boolean czekamy = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OknoOgloszen window = new OknoOgloszen();
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
	public OknoOgloszen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblPodajMarkI = new JLabel("Podaj markę i model auta");
		lblPodajMarkI.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField.setColumns(10);
		
		JButton btnPobierz = new JButton("Pobierz");
		btnPobierz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pobierz();
			}

		});
		btnPobierz.setFont(new Font("Dialog", Font.BOLD, 16));
		
		lblStatus = new JLabel("Status");
		
		JButton btnZapiszDoPliku = new JButton("Zapisz do pliku");
		btnZapiszDoPliku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zapisz();
			}
		});
		btnZapiszDoPliku.setFont(new Font("Dialog", Font.BOLD, 16));

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
						.addComponent(lblPodajMarkI)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnPobierz)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
						.addComponent(btnZapiszDoPliku))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPodajMarkI)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnPobierz, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(btnZapiszDoPliku)
					.addContainerGap(107, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	private void zapisz() {
		if(czekamy) {
			JOptionPane.showMessageDialog(frame, "Operacja w trakcie, musisz poczekać", "Czekamy...", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JFileChooser fileChooser = new JFileChooser(".");
		fileChooser.setDialogTitle("Zapisz wyniki do pliku...");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new FileNameExtensionFilter("CSV", "csv", "CSV"));
		int coSieStalo = fileChooser.showSaveDialog(frame);
		if(coSieStalo == JFileChooser.APPROVE_OPTION) {
			File plikWynikowy = fileChooser.getSelectedFile();
			
			ObslugaCSV.zapiszCSV(ogloszenia, plikWynikowy);
			lblStatus.setText("Zapisano ogłoszeń " + ogloszenia.size());
		}
		
	}

	private void pobierz() {
		if(czekamy) {
			return;
		}
		
		final String szukam = textField.getText();
		czekamy = true;
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			// To sie wykona w innym wątku
			protected Void doInBackground() throws Exception {
				Pobieracz pobieracz = null;
				try {
					pobieracz = new Pobieracz();
				
					pobieracz.wyszukaj(szukam);
				} finally {
					pobieracz.close();
				}
				ogloszenia = pobieracz.getOgloszenia();
				return null;
			}
			
			protected void done() {
				// To zostanie wykonane w wątku EDT (czyli przez okienko)
				lblStatus.setText("Znaleziono ogłoszeń " + ogloszenia.size());
				czekamy = false;
			}
		};
		
		worker.execute();
	}

}
