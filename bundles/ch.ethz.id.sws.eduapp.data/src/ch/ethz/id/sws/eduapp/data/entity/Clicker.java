package ch.ethz.id.sws.eduapp.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * JPA mapping object for table t_clicker. Represents a Clicker in the EduApp context.
 * 
 * 
 * @author Peter Mundt (peter.mundt@orcasys.ch)
 *
 */
@Entity
@Table(name="t_clicker")
@NamedQuery(name="Clicker.findAll", query="SELECT c FROM Clicker c")
public class Clicker extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
	public static final Clicker newClicker(String title) {
		final Clicker clicker = new Clicker();
		clicker.setTitle(title);
		return clicker;
	}



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pk_clicker_id")
    private int id;
    
	@Column(name = "title")
	private String title;

    public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

	/**
	 * Factory method to create an empty <code>Clicker</code> model.
	 * 
	 * @return {@link Clicker}
	 */
	public static Clicker getEmptyClicker() {
	    final EmptyClicker out = new EmptyClicker();
	    out.setId(0);
	    out.setTitle(""); //$NON-NLS-1$
	    //out.divisionShort = ""; //$NON-NLS-1$
	    return out;	
	}
	
	@SuppressWarnings("serial")
    private static class EmptyClicker extends Clicker {
	    @Override
	    public void setId(int id) {
	        super.setId(0);
	    }	    
	}}
