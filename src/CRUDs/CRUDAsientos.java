/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;

import POJOs.Asientos;
import POJOs.Salas;
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
public class CRUDAsientos {
    
     public static Asientos select(Integer idAsiento){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Asientos.class);
        criteria.add(Restrictions.eq("idAsiento", idAsiento));
        Asientos select = (Asientos) criteria.uniqueResult();
        
        if(select == null){
            select = new Asientos();
            select.setNumeroAsiento(0);
        }
        session.close();
        return select;
    }
    
    public static List <Asientos> universo(){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
        List <Asientos> lista = null;
        
        try{
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Asientos.class);
            criteria.addOrder(Order.desc("idAsiento"));
            criteria.setMaxResults(500);
            lista = criteria.list();
        }catch(HibernateException e){
            System.out.println("Error: " + e);
        }finally{
            session.getTransaction().commit();
        }
        return lista;
    }
    
    public static boolean insertar(Salas salas, Integer numeroAsiento, String estadoHaciento){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Asientos.class);
        criteria.add(Restrictions.eq("numeroAsiento", numeroAsiento));
        Asientos insert = (Asientos) criteria.uniqueResult();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            if (insert == null){
                insert = new Asientos();
                insert.setSalas(salas);
                insert.setNumeroAsiento(numeroAsiento);
                insert.setEstadoHaciento(estadoHaciento);
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

    public static boolean update(Integer id, Salas salas, Integer numeroAsiento, String estadoHaciento){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Asientos.class);
        criteria.add(Restrictions.eq("idAsiento", id));
        Asientos update = (Asientos)criteria.uniqueResult();
        Transaction transaction = null;
                try{
                    transaction = session.beginTransaction();
                    if (update != null){
                        update.setSalas(salas);
                        update.setNumeroAsiento(numeroAsiento);
                        update.setEstadoHaciento(estadoHaciento);
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
    
    public static boolean eliminar(Integer idAsiento){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Asientos.class);
        criteria.add(Restrictions.eq("idAsiento", idAsiento));
        Asientos eliminar = (Asientos)criteria.uniqueResult();
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

