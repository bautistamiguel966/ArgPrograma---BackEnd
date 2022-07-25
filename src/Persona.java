/**
 * POJO que representará una persona en el ámbito del sistema
 * 
 * @author mmirabete
 *
 */
public class Persona {
	private int dni;
	private String primerNombre;
	private String segundoNombre;
	private String apellido;
	private String email;
	private String telefono;

	/**
	 * Constructor de la clase sin campos
	 */
	public Persona() {
		
	}

	/**
	 * Constructor de la clase con campos
	 */
	public Persona(int dni, String primerNombre, String segundoNombre, String apellido, String email, String telefono) {
		super();
		this.dni = dni;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
	}

	/*
	 * Métodos accesores 
	 */
	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
