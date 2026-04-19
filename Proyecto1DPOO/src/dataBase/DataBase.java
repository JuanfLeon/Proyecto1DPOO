package dataBase;

import java.io.Serializable;
import java.util.ArrayList;

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
	
	
}