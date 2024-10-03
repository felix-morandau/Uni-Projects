package presentation.controller;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Controller for managing table operations.
 *
 * @param <T> The type of objects displayed in the table.
 */
public class TableController<T> {

    /**
     * Fills a table model with data from a list of objects.
     *
     * @param objects The list of objects to be displayed in the table.
     * @param model   The table model to be filled with data.
     */
    public void fillTable(List<T> objects, DefaultTableModel model) {
        model.setRowCount(0);
        model.setColumnCount(0);

        if (!objects.isEmpty()) {
            T element = objects.get(0);

            for (Field field : element.getClass().getDeclaredFields()) {
                model.addColumn(field.getName());
            }
        }

        for (T object : objects) {
            Object[] rowData = new Object[model.getColumnCount()];
            int columnIndex = 0;

            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    rowData[columnIndex++] = field.get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            model.addRow(rowData);
        }
    }
}

