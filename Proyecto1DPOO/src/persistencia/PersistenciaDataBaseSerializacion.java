package persistencia;

import java.io.*;
import dataBase.DataBase;

public class PersistenciaDataBaseSerializacion implements IPersistenciaDataBase {

	@Override
	public DataBase cargarDataBase(String archivo) throws IOException, ClassNotFoundException {
		//
		File f = new File(archivo);
		
		if (f.exists()) {
			return new DataBase(null, null, null, null);
		}
		
		ObjectInputStream ois = null ;
		try { 
			ois = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(f)));
			return (DataBase) ois.readObject();}
		
		finally {
			if (ois != null) {
				ois.close();}
				}
			}

	@Override
	public void guardarDataBase(String archivo, DataBase dataBase) throws IOException {
		// TODO Auto-generated method stub
		
		File f = new File(archivo) ;
		
		if (f.getParentFile() == null) f.getParentFile().mkdirs(); 
		
		try(ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(f)))){
				oos.writeObject(dataBase);
			}
		}

}