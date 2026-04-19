package persistencia;

import java.io.*;
import cafe.Cafe;

public class PersistenciaCafeSerializacion implements IPersistenciaCafe {

	@Override
	public Cafe cargarCafe(String archivo) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		File f = new File(archivo) ;
		
		if (!f.exists()) {
			return new Cafe(50, null, null);}
		
	ObjectInputStream ois = null ;
	try {
		ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(f))); 
	return (Cafe) ois.readObject();}
	finally {
		if (ois != null) {
			ois.close();}
			}
		}
	
	@Override
	public void guardarCafe(String archivo, Cafe cafe) throws IOException {
		// TODO Auto-generated method stub
			File f = new File(archivo) ;
			if (f.getParentFile() != null) f.getParentFile().mkdirs() ;
			
			try (ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(f)))) {
				oos.writeObject(cafe);
			}
		}
	}
