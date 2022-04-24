package hibernate.model;

import java.io.Serializable;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.factories.SessionFactoryBuilderComplaintCategories;

@Entity
@Table(name="complaint_categories")
public class ComplaintCategoriesHibernate implements Serializable {

	/**
	 * 
	 */
	
	private final static Logger logger = Logger.getLogger(ComplaintCategoriesHibernate.class.getName()); 
	
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
	
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="category_name")
	private String categoryName;
	
	public ComplaintCategoriesHibernate() {
		this.id = 0;
		this.categoryId = 0;
		this.categoryName = "";
	}

	public ComplaintCategoriesHibernate(int id, int categoryId, String categoryName) {
		this.id = id;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	public ComplaintCategoriesHibernate(ComplaintCategoriesHibernate obj) {
		this.id = obj.id;
		this.categoryId = obj.categoryId;
		this.categoryName = obj.categoryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "id: " + id + "\ncategoryId: " + categoryId + "\ncategoryName: " + categoryName + "\n";
	}
	
	public void create() {
		setupLogger();
		Session session = SessionFactoryBuilderComplaintCategories.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
		logger.info("complaint category created");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ComplaintCategoriesHibernate> readAll() {
		ArrayList<ComplaintCategoriesHibernate> complaintCategoriesList = new ArrayList<>();
		Session session = SessionFactoryBuilderComplaintCategories.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		complaintCategoriesList = (ArrayList<ComplaintCategoriesHibernate>) session.createQuery("FROM ComplaintCategoriesHibernate").getResultList();
		transaction.commit();
		session.close();
		return complaintCategoriesList;
	}
	
	public void update() {
		Session session = SessionFactoryBuilderComplaintCategories.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		ComplaintCategoriesHibernate obj = (ComplaintCategoriesHibernate) session.get(ComplaintCategoriesHibernate.class, this.id);
		obj.setCategoryId(this.categoryId);
		obj.setCategoryName(this.categoryName);
		transaction.commit();
		session.close();
	}
	
	public void delete() {
		Session session = SessionFactoryBuilderComplaintCategories.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		ComplaintCategoriesHibernate obj = (ComplaintCategoriesHibernate) session.get(ComplaintCategoriesHibernate.class, this.id);
		session.delete(obj);
		transaction.commit();
		session.close();
	}
	
}
