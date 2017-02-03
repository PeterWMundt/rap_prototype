package ch.ethz.id.sws.eduapp.data.service;

import java.util.List;

import ch.ethz.id.sws.eduapp.data.entity.Clicker;

/**
 * The application's Data Access Object.
 */
public interface DBHelper {

	/**
	 * Returns a list of Clickers (ORDER BY title ASC)
	 *
	 * @return List&lt;Clicker>
	 */
	List<Clicker> getClickers();

	DBHelper save(Clicker clicker);

	DBHelper transactionBegin();

	DBHelper transactionCommit();

	DBHelper transactionRollback();

	DBHelper close();

}
