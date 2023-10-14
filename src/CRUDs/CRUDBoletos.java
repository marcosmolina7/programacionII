/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;

import POJOs.Asientos;
import POJOs.Boletos;
import POJOs.Funciones;
import POJOs.Usuarios;
import java.math.BigDecimal;
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
public class CRUDBoletos {
    
    public static Boletos select(Integer idBoleto){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Boletos.class);
        criteria.add(Restrictions.eq("idBoleto", idBoleto));
        Boletos select = (Boletos) criteria.uniqueResult();
        
        if(select == null){
            select = new Boletos();
            select.setNombreCliente("0");
        }
        session.close();
        return select;
    }
    
    public static List <Boletos> universo(){
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
        List <Boletos> lista = null;
        
        try{
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Boletos.class);
            criteria.addOrder(Order.desc("idBoleto"));
            criteria.setMaxResults(500);
            lista = criteria.list();
        }catch(HibernateException e){
            System.out.println("Error: " + e);
        }finally{
            session.getTransaction().commit();
        }
        return lista;
    }
    
    public static boolean insertar(Asientos asientos, Funciones funciones, Usuarios usuarios, String nombreCliente, BigDecimal precio, Date fechaCompra, String metodoPago){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Boletos.class);
        criteria.add(Restrictions.eq("nombreCliente", nombreCliente));
        Boletos insert = (Boletos) criteria.uniqueResult();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            if (insert == null){
                insert = new Boletos();
                insert.setAsientos(asientos);
                insert.setFunciones(funciones);
                insert.setUsuarios(usuarios);
                insert.setNombreCliente(nombreCliente);
                insert.setPrecio(precio);
                insert.setFechaCompra(fechaCompra);
                insert.setMetodoPago(metodoPago);
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

    public static boolean update(Integer id, Asientos asientos, Funciones funciones, Usuarios usuarios, String nombreCliente, BigDecimal precio, Date fechaCompra, String metodoPago){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Boletos.class);
        criteria.add(Restrictions.eq("idBoleto", id));
        Boletos update = (Boletos)criteria.uniqueResult();
        Transaction transaction = null;
                try{
                    transaction = session.beginTransaction();
                    if (update != null){
                        update.setAsientos(asientos);
                        update.setFunciones(funciones);
                        update.setUsuarios(usuarios);
                        update.setNombreCliente(nombreCliente);
                        update.setPrecio(precio);
                        update.setFechaCompra(fechaCompra);
                        update.setMetodoPago(metodoPago);
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
    
    public static boolean eliminar(Integer idBoleto){
        boolean bandera = false;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Boletos.class);
        criteria.add(Restrictions.eq("idBoleto", idBoleto));
        Boletos eliminar = (Boletos)criteria.uniqueResult();
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

