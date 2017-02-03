package ch.ethz.id.sws.eduapp.data.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Base class for the entities (i.e. model objects) in this project.<br />
 * This class adds property change support.
 * <p>
 * same implementation as SysStat!<br>
 * .. see ch.ethz.id.sws.swc.sysstat.data.entities.BaseEntity!
 * </p>
 *
 * @author peter.mundt@orcasys.ch
 */
public abstract class BaseEntity {
	private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
	
	public void addPropertyChangeListener(PropertyChangeListener 
			listener) {
		changeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener 
			listener) {
		changeSupport.removePropertyChangeListener(listener);
	}
	
	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
	
}