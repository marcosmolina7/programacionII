/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;

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
public class CRUDSalas {
    
public static Salas select(Integer idSala){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Salas.class);
        criteria.add(Restrictions.eq("idSala", idSala));
        Salas select = (Salas) criteria.uniqueResult();
        
        if(select == null){
            select = new Salas();
            select.setNumeroSala(0);
        }
        session.close();
        return select;
    }
    
    public static List <Salas> universo(){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
        List <Salas> lista = null;
        
        try{
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Salas.class);
            criteria.addOrder(Order.desc("idSala"));
            criteria.setMaxResults(500);
            lista = criteria.list();
        }catch(HibernateException e){
            System.out.println("Error: " + e);
        }finally{
            session.getTransaction().commit();
        }
        return lista;
    }
    
    public static boolean insertar(Integer numeroSala, Integer capacidad){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Salas.class);
        criteria.add(Restrictions.eq("numeroSala", numeroSala));
        Salas insert = (Salas) criteria.uniqueResult();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            if (insert == null){
                insert = new Salas();
                insert.setNumeroSala(numeroSala);
                insert.setCapacidad(capacidad);
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

    public static boolean update(Integer id, Integer numeroSala, Integer capacidad){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Salas.class);
        criteria.add(Restrictions.eq("idSala", id));
        Salas update = (Salas)criteria.uniqueResult();
        Transaction transaction = null;
                try{
                    transaction = session.beginTransaction();
                    if (update != null){
                        update.setNumeroSala(numeroSala);
                        update.setCapacidad(capacidad);
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
    
    public static boolean eliminar(Integer idSala){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Salas.class);
        criteria.add(Restrictions.eq("idSala", idSala));
        Salas eliminar = (Salas)criteria.uniqueResult();
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

