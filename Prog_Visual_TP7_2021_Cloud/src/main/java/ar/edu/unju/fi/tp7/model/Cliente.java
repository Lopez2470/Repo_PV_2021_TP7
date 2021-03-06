package ar.edu.unju.fi.tp7.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "CLIENTES")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cli_tipoDocumento", nullable = false, length = 10)
	private String tipoDocumento;
	
	@Column(name = "cli_nroDocumento", nullable = false)
	private int nroDocumento;
	
	@Column(name = "cli_apellidoNombre", nullable = false, length = 150)
	private String apellidoNombre;
	
	@Column(name = "cli_email", nullable = false, length = 100)
	private String email;
	
	@Column(name = "cli_password", nullable = false, length = 100)
	private String password;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "cli_fechaNacimiento", nullable = false)
	private LocalDate fechaNacimiento;
	
	@Column(name = "cli_edad", nullable = false)
	private int edad;
	
	@Column(name = "cli_codigoAreaTelefono", nullable = false)
	private int codigoAreaTelefono;
	
	@Column(name = "cli_nroTelefono", nullable = false)
	private int nroTelefono;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "cli_fechaUltimaCompra", nullable = false)
	private LocalDate fechaUltimaCompra;
	
	@Autowired
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cue_id")
	private Cuenta cuenta;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(String tipoDocumento, int nroDocumento, String apellidoNombre, String email, String password,
			LocalDate fechaNacimiento, int edad, int codigoAreaTelefono, int nroTelefono, LocalDate fechaUltimaCompra,
			Cuenta cuenta) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.apellidoNombre = apellidoNombre;
		this.email = email;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.codigoAreaTelefono = codigoAreaTelefono;
		this.nroTelefono = nroTelefono;
		this.fechaUltimaCompra = fechaUltimaCompra;
		this.cuenta = cuenta;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getApellidoNombre() {
		return apellidoNombre;
	}

	public void setApellidoNombre(String apellidoNombre) {
		this.apellidoNombre = apellidoNombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getEdad() {
		this.edad = calcularEdad();
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getCodigoAreaTelefono() {
		return codigoAreaTelefono;
	}

	public void setCodigoAreaTelefono(int codigoAreaTelefono) {
		this.codigoAreaTelefono = codigoAreaTelefono;
	}

	public int getNroTelefono() {
		return nroTelefono;
	}

	public void setNroTelefono(int nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	public LocalDate getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}
	
	
	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public int calcularEdad() {
		LocalDate hoy = LocalDate.now();
		Period periodo = Period.between(this.fechaNacimiento, hoy);
		int edadd = periodo.getYears();
		return edadd;	
	}	
	
	
	public String tDesdeUltimaCompra() {
		LocalDate hoy = LocalDate.now();
		Period periodo = Period.between(this.fechaUltimaCompra, hoy);
		//String ultimaCompra = periodo.getYears() + "-" + periodo.getMonths() + 
		//		"-" + periodo.getDays();
		String ultimaCompra = periodo.getYears() + " A??os - " + periodo.getMonths() + " Meses - " + 
							  periodo.getDays() + " Dias";
		return ultimaCompra;
	}	
	
	public long tDesdeFechaNacimiento() {
		LocalDateTime hoy = LocalDateTime.now();
        LocalDateTime fechaNacLDT = LocalDateTime.of(this.fechaNacimiento, LocalTime.of(0,0,0));
      
        Duration duration = Duration.between(fechaNacLDT,hoy);
        long dias = duration.toDaysPart();
		return dias;	
	}
	
	public String tHastaProxCumple() {
		
		LocalDateTime fechaNacLDT = LocalDateTime.of(this.fechaNacimiento, LocalTime.of(0,0,0));
		LocalDateTime hoyLDT = LocalDateTime.now();
		LocalDateTime proximoCumpleLDT = fechaNacLDT.withYear(hoyLDT.getYear());
		
		if (proximoCumpleLDT.isBefore(hoyLDT) || proximoCumpleLDT.isEqual(hoyLDT)) {
        	proximoCumpleLDT = proximoCumpleLDT.plusYears(1);
		}
		
        Duration duracion = Duration.between(hoyLDT,proximoCumpleLDT);
        int horas = duracion.toHoursPart();
        int minutos = duracion.toMinutesPart();
        int segundos = duracion.toSecondsPart();
        
        LocalDate hoy = LocalDate.now();
        LocalDate proximoCumple = proximoCumpleLDT.toLocalDate();
        Period periodo = Period.between(hoy,proximoCumple);
        int anio = periodo.getYears();
        int meses = periodo.getMonths();
        int dias = periodo.getDays();
        
        String tiempoProximoCumple = meses + " meses - " + dias  + " dias - " + horas  + " horas - " 
        							+ minutos + " minutos - " + segundos + " segundos";
        if ((meses == 0 ) && (dias == 0)){
        	tiempoProximoCumple = "Feliz Cumplea??os!!!";
        }
        return tiempoProximoCumple;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", tipoDocumento=" + tipoDocumento + ", nroDocumento=" + nroDocumento
				+ ", apellidoNombre=" + apellidoNombre + ", email=" + email + ", password=" + password
				+ ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad + ", codigoAreaTelefono="
				+ codigoAreaTelefono + ", nroTelefono=" + nroTelefono + ", fechaUltimaCompra=" + fechaUltimaCompra
				+ ", cuenta=" + cuenta + "]";
	}	
}