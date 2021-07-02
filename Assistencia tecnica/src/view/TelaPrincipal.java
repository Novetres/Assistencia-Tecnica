package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JButton btnSobre;
	public JButton btnRelatorios;
	private JButton btnOrdemServico;
	public JButton btnUsuarios;
	private JButton btnClientes;
	private JButton btnTecnicos;

	public JPanel rodape;
	public JLabel lblUsuario;
	private JLabel lblNewLabel_1;
	private JLabel lblDataLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				mostrarData();
			}
		});
		setTitle("Principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/icones/favicon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 489, 350);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnOrdemServico = new JButton("");
		btnOrdemServico.setBackground(SystemColor.control);
		btnOrdemServico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOrdemServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnOrdemServico.setToolTipText("Ordem de Servi\u00E7o");
		btnOrdemServico.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/OS.png")));
		btnOrdemServico.setBounds(76, 40, 64, 64);
		contentPane.add(btnOrdemServico);

		btnUsuarios = new JButton("");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaUsuario usuario = new TelaUsuario();
				usuario.setLocationRelativeTo(null);
				usuario.setVisible(true);

			}
		});
		btnUsuarios.setEnabled(false);
		btnUsuarios.setBackground(SystemColor.control);
		btnUsuarios.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/usuario.png")));
		btnUsuarios.setToolTipText("Usu\u00E1rios");
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setBounds(188, 91, 64, 64);
		contentPane.add(btnUsuarios);

		btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCliente cliente = new TelaCliente();
				cliente.setVisible(true);
				cliente.setLocationRelativeTo(null);
			}
		});
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/cliente.png")));
		btnClientes.setBackground(SystemColor.control);
		btnClientes.setToolTipText("Clientes");
		btnClientes.setBounds(316, 40, 64, 64);
		contentPane.add(btnClientes);

		btnRelatorios = new JButton("");
		btnRelatorios.setEnabled(false);
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRelatorios.setBackground(SystemColor.control);
		btnRelatorios.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/Relatorio.png")));
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setToolTipText("Relat\u00F3rios");
		btnRelatorios.setBounds(76, 157, 64, 64);
		contentPane.add(btnRelatorios);

		btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaSobre sobre = new TelaSobre();
				sobre.setLocationRelativeTo(null);
				sobre.setVisible(true);

			}
		});
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/Sobre.png")));
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setToolTipText("Sobre");
		btnSobre.setBounds(10, 229, 32, 32);
		contentPane.add(btnSobre);

		btnTecnicos = new JButton("");
		btnTecnicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaTecnico tecnico = new TelaTecnico();
			    tecnico.setVisible(true);
			    Sair();
			}
		});
		btnTecnicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTecnicos.setBackground(SystemColor.control);
		btnTecnicos.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/tecnico.png")));
		btnTecnicos.setToolTipText("T\u00E9cnicos");
		btnTecnicos.setBounds(316, 157, 64, 64);
		contentPane.add(btnTecnicos);

		rodape = new JPanel();
		rodape.setBackground(SystemColor.inactiveCaption);
		rodape.setBounds(0, 268, 473, 43);
		contentPane.add(rodape);
		rodape.setLayout(null);

		lblDataLabel = new JLabel("");
		lblDataLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataLabel.setBounds(240, 11, 223, 14);
		rodape.add(lblDataLabel);

		JLabel lblNewLabel = new JLabel("TECCOM");
		lblNewLabel.setBounds(28, 11, 89, 23);
		rodape.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblNewLabel_1 = new JLabel("Bem vindo(a)");
		lblNewLabel_1.setBounds(316, 247, 88, 14);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblUsuario = new JLabel("");
		lblUsuario.setBounds(400, 247, 97, 14);
		contentPane.add(lblUsuario);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));

	}

	private void mostrarData() // Necessário criar metodo
	{

		Date datalabel = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		lblDataLabel.setText(formatador.format(datalabel));
	}

	private void Sair() {

		this.dispose();

	}
}
