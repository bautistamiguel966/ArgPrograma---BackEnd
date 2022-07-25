import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.*;

/**
 * Panel que contiene los detalles de persona y los botones de acciones CRUD
 * 
 * @author mmirabete
 *
 */
public class PersonaUI extends JPanel {
	private static final long serialVersionUID = 1L;
	
	// Instancias de cuadros de texto para cada uno de los campos
	private JTextField txtDni = new JTextField(8);
	private JTextField txtPrimerNombre = new JTextField(30);
	private JTextField txtSegundoNombre = new JTextField(30);
	private JTextField txtApellido = new JTextField(30);
	private JTextField txtEmail = new JTextField(30);
	private JTextField txtTelefono = new JTextField(11);

	// Se crean las instancias para los botones
	private JButton btnNuevo = new JButton("Nuevo...");
	private JButton btnActualizar = new JButton("Actualizar");
	private JButton btnBorrar = new JButton("Borrar");
	private JButton btnPrimero = new JButton("Primero");
	private JButton btnAnterior = new JButton("Anterior");
	private JButton btnProximo = new JButton("Proximo");
	private JButton btnUltimo = new JButton("Ultimo");

	private PersonaBean bean = new PersonaBean();

	/**
	 * Constructor del panel
	 */
	public PersonaUI() {
		// Tipo y la separaci�n del borde
		setLayout(new BorderLayout(5, 5));
		// T�tulo del recuadro
		setBorder(new TitledBorder(new EtchedBorder(), "Detalles de persona"));
		// Agrega el panel de campos
		add(iniCampos(), BorderLayout.NORTH);
		// Agrega el panel de botones
		add(iniBotones(), BorderLayout.CENTER);
		setDatos(bean.mueveAlPrimero());
	}

