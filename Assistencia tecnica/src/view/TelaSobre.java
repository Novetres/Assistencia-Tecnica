package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TelaSobre extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaSobre dialog = new TelaSobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaSobre() {
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaSobre.class.getResource("/icones/favicon.png")));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(120, 130, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Assist\u00EAncia t\u00E9cnica de computadores TECCOM");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel.setBounds(20, 11, 290, 21);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon(TelaSobre.class.getResource("/icones/Certificado.png")));
			lblNewLabel_1.setBounds(302, 58, 64, 64);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Sob licen\u00E7a GLP");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_2.setBounds(291, 133, 98, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("New label");
			lblNewLabel_3.setIcon(new ImageIcon(TelaSobre.class.getResource("/icones/instragram.png")));
			lblNewLabel_3.setBounds(35, 212, 32, 32);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("New label");
			lblNewLabel_4.setIcon(new ImageIcon(TelaSobre.class.getResource("/icones/github.png")));
			lblNewLabel_4.setBounds(98, 212, 32, 32);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("New label");
			lblNewLabel_5.setIcon(new ImageIcon(TelaSobre.class.getResource("/icones/linkedin.png")));
			lblNewLabel_5.setBounds(155, 212, 32, 32);
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("New label");
			lblNewLabel_6.setIcon(new ImageIcon(TelaSobre.class.getResource("/icones/facebook.png")));
			lblNewLabel_6.setBounds(221, 212, 32, 32);
			contentPanel.add(lblNewLabel_6);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Nos siga nas redes");
			lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_7.setBounds(81, 187, 136, 14);
			contentPanel.add(lblNewLabel_7);
		}
		{
			JLabel lblNewLabel_8 = new JLabel("Paloma Kimberly");
			lblNewLabel_8.setBounds(51, 83, 136, 14);
			contentPanel.add(lblNewLabel_8);
		}
		{
			JLabel lblNewLabel_9 = new JLabel("Jonathan Santos");
			lblNewLabel_9.setBounds(51, 108, 106, 14);
			contentPanel.add(lblNewLabel_9);
		}
		{
			JLabel lblNewLabel_10 = new JLabel("Administradores");
			lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel_10.setBounds(48, 58, 109, 14);
			contentPanel.add(lblNewLabel_10);
		}
		{
			JLabel lblNewLabel_11 = new JLabel("Vers\u00E3o 1.0     Jun/2021");
			lblNewLabel_11.setFont(new Font("Tahoma", Font.ITALIC, 12));
			lblNewLabel_11.setBounds(288, 236, 136, 14);
			contentPanel.add(lblNewLabel_11);
		}
		
		btnSair = new JButton("");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal principal = new TelaPrincipal();
				// principal.setLocationRelativeTo(null);
				Sair();
				principal.setVisible(true);
			}
		});
		btnSair.setIcon(new ImageIcon(TelaSobre.class.getResource("/icones/exit.png")));
		btnSair.setBounds(428, 11, 32, 32);
		contentPanel.add(btnSair);
	}
	private void Sair() {

		this.dispose();
}}
