package model.dao;

import hibernate.util.HibernateUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.CustomerBean;
import model.CustomerDAO;

public class CustomerDAOHibernate implements CustomerDAO {
	
	public static final String SELECT_BY_ID="select *from customer where custid=?";
	public static final String UPDATE="update customer set password=?, email=?, birth=? where custid=?";

//	public static void main(String[]args){
//		CustomerDAO dao = new CustomerDAOHibernate();
//		CustomerBean person = dao.select("Alex");
//		System.out.println(person.toString());
//	}
	/* (non-Javadoc)
	 * @see modeldao.CustomerDAO#select(java.lang.String)
	 */
	@Override
	public CustomerBean select(String id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		CustomerBean customerBean = null;
		try {
			session.beginTransaction();
			customerBean = (CustomerBean) session.get(CustomerBean.class, id);
			session.getTransaction().commit();
		
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return customerBean;
	}
	/* (non-Javadoc)
	 * @see modeldao.CustomerDAO#update(byte[], java.lang.String, java.util.Date, java.lang.String)
	 */
	@Override
	public boolean update(byte[] password,String email, java.util.Date birth,String custid){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			
			
			session.beginTransaction();
			
			CustomerBean customerBean = new CustomerBean();
			customerBean.setPassword(password);
			customerBean.setEmail(email);
			customerBean.setBirth(birth);
			customerBean.setCustid(custid);
			session.saveOrUpdate(customerBean);
			session.getTransaction().commit();
			return true;
				
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		}	
		return false;
	}
	
}
