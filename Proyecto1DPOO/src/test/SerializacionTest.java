package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.*;

import cafe.*;
import dataBase.*;
import java.util.ArrayList;

public class SerializacionTest {

    private static final String RUTA = "testDB.ser";

    //TEST GUARDAR

    @Test
    void testGuardarObjeto() throws Exception {

        DataBase db = new DataBase(null, null, null, null);

        FileOutputStream fos = new FileOutputStream(RUTA);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(db);
        oos.close();

        File archivo = new File(RUTA);
        assertTrue(archivo.exists());
    }

    //TEST GUARDAR Y CARGAR

    @Test
    void testGuardarYCargarObjeto() throws Exception {

        DataBase dbOriginal = new DataBase(null, null, null, null);

        // Guardar
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA));
        oos.writeObject(dbOriginal);
        oos.close();

        // Cargar
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA));
        DataBase dbCargada = (DataBase) ois.readObject();
        ois.close();

        assertNotNull(dbCargada);
    }


    // SOBRESCRIBIR ARCHIVO

    @Test
    void testSobrescribirArchivo() throws Exception {

        DataBase db1 = new DataBase(null, null, null, null);
        DataBase db2 = new DataBase(null, null, null, null);

        // Guardar primero
        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(RUTA));
        oos1.writeObject(db1);
        oos1.close();

        // Sobrescribir
        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(RUTA));
        oos2.writeObject(db2);
        oos2.close();

        File archivo = new File(RUTA);
        assertTrue(archivo.exists());
    }
}