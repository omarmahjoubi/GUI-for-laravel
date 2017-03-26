package com.test.guielement;



import java.awt.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.test.Route ;



public class DynamicModel extends AbstractTableModel
	{
	private final ArrayList<Route> routes = new ArrayList<Route>();

	private final String[] entetes = { "Uri", "Methode", "action", "middleware" };

	public DynamicModel() {
		super();

	}

	public int getRowCount() {
		return routes.size();
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return routes.get(rowIndex).getUri();
		case 1:
			return routes.get(rowIndex).getMethod();
		case 2:
			return routes.get(rowIndex).getAction();
		case 3:
			return routes.get(rowIndex).getMiddleware();
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void addRoute(Route route) {
		routes.add(route);

		fireTableRowsInserted(routes.size() - 1, routes.size() - 1);
	}

	public void removeRoute(int rowIndex) {
		routes.remove(rowIndex);

		fireTableRowsDeleted(rowIndex, rowIndex);
	}
}

