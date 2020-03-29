
package com.ephoenix.adb.window;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.ephoenix.adb.Device;

public class AndroidDevicesTableModel extends AbstractTableModel {
	private static final int COLUMN_DEVICE = 0;
	private static final int COLUMN_STATE = 1;
	private static final int COLUMN_ACTION = 2;
	private static final int COLUMN_COUNT = 3;
	private static final String DEVICE = "Device";
	private static final String STATE = "State";
	private static final String ACTION = "Action";
	private static final String CONNECTED = "Connected";
	private static final String DISCONNECTED = "Disconnected";
	private final List<Device> devices = new ArrayList<Device>();

	@Override
	public String getColumnName(int column) {
		String value = null;
		switch (column) {
		case COLUMN_DEVICE:
			value = DEVICE;
			break;
		case COLUMN_STATE:
			value = STATE;
			break;
		case COLUMN_ACTION:
			value = ACTION;
			break;
		default:
			return value;
		}
		return value;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class value = Object.class;
		switch (columnIndex) {
		case COLUMN_DEVICE:
			value = String.class;
			break;
		case COLUMN_STATE:
			value = String.class;
			break;
		default:
			return value;
		}
		return value;
	}

	@Override
	public int getRowCount() {
		return devices.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_COUNT;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Device obj = devices.get(rowIndex);
		Object value = null;
		switch (columnIndex) {
		case COLUMN_DEVICE:
			value = obj.toString();
			break;
		case COLUMN_STATE:
			value = obj.isConnected() ? CONNECTED : DISCONNECTED;
			break;
		case COLUMN_ACTION:
			break;
		default:
			return value;
		}
		return value;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == COLUMN_ACTION) {
			fireTableCellUpdated(rowIndex, columnIndex);
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == COLUMN_ACTION;
	}

	public void clear() {
		if (devices != null) {
			devices.clear();
		}
	}

	public void add(Device value) {
		int startIndex = getRowCount();
		devices.add(value);
		fireTableRowsInserted(startIndex, getRowCount() - 1);
	}

	public Device get(int index) {
		if (index >= 0 && index < devices.size()) {
			return devices.get(index);
		}
		return null;
	}
}