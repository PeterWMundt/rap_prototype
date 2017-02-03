package ch.ethz.id.sws.eduapp.clicker.part;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.core.services.nls.MessageFunction;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.id.sws.eduapp.app.util.EduAppHelper;
import ch.ethz.id.sws.eduapp.clicker.ClickerConstants;
import ch.ethz.id.sws.eduapp.clicker.MessageRegistry;
import ch.ethz.id.sws.eduapp.clicker.Messages;
import ch.ethz.id.sws.eduapp.clicker.handler.DeleteHandler;
import ch.ethz.id.sws.eduapp.data.entity.Clicker;
import ch.ethz.id.sws.eduapp.data.service.EduAppDBHelperFactory;

public class ClickerListPart {

    private static final Logger LOG = LoggerFactory.getLogger(ClickerListPart.class);
    
    protected static final int COLUMNS = 1;
    private static final int COL_SMALL = 150;
    private static final int COL_MIDDLE = 300;
    private static final int COL_LARGE = 600;
    
    @SuppressWarnings("rawtypes")
    private static final MessageFunction[] COL_TITLES = { //
    		m -> ((Messages) m).PartClickerList_colTitle //
//    		, m -> ((Messages) m).colName //
//    		, m -> ((Messages) m).colAdministrator // 
    		};
    private static final int[] COL_WIDTHS = { COL_MIDDLE, COL_LARGE, COL_SMALL };

    private enum Sorter {
        TITEL(m -> m.getTitle()) // 
//        , NAME(m -> m.getAdminName()) //
//        , ADMIN(m -> m.getIsAdmin().toString()) //
        ;

        private final Function<? super Clicker, ? extends String> func;

        Sorter(final Function<? super Clicker, ? extends String> func) {
            this.func = func;
        }

        public String getValue(final Clicker clicker) {
            return this.func.apply(clicker).toLowerCase();
        }
    }

    
    @Inject
    private ESelectionService selectionService;

    @Inject
    private IEventBroker broker;

    @Inject
    private MessageRegistry messageRegistry;

    private TableViewer viewer;

	private ClickerFilterByText filterByText = new ClickerFilterByText();

	@PostConstruct
	public void postConstruct(final Composite parent, final EMenuService menuService, 
			final EduAppDBHelperFactory dbHelperFactory, final MessageRegistry messageRegistry) {
        LOG.debug("postConstruct(..) START");  //$NON-NLS-1$
        System.out.println("ClickerListPart:postConstruct(..) START *************************************************** ");	 //$NON-NLS-1$

        this.messageRegistry = messageRegistry;

        createTable(parent, menuService, dbHelperFactory);
	}
	
	// --- 

