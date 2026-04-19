package persistencia;

import java.io.*;
import tiendaDeJuegos.InventarioJuegos;

public class PersistenciaTiendaDeJuegosSerializacion implements IPersistenciaTiendaDeJuegos{

	@Override
	public InventarioJuegos cargarTiendaDeJuegos(String archivo) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		File f = new File(archivo) ;
		
		if (!f.exists()) {
			return new InventarioJuegos();}
		
		ObjectInputStream ios = null ;
		try {
			ios = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(f)));
			return (InventarioJuegos) ios.readObject();
			}
		
		finally {
			if (f != null) {
				ios.close();}
				}
			}

	@Override
	public void guardarTiendaDeJuegos(String archivo, InventarioJuegos inventario) throws IOException {
		// TODO Auto-generated method stub
		
		File f = new File(archivo) ;
		
		if (f.getParentFile() == null) f.getParentFile().mkdirs();
		
		try(ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(f)))){
				oos.writeObject(inventario);
				}
			}
}
