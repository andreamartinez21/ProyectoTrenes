package ventanas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public static DefaultTableCellRenderer textRenderer = new DefaultTableCellRenderer() {

		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			if (row % 2 == 0) {
				cell.setBackground(new Color(224, 224, 224));
			} else {
				cell.setBackground(Color.WHITE);
			}

			setHorizontalAlignment(SwingConstants.CENTER);

			return cell;
		}
	};
	
	public static DefaultTableCellRenderer numRenderer = new DefaultTableCellRenderer() {

		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			
			if (row % 2 == 0) {
				cell.setBackground(new Color(224, 224, 224));
			} else {
				cell.setBackground(Color.WHITE);
			}

			setHorizontalAlignment(SwingConstants.RIGHT);

			return cell;
		}
	};
}
