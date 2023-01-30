package ejercicio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EjercicioPractico1 extends JFrame {

	private static final long serialVersionUID = -4972756141485488898L;
	private JPanel contentPane;
	private JTextField txtFld_CiudadOrigen;
	private JRadioButton rdbtnNacional_Origen;
	private JRadioButton rdbtnExtranjero_Origen;
	private JTextField txtFld_CiudadDestino;
	private JLabel lblCiudadDestino;
	private final ButtonGroup btnGrp_Origen = new ButtonGroup();
	private JPanel panel;
	private JRadioButton rdbtnNacional_Destino;
	private JRadioButton rdbtnExtranjero_Destino;
	private JLabel lblTipoDeEnvo;
	private JComboBox<String> cmbBox_TipoEnvio;
	private JLabel lblPeso;
	private JSpinner spinner_Peso;
	private JLabel lblKg;
	private JButton btnCalcularPrecio;
	private static int Recargos[] = {5,2,0}; 
	private static double envioNacional = 4;
	private static double envioExtranjero = 7;
	private static double recargoPeso = 3.5;
	private final ButtonGroup btnGrp_Destino = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EjercicioPractico1 frame = new EjercicioPractico1();
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
	public EjercicioPractico1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[30.00][][grow][grow][][]", "[][][][][][][][][]"));
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, "cell 0 0 6 1,grow");
		panel.setLayout(new MigLayout("", "[40.00,grow]", "[]"));
		
		JLabel lblCalculadoraDeEnvos = new JLabel("Calculadora de Envíos");
		lblCalculadoraDeEnvos.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblCalculadoraDeEnvos, "cell 0 0,alignx center,growy");
		lblCalculadoraDeEnvos.setForeground(Color.BLACK);
		lblCalculadoraDeEnvos.setBackground(Color.DARK_GRAY);
		
		JLabel lblCiudadOrigen = new JLabel("Ciudad Origen:");
		contentPane.add(lblCiudadOrigen, "cell 1 1,alignx left");
		
		txtFld_CiudadOrigen = new JTextField();
		contentPane.add(txtFld_CiudadOrigen, "cell 2 1 2 1,growx");
		txtFld_CiudadOrigen.setColumns(10);
		
		rdbtnNacional_Origen = new JRadioButton("Nacional");
		btnGrp_Origen.add(rdbtnNacional_Origen);
		contentPane.add(rdbtnNacional_Origen, "cell 2 2");
		
		rdbtnExtranjero_Origen = new JRadioButton("Extranjero");
		btnGrp_Origen.add(rdbtnExtranjero_Origen);
		contentPane.add(rdbtnExtranjero_Origen, "cell 3 2");
		
		lblCiudadDestino = new JLabel("Ciudad Destino:");
		contentPane.add(lblCiudadDestino, "cell 1 3,alignx left");
		
		txtFld_CiudadDestino = new JTextField();
		txtFld_CiudadDestino.setColumns(10);
		contentPane.add(txtFld_CiudadDestino, "cell 2 3 2 1,growx");
		
		rdbtnNacional_Destino = new JRadioButton("Nacional");
		btnGrp_Destino.add(rdbtnNacional_Destino);
		contentPane.add(rdbtnNacional_Destino, "cell 2 4");
		
		rdbtnExtranjero_Destino = new JRadioButton("Extranjero");
		btnGrp_Destino.add(rdbtnExtranjero_Destino);
		contentPane.add(rdbtnExtranjero_Destino, "cell 3 4");
		
		lblTipoDeEnvo = new JLabel("Tipo de Envío:");
		contentPane.add(lblTipoDeEnvo, "cell 1 5,alignx left");
		
		cmbBox_TipoEnvio = new JComboBox<String>();
		cmbBox_TipoEnvio.setModel(new DefaultComboBoxModel<String>(new String[] {"Paq 10 - Antes de las 10 h", "Paq 14 - Antes de las 14 h", "Paq 24 - Al día siguiente"}));
		cmbBox_TipoEnvio.setSelectedIndex(0);
		contentPane.add(cmbBox_TipoEnvio, "cell 2 5 2 1,growx");
		
		lblPeso = new JLabel("Peso:");
		contentPane.add(lblPeso, "cell 1 6,alignx left,aligny center");
		
		spinner_Peso = new JSpinner();
		spinner_Peso.setModel(new SpinnerNumberModel(1, 1, 40, 1));
		contentPane.add(spinner_Peso, "flowx,cell 2 6,growx");
		
		lblKg = new JLabel("Kg");
		contentPane.add(lblKg, "cell 2 6");
		
		btnCalcularPrecio = new JButton("Calcular Precio");
		btnCalcularPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validaDatos();
			}
		});
		contentPane.add(btnCalcularPrecio, "cell 0 8 6 1,alignx center");
	}

	protected void validaDatos() {
		String ciudadOrigen = txtFld_CiudadOrigen.getText();
		String ciudadDestino = txtFld_CiudadDestino.getText();
		if(ciudadOrigen == null || ciudadOrigen.isBlank() || ciudadDestino == null || ciudadDestino.isBlank()) {
			JOptionPane.showMessageDialog(this, "Error, uno de los campos 'Ciudad Origen' o 'Ciudad Destino'\n está vacío. Rellénelos antes de continuar.", "Error - Faltan datos.",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		calculaEnvio();
	}

	private void calculaEnvio() {
		String ciudadOrigen = txtFld_CiudadOrigen.getText();
		String ciudadDestino = txtFld_CiudadDestino.getText();
		Integer tipoEnvio= cmbBox_TipoEnvio.getSelectedIndex();
		Integer peso = (Integer) spinner_Peso.getValue();
		double totalPeso = 0;
		double precio = 0;
		if(rdbtnNacional_Origen.isSelected() && rdbtnNacional_Destino.isSelected()) {
			precio=precio+envioNacional;
		}else {
			precio=precio+envioExtranjero;
		}
		precio=precio+Recargos[tipoEnvio];
		if(peso>=10) {
			totalPeso=(peso/10)*recargoPeso;
			precio=precio+totalPeso;
		}
		JOptionPane.showMessageDialog(this, "Origen: "+ciudadOrigen+"\nDestino: "+ciudadDestino+"\nTipo: "+cmbBox_TipoEnvio.getSelectedItem()+"\nPeso: "+peso+" Kg\nImporte: "+precio+" €","Cálculo",JOptionPane.INFORMATION_MESSAGE);
	}

}
