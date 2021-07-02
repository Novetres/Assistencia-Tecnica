package view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

public class TelaCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCliente;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaCliente dialog = new TelaCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaCliente() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCliente.class.getResource("/icones/favicon.png")));
		setTitle("Cliente");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 800, 643);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		txtCliente = new JTextField();
		txtCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarClientes();
			}
		});
		txtCliente.setBounds(23, 25, 280, 20);
		contentPanel.add(txtCliente);
		txtCliente.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 65, 749, 145);
		contentPanel.add(scrollPane);

		tableCliente = new JTable();
		tableCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		tableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});

		scrollPane.setViewportView(tableCliente);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Sair();
				TelaPrincipal principal = new TelaPrincipal();
				principal.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/exit.png")));
		btnNewButton.setBounds(740, 19, 32, 32);
		contentPanel.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(23, 232, 24, 14);
		contentPanel.add(lblNewLabel);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(67, 229, 86, 20);
		contentPanel.add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setBounds(227, 232, 57, 14);
		contentPanel.add(lblNewLabel_2);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(294, 229, 352, 20);
		contentPanel.add(txtNome);

		JLabel lblNewLabel_1 = new JLabel("CEP");
		lblNewLabel_1.setBounds(175, 274, 46, 14);
		contentPanel.add(lblNewLabel_1);

		txtCEP = new JTextField();
		txtCEP.setBounds(227, 271, 126, 20);
		contentPanel.add(txtCEP);
		txtCEP.setColumns(10);

		JButton btnCEP = new JButton("Buscar CEP");
		btnCEP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCEP.setBounds(388, 270, 115, 23);
		contentPanel.add(btnCEP);

		JLabel lblNewLabel_3 = new JLabel("Logradouro");
		lblNewLabel_3.setBounds(60, 318, 66, 14);
		contentPanel.add(lblNewLabel_3);

		txtLogradouro = new JTextField();
		txtLogradouro.setBounds(140, 315, 163, 20);
		contentPanel.add(txtLogradouro);
		txtLogradouro.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("N\u00BA");
		lblNewLabel_4.setBounds(339, 318, 24, 14);
		contentPanel.add(lblNewLabel_4);

		txtNumero = new JTextField();
		txtNumero.setBounds(373, 315, 46, 20);
		contentPanel.add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Complemento");
		lblNewLabel_5.setBounds(479, 318, 86, 14);
		contentPanel.add(lblNewLabel_5);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(561, 315, 138, 20);
		contentPanel.add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Bairro");
		lblNewLabel_6.setBounds(23, 360, 46, 14);
		contentPanel.add(lblNewLabel_6);

		txtBairro = new JTextField();
		txtBairro.setBounds(78, 357, 143, 20);
		contentPanel.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Cidade");
		lblNewLabel_7.setBounds(270, 360, 46, 14);
		contentPanel.add(lblNewLabel_7);

		txtCidade = new JTextField();
		txtCidade.setBounds(328, 357, 175, 20);
		contentPanel.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("UF");
		lblNewLabel_8.setBounds(571, 360, 24, 14);
		contentPanel.add(lblNewLabel_8);

		JSeparator separator = new JSeparator();
		separator.setBounds(68, 419, 1, 2);
		contentPanel.add(separator);

		lblNewLabel_9 = new JLabel("RG");
		lblNewLabel_9.setBounds(121, 404, 46, 14);
		contentPanel.add(lblNewLabel_9);

		txtRG = new JTextField();
		txtRG.setBounds(158, 401, 126, 20);
		contentPanel.add(txtRG);
		txtRG.setColumns(10);

		lblNewLabel_10 = new JLabel("CPF");
		lblNewLabel_10.setBounds(357, 404, 46, 14);
		contentPanel.add(lblNewLabel_10);

		txtCPF = new JTextField();
		txtCPF.setBounds(398, 401, 163, 20);
		contentPanel.add(txtCPF);
		txtCPF.setColumns(10);

		lblNewLabel_11 = new JLabel("Fone");
		lblNewLabel_11.setBounds(23, 447, 46, 14);
		contentPanel.add(lblNewLabel_11);

		txtFone = new JTextField();
		txtFone.setBounds(78, 444, 126, 20);
		contentPanel.add(txtFone);
		txtFone.setColumns(10);

		lblNewLabel_12 = new JLabel("Fone (2)");
		lblNewLabel_12.setBounds(227, 447, 46, 14);
		contentPanel.add(lblNewLabel_12);

		txtFone2 = new JTextField();
		txtFone2.setBounds(283, 444, 132, 20);
		contentPanel.add(txtFone2);
		txtFone2.setColumns(10);

		lblNewLabel_13 = new JLabel("E-mail");
		lblNewLabel_13.setBounds(448, 447, 46, 14);
		contentPanel.add(lblNewLabel_13);

		lblNewLabel_14 = new JLabel("OBS.");
		lblNewLabel_14.setBounds(54, 496, 46, 14);
		contentPanel.add(lblNewLabel_14);

		txtObs = new JTextField();
		txtObs.setBounds(121, 486, 525, 34);
		contentPanel.add(txtObs);
		txtObs.setColumns(10);

		btnAlterar = new JButton("");
		btnAlterar.setToolTipText("Atualizar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCliente();
			}
		});
		btnAlterar.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/update.png")));
		btnAlterar.setBounds(373, 541, 48, 48);
		contentPanel.add(btnAlterar);

		btnAdicionar = new JButton("");
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirCliente();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/add.png")));
		btnAdicionar.setBounds(91, 541, 48, 48);
		contentPanel.add(btnAdicionar);

		btnNewButton_2 = new JButton("");
		btnNewButton_2.setToolTipText("Deletar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarCliente();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/delete.png")));
		btnNewButton_2.setBounds(651, 541, 48, 48);
		contentPanel.add(btnNewButton_2);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(504, 444, 248, 20);
		contentPanel.add(txtEmail);

		RestrictedTextField nome = new RestrictedTextField(txtNome);
		nome.setLimit(50);

		RestrictedTextField cep = new RestrictedTextField(txtCEP, "0123456789");
		cep.setOnlyNums(true);
		cep.setLimit(9);

		RestrictedTextField logradouro = new RestrictedTextField(txtLogradouro);
		logradouro.setLimit(100);

		RestrictedTextField numero = new RestrictedTextField(txtNumero, "0123456789");
		numero.setOnlyNums(true);
		numero.setLimit(50);

		RestrictedTextField complemento = new RestrictedTextField(txtComplemento);
		complemento.setLimit(20);

		RestrictedTextField bairro = new RestrictedTextField(txtBairro);
		bairro.setLimit(50);
		
		RestrictedTextField rg = new RestrictedTextField(txtRG);
		rg.setLimit(15);
		
		RestrictedTextField cpf = new RestrictedTextField(txtCPF);
		cpf.setOnlyNums(true);
		cpf.setLimit(15);

		RestrictedTextField fone = new RestrictedTextField(txtFone);
		fone.setOnlyNums(true);
		fone.setLimit(15);

		RestrictedTextField fone2 = new RestrictedTextField(txtFone2);
		fone2.setOnlyNums(true);
		fone2.setLimit(15);

		RestrictedTextField email = new RestrictedTextField(txtEmail);
		email.setLimit(100);

		RestrictedTextField observacao = new RestrictedTextField(txtObs);
		observacao.setLimit(250);

		cboUF = new JComboBox();
		cboUF.setEditable(true);
		cboUF.setModel(new DefaultComboBoxModel(new String[] { "  --", " AC ", " AL ", " AM", " AP ", " BA ", " CE ",
				" DF", " ES ", " GO ", " MA", " MG ", " MS", " MT  ", " PA", " PB", " PE ", " PI", " PR   ", " RJ",
				" RN", " RO", " RR", " RS  ", " SC  ", " SE", " SP ", " TO " }));
		cboUF.setBounds(625, 356, 57, 22);
		contentPanel.add(cboUF);

		btnBuscaar = new JButton("");
	
		btnBuscaar.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/search2.png")));
		btnBuscaar.setBounds(313, 19, 32, 32);
		contentPanel.add(btnBuscaar);
		observacao.setLimit(250);
		// tableCliente.setBounds(23, 65, 749, 145);
	} // Final Fantasy

	DAO dao = new DAO();
	private JTable tableCliente;
	private JTextField txtID;
	private JTextField txtNome;
	private JTextField txtCEP;
	private JTextField txtLogradouro;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JLabel lblNewLabel_9;
	private JTextField txtRG;
	private JLabel lblNewLabel_10;
	private JTextField txtCPF;
	private JLabel lblNewLabel_11;
	private JTextField txtFone;
	private JLabel lblNewLabel_12;
	private JTextField txtFone2;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JTextField txtObs;
	private JButton btnAlterar;
	private JButton btnAdicionar;
	private JButton btnNewButton_2;
	private JTextField txtEmail;
	private JComboBox cboUF;
	private JButton btnBuscaar;

	// metodo para pesquisa avancada de clientes
	private void pesquisarClientes() {
		String pesquisar = "select * from tbclientes where nomecli like ?";
		try {

			Connection con = dao.conectar();

			PreparedStatement pst = con.prepareStatement(pesquisar);

			pst.setString(1, txtCliente.getText() + "%");
			// atencao ao % na passagem do parametro
			ResultSet rs = pst.executeQuery();

			tableCliente.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void setarCampos() {

		// a variavel abaixo obtem o conteúdo da linha da coluna
		int setar = tableCliente.getSelectedRow();
		// System.out.println(setar); apoio ao ente

		txtID.setText(tableCliente.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(tableCliente.getModel().getValueAt(setar, 1).toString());
		txtCEP.setText(tableCliente.getModel().getValueAt(setar, 2).toString());
		txtLogradouro.setText(tableCliente.getModel().getValueAt(setar, 3).toString());
		txtNumero.setText(tableCliente.getModel().getValueAt(setar, 4).toString());
		txtComplemento.setText(tableCliente.getModel().getValueAt(setar, 5).toString());
		txtBairro.setText(tableCliente.getModel().getValueAt(setar, 6).toString());
		txtCidade.setText(tableCliente.getModel().getValueAt(setar, 7).toString());
		cboUF.setSelectedItem(tableCliente.getModel().getValueAt(setar, 8).toString());
		txtRG.setText(tableCliente.getModel().getValueAt(setar, 9).toString());
		txtCPF.setText(tableCliente.getModel().getValueAt(setar, 10).toString());
		txtFone.setText(tableCliente.getModel().getValueAt(setar, 11).toString());
		txtFone2.setText(tableCliente.getModel().getValueAt(setar, 12).toString());
		txtEmail.setText(tableCliente.getModel().getValueAt(setar, 13).toString());
		txtObs.setText(tableCliente.getModel().getValueAt(setar, 14).toString());
		
		txtRG.setEditable(false);
		txtCPF.setEditable(false);
	  btnAdicionar.setEnabled(false);
	}



	private void buscarCep() {
		// as variaveis abaixo foram usadas para unir tipo_logradouro com logradouro
		String logradouro = "";
		String tipoLogradouro = "";
		// variavel de apoio para verificar se o CEP existe
		String resultado = null;

		try {
			String cep = txtCEP.getText();
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> i = root.elementIterator(); i.hasNext();) {
				Element elemento = (Element) i.next();
				if (elemento.getQualifiedName().equals("resultado")) {
					resultado = elemento.getText();
					// a estrutura abaixo verificar se o cep existe
				}
				if (resultado.equals("0")) {
					JOptionPane.showMessageDialog(null, "CEP não encontrado");
				}
				if (elemento.getQualifiedName().equals("uf")) {
					cboUF.setSelectedItem(elemento.getText());
				}
				if (elemento.getQualifiedName().equals("cidade")) {
					txtCidade.setText(elemento.getText());
				}
				if (elemento.getQualifiedName().equals("bairro")) {
					txtBairro.setText(elemento.getText());
				}
				if (elemento.getQualifiedName().equals("logradouro")) {
					logradouro = elemento.getText();
				}
				if (elemento.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = elemento.getText();
				}
			}
			txtLogradouro.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
		}

	}

	private void Sair() {
		this.dispose();
	}

	private void inserirCliente() {
// validacao dos campos obrigatorios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a campo Nome");
// retorno o cursor ao campo
			txtNome.requestFocus();

		} else if

		(txtCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo CEP");
// retorno o cursor ao campo
			txtCEP.requestFocus();

		} else if (txtLogradouro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Logradouro");
			txtLogradouro.requestFocus();

		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Numero");
			txtNumero.requestFocus();

		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Bairro");
			txtBairro.requestFocus();

		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cidade");
			txtCidade.requestFocus();

		} else if (cboUF.getSelectedItem() == "--") {
			JOptionPane.showMessageDialog(null, "Preencha o campo UF");
			cboUF.requestFocus();

		} else if (txtCPF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo CPF");
			txtCPF.requestFocus();

		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Telefone");
			txtFone.requestFocus();

		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo E-mail");
			txtEmail.requestFocus();

		} else {
// validacao bem sucedida - inserir dados no banco
// Query
			String insertCliente = "insert into tbclientes(nomecli,cep,logradouro,numero,complemento,bairro,cidade,uf,rg,cpf,fonecli,fonecli2,emailcli,obs) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//
			try {
				Connection con = dao.conectar();

				PreparedStatement pst = con.prepareStatement(insertCliente);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCEP.getText());
				pst.setString(3, txtLogradouro.getText());
				pst.setString(4, txtNumero.getText());
				pst.setString(5, txtComplemento.getText());
				pst.setString(6, txtBairro.getText());
				pst.setString(7, txtCidade.getText());
				pst.setString(8, cboUF.getSelectedItem().toString());
				pst.setString(9, txtRG.getText());
				pst.setString(10, txtCPF.getText());
				pst.setString(11, txtFone.getText());
				pst.setString(12, txtFone2.getText());
				pst.setString(13, txtEmail.getText());
				pst.setString(14, txtObs.getText());


				int sucesso = pst.executeUpdate();

				if (sucesso == 1) {
					JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
				}

				con.close();
				clean();

			} catch 	
			
			(SQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null, "RG ou CPF duplicado");{
					
				}
			} catch (Exception e) {
				System.out.println(e);
					
		
	}}}

	private void alterarCliente() {
		
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a campo Nome");

			txtNome.requestFocus();

		} else if

		(txtCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo CEP");

			txtCEP.requestFocus();

		} else if (txtLogradouro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Logradouro");
			txtLogradouro.requestFocus();

		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Numero");
			txtNumero.requestFocus();

		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Bairro");
			txtBairro.requestFocus();

		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cidade");
			txtCidade.requestFocus();

		} else if (cboUF.getSelectedItem() == "--") {
			JOptionPane.showMessageDialog(null, "Preencha o campo UF");
			cboUF.requestFocus();

		} else if (txtCPF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo CPF");
			txtCPF.requestFocus();

		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Telefone");
			txtFone.requestFocus();

		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo E-mail");
			txtEmail.requestFocus();

		} else {
// validacao bem sucedida - inserir dados no banco
// Query
		String alterar = "update tbclientes set nomecli=?,cep=?,logradouro=?,numero=?,complemento=?,bairro=?,cidade=?,uf=?,fonecli=?,fonecli2=?,emailcli=?,obs=? where idcli=?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(alterar);
			
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtCEP.getText());
			pst.setString(3, txtLogradouro.getText());
			pst.setString(4, txtNumero.getText());
			pst.setString(5, txtComplemento.getText());
			pst.setString(6, txtBairro.getText());
			pst.setString(7, txtCidade.getText());
			pst.setString(8, cboUF.getSelectedItem().toString());
			pst.setString(9, txtFone.getText());
			pst.setString(10, txtFone2.getText());
			pst.setString(11, txtEmail.getText());
			pst.setString(12, txtObs.getText());
			pst.setString(13, txtID.getText());
			pst.executeUpdate();

			JOptionPane.showMessageDialog(null, "Cliente atualizado");

			con.close();
			clean();
		} catch (Exception e) {
			System.out.println(e);
		}}
	}

	private void deletarCliente() {
			String delete = "delete from tbclientes where idcli=?";
			int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste cliente?", "Atenção!",
					JOptionPane.YES_NO_OPTION);
			if (confirma == JOptionPane.YES_OPTION) {
				try {
					Connection con = dao.conectar();
					PreparedStatement pst = con.prepareStatement(delete);
					pst.setString(1, txtID.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
					
					con.close();
					clean();
				} catch (Exception e) {
					System.out.println(e);
				
			}}}

	private void clean() {
			
			txtNome.setText(null);
			txtCEP.setText(null);
			txtLogradouro.setText(null);
			txtNumero.setText(null);
			txtComplemento.setText(null);
			txtBairro.setText(null);
               txtCidade.setText(null);
				cboUF.setSelectedItem(null);
				txtRG.setText(null);
				txtCPF.setText(null);
				txtFone.setText(null);
				txtFone2.setText(null);
				txtEmail.setText(null);
				txtObs.setText(null);
				txtCliente.setText(null);
				((DefaultTableModel) tableCliente.getModel()).setRowCount(0);
				txtID.setText(null);
				
		}

}
