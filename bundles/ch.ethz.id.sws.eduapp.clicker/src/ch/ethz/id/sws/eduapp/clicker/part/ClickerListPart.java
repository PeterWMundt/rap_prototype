package ch.ethz.id.sws.eduapp.clicker.part;

import static ch.ethz.id.sws.eduapp.clicker.part.Util.MODERN_STYLE;
import static ch.ethz.id.sws.eduapp.clicker.part.Util.createFillData;
import static ch.ethz.id.sws.eduapp.clicker.part.Util.createFillLayout;
import static ch.ethz.id.sws.eduapp.clicker.part.Util.createMainLayout;
import static ch.ethz.id.sws.eduapp.clicker.part.Util.*;

import javax.annotation.PostConstruct;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.id.sws.eduapp.clicker.part.Util.TreeContentProvider;
import ch.ethz.id.sws.eduapp.clicker.part.Util.TreeLabelProvider;
import ch.ethz.id.sws.eduapp.clicker.part.Util.TreeObject;
import ch.ethz.id.sws.eduapp.data.entity.Clicker;

public class ClickerListPart {

	private static final Logger LOG = LoggerFactory.getLogger(ClickerListPart.class);

	private Table tableClicker;
	private TreeViewer clickerTree;
	private Table tableSemester;
	private Table tableClickerQuestions;
	private TabFolder tabFolder;
	private TabItem tabItemPresentation;
	private TabItem tabItemDetailsAndEdit;

	@PostConstruct
	public void postConstruct(final Composite parent) {
		// blic void postConstruct(final Composite parent, final EMenuService
		// menuService, final EduAppDBHelperFactory dbHelperFactory, final
		// MessageRegistry messageRegistry) {
		LOG.debug("postConstruct(..) START"); //$NON-NLS-1$
		System.out.println("postConstruct(..) START *************************************************** ");

		parent.setLayout(createMainLayout(2));

		createTopLeft(parent);

		createTopRight(parent);

		createFooter(parent);

		setFocus();
	}

	private void createTopLeft(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(createFillData());
		composite.setLayout(createFillLayout(SWT.VERTICAL, true));

		//tableClicker = createClickerTable(composite);
		clickerTree = createClickerTree(composite, createClickerModel() );

		tableSemester = createTableSemester(composite);
	}

	private TreeViewer createClickerTree(Composite parent, TreeObject model) {
	    Tree tree = new Tree( parent, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION );
	    TreeViewer result = new TreeViewer( tree );
	    result.setContentProvider( new TreeContentProvider() );
	    TreeLabelProvider labelProvider = new TreeLabelProvider( parent.getDisplay(), MODERN_STYLE );
	    result.setLabelProvider( labelProvider );
	    result.setInput( model );
	    result.expandAll();
	   // tree.addFocusListener( new TreeFocusGainedHandler() );
//	    addDNDSupport( result );
//	    addCellEditor( result );
//	    addToolTipSupport( result );
	    return result;
	}
	
	  private static TreeObject createClickerModel() {
		    TreeObject result = new TreeObject( "" );
		    TreeObject clickers = new TreeObject( "Clicker" );
		    result.addChild( clickers );
		    clickers.addChild(  new TreeClicker( "EduApp Kurs"  ) );
		    clickers.addChild(  new TreeClicker( "EduApp Kurs"  ) );
		    clickers.addChild(  new TreeClicker( "Analysis I"  ) );
		    TreeObject backchannels = new TreeObject( "Backchannel" );
		    result.addChild( backchannels );
		    backchannels.addChild(  new TreeClicker( "EduApp Kurs"  ) );
		    backchannels.addChild(  new TreeClicker( "Analysis I"  ) );
		    backchannels.addChild(  new TreeClicker( "Physik III"  ) );
		    backchannels.addChild(  new TreeClicker( "Chemie II"  ) );
		    return result;
		  }


	private Table createTableSemester(Composite parent) {
		Table tableSemester = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION);
		// tableSemester.set
		tableSemester.setHeaderVisible(true);
		tableSemester.setLinesVisible(true);

		// idData gd_tableSemester = new GridData(SWT.FILL, SWT.TOP, true, true,
		// 1, 1);
		GridData gd_tableSemester = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		//tableSemester.setLayoutData(gd_tableSemester);
		return tableSemester;
	}

	private Table createClickerTable(Composite parent) {
		Table tableClicker = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION);
		// tableClicker.setBounds(0, 10, 85, 66);
		tableClicker.setHeaderVisible(true);
		tableClicker.setLinesVisible(true);

		// idData gd_tableClicker = new GridData(SWT.FILL, SWT.TOP, true, true,
		// 1, 1);
		GridData gd_tableClicker = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		//tableClicker.setLayoutData(gd_tableClicker);
		return tableClicker;
	}

	private void createTopRight(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		
//		composite.setLayoutData(createFillData());
//		composite.setLayout(createFillLayout(true));
		
		composite.setLayoutData(new GridData(GridData.FILL_BOTH)); 
		composite.setLayout(new GridLayout(1, false));

		Label lblClickerfagenEduapp = new Label(composite, SWT.NONE);
		lblClickerfagenEduapp.setText("Clickerfagen - EduApp Kurs");

		tabFolder = new TabFolder(composite, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		// idData gd_tabFolder = new GridData(SWT.FILL, SWT.TOP, true, true, 1,
		// 1);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		tabFolder.setLayoutData(gd_tabFolder);

		tabItemPresentation = new TabItem(tabFolder, SWT.NONE);
		tabItemPresentation.setText("Präsentation ");

		tableClickerQuestions = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tableClickerQuestions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		// tableClickerQuestions.setBounds(0, 0, 85, 45);
		tableClickerQuestions.setHeaderVisible(true);
		tableClickerQuestions.setLinesVisible(true);

		GridData gd_tableClickerQuestions = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		tableClickerQuestions.setLayoutData(gd_tableClickerQuestions);

		tabItemPresentation.setControl(tableClickerQuestions);

		tabItemDetailsAndEdit = new TabItem(tabFolder, SWT.NONE);
		tabItemDetailsAndEdit.setText("Details & Bearbeiten");
	}


	private void createFooter(Composite parent) {
		Composite footerComp = new Composite(parent, SWT.NONE);
		footerComp.setLayout(createRowLayout(SWT.HORIZONTAL, true));
		// createControlButtons( footerComp );
	}

	private void setFocus() {
		Util.setFocus(clickerTree);
	}

}
