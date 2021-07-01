package view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.DAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaUsuario extends JDialog {
	private JTextField txtID;
	private JPasswordField txtPassword;
	private JTextField txtLogin;
	private JTextField txtUsuario;
	private JButton btnAdicionar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuario dialog = new TelaUsuario();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public TelaUsuario() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setTitle("Usu\u00E1rios");
		setBounds(120, 130, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(39, 26, 25, 14);
		getContentPane().add(lblNewLabel);

		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setBounds(116, 23, 99, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setBounds(39, 56, 46, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(39, 91, 46, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(39, 127, 46, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Perfil");
		lblNewLabel_4.setBounds(39, 162, 46, 14);
		getContentPane().add(lblNewLabel_4);

		cboPerfil = new JComboBox();
		cboPerfil.setEditable(true);
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] { " -- Selecione --", "  admin", "  user" }));
		cboPerfil.setBounds(116, 158, 122, 22);
		getContentPane().add(cboPerfil);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(116, 124, 169, 20);
		getContentPane().add(txtPassword);

		txtLogin = new JTextField();
		txtLogin.setBounds(116, 88, 141, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(116, 54, 122, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirUsuario();
			}
		});
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/add.png")));
		btnAdicionar.setBounds(37, 207, 48, 48);
		getContentPane().add(btnAdicionar);

		btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();
				btnAdicionar.setEnabled(true);
				btnAlterar.setEnabled(true);
				btnExcluir.setEnabled(true);

			}
		});
		btnPesquisar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/search.png")));
		btnPesquisar.setBounds(154, 207, 48, 48);
		getContentPane().add(btnPesquisar);

		btnAlterar = new JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
			}
		});
		btnAlterar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/update.png")));
		btnAlterar.setBounds(262, 207, 48, 48);
		getContentPane().add(btnAlterar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarUsuario();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/delete.png")));
		btnExcluir.setBounds(363, 207, 48, 48);
		getContentPane().add(btnExcluir);

		JButton btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();

			}
		});
		btnLimpar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/limpar.png")));
		btnLimpar.setBounds(363, 96, 48, 48);
		getContentPane().add(btnLimpar);

		btnSair = new JButton("");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal principal = new TelaPrincipal();
				// principal.setLocationRelativeTo(null);
				Sair();
				principal.setVisible(true);
			}
		});
		btnSair.setToolTipText("Exit");
		btnSair.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/exit.png")));
		btnSair.setBounds(428, 11, 32, 32);
		getContentPane().add(btnSair);

	} // The End

	DAO dao = new DAO();
	private JComboBox cboPerfil;
	private JButton btnPesquisar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;

	private void pesquisarUsuario() {
		// instrução sql para pesquisar um contato pelo nome
		String pesquisar = "select * from tbusuarios where usuario = ?";
		try {

			Connection con = dao.conectar();

			PreparedStatement pst = con.prepareStatement(pesquisar);

			pst.setString(1, txtUsuario.getText());

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				txtID.setText(rs.getString(1));
				txtLogin.setText(rs.getString(3));
				cboPerfil.setSelectedItem(rs.getString(5));
				txtPassword.setEnabled(false);
				// btnAlterar.setEnabled(true);
				// btnExcluir.setEnabled(true);
				// btnPesquisar.setEnabled(false);
				cboPerfil.setEnabled(false);

			} else {
				JOptionPane.showMessageDialog(null, "Usuario inexistente");
				btnAdicionar.setEnabled(true);
				btnPesquisar.setEnabled(false);
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	private void limpar() {
		txtID.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		cboPerfil.setSelectedItem(null);
		btnAdicionar.setEnabled(false);
		txtPassword.setText(null);
		btnExcluir.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnPesquisar.setEnabled(true);

	}

	private void inserirUsuario() {
		// validação dos campos
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo usuario obrigatório");
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo login obrigatório");
		} else if (txtPassword.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo senha obrigatório");
		} else if (cboPerfil.getSelectedItem() == "Selecione") {
			JOptionPane.showMessageDialog(null, "Campo pefil obrigatório");
		} else {
			String create = "insert into tbusuarios (usuario, login, senha, perfil) values (?,?,md5(?),?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtPassword.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Usuario adicionado");
				con.close();
				limpar();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void alterarUsuario() {
		String alterar = "update tbusuarios set usuario=?,login=?,senha=md5(?),perfil=? where iduser=?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(alterar);
			pst.setString(1, txtUsuario.getText());
			pst.setString(2, txtLogin.getText());
			pst.setString(3, txtPassword.getText());
			pst.setString(4, cboPerfil.getSelectedItem().toString());
			pst.setString(5, txtID.getText());
			pst.executeUpdate();

			JOptionPane.showMessageDialog(null, "Usuario atualizado");

			con.close();
			limpar();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void deletarUsuario() {
		String delete = "delete from tbusuarios where usuario=?";
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste usuario?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtUsuario.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso");
				limpar();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void Sair() {

		this.dispose();
	}
}