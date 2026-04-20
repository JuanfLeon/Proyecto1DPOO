package tiendaDeJuegos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import exceptions.EstadoInvalidoException;
import exceptions.JuegoNoDisponibleException;

public class InventarioJuegos implements Serializable{
	private static final long serialVersionUID = 1L ;
	private HashMap<String,ArrayList<JuegoDeMesaFisico>> inventarioPrestamo;
	private HashMap<String,ArrayList<JuegoDeMesaFisico>> inventarioVenta;
	private HashMap<String, JuegoDeMesa> catalogoJuegos;
	private ArrayList<Prestamo> historialDePrestamos;
	
	
	
	
	// Getters y setters
	public HashMap<String, ArrayList<JuegoDeMesaFisico>> getInventarioVenta() {
		return inventarioVenta;
	}

	public void setInventarioVenta(HashMap<String, ArrayList<JuegoDeMesaFisico>> inventarioVenta) {
		this.inventarioVenta = inventarioVenta;
	}

	public HashMap<String, ArrayList<JuegoDeMesaFisico>> getInventarioPrestamo() {
		return inventarioPrestamo;
	}
	
	public void setInventarioPrestamo(HashMap<String, ArrayList<JuegoDeMesaFisico>> inventarioPrestamo) {
		this.inventarioPrestamo = inventarioPrestamo;
	}
	
	public ArrayList<Prestamo> getHistorialDePrestamos() {
		return historialDePrestamos;
	}

	public void setHistorialDePrestamos(ArrayList<Prestamo> historialDePrestamos) {
		this.historialDePrestamos = historialDePrestamos;
	}
	
	public HashMap<String, JuegoDeMesa> getCatalogoJuegos() {
		return catalogoJuegos;
	}

	public void setCatalogoJuegos(HashMap<String, JuegoDeMesa> catalogoJuegos) {
		this.catalogoJuegos = catalogoJuegos;
	}
	
	//Requerimientos Funcionales
	


	public boolean validarDisponibilidadJuegos(String nombreJuego, int cantidad, TipoInventario tipoInventario) 
	        throws JuegoNoDisponibleException{
		
		if (tipoInventario== TipoInventario.VENTAS) {
			if (cantidad>inventarioVenta.get(nombreJuego).size()) {
				throw new JuegoNoDisponibleException(nombreJuego);
			}
			return true;
		}
		else if (tipoInventario== TipoInventario.PRESTAMO) {
			if (cantidad>inventarioPrestamo.get(nombreJuego).size()) {
				throw new JuegoNoDisponibleException(nombreJuego);
			}
			return true;
		}
		return false;
		}
	
	public JuegoDeMesaFisico eliminarJuegoInventario(String idJuego, String nombreJuego, TipoInventario tipoInventario) throws EstadoInvalidoException, JuegoNoDisponibleException {
		HashMap<String,ArrayList<JuegoDeMesaFisico>> copiaInventario= new HashMap<>();
		boolean encontrado= false;
		JuegoDeMesaFisico juegoEliminado= null;
		if (tipoInventario == TipoInventario.VENTAS) {
			copiaInventario= inventarioVenta;
			for (JuegoDeMesaFisico juegoVenta : this.getInventarioVenta().get(nombreJuego)) {
				if (juegoVenta.getIdJuego().equals(idJuego)) {
					copiaInventario.get(nombreJuego).remove(juegoVenta);
					encontrado=true;
					juegoEliminado=juegoVenta;
				}
			}
			if(!encontrado) {
				throw new JuegoNoDisponibleException(idJuego);
			}
			setInventarioVenta(copiaInventario);
		}
		else if (tipoInventario == TipoInventario.PRESTAMO) {
			copiaInventario= inventarioPrestamo;
			for (JuegoDeMesaFisico juegoPrestamo : this.getInventarioPrestamo().get(nombreJuego)) {
				if (juegoPrestamo.getIdJuego().equals(idJuego)) {
					copiaInventario.get(nombreJuego).remove(juegoPrestamo);
					encontrado=true;
					juegoEliminado=juegoPrestamo;
					}
				}
			if(!encontrado) {
				throw new JuegoNoDisponibleException(idJuego);
			}
			setInventarioPrestamo(copiaInventario);
			}
		
		else {
			throw new EstadoInvalidoException(tipoInventario.toString());
		}
		return juegoEliminado;
	}
	
	public JuegoDeMesaFisico eliminarPrimerJuego(String nombreJuego, TipoInventario tipoInventario) throws JuegoNoDisponibleException {
		JuegoDeMesaFisico juego= null;
		if (tipoInventario==TipoInventario.VENTAS) {
			juego= inventarioVenta.get(nombreJuego).removeFirst();
		}
		else if (tipoInventario==TipoInventario.PRESTAMO) {
			juego= inventarioPrestamo.get(nombreJuego).removeFirst();
		}
		if(juego==null) {
			throw new JuegoNoDisponibleException(nombreJuego);
		}
		return juego;
	}

	public InventarioJuegos() {
		this.inventarioPrestamo = new HashMap<String,ArrayList<JuegoDeMesaFisico>>();
		this.inventarioVenta = new HashMap<String,ArrayList<JuegoDeMesaFisico>>();
		this.historialDePrestamos = new ArrayList<Prestamo>();
	}

	public void agregarJuegoInventario(JuegoDeMesaFisico juegoNuevo, String nombreJuego, TipoInventario tipoInventario) {
		if (tipoInventario == TipoInventario.VENTAS) {
	        inventarioVenta
	            .computeIfAbsent(nombreJuego, k -> new ArrayList<>())
	            .add(juegoNuevo);
	    }
		else if (tipoInventario == TipoInventario.PRESTAMO) {
		    inventarioPrestamo
		        .computeIfAbsent(nombreJuego, k -> new ArrayList<>())
		        .add(juegoNuevo);
		}
		
	}
	public void agregarPrestamoAHistorial(Prestamo prestamo) {
		historialDePrestamos.add(prestamo);
	}
	

}
	

