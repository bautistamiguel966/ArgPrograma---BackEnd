
import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

import com.sun.rowset.JdbcRowSetImpl;
/**
 * Funcionalidad para interaccionar con la tabla persona
 *   
 * @author mmirabete
 *
 */
public class PersonaBean {
	static final String JDBC_DRIVER = "org.firebirdsql.jdbc.FBDriver";
	static final String DB_URL = "jdbc:firebirdsql:localhost/3050:C:\\DB\\EXAMENES.FDB";
	static final String DB_USER = "SYSDBA";
	static final String DB_PASS = "masterkey";
	
	private JdbcRowSet rowSet = null;

	/*
	 * Constructor de la clase
	 */
	public PersonaBean() {
		try {
			Class.forName(JDBC_DRIVER);
			rowSet = RowSetProvider.newFactory().createJdbcRowSet();
			rowSet.setUrl(DB_URL);
			rowSet.setUsername(DB_USER);
			rowSet.setPassword(DB_PASS);
			rowSet.setCommand("SELECT * FROM Persona");
			rowSet.execute();

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param persona
	 * @return La instancia de persona creada en la tabla
	 */
	public Persona crear(Persona persona) {
		try {
			rowSet.moveToInsertRow();
			rowSet.updateInt("dni", persona.getDni());
			rowSet.updateString("primerNombre", persona.getPrimerNombre());
			rowSet.updateString("segundoNombre", persona.getSegundoNombre());
			rowSet.updateString("apellido", persona.getApellido());
			rowSet.updateString("email", persona.getEmail());
			rowSet.updateString("telefono", persona.getTelefono());
			rowSet.insertRow(); // Inserta un registro en la tabla
			rowSet.moveToCurrentRow(); // Mueve al registro actual
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
				persona = null;
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return persona;
	}

	/**
	 * 
	 * @param persona
	 * @return La instancia de persona con los datos actualizados en la BD
	 */
	public Persona actualizar(Persona persona) {
		try {
			rowSet.updateString("primerNombre", persona.getPrimerNombre());
			rowSet.updateString("segundoNombre", persona.getSegundoNombre());
			rowSet.updateString("apellido", persona.getApellido());
			rowSet.updateString("email", persona.getEmail());
			rowSet.updateString("telefono", persona.getTelefono());
			rowSet.updateRow(); // Actualiza el registro en la Tabla
			rowSet.moveToCurrentRow(); // Mueve al registro actual
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return persona;
	}

	/**
	 * Elimina el registro actual de la Tabla
	 */
	public void borrar() {
		try {
			rowSet.moveToCurrentRow(); // Mueve al registro actual
			rowSet.deleteRow(); // Elimina el registro de la BD
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
		}

	}

	/**
	 * @return Instancia de personas con los datos del registro
	 */
	public Persona mueveAlPrimero() {
		Persona persona = new Persona();
		try {
			rowSet.first();
			persona.setDni(rowSet.getInt("dni"));
			persona.setPrimerNombre(rowSet.getString("primerNombre"));
			persona.setSegundoNombre(rowSet.getString("segundoNombre"));
			persona.setApellido(rowSet.getString("apellido"));
			persona.setEmail(rowSet.getString("email"));
			persona.setTelefono(rowSet.getString("telefono"));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return persona;
	}

	/**
	 * @return Instancia de personas con los datos del registro
	 */
	public Persona mueveAlUltimo() {
		Persona persona = new Persona();
		try {
			rowSet.last();
			persona.setDni(rowSet.getInt("dni"));
			persona.setPrimerNombre(rowSet.getString("primerNombre"));
			persona.setSegundoNombre(rowSet.getString("segundoNombre"));
			persona.setApellido(rowSet.getString("apellido"));
			persona.setEmail(rowSet.getString("email"));
			persona.setTelefono(rowSet.getString("telefono"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return persona;
	}

	/**
	 * @return Instancia de personas con los datos del registro
	 */
	public Persona mueveAlProximo() {
		Persona persona = new Persona();
		try {
			if (rowSet.next() == false)
				rowSet.previous();
			persona.setDni(rowSet.getInt("dni"));
			persona.setPrimerNombre(rowSet.getString("primerNombre"));
			persona.setSegundoNombre(rowSet.getString("segundoNombre"));
			persona.setApellido(rowSet.getString("apellido"));
			persona.setEmail(rowSet.getString("email"));
			persona.setTelefono(rowSet.getString("telefono"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return persona;
	}

	/**
	 * @return Instancia de personas con los datos del registro
	 */
	public Persona mueveAlAnterior() {
		Persona persona = new Persona();
		try {
			if (rowSet.previous() == false)
				rowSet.next();
			persona.setDni(rowSet.getInt("dni"));
			persona.setPrimerNombre(rowSet.getString("primerNombre"));
			persona.setSegundoNombre(rowSet.getString("segundoNombre"));
			persona.setApellido(rowSet.getString("apellido"));
			persona.setEmail(rowSet.getString("email"));
			persona.setTelefono(rowSet.getString("telefono"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return persona;
	}

	/**
	 * @return Instancia de personas con los datos del registro
	 */
	public Persona getDatosRegistro() {
		Persona persona = new Persona();
		try {
			rowSet.moveToCurrentRow();
			persona.setDni(rowSet.getInt("dni"));
			persona.setPrimerNombre(rowSet.getString("primerNombre"));
			persona.setSegundoNombre(rowSet.getString("segundoNombre"));
			persona.setApellido(rowSet.getString("apellido"));
			persona.setEmail(rowSet.getString("email"));
			persona.setTelefono(rowSet.getString("telefono"));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return persona;
	}
}
