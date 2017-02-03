package ch.ethz.id.sws.eduapp.data.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_semester")
@NamedQuery(name="Semester.findAll", query="SELECT s FROM Semester s")
public class Semester extends BaseEntity implements Serializable {

	   private static final long serialVersionUID = -8615531907213017389L;

	    @Id
	    @Column(name = "`SEMKEZ`")
	    private String semKez;

	    @Column(name = "`SEM_KURZBEZ`")
	    private String semKurzBez;

	    @Column(name = "`SEM_LANGBEZ`")
	    private String semLangBez;

	    @Column(name = "`SEM_LANGBEZ_E`")
	    private String semLangBezE;

	    @Column(name = "`SEM_BEGINN`")
	    private Date semBeginn;

	    @Column(name = "`SEM_ENDE`")
	    private Date semEnde;

	    @Column(name = "`SEM_MITTE`")
	    private Date semMitte;

	    @Column(name = "`SEM_INTERVALL_ENDE`")
	    private Date semIntervallEnde;

	    @Column(name = "`SEM_VIRTUELL_BEGINN`")
	    private Date semVirtuellBeginn;

	    @Column(name = "`SEM_VIRTUELL_ENDE`")
	    private Date semVirtuellEnde;

//	    @OneToMany(mappedBy = "semester", targetEntity = LernEinheit.class)
//	    private Set<LernEinheit> lernEinheit;
//
//	    @OneToMany(mappedBy = "semester", targetEntity = BelegungOrt.class)
//	    private Set<BelegungOrt> belegungOrt;

	    public String getSemKez() {
	        return semKez;
	    }

	    public void setSemKez(String semKez) {
	        this.semKez = semKez;
	    }

	    public String getSemKurzBez() {
	        return semKurzBez;
	    }

	    public void setSemKurzBez(String semKurzBez) {
	        this.semKurzBez = semKurzBez;
	    }
	    
	    public String getSemLangBez() {
	        return semLangBez;
	    }

	    public void setSemLangBez(String semLangBez) {
	        this.semLangBez = semLangBez;
	    }

	    public String getSemLangBezE() {
	        return semLangBezE;
	    }

	    public void setSemLangBezE(String semLangBezE) {
	        this.semLangBezE = semLangBezE;
	    }

	    public Date getSemBeginn() {
	        return semBeginn;
	    }

	    public void setSemBeginn(Date semBeginn) {
	        this.semBeginn = semBeginn;
	    }

	    public Date getSemEnde() {
	        return semEnde;
	    }

	    public void setSemEnde(Date semEnde) {
	        this.semEnde = semEnde;
	    }

	    public Date getSemMitte() {
	        return semMitte;
	    }

	    public void setSemMitte(Date semMitte) {
	        this.semMitte = semMitte;
	    }

	    public Date getSemIntervallEnde() {
	        return semIntervallEnde;
	    }

	    public void setSemIntervallEnde(Date semIntervallEnde) {
	        this.semIntervallEnde = semIntervallEnde;
	    }

	    public Date getSemVirtuellBeginn() {
	        return semVirtuellBeginn;
	    }

	    public void setSemVirtuellBeginn(Date semVirtuellBeginn) {
	        this.semVirtuellBeginn = semVirtuellBeginn;
	    }

	    public Date getSemVirtuellEnde() {
	        return semVirtuellEnde;
	    }

	    public void setSemVirtuellEnde(Date semVirtuellEnde) {
	        this.semVirtuellEnde = semVirtuellEnde;
	    }

//	    public Set<LernEinheit> getLernEinheit() {
//	        return lernEinheit;
//	    }
//
//	    public void setLernEinheit(Set<LernEinheit> lernEinheit) {
//	        this.lernEinheit = lernEinheit;
//	    }
//
//	    public Set<BelegungOrt> getBelegungOrt() {
//	        return belegungOrt;
//	    }
//
//	    public void setBelegungOrt(Set<BelegungOrt> belegungOrt) {
//	        this.belegungOrt = belegungOrt;
//	    }

}
