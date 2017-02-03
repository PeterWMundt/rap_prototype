package ch.ethz.id.sws.eduapp.data.internal.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import ch.ethz.id.sws.eduapp.data.entity.Clicker;
import ch.ethz.id.sws.eduapp.data.service.DBHelper;
import ch.ethz.id.sws.eduapp.data.service.EduAppJDBCDataHandler;

/** The DAO implementation */
public class DBHelperImpl implements DBHelper {
	
	@SuppressWarnings("unused")
	private final EduAppJDBCDataHandler dataHandler;
	
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    private final CriteriaQuery<Clicker> queryClicker;
    @SuppressWarnings("unused")
	private final EntityType<Clicker> entityTypeClicker;

    public DBHelperImpl(final EduAppJDBCDataHandler dataHandler) {
        this.dataHandler = dataHandler;
        this.entityManager = dataHandler.createEntityManager();
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        this.queryClicker = this.criteriaBuilder.createQuery(Clicker.class);
        this.entityTypeClicker = this.entityManager.getMetamodel().entity(Clicker.class);
    }

	 /** 
	  * Returns a list of Clickers (ORDER BY title ASC)
      *
      * @return List&lt;Clicker> */
	@Override
	public List<Clicker> getClickers() {
	      final Root<Clicker> root = this.queryClicker.from(Clicker.class);
	      this.queryClicker.orderBy(this.criteriaBuilder.asc(root.get("title"))); //$NON-NLS-1$
	      final TypedQuery<Clicker> query = this.dataHandler.createEntityManager()
	              .createQuery(this.queryClicker.select(root));
	      return query.getResultList();
		
	}
	
	@Override
	public DBHelper save(Clicker entity) {
		transactionBegin();
		this.entityManager.merge(entity);
		this.entityManager.flush();
		transactionCommit();
		return this;
	}

    @Override
    public DBHelper transactionBegin() {
        this.entityManager.getTransaction().begin();
        return this;
    }

    @Override
    public DBHelper transactionCommit() {
        this.entityManager.getTransaction().commit();
        return this;
    }

    @Override
    public DBHelper transactionRollback() {
        this.entityManager.getTransaction().rollback();
        return this;
    }

    @Override
    public DBHelper close() {
        this.entityManager.clear();
        return this;
    }

}
