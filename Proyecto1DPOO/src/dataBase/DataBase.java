package dataBase;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

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
	
	private Turno[] crearTurnos() {
		return new Turno[7];
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
	
	
}