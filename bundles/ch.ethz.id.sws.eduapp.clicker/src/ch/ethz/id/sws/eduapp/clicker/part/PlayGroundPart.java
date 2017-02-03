package ch.ethz.id.sws.eduapp.clicker.part;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.swt.widgets.Composite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.id.sws.eduapp.clicker.MessageRegistry;
import ch.ethz.id.sws.eduapp.data.service.EduAppDBHelperFactory;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PlayGroundPart {

	private static final Logger LOG = LoggerFactory.getLogger(PlayGroundPart.class);
	private Text text;
	   
	@SuppressWarnings("serial")
	@PostConstruct
	public void postConstruct(final Composite parent, final EMenuService menuService, 
			final EduAppDBHelperFactory dbHelperFactory, final MessageRegistry messageRegistry) {

		LOG.debug("postConstruct(..) START");  //$NON-NLS-1$
        System.out.println("postConstruct(..) START *************************************************** ");	 //$NON-NLS-1$

		parent.setLayout(new GridLayout(2, false));
		new Label(parent, SWT.NONE);
		
		Button btnNewButton = new Button(parent, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("show something");
			}
		});
		btnNewButton.setText("New Button");
		new Label(parent, SWT.NONE);
		
		Button btnNewButton_1 = new Button(parent, SWT.NONE);
		btnNewButton_1.setText("New Button");
		
		Label lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setText("New Label");
		
		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
	}

}
