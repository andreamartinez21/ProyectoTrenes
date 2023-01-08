package ventanas;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import clases.Billete;

public class BilleteTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private final List<String> headers = Arrays.asList("ORIGEN", "DESTINO", "LOCALIZADOR(ES)", "PRECIO", "FECHA(S)");
	private List<Billete> billetes;
	
	public BilleteTableModel(List<Billete> billetes) {
		this.billetes = billetes;
	}
	
	@Override
	public String getColumnName(int column) {
		return headers.get(column);
	}
	
	@Override
	public int getRowCount() {
		return billetes.size();
	}

	@Override
	public int getColumnCount() {
		return headers.size();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		// all cells false
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Billete billete = billetes.get(rowIndex);
		switch (columnIndex) {
			case 0: return billete.getViajeIda().getOrigen();
			case 1: return billete.getViajeIda().getDestino();
			case 2: return billete.getViajeIda().getLocalizador() + " - " + billete.getViajeVuelta().getLocalizador();
			case 3: return VentanaConfirmacionCompra.formato1.format(billete.getPrecio()) + " €";
			case 4: return billete.getViajeIda().getFecha() + " - " + billete.getViajeVuelta().getFecha();
			default: return null;
		}
	}
}
