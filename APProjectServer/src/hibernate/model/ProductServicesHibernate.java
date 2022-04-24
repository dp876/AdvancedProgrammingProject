package hibernate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.factories.SessionFactoryBuilderProductServices;

@Entity
@Table(name="product_services")
public class ProductServicesHibernate implements Serializable {

	/**
	 * 
	 */
	
	private final static Logger logger = Logger.getLogger(ProductServicesHibernate.class.getName()); 
	
	private static void setupLogger() {
        LogManager.getLogManager().reset();
        
        ConsoleHandler ch = new ConsoleHandler();
        logger.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("Log.log", true);
            logger.addHandler(fh);
        } catch (java.io.IOException e) {            
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
         
    }
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id;
	
	@Column(name="product_id")
	private int productId;
	
	@Column(name="product_name")
	private String productName;
	
	public ProductServicesHibernate() {
		this.id = 0;
		this.productId = 0;
		this.productName = "";
	}
	
	public ProductServicesHibernate(int id, int productId, String productName) {
		this.id = id;
		this.productId = productId;
		this.productName = productName;
	}
	
	public ProductServicesHibernate(ProductServicesHibernate obj) {
		this.id = obj.id;
		this.productId = obj.productId;
		this.productName = obj.productName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "id: " + id + "\nproductId: " + productId + "\nproductName: " + productName + "\n";
	}
	
	public void create() {
		setupLogger();
		Session session = SessionFactoryBuilderProductServices.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
		logger.info("product service created");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ProductServicesHibernate> readAll() {
		setupLogger();
		ArrayList<ProductServicesHibernate> productServicesList = new ArrayList<>();
		Session session = SessionFactoryBuilderProductServices.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		productServicesList = (ArrayList<ProductServicesHibernate>) session.createQuery("FROM ProductServicesHibernate").getResultList();
		transaction.commit();
		session.close();
		logger.info("product services list read");
		return productServicesList;
	}
	
	public void update() {
		setupLogger();
		Session session = SessionFactoryBuilderProductServices.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		ProductServicesHibernate obj = (ProductServicesHibernate) session.get(ProductServicesHibernate.class, this.id);
		obj.setProductId(this.productId);
		obj.setProductName(this.productName);
		session.update(obj);
		transaction.commit();
		session.close();
		logger.info("product service updated");
	}
	
	public void delete() {
		setupLogger();
		Session session = SessionFactoryBuilderProductServices.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		ProductServicesHibernate obj = (ProductServicesHibernate) session.get(ProductServicesHibernate.class, this.id);
		session.delete(obj);
		transaction.commit();
		session.close();
		logger.info("product service deleted");
	}

}
