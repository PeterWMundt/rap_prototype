package ch.ethz.id.sws.eduapp.clicker.part;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.id.sws.eduapp.clicker.MessageRegistry;
import ch.ethz.id.sws.eduapp.data.service.EduAppDBHelperFactory;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class PlayGroundPart {

	private static final Logger LOG = LoggerFactory.getLogger(PlayGroundPart.class);
	private Table tableClicker;
	private Table tableSemester;
	private Table tableClickerQuestions;
	private TabFolder tabFolder;
	private TabItem tabItemPresentation;
	private TabItem tabItemDetailsAndEdit;
	   
	@PostConstruct
	public void postConstruct(final Composite parent) {
	//blic void postConstruct(final Composite parent, final EMenuService menuService, final EduAppDBHelperFactory dbHelperFactory, final MessageRegistry messageRegistry) {
		LOG.debug("postConstruct(..) START");  //$NON-NLS-1$
        System.out.println("postConstruct(..) START *************************************************** ");

		parent.setLayout(new GridLayout(2, true));
		
		Composite leftSide = new Composite(parent, SWT.NONE);
		GridData gd_leftSide = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		//gd_leftSide.horizontalAlignment = SWT.FILL;
		leftSide.setLayoutData(gd_leftSide);
		
		//RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
//		leftSide.setLayout(rowLayout);
		leftSide.setLayout(new GridLayout(1, false));
		
		tableClicker = new Table(leftSide, SWT.BORDER | SWT.FULL_SELECTION);
//		tableClicker.setBounds(0, 10, 85, 66);
		tableClicker.setHeaderVisible(true);
		tableClicker.setLinesVisible(true);

		//idData gd_tableClicker = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
		GridData gd_tableClicker = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		tableClicker.setLayoutData(gd_tableClicker);

		tableSemester = new Table(leftSide, SWT.BORDER | SWT.FULL_SELECTION);
		//tableSemester.set
		tableSemester.setHeaderVisible(true);
		tableSemester.setLinesVisible(true);

		//idData gd_tableSemester = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
		GridData gd_tableSemester = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		tableSemester.setLayoutData(gd_tableSemester);

		Composite rightSide = new Composite(parent, SWT.NONE);
		GridData gd_rightSide = new GridData(GridData.FILL_BOTH); // ?????????????? new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		rightSide.setLayoutData(gd_rightSide); //new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));
		
		rightSide.setLayout(new GridLayout(1, false));
		
		Label lblClickerfagenEduapp = new Label(rightSide, SWT.NONE);
		lblClickerfagenEduapp.setText("Clickerfagen - EduApp Kurs");
		
		tabFolder = new TabFolder(rightSide, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		//idData gd_tabFolder = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		tabFolder.setLayoutData(gd_tabFolder);

		tabItemPresentation = new TabItem(tabFolder, SWT.NONE);
		tabItemPresentation.setText("Präsentation ");

		tableClickerQuestions = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tableClickerQuestions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
//		tableClickerQuestions.setBounds(0, 0, 85, 45);
		tableClickerQuestions.setHeaderVisible(true);
		tableClickerQuestions.setLinesVisible(true);	

		GridData gd_tableClickerQuestions = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		tableClickerQuestions.setLayoutData(gd_tableClickerQuestions);


		tabItemPresentation.setControl(tableClickerQuestions);

		tabItemDetailsAndEdit = new TabItem(tabFolder, SWT.NONE);
		tabItemDetailsAndEdit.setText("Details & Bearbeiten");
	}
}
