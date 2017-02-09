package ch.ethz.id.sws.eduapp.administration.part;

import static ch.ethz.id.sws.eduapp.administration.part.PartAdministrationUtil.MODERN_STYLE;
import static ch.ethz.id.sws.eduapp.administration.part.PartAdministrationUtil.createFillDataWithHorizontalSpan;
import static ch.ethz.id.sws.eduapp.administration.part.PartAdministrationUtil.createFillLayout;
import static ch.ethz.id.sws.eduapp.administration.part.PartAdministrationUtil.createMainLayout;
import static ch.ethz.id.sws.eduapp.administration.part.PartAdministrationUtil.createRowLayout;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.id.sws.eduapp.administration.MessageRegistry;
import ch.ethz.id.sws.eduapp.administration.part.PartAdministrationUtil.TreeClicker;
import ch.ethz.id.sws.eduapp.administration.part.PartAdministrationUtil.TreeContentProvider;
import ch.ethz.id.sws.eduapp.administration.part.PartAdministrationUtil.TreeLabelProvider;
import ch.ethz.id.sws.eduapp.administration.part.PartAdministrationUtil.TreeObject;
import ch.ethz.id.sws.eduapp.data.entity.Semester;
import ch.ethz.id.sws.eduapp.data.service.EduAppDBHelperFactory;

public class PartAdministration {

	private static final Logger LOG = LoggerFactory.getLogger(PartAdministration.class);
	
	private static final int COLUMNS_NUMBER = 4;
	
	private Composite topLeft;
	private Composite topRight;
	private Composite footer;

	private TreeViewer clickerBackchannelTree;
	private TableViewer tableViewerSemester;
	private Table tableClickerQuestions;
	private TabFolder tabFolder;
	private TabItem tabItemPresentation;
	private TabItem tabItemDetailsAndEdit;

	@PostConstruct
	public void postConstruct(final Composite parent, final EMenuService menuService, final EduAppDBHelperFactory dbHelperFactory, final MessageRegistry messageRegistry) {
		LOG.debug("postConstruct(..) START"); //$NON-NLS-1$
		System.out.println(
				"PartAdministration::postConstruct(..) START *************************************************** ");

		parent.setLayout(createMainLayout(COLUMNS_NUMBER));

		topLeft = createTopLeft(parent, 1);

		this.topRight = createTopRight(parent, COLUMNS_NUMBER - 1 );

		this.footer = createFooter(parent);

		this.clickerBackchannelTree = createClickerBackchannelTree(this.topLeft, createClickerModel(dbHelperFactory));
		this.tableViewerSemester = createTableViewerSemester(this.topLeft, dbHelperFactory);		
		createClickerQueryList(this.topRight);
		
		setFocus();
	}

	private Composite createTopLeft(final Composite parent, int horizontalSpan) {		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(createFillDataWithHorizontalSpan(horizontalSpan));
		composite.setLayout(createFillLayout(SWT.VERTICAL, true));
		return composite;
	}

	private Composite createTopRight(final Composite parent, int horizontalSpan) {
		Composite topRight = new Composite(parent, SWT.NONE);
		topRight.setLayoutData(createFillDataWithHorizontalSpan(horizontalSpan)); 
		topRight.setLayout(new GridLayout(1, false));
		return topRight;
	}

