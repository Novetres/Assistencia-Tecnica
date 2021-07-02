package view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			
			
			{
				try {
					
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
				status();
				
			}
		});
		setTitle("TECCOM - Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLogin.class.getResource("/icones/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setBounds(59, 72, 46, 14);
		contentPane.add(lblNewLabel);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(137, 69, 176, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(59, 117, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(137, 114, 176, 20);
		contentPane.add(txtSenha);

		JButton btnlogin = new JButton("Login");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
				
				
			}
		});
		btnlogin.setBounds(183, 169, 89, 23);
		contentPane.add(btnlogin);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/icones/connecting.png")));
		lblStatus.setBounds(381, 218, 32, 32);
		contentPane.add(lblStatus);
	}
	// THE END

	DAO dao = new DAO();
	private JLabel lblStatus;
	

	/**
	 * Status da conexão
	 */
	private void status() {
		try {

			Connection con = dao.conectar();
			

			if (con != null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/on.png")));
				lblStatus.setToolTipText("ONLINE");
				

			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/off.png")));
				lblStatus.setToolTipText("OFFLINE");
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@SuppressWarnings("deprecation")
	private void logar() {
		if (txtUsuario.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Usuario");
			// retorno o cursor ao campo
			txtUsuario.requestFocus();

		} else if (txtSenha.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Senha");
			txtSenha.requestFocus();

		} else {

			String read = "select * from tbusuarios where login = ? and senha = md5(?)";
			try {
				Connection con = dao.conectar();

				PreparedStatement pst = con.prepareStatement(read);

				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtSenha.getText());

				ResultSet rs = pst.executeQuery();

				if (rs.next()) {
					// tratamento do perfil de usuario
					String perfil = rs.getString(5);
					// apoio ao entendimento da logica
					// System.out.println(perfil);

					if (perfil.equals("admin")) {
						TelaPrincipal principal = new TelaPrincipal();
						principal.btnRelatorios.setEnabled(true);
						principal.btnUsuarios.setEnabled(true);
						principal.lblUsuario.setText(rs.getString(2));
						principal.rodape.setBackground(SystemColor.controlShadow);
						principal.setLocationRelativeTo(null);
						principal.setVisible(true);
						this.dispose();
						con.close();
					} else {
						TelaPrincipal principal = new TelaPrincipal();
						principal.setVisible(true);
						principal.lblUsuario.setText(rs.getString(2));
						principal.rodape.setBackground(SystemColor.activeCaption);
						principal.setLocationRelativeTo(null);
						this.dispose();
						con.close();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
					txtUsuario.setText(null);
					txtSenha.setText(null);
					txtUsuario.requestFocus();
				}

			} catch (Exception e) {

				System.out.println(e);
			}

		}
	}
}
