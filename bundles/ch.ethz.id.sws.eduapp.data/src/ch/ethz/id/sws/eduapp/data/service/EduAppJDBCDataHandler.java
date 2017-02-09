package ch.ethz.id.sws.eduapp.data.service;

import java.util.Dictionary;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;

import ch.ethz.id.sws.swc.core.persistence.AbstractJDBCDataHandler;

/**
 * OSGi service responsible for creating <code>EntityManager</code>
 * instances.<br />
 * This service is managed and, therefore, is updated when the OSGI
 * configuration of the JDBC data changes. Hence this class propagates the JDBC
 * configuration to running application.
 */
@org.osgi.service.component.annotations.Component(immediate = true, service = { EduAppJDBCDataHandler.class,
		ManagedService.class }, configurationPid = AbstractJDBCDataHandler.DFT_CONFIG_PID)
public class EduAppJDBCDataHandler extends AbstractJDBCDataHandler {
	
	private static final String PERSISTENCE_UNIT_NAME = "eduapp";
	
	private static EntityManagerFactory emf;

	@Override
	public void updated(final Dictionary<String, ?> values) throws ConfigurationException {
		if(null != values){			
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, getJDBCConfig(values));
		} else {
			System.out.println("EduAppJDBCDataHandler::updated() CAUTION!!! -> Dictionary is " + values);
		}
	}

	/**
	 * @return {@link EntityManager} a newly created instance of the
	 *         <code>EntityManager</code>
	 */
	public EntityManager createEntityManager() {
		return emf.createEntityManager();
	}

	/**
	 * Checks whether this handler (i.e. it's <code>EntityManagerFactory</code>)
	 * is initialized.
	 *
	 * @return boolean <code>true</code> if the
	 */
	public boolean isInitialized() {
		return emf != null;
	}

}