	private TreeViewer createClickerBackchannelTree(Composite parent, TreeObject model) {
		Tree tree = new Tree(parent, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		TreeViewer result = new TreeViewer(tree);
		result.setContentProvider(new TreeContentProvider());
		TreeLabelProvider labelProvider = new TreeLabelProvider(parent.getDisplay(), MODERN_STYLE);
		result.setLabelProvider(labelProvider);
		result.setInput(model);
		result.expandAll();
		return result;
	}

	private static TreeObject createClickerModel(EduAppDBHelperFactory dbHelperFactory) {
		TreeObject result = new TreeObject("");
		TreeObject clickers = new TreeObject("Clicker");
		result.addChild(clickers);
		clickers.addChild(new TreeClicker("EduApp Kurs"));
		clickers.addChild(new TreeClicker("EduApp Kurs"));
		clickers.addChild(new TreeClicker("Analysis I"));
		TreeObject backchannels = new TreeObject("Backchannel");
		result.addChild(backchannels);
		backchannels.addChild(new TreeClicker("EduApp Kurs"));
		backchannels.addChild(new TreeClicker("Analysis I"));
		backchannels.addChild(new TreeClicker("Physik III"));
		backchannels.addChild(new TreeClicker("Chemie II"));
		return result;
	}

	private TableViewer createTableViewerSemester(Composite parent, EduAppDBHelperFactory dbHelperFactory) {
		// define the TableViewer
		this.tableViewerSemester = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
		                        | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		createTableViewerSemesterColumns(this.tableViewerSemester);

		// make lines and header visible
		final Table table = this.tableViewerSemester.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		// set the content provider
		this.tableViewerSemester.setContentProvider(ArrayContentProvider.getInstance());
		
		// provide the input to the viewer
		// setInput() calls getElements() on the content provider instance
		this.tableViewerSemester.setInput(getListSemester());
		
		return this.tableViewerSemester;
	}

	private List<Semester> getListSemester() {
		List<Semester> semesters = new ArrayList<>();
		semesters.add(makeSemester("FS2017"));
		semesters.add(makeSemester("HS2016"));
		semesters.add(makeSemester("FS2016"));
		semesters.add(makeSemester("HS2015"));
		return semesters;
	}

	private Semester makeSemester(String semKez) {
		Semester semesterFS2017  = new Semester();
		semesterFS2017.setSemKez(semKez);
		return semesterFS2017;
	}

	private void createTableViewerSemesterColumns(TableViewer viewer ) {
		TableViewerColumn semKezColumn = createTableViewerColumn(viewer, "Semkez", 130 );
		semKezColumn.setLabelProvider(new ColumnLabelProvider(){

			/* (non-Javadoc)
			 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
			 */
			@Override
			public Image getImage(Object element) {
				return null;
			}

			/* (non-Javadoc)
			 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
			 */
			@Override
			public String getText(Object element) {
				if(element instanceof Semester){
					return ((Semester)element).getSemKez();					
				} 
				return super.getText(element);
			}

		});
	}

	private TableViewerColumn createTableViewerColumn(TableViewer viewer, String title, int width ) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(width);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	private void createClickerQueryList(Composite topRight) {
		Label lblClickerfagenEduapp = new Label(topRight, SWT.NONE);
		lblClickerfagenEduapp.setText("Clickerfagen - EduApp Kurs");

		this.tabFolder = new TabFolder(topRight, SWT.NONE);
		this.tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		this.tabFolder.setLayoutData(gd_tabFolder);

		this.tabItemPresentation = new TabItem(this.tabFolder, SWT.NONE);
		this.tabItemPresentation.setText("Präsentation ");

		this.tableClickerQuestions = new Table(this.tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		this.tableClickerQuestions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		this.tableClickerQuestions.setHeaderVisible(true);
		this.tableClickerQuestions.setLinesVisible(true);

		GridData gd_tableClickerQuestions = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		this.tableClickerQuestions.setLayoutData(gd_tableClickerQuestions);

		this.tabItemPresentation.setControl(this.tableClickerQuestions);

		this.tabItemDetailsAndEdit = new TabItem(this.tabFolder, SWT.NONE);
		this.tabItemDetailsAndEdit.setText("Details & Bearbeiten");
	}

	private Composite createFooter(Composite parent) {
		Composite footer = new Composite(parent, SWT.NONE);
		footer.setLayout(createRowLayout(SWT.HORIZONTAL, true));
		return footer;
	}

	private void setFocus() {
		PartAdministrationUtil.setFocus(this.clickerBackchannelTree, 0);
	}

}
