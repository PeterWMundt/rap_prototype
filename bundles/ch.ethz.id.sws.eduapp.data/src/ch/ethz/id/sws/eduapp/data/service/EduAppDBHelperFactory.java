package ch.ethz.id.sws.eduapp.data.service;

import org.osgi.service.component.annotations.Reference;

import ch.ethz.id.sws.eduapp.data.entity.Clicker;
import ch.ethz.id.sws.eduapp.data.internal.service.DBHelperImpl;
import ch.ethz.id.sws.eduapp.data.internal.service.NoOpDBHelper;

/** OSGi service acting as factory for <code>DBHelper</code> instances.<br />
 * Classes that want to access the persistence layer have to get an instance of this factory injected,
 * for that they can call <code>DBHelperFactory.createDBHelper()</code>. This is possible if such classes
 * are managed by the OSGi container. */
@org.osgi.service.component.annotations.Component(service = { EduAppDBHelperFactory.class }, immediate = true)
public class EduAppDBHelperFactory {
	
    private EduAppJDBCDataHandler jdbcHandler;
    private boolean dbLoaded = false;

    @Reference
    public void setEduAppJDBCDataHandler(final EduAppJDBCDataHandler jdbcHandler) {
        this.jdbcHandler = jdbcHandler;
    }

    public void unsetEduAppJDBCDataHandler(final EduAppJDBCDataHandler jdbcHandler) {
        this.jdbcHandler = jdbcHandler;
    }

    /** @return {@link DBHelper} */
    public DBHelper createDBHelper() {
//    	boolean jdbcHandlerIsInitialized = this.jdbcHandler.isInitialized();
//    	System.out.println("EduAppDBHelperFactory::createDBHelper jdbcHandlerIsInitialized = " + jdbcHandlerIsInitialized );
//        return jdbcHandlerIsInitialized ? new DBHelperImpl(this.jdbcHandler) : new NoOpDBHelper();
    	if( this.jdbcHandler.isInitialized() ) {    		
    		System.out.println("EduAppDBHelperFactory::createDBHelper use Database!" );
    		final DBHelper dBHelper = new DBHelperImpl(this.jdbcHandler); 
    		if(!this.dbLoaded){
    			loadSamples(dBHelper);
    			this.dbLoaded = true;
    		}
    		return dBHelper;
    	} else {    		
    		System.out.println("EduAppDBHelperFactory::createDBHelper use NoOpDBHelper!" );
    		return new NoOpDBHelper();
    	}
    }

	private void loadSamples(DBHelper dBHelper) {
		System.out.println("EduAppDBHelperFactory::loadSamples -> save clicker!" );
		for (int i = 0; i < 4; i++) {			
			dBHelper.save(Clicker.newClicker("Test" + i));
		}
	}

}