	/**
	 * @return panel que contiene los botones
	 */
	private JPanel iniBotones() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		panel.add(btnNuevo);
		btnNuevo.addActionListener(new ManejadorBotones());
		panel.add(btnActualizar);
		btnActualizar.addActionListener(new ManejadorBotones());
		panel.add(btnBorrar);
		btnBorrar.addActionListener(new ManejadorBotones());
		panel.add(btnPrimero);
		btnPrimero.addActionListener(new ManejadorBotones());
		panel.add(btnAnterior);
		btnAnterior.addActionListener(new ManejadorBotones());
		panel.add(btnProximo);
		btnProximo.addActionListener(new ManejadorBotones());
		panel.add(btnUltimo);
		btnUltimo.addActionListener(new ManejadorBotones());
		return panel;
	}

	/**
	 * @return panel que contiene los campos
	 */
	private JPanel iniCampos() {
		JPanel panel = new JPanel();
		/*
		 * Asigna el layout a utilizar
		 * http://thesuperjez.blogspot.com.ar/2009/05/hablemos-de-java-miglayout
		 * html http://www.miglayout.com/
		 */
		panel.setLayout(new MigLayout());

		// Agurega los campos y sus etiquetas
		panel.add(new JLabel("DNI:"), "align label");
		panel.add(txtDni, "wrap");
		// Hace que el campo sea de s�lo lectura
		txtDni.setEnabled(false);
		// Alinea junto a la etiqueta
		panel.add(new JLabel("Primer nombre:"), "align label");
		panel.add(txtPrimerNombre, "wrap"); // Realiza un salto de línea
		panel.add(new JLabel("Segundo nombre:"), "align label");
		panel.add(txtSegundoNombre, "wrap");
		panel.add(new JLabel("Apellido:"), "align label");
		panel.add(txtApellido, "wrap");
		panel.add(new JLabel("Email:"), "align label");
		panel.add(txtEmail, "wrap");
		panel.add(new JLabel("Teléfono:"), "align label");
		panel.add(txtTelefono, "wrap");
		return panel;
	}

	/**
	 * @return persona con las datos asignados desde los cuadros de texto
	 */
	private Persona getDatos() {
		Persona persona = new Persona();
		persona.setDni(Integer.parseInt(txtDni.getText()));
		persona.setPrimerNombre(txtPrimerNombre.getText());
		persona.setSegundoNombre(txtSegundoNombre.getText());
		persona.setApellido(txtApellido.getText());
		persona.setEmail(txtEmail.getText());
		persona.setTelefono(txtTelefono.getText());
		return persona;
	}

	/**
	 * Carga los datos desde la instancia a los cuadros de texto
	 * 
	 * @param persona
	 */
	private void setDatos(Persona persona) {
		txtDni.setText(String.valueOf(persona.getDni()));
		txtPrimerNombre.setText(persona.getPrimerNombre());
		txtSegundoNombre.setText(persona.getSegundoNombre());
		txtApellido.setText(persona.getApellido());
		txtEmail.setText(persona.getEmail());
		txtTelefono.setText(persona.getTelefono());
	}

	/**
	 * @return {@code True} si los datos est�n vac�os. En caso contrario
	 *         devolver� {@code False}
	 */
	private boolean isDatosVacios() {
		return (txtPrimerNombre.getText().trim().isEmpty() && txtSegundoNombre.getText().trim().isEmpty()
				&& txtApellido.getText().trim().isEmpty() && txtEmail.getText().trim().isEmpty()
				&& txtTelefono.getText().trim().isEmpty());
	}

	/**
	 * Clase para manejar los botones de la ventana
	 * 
	 * @author mmirabete
	 *
	 */
	private class ManejadorBotones implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Persona persona = getDatos();
			switch (e.getActionCommand()) {
			case "Guardar":
				if (isDatosVacios()) {
					JOptionPane.showMessageDialog(null, "No se puede crear un registro esta vac�o.");
					return;
				}
				if (bean.crear(persona) != null)
					JOptionPane.showMessageDialog(null, "Nueva persona creada correctamente.");
				txtDni.setEnabled(false);
				btnActualizar.setEnabled(true);
				btnBorrar.setEnabled(true);
				btnNuevo.setText("Nuevo...");
				break;
			case "Nuevo...":
				txtDni.setEnabled(true);
				btnActualizar.setEnabled(false);
				btnBorrar.setEnabled(false);
				persona.setDni(0);
				persona.setPrimerNombre("");
				persona.setSegundoNombre("");
				persona.setApellido("");
				persona.setEmail("");
				persona.setTelefono("");
				setDatos(persona);
				btnNuevo.setText("Guardar");
				break;
			case "Actualizar":
				if (isDatosVacios()) {
					JOptionPane.showMessageDialog(null, "No se puede actualizar un registro vac�o.");
					return;
				}
				if (bean.actualizar(persona) != null)
					JOptionPane.showMessageDialog(null, "La persona con DNI:"
							+ String.valueOf(persona.getDni() + " se ha actualizado correctamente"));
				break;
			case "Borrar":
				if (isDatosVacios()) {
					JOptionPane.showMessageDialog(null, "No se puede eliminar un registro vac�o");
					return;
				}
				persona = bean.getDatosRegistro();
				bean.borrar();
				JOptionPane.showMessageDialog(null,
						"La persona con DNI:" + String.valueOf(persona.getDni() + " se elimin� correctamente"));
				break;
			case "Primero":
				txtDni.setEnabled(false);
				btnActualizar.setEnabled(true);
				btnBorrar.setEnabled(true);
				btnNuevo.setText("Nuevo...");
				setDatos(bean.mueveAlPrimero());
				break;
			case "Anterior":
				txtDni.setEnabled(false);
				btnActualizar.setEnabled(true);
				btnBorrar.setEnabled(true);
				btnNuevo.setText("Nuevo...");
				setDatos(bean.mueveAlAnterior());
				break;
			case "Pr�ximo":
				txtDni.setEnabled(false);
				btnActualizar.setEnabled(true);
				btnBorrar.setEnabled(true);
				btnNuevo.setText("Nuevo...");
				setDatos(bean.mueveAlProximo());
				break;
			case "Ultimo":
				txtDni.setEnabled(false);
				btnActualizar.setEnabled(true);
				btnBorrar.setEnabled(true);
				btnNuevo.setText("Nuevo...");
				setDatos(bean.mueveAlUltimo());
				break;
			default:
				JOptionPane.showMessageDialog(null, "Comando inv�lido");
			}
		}
	}
}
