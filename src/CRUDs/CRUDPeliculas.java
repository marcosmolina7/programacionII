/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;

import POJOs.Peliculas;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author estua
 */
public class CRUDPeliculas {
    
    public static Peliculas select(Integer idPelicula){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Peliculas.class);
        criteria.add(Restrictions.eq("idPelicula", idPelicula));
        Peliculas select = (Peliculas) criteria.uniqueResult();
        
        if(select == null){
            select = new Peliculas();
            select.setNombrePelicula("0");
        }
        session.close();
        return select;
    }
    
    public static List <Peliculas> universo(){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
        List <Peliculas> lista = null;
        
        try{
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Peliculas.class);
            criteria.addOrder(Order.desc("idPelicula"));
            criteria.setMaxResults(500);
            lista = criteria.list();
        }catch(HibernateException e){
            System.out.println("Error: " + e);
        }finally{
            session.getTransaction().commit();
        }
        return lista;
    }
    
    public static boolean insertar(String nombrePelicula, Integer duracion , String director, String genero){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Peliculas.class);
        criteria.add(Restrictions.eq("nombrePelicula", nombrePelicula));
        Peliculas insert = (Peliculas) criteria.uniqueResult();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            if (insert == null){
                insert = new Peliculas();
                insert.setNombrePelicula(nombrePelicula);
                insert.setDuracion(duracion);
                insert.setDirector(director);
                insert.setGenero(genero);
                session.save(insert);
                bandera = true;
            }
            transaction.commit();
        }catch(HibernateException e){
            transaction.rollback();
            System.out.println("Error =" + e);
        }finally{
            session.close();
        }
        return bandera;
    }

    public static boolean update(Integer id, String nombrePelicula, Integer duracion, String director, String genero){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Peliculas.class);
        criteria.add(Restrictions.eq("idPelicula", id));
        Peliculas update = (Peliculas)criteria.uniqueResult();
        Transaction transaction = null;
                try{
                    transaction = session.beginTransaction();
                    if (update != null){
                        update.setNombrePelicula(nombrePelicula);
                        update.setDuracion(duracion);
                        update.setDirector(director);
                        update.setGenero(genero);
                        session.update(update);
                        bandera = true;
                    }
                    transaction.commit();
                }catch(HibernateException e){
                    transaction.rollback();
                    System.out.println("Error = " + e);
                }finally{
                    session.close();
                }
                return bandera;
    }
    
    public static boolean eliminar(Integer idPelicula){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Peliculas.class);
        criteria.add(Restrictions.eq("idPelicula", idPelicula));
        Peliculas eliminar = (Peliculas)criteria.uniqueResult();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            if (eliminar != null){
                session.delete(eliminar);
                bandera = true;
            }
            transaction.commit();
        }catch(HibernateException e){
            transaction.rollback();
            System.out.println("Error: " + e);
        }finally{
            session.close();
        }
        return bandera;
    }
}

