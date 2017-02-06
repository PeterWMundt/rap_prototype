package ch.ethz.id.sws.eduapp.administration.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Tree;

public class PartAdministrationUtil {

	private static final int DEFAULT_SPACE = 10;

	public final static int MODERN_STYLE = 0;

	public final static int TABLE_STYLE = 1;

	public static final class TreeLabelProvider extends CellLabelProvider {
		private static final String ICON_GREENDOT = "greendot.gif";
		private static final String ICON_WORLD = "world.gif";
		private static final String ICON_EARTH = "earth-icon.png";

		private static final int COLUMN_TEXT = 0;
		private static final int COLUMN_OFFSET = 2;
		private static final int COLUMN_TIMEZONE = 1;

		private final Device device;
		private final Image continentImage;
		private final Image cityImage;
		private final Font cityFont;
		private final Font continentFont;
		private final Color timezoneTextColor;
		private final Color offsetTextColor;
		private final int style;

		TreeLabelProvider(Device device, int style) {
			this.device = device;
			this.style = style;
			this.cityFont = createFont("Times New Roman", 13, SWT.NONE);
			this.continentFont = createFont("Arial", 14, SWT.ITALIC);
			this.timezoneTextColor = new Color(device, 239, 41, 41);
			this.offsetTextColor = new Color(device, 252, 175, 62);
			if (style == MODERN_STYLE) {
				this.continentImage = ImageUtil.getImage(device, ICON_EARTH);
				this.cityImage = ImageUtil.getImage(device, ICON_GREENDOT);
			} else {
				this.continentImage = ImageUtil.getImage(device, ICON_WORLD);
				this.cityImage = ImageUtil.getImage(device, ICON_GREENDOT);
			}
		}

		@Override
		public void update(ViewerCell cell) {
			TreeObject treeObject = (TreeObject) cell.getElement();
			int columnIndex = cell.getColumnIndex();
			switch (columnIndex) {
			case COLUMN_TEXT:
				updateName(cell, treeObject);
				break;
			case COLUMN_TIMEZONE:
				updateTimeZone(cell, treeObject);
				break;
			case COLUMN_OFFSET:
				updateOffset(cell, treeObject);
				break;
			}
		}

		@Override
		public String getToolTipText(Object element) {
			String result = "";
			// if (element instanceof City) {
			// City city = (City) element;
			// String name = city.getTitle();
			// String timeZone = city.getTimeZone();
			// String utcOffset = getUTCOffset(city);
			// result = name + " (" + timeZone + ", " + utcOffset + ")";
			// }
			return result;
		}

		private void updateName(ViewerCell cell, TreeObject treeObject) {
			cell.setText(treeObject.name);
			if (this.style == MODERN_STYLE) {
				// if (treeObject instanceof City) {
				// cell.setFont(cityFont);
				// }
				cell.setFont(this.continentFont);
			}
			cell.setImage(this.cityImage); // treeObject instanceof City ? cityImage
										// : continentImage);
		}

		private void updateTimeZone(ViewerCell cell, TreeObject treeObject) {
			// if (treeObject instanceof City) {
			// City city = (City) treeObject;
			// cell.setText(city.getTimeZone());
			// if (style == TABLE_STYLE) {
			// cell.setForeground(timezoneTextColor);
			// }
			// }
		}

		private void updateOffset(ViewerCell cell, TreeObject treeObject) {
			// if (treeObject instanceof City) {
			// if (style == TABLE_STYLE) {
			// cell.setForeground(offsetTextColor);
			// }
			// City city = (City) treeObject;
			// cell.setText(getUTCOffset(city));
			// }
		}

		private Font createFont(String name, int size, int style) {
			FontData fontData = new FontData(name, size, style);
			return new Font(this.device, fontData);
		}

		// private static String getUTCOffset(City city) {
		// String sign = city.getOffset() >= 0 ? "-" : "";
		// return "UTC " + sign + String.valueOf(city.getOffset());
		// }
	}

	public static class TreeContentProvider implements ITreeContentProvider {

		@Override
		public Object[] getElements(Object parent) {
			return getChildren(parent);
		}

		@Override
		public Object getParent(Object child) {
			Object result = null;
			// if( child instanceof City ) {
			// result = ( ( City )child ).getParent();
			// }
			return result;
		}

		@Override
		public Object[] getChildren(Object parent) {
			Object[] result = new Object[0];
			if (parent instanceof TreeObject) {
				result = ((TreeObject) parent).getChildren();
			}
			return result;
		}

		@Override
		public boolean hasChildren(Object parent) {
			boolean result = false;
			if (parent instanceof TreeObject) {
				result = ((TreeObject) parent).hasChildren();
			}
			return result;
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public void dispose() {
		}
	}

	public static class TreeObject {
		private final List<TreeObject> children;
		private String name;
		private TreeObject parent;

		public TreeObject(String name) {
			setName(name);
			this.children = new ArrayList<TreeObject>();
		}

		public void setParent(TreeObject parent) {
			this.parent = parent;
		}

		public TreeObject getParent() {
			return this.parent;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTitle() {
			return this.name;
		}

		public void addChild(TreeObject child) {
			this.children.add(child);
			child.setParent(this);
		}

		public void removeChild(TreeObject child) {
			this.children.remove(child);
			child.setParent(null);
		}

		public TreeObject[] getChildren() {
			TreeObject[] result = new TreeObject[this.children.size()];
			this.children.toArray(result);
			return result;
		}

		public boolean hasChildren() {
			return this.children.size() > 0;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	public static class TreeClicker extends TreeObject  {

		public TreeClicker(String name) {
			super(name);
		}
	}

	public static GridData createFillData() {
		return new GridData(SWT.FILL, SWT.FILL, true, true);
	}

	public static GridData createFillDataWithHorizontalSpan(int horizontalSpan ) {
		GridData gridData = createFillData();
		gridData.horizontalSpan = horizontalSpan;
		return gridData;
	}

	public static FillLayout createFillLayout(int type, boolean setMargin) {
		FillLayout result = new FillLayout(type);
		if (setMargin) {
			result.marginWidth = DEFAULT_SPACE;
			result.marginHeight = DEFAULT_SPACE;
		}
		return result;
	}

	public static GridLayout createMainLayout(int numColumns) {
		GridLayout result = new GridLayout(numColumns, true);
		result.marginWidth = 0;
		result.marginHeight = 0;
		result.marginTop = 0;
		result.verticalSpacing = 0;
		result.horizontalSpacing = 60;
		return result;
	}

	public static RowLayout createRowLayout(int type, boolean setMargin) {
		RowLayout result = new RowLayout(type);
		result.marginTop = 0;
		result.marginLeft = 0;
		result.marginHeight = 0;
		if (setMargin) {
			result.marginBottom = DEFAULT_SPACE;
			result.marginWidth = DEFAULT_SPACE;
		} else {
			result.marginBottom = 0;
			result.marginWidth = 0;
		}
		return result;
	}

	public static void setFocus(TreeViewer treeViewer, int index) {
		Tree tree = treeViewer.getTree();
		tree.forceFocus();
		tree.select(tree.getItem(index));
	}

	private PartAdministrationUtil() {
		// prevent instantiation
	}

}
