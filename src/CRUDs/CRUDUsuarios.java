/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;

import POJOs.Usuarios;
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
public class CRUDUsuarios {
    public static Usuarios select(Integer idUsuario){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Usuarios.class);
        criteria.add(Restrictions.eq("idUsuario", idUsuario));
        Usuarios select = (Usuarios) criteria.uniqueResult();
        
        if(select == null){
            select = new Usuarios();
            select.setNombreUsuario("0");
        }
        session.close();
        return select;
    }

     
 public static List <Usuarios> universo(){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
        List <Usuarios> lista = null;
        
        try{
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Usuarios.class);
            criteria.addOrder(Order.desc("idUsuario"));
            criteria.setMaxResults(500);
            lista = criteria.list();
        }catch(HibernateException e){
            System.out.println("Error: " + e);
        }finally{
            session.getTransaction().commit();
        }
        return lista;
    }
     
public static boolean insertar(String nombreUsuario, String email, short contrasenia, String rol){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Usuarios.class);
        criteria.add(Restrictions.eq("nombreUsuario", nombreUsuario));
        Usuarios insert = (Usuarios) criteria.uniqueResult();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            if (insert == null){
                insert = new Usuarios();
                insert.setNombreUsuario(nombreUsuario);
                insert.setEmail(email);
                insert.setContrasenia(contrasenia);
                insert.setRol(rol);
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


  public static boolean update(Integer id, String nombreUsuario, String email, short contrasenia, String rol){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Usuarios.class);
        criteria.add(Restrictions.eq("idUsuario", id));
        Usuarios update = (Usuarios)criteria.uniqueResult();
        Transaction transaction = null;
                try{
                    transaction = session.beginTransaction();
                    if (update != null){
                        update.setNombreUsuario(nombreUsuario);
                        update.setEmail(email);
                        update.setContrasenia(contrasenia);
                        update.setRol(rol);
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

     public static boolean eliminar(Integer idUsuario){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Usuarios.class);
        criteria.add(Restrictions.eq("idUsuario", idUsuario));
        Usuarios eliminar = (Usuarios)criteria.uniqueResult();
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

