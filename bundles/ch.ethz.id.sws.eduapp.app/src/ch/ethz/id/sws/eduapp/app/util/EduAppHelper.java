package ch.ethz.id.sws.eduapp.app.util;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class EduAppHelper {

	private EduAppHelper() {
		// prevent instantiation
	}

	/**
	 * Creates a text field that acts as filter through the specified listeners.
	 *
	 * @param parent
	 *            {@link Composite}
	 * @param modifyListener
	 *            {@link ModifyListener}
	 * @param keyListener
	 *            {@link KeyListener}
	 * @return {@link Text}
	 */
	public static Text createTextFilter(final Composite parent, final ModifyListener modifyListener,
			final KeyListener keyListener) {
		final Text txtFilter = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).grab(true, false).applyTo(txtFilter);
		txtFilter.addModifyListener(modifyListener);
		txtFilter.addKeyListener(keyListener);
		txtFilter.setData(RWT.ACTIVE_KEYS, new String[] { "ENTER", "ESCAPE", "ARROW_DOWN" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return txtFilter;
	}
}
