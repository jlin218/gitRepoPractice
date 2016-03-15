package model.dao;

import hibernate.util.HibernateUtil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import model.ProductBean;
import model.ProductDAO;

public class ProductDAOHibernate implements ProductDAO {

	private static final String SELECT_BY_ID = "select * from product where id=?";
	@Override
	public ProductBean select(int id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.getTransaction();
		ProductBean result = null;
		try {
			tx.begin();
			result= (ProductBean) session.get(ProductBean.class, id);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return result;
	}
	
	private static final String SELECT_ALL = "from ProductBean";
	/* (non-Javadoc)
	 * @see modeldao.ProductDAO#select()
	 */
	@Override
	public List<ProductBean> select() {
		List<ProductBean> result = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			Query query = session.createQuery(SELECT_ALL);
			List<ProductBean> resultTest = query.list();
			result = resultTest;
			System.out.println(resultTest);
	
			tx.commit();
			
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return result;
	}
	private static final String UPDATE = "update product set name=?, price=?, make=?, expire=? where id=?";
	/* (non-Javadoc)
	 * @see modeldao.ProductDAO#update(java.lang.String, double, java.util.Date, int, int)
	 */
	@Override
	public ProductBean update(String name, double price, java.util.Date make, int expire, int id) {
		ProductBean result = null;
		ProductBean temp = new ProductBean();
		
		temp.setId(id);
		temp.setName(name);
		temp.setPrice(price);
		temp.setMake(make);
		temp.setExpire(expire);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.update(temp);
			tx.commit();
			result=temp;

		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		}

		return result;
	}
	private static final String INSERT = "insert into product (id, name, price, make, expire) values (?, ?, ?, ?, ?)";
	/* (non-Javadoc)
	 * @see modeldao.ProductDAO#insert(model.ProductBean)
	 */
	@Override
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.getTransaction();
		System.out.println("222"+bean.getId());
		try {
			tx.begin();
			System.out.println("beanId:"+bean.getId());
			ProductBean pb2 = new ProductBean();
			pb2.setId(bean.getId());
			pb2.setName(bean.getName());
			pb2.setPrice(bean.getPrice());
			pb2.setMake(bean.getMake());
			pb2.setExpire(bean.getExpire());
			System.out.println("dfdfs"+pb2.getMake());
			session.save(pb2);
			tx.commit();
			result = bean;
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		}

		return result;
	}
	private static final String DELETE = "delete from product where id=:id";
	/* (non-Javadoc)
	 * @see modeldao.ProductDAO#delete(int)
	 */
	@Override
	public boolean delete(int id) {
		int n = 0;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			ProductBean pb = (ProductBean) session.get(ProductBean.class, id);
			session.delete(pb);
			tx.commit();
			n=1;
			
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		}

		return n==1;
	}	

}
