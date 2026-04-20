package dataBase;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import cafe.Bebida;
import cafe.Pasteleria;
import tiendaDeJuegos.JuegoDeMesa;

public class DataBase implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Informe> informes;
	private ArrayList<Factura> facturas;
	private ArrayList<Usuario> usuarios;
	private Turno[] turnos;
	private ArrayList<Solicitud> solicitudes;
	
	public DataBase(ArrayList<Informe> informes, ArrayList<Factura> facturas, ArrayList<Usuario> usuarios, ArrayList<Solicitud> solicitudes) {
		this.informes = informes;
		this.facturas = facturas;
		this.usuarios = usuarios;
		this.turnos = new Turno[7];
		this.solicitudes = solicitudes;
	}
	
	
	public ArrayList<Informe> getInformes() {
		return informes;
	}

	public ArrayList<Factura> getFacturas() {
		return facturas;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public Turno[] getTurnos() {
		return turnos;
	}

	public ArrayList<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setInformes(ArrayList<Informe> informes) {
		this.informes = informes;
	}

	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void setTurnos(Turno[] turnos) {
		this.turnos = turnos;
	}

	public void setSolicitudes(ArrayList<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}
	
	///
	/// 
	/// 
	public Informe generarInforme(LocalDate fechaInicio, LocalDate fechaFin) {
		double totalJuegos = 0;
	    double totalPlatillos = 0;
	    double subTotalJuegos = 0;
	    double subTotalPlatillos = 0;
	    double totalImpuestos = 0;
	    double totalPropinas = 0;
	    double totalCosto = 0;

	    for (Factura f : facturas) {

	        LocalDate fecha = f.getFecha();

	        if ((fecha.isEqual(fechaInicio) || fecha.isAfter(fechaInicio)) &&
	            (fecha.isEqual(fechaFin) || fecha.isBefore(fechaFin))) {

	            totalImpuestos += f.getImpuestos();
	            totalPropinas += f.getPropina();
	            totalCosto += f.getTotal();

	            for (DetalleVenta d : f.getDetallesDeLaVenta()) {

	                double valor = d.getSubtotal() * d.getCantidad();

	                if (d.getProducto() instanceof JuegoDeMesa) {
	                    totalJuegos += valor;
	                    subTotalJuegos += valor;
	                } 
	                else if (d.getProducto() instanceof Pasteleria || d.getProducto() instanceof Bebida) {
	                    totalPlatillos += valor;
	                    subTotalPlatillos += valor;
	                }
	            }
	        }
	    }

	    Informe informe = new Informe(
	        totalJuegos,
	        totalPlatillos,
	        subTotalJuegos,
	        subTotalPlatillos,
	        totalImpuestos,
	        totalPropinas,
	        totalCosto,
	        fechaInicio,
	        fechaFin
	    );

	    informes.add(informe);

	    return informe;
	}
	 
}
