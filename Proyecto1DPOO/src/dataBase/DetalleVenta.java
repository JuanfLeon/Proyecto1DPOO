package dataBase;

import java.util.ArrayList;

import cafe.Producto;
import tiendaDeJuegos.JuegoDeMesaFisico;

public class DetalleVenta {

	private Producto producto;
	private int cantidad;
	private float subtotal;
	private float impuestos;
	private String tipoCompra;
	private ArrayList<JuegoDeMesaFisico> juegosComprados;
}