	private void createTable(Composite parent, EMenuService menuService, EduAppDBHelperFactory dbHelperFactory) {
        parent.setLayout(GridLayoutFactory.fillDefaults().create());
        parent.setLayoutData(GridDataFactory.fillDefaults().create());

        final Composite filterLine = new Composite(parent, SWT.NONE);
        GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 2).applyTo(filterLine);
        GridDataFactory.fillDefaults().grab(true, false).applyTo(filterLine);
        EduAppHelper.createTextFilter(filterLine, new ModifyListener() {
            @Override
            public void modifyText(final ModifyEvent event) {
                final Text text = (Text) event.widget;
                ClickerListPart.this.filterByText .setText(text.getText());
                ClickerListPart.this.viewer.refresh();
            }
        }, new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent event) {
                if (event.keyCode == 13 || event.keyCode == SWT.ESC || event.keyCode == SWT.ARROW_DOWN) {
                    handleSelection(ClickerListPart.this.viewer, event.keyCode == SWT.ARROW_DOWN);
                    ClickerListPart.this.viewer.getTable().forceFocus();
                }
            }

        });
   		
        createAdminFilter(filterLine);

        this.viewer = makeTable(parent, dbHelperFactory);
        menuService.registerContextMenu(this.viewer.getControl(), ClickerConstants.ID_POPUP);
    }

	private Button createAdminFilter(Composite filterLine) {
        final Button adminFilter = new Button(filterLine, SWT.CHECK);
        //this.messageRegistry.register(adminFilter::setText, m -> m.PartClickerList_lblFilter);
        adminFilter.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent event) {
                final Button check = (Button) event.widget;
                //PartClickerList.this.filterByAdmin.setAdminOnly(check.getSelection());
                ClickerListPart.this.viewer.refresh();
            }
        });
        return adminFilter;
	}

	private void handleSelection(TableViewer viewer, boolean b) {
		// TODO Auto-generated method stub
		
	}

	private TableViewer makeTable(Composite parent, EduAppDBHelperFactory dbHelperFactory) {
	       final TableViewer out = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
	        out.setContentProvider(new ViewContentProvider(dbHelperFactory));
	        out.setLabelProvider(new ViewLabelProvider());

	        out.addSelectionChangedListener(new ISelectionChangedListener() {
	            @Override
	            public void selectionChanged(final SelectionChangedEvent event) {
	                final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
	                ClickerListPart.this.selectionService
	                .setSelection(selection.size() == 1 ? selection.getFirstElement() : selection.toArray());
	            }
	        });

	        out.getControl().addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(final KeyEvent event) {
	                if (event.keyCode == SWT.DEL) {
	                    DeleteHandler.deleteClicker((Clicker) ClickerListPart.this.selectionService.getSelection(),
	                            ClickerListPart.this.broker, parent.getShell(), dbHelperFactory,
	                            ClickerListPart.this.messageRegistry.getMessages());
	                }
	            }
	        });

	        final Table table = out.getTable();
	        table.setHeaderVisible(true);
	        table.setLinesVisible(true);
	        table.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

	        out.setColumnProperties(initColumnProperties(table));
	        out.setInput(this);
	        out.addFilter(this.filterByText);
	        //out.addFilter(this.filterByAdmin);

	        return out;
	}
	
    private String[] initColumnProperties(Table table) {
        final String[] result = new String[COLUMNS];
        final Sorter[] sorters = Sorter.values();
        for (int i = 0; i < COLUMNS; i++) {
            result[i] = createTableCol(table, i, COL_TITLES[i], COL_WIDTHS[i], sorters[i]);
        }
        return result;
	}

    @SuppressWarnings("serial")
    private String createTableCol(final Table table, final int index, final MessageFunction<Messages> msgFunction,
            final int width,
            final Sorter sorter) {
        final TableColumn tableColumn = new TableColumn(table, SWT.NONE);
        this.messageRegistry.register(tableColumn::setText, msgFunction);
        tableColumn.setWidth(width);
        tableColumn.setMoveable(true);
        tableColumn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent event) {
                sortByColumn((TableColumn) event.widget, sorter, false);
            }
        });
        return String.format("col_%s", index);
    }

    private void sortByColumn(final TableColumn column, final Sorter sorter, final boolean reset) {
        final int sortDirection = updateSortDirection(column);
        sort(this.viewer, sorter, sortDirection == SWT.DOWN);
        handleSelection(this.viewer, reset);
    }

    @SuppressWarnings("unchecked")
    private static void sort(final TableViewer viewer, final Sorter sorter, final boolean ascending) {
        if ((viewer.getControl().getStyle() & SWT.VIRTUAL) != 0) {
            ((List<Clicker>) viewer.getInput())
            .sort((final Clicker m1, final Clicker m2) -> sorter.getValue(m1).compareTo(sorter.getValue(m2)));
            viewer.refresh();
        } else {
            viewer.setComparator(new ElementComparator(sorter, ascending));
        }
    }

    @SuppressWarnings("serial")
    private final static class ElementComparator extends ViewerComparator implements Comparator<Clicker> {
        private final Sorter sorter;
        private final boolean ascending;

        public ElementComparator(final Sorter sorter, final boolean ascending) {
            this.sorter = sorter;
            this.ascending = ascending;
        }

        @Override
        public int compare(final Clicker m1, final Clicker m2) {
            final int result = this.sorter.getValue(m1).compareTo(this.sorter.getValue(m2));
            return this.ascending ? result * -1 : result;
        }

        @Override
        public int compare(final Viewer viewer, final Object e1, final Object e2) {
            return compare((Clicker) e1, (Clicker) e2);
        }

        @Override
        public boolean isSorterProperty(final Object element, final String property) {
            return true;
        }
    }

    private static int updateSortDirection(final TableColumn column) {
        final Table table = column.getParent();
        if (column == table.getSortColumn()) {
            if (table.getSortDirection() == SWT.UP) {
                table.setSortDirection(SWT.DOWN);
            } else {
                table.setSortDirection(SWT.UP);
            }
        } else {
            table.setSortColumn(column);
            table.setSortDirection(SWT.DOWN);
        }
        return table.getSortDirection();
    }

	@SuppressWarnings("serial")
    private class ViewContentProvider implements IStructuredContentProvider {
        private EduAppDBHelperFactory dbHelperFactory;

        public ViewContentProvider(final EduAppDBHelperFactory dbHelperFactory) {
            this.dbHelperFactory = dbHelperFactory;
        }

        @Override
        public Object[] getElements(final Object inputElement) {
            final List<Clicker> admins = this.dbHelperFactory.createDBHelper().getClickers();
            final Object[] result = new Object[admins.size()];
            admins.toArray(result);
            return result;
        }

        @Override
        public void dispose() {
            this.dbHelperFactory = null;
        }

        @Override
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            // nothing to do
        }
    }

    @SuppressWarnings("serial")
    private class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {

        @Override
        public String getColumnText(final Object element, final int columnIndex) {
            final Clicker clicker = (Clicker) element;
            switch (columnIndex) {
            case 0:
                return clicker.getTitle();
//            case 1:
//                return admin.getAdminName();
//            case 2:
//                return admin.getIsAdmin().intValue() == 1 ? "ja" : "";
            default:
                break;
            }
            return "-";
        }

        @Override
        public Image getColumnImage(final Object element, final int columnIndex) {
            return null;
        }

    }


    @SuppressWarnings("serial")
    private static final class ClickerFilterByText extends ViewerFilter {
        private String text;

        protected void setText(final String textInput) {
            this.text = textInput;
        }

        @Override
        public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
            boolean result = true;
            final Clicker clicker = (Clicker) element;
            if (this.text != null && this.text.length() > 0) {
                final String lowerCaseText = this.text.toLowerCase();
                result = check(clicker.getTitle(), lowerCaseText);
//                if (!result) {
//                    result = check(clicker.getAdminName(), lowerCaseText);
//                }
            }
            return result;
        }

        private boolean check(final String value, final String compare) {
            return value == null ? false : value.toLowerCase().indexOf(compare) != -1;
        }

        @Override
        public boolean isFilterProperty(final Object element, final String property) {
            return true;
        }
    }


}
