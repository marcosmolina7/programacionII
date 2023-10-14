/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;


import POJOs.Funciones;
import POJOs.Peliculas;
import POJOs.Salas;
import java.util.Date;
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
public class CRUDFunciones {
    
    public static Funciones select(Integer idFuncion){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Funciones.class);
        criteria.add(Restrictions.eq("idFuncion", idFuncion));
        Funciones select = (Funciones) criteria.uniqueResult();
        
        if(select == null){
            select = new Funciones();
            select.setFechaHora(new Date());
        }
        session.close();
        return select;
    }
    
    public static List <Funciones> universo(){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
        List <Funciones> lista = null;
        
        try{
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Funciones.class);
            criteria.addOrder(Order.desc("idFuncion"));
            criteria.setMaxResults(500);
            lista = criteria.list();
        }catch(HibernateException e){
            System.out.println("Error: " + e);
        }finally{
            session.getTransaction().commit();
        }
        return lista;
    }
    
    public static boolean insertar(Peliculas peliculas, Salas salas, Date fechaHora){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Funciones.class);
        criteria.add(Restrictions.eq("fechaHora", fechaHora));
        Funciones insert = (Funciones) criteria.uniqueResult();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            if (insert == null){
                insert = new Funciones();
                insert.setPeliculas(peliculas);
                insert.setSalas(salas);
                insert.setFechaHora(fechaHora);
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

    public static boolean update(Integer id, Peliculas peliculas, Salas salas, Date fechaHora){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Funciones.class);
        criteria.add(Restrictions.eq("idFuncion", id));
        Funciones update = (Funciones)criteria.uniqueResult();
        Transaction transaction = null;
                try{
                    transaction = session.beginTransaction();
                    if (update != null){
                        update.setPeliculas(peliculas);
                        update.setSalas(salas);
                        update.setFechaHora(fechaHora);
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
    
    public static boolean eliminar(Integer idFuncion){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Funciones.class);
        criteria.add(Restrictions.eq("idFuncion", idFuncion));
        Funciones eliminar = (Funciones)criteria.uniqueResult();
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
