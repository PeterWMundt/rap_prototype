package ch.ethz.id.sws.eduapp.data.internal.service;

import java.util.ArrayList;
import java.util.List;

import ch.ethz.id.sws.eduapp.data.entity.Clicker;
import ch.ethz.id.sws.eduapp.data.service.DBHelper;

/** A DAO implementation doing nothing in case where the DB connection is not initialized yet. */
public class NoOpDBHelper implements DBHelper {
	
	//private static int count =  0;

	@Override
	public List<Clicker> getClickers() {
		final List<Clicker> clickers = new ArrayList<>();
		loadSamples(clickers);
		return clickers;
	}

	private void loadSamples(final List<Clicker> clickers) {
		for (int i = 0; i < 4; i++) {			
			clickers.add(Clicker.newClicker("Test" + i));
		}
	}

	@Override
	public DBHelper save(Clicker clicker) {
		System.out.println("NoOpDBHelper::save(..) -> do nothing!");
		return this;
	}

	@Override
	public DBHelper transactionBegin() {
		System.out.println("NoOpDBHelper::transactionBegin(..) -> do nothing!");
		return this;
	}

	@Override
	public DBHelper transactionCommit() {
		System.out.println("NoOpDBHelper::transactionCommit(..) -> do nothing!");
		return this;
	}

	@Override
	public DBHelper transactionRollback() {
		System.out.println("NoOpDBHelper::transactionRollback(..) -> do nothing!");
		return this;
	}

	@Override
	public DBHelper close() {
		System.out.println("NoOpDBHelper::close(..) -> do nothing!");
		return this;
	}
}
