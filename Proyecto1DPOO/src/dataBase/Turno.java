package dataBase;

import java.util.ArrayList;

public class Turno {

	private DiaSemana dia_turno;
	private ArrayList<Mesero> meseros;
	private ArrayList<Cocinero> cocineros;
	
	
	public Turno(DiaSemana dia, ArrayList<Mesero> meseros, ArrayList<Cocinero> cocineros) throws Exception{
		
		if (dia == null) {
			throw new Exception("DiaNulo");
		}
		
		if (meseros == null || meseros.size() < 2) {
			throw new Exception("MeserosInsuficientes");
		}
		
		if (cocineros == null || cocineros.size() < 1) {
			throw new Exception("CocinerosInsuficientes");
		}	
		
		
		dia_turno = dia;
		this.meseros = meseros;
		this.cocineros = cocineros;
	}
	
	public void agregarMesero(Mesero mesero) throws Exception {
		if (!meseros.contains(mesero)) {
			meseros.add(mesero);
			mesero.agregarTurno(this);
		}
		else {
			throw new Exception("MeseroYaPerteneceAlTurno");
		}
	}
	
	public void agregarCocinero(Cocinero cocinero) throws Exception {
		if (!cocineros.contains(cocinero)) {
			cocineros.add(cocinero);
			cocinero.agregarTurno(this);
		}
		else {
			throw new Exception("CocineroYaPerteneceAlTurno");
		}
	}
	
	public void eliminarMesero(Mesero mesero) throws Exception{
		
		if (meseros.size() <= 2) {
			throw new Exception("MeserosInsuficientes");
		}
		else if (meseros.contains(mesero)) {
            meseros.remove(mesero);
            mesero.eliminarTurno(this);
        }
	}
	
	public void eliminarCocinero(Cocinero cocinero) throws Exception{
		
		if (cocineros.size() <= 1) {
			throw new Exception("CocinerosInsuficientes");
		}
		else if (cocineros.contains(cocinero)) {
            cocineros.remove(cocinero);
            cocinero.eliminarTurno(this);
        }
	}
	
	 public void agregarEmpleado(Empleado e) throws Exception {
	        if (e instanceof Mesero) {
	            agregarMesero((Mesero) e);
	        } else if (e instanceof Cocinero) {
	            agregarCocinero((Cocinero) e);
	        }
	    }

	    public void eliminarEmpleado(Empleado e) throws Exception {
	        if (e instanceof Mesero) {
	            eliminarMesero((Mesero) e);
	        } else if (e instanceof Cocinero) {
	            eliminarCocinero((Cocinero) e);
	        }
	    }

	public DiaSemana getDia_turno() {
		return dia_turno;
	}

	public ArrayList<Mesero> getMeseros() {
		return meseros;
	}

	public ArrayList<Cocinero> getCocineros() {
		return cocineros;
	}

	public void setDia_turno(DiaSemana dia_turno) {
		this.dia_turno = dia_turno;
	}

	public void setMeseros(ArrayList<Mesero> meseros) {
		this.meseros = meseros;
	}

	public void setCocineros(ArrayList<Cocinero> cocineros) {
		this.cocineros = cocineros;
	}
	
	
}
