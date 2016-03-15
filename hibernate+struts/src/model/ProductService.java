package model;

import java.util.ArrayList;
import java.util.List;

import model.dao.ProductDAOHibernate;
import model.dao.ProductDAOJdbc;

public class ProductService {
	private ProductDAO productDao = new ProductDAOHibernate();
//	public static void main(String[] args) {
//		ProductService service = new ProductService();
//		List<ProductBean> beans = service.select(null);
//		System.out.println("beans="+beans);
//	}
	public List<ProductBean> select(ProductBean bean) {
		List<ProductBean> result = null;
		System.out.println(bean);
		if(bean!=null &&bean.getId()!=null&& bean.getId()!=0) {
			ProductBean temp = productDao.select(bean.getId());
			if(temp!=null) {
				System.out.println("select");

				result = new ArrayList<ProductBean>();
				result.add(temp);
			}
		} else {
			System.out.println("select all");
			result = productDao.select(); 
			System.out.println(result);
		}
		return result;
	}
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		System.out.println("bean: " +bean.getId());
		if(bean!=null) {
			result = productDao.insert(bean);
		}
		return result;
	}
	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		if(bean!=null) {
			System.out.println("service"+bean.getMake());
			result = productDao.update(bean.getName(), bean.getPrice(),
					bean.getMake(), bean.getExpire(), bean.getId());
		}
		return result;
	}
	public boolean delete(ProductBean bean) {
		boolean result = false;
		if(bean!=null) {
			result = productDao.delete(bean.getId());
		}
		return result;
	}

}
