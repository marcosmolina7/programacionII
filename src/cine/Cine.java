/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine;

import CRUDs.CRUDFunciones;
import CRUDs.CRUDPeliculas;
import POJOs.Funciones;
import POJOs.Peliculas;
import POJOs.Salas;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author estua
 */
public class Cine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
//             boolean insertResult = CRUDPeliculas.insertar("nombrePelicula1", 120, "director1", "genero1");
  //      System.out.println("Resultado de la inserción: " + insertResult);

        // Prueba de actualización
       // boolean updateResult = CRUDPeliculas.update(2, "nombrePelicula2", 150, "director2", "genero2");
       // System.out.println("Resultado de la actualización: " + updateResult); 

      // Prueba de eliminación
      //  boolean deleteResult = CRUDPeliculas.eliminar(2);
      //  System.out.println("Resultado de la eliminación: " + deleteResult);

      /*  // Prueba de impresión
        List<Peliculas> peliculas = CRUDPeliculas.universo();
        for (Peliculas pelicula : peliculas) {
            System.out.println("ID: " + pelicula.getIdPelicula());
            System.out.println("Nombre de Película: " + pelicula.getNombrePelicula());
            System.out.println("Duración: " + pelicula.getDuracion());
            System.out.println("Director: " + pelicula.getDirector());
            System.out.println("Género: " + pelicula.getGenero());
            System.out.println("-------------------------");
        }*/
        
        
        
         // Crear objetos de Peliculas y Salas para las pruebas
        Peliculas peliculaPrueba = new Peliculas();
        Salas salaPrueba = new Salas();

        // Prueba de inserción
        boolean insertResult = CRUDFunciones.insertar(peliculaPrueba, salaPrueba, new Date());
        System.out.println("Resultado de la inserción: " + insertResult);

      /*  // Prueba de actualización
        boolean updateResult = CRUDFunciones.update(1, peliculaPrueba, salaPrueba, new Date());
        System.out.println("Resultado de la actualización: " + updateResult);

        // Prueba de eliminación
        boolean deleteResult = CRUDFunciones.eliminar(1);
        System.out.println("Resultado de la eliminación: " + deleteResult);

        // Prueba de impresión
        List<Funciones> funciones = CRUDFunciones.universo();
        for (Funciones funcion : funciones) {
            System.out.println("ID: " + funcion.getIdFuncion());
            System.out.println("Película: " + funcion.getPeliculas().getNombrePelicula());
            System.out.println("Sala: " + funcion.getSalas().getNumeroSala());
            System.out.println("Fecha y Hora: " + funcion.getFechaHora());
            System.out.println("-------------------------");
        }*/
    }
}



