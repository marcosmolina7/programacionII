package POJOs;
// Generated 13-oct-2023 14:06:49 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Peliculas generated by hbm2java
 */
public class Peliculas  implements java.io.Serializable {


     private Integer idPelicula;
     private String nombrePelicula;
     private Integer duracion;
     private String director;
     private String genero;
     private Set<Funciones> funcioneses = new HashSet<Funciones>(0);

    public Peliculas() {
    }

    public Peliculas(String nombrePelicula, Integer duracion, String director, String genero, Set<Funciones> funcioneses) {
       this.nombrePelicula = nombrePelicula;
       this.duracion = duracion;
       this.director = director;
       this.genero = genero;
       this.funcioneses = funcioneses;
    }
   
    public Integer getIdPelicula() {
        return this.idPelicula;
    }
    
    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }
    public String getNombrePelicula() {
        return this.nombrePelicula;
    }
    
    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }
    public Integer getDuracion() {
        return this.duracion;
    }
    
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    public String getDirector() {
        return this.director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    public String getGenero() {
        return this.genero;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public Set<Funciones> getFuncioneses() {
        return this.funcioneses;
    }
    
    public void setFuncioneses(Set<Funciones> funcioneses) {
        this.funcioneses = funcioneses;
    }
}


