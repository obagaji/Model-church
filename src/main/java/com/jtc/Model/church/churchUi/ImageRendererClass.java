package com.jtc.Model.church.churchUi;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class ImageRendererClass implements TableCellRenderer {
        private String columnName;
        private JTable setTable;

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column)
        {
            TableColumn tc = getSetTable().getColumn("member_photo");
            tc.setMinWidth(150);
            tc.setMaxWidth(150);
            getSetTable().setRowHeight(150);
            return (Component) value;
        }
        public void setString(String str)
        {
            columnName = str;
        }
        public String getString()
        {
            return columnName;
        }
        public JTable getSetTable() {
            return setTable;
        }
    public void setSetTable(JTable setTable) {
            this.setTable = setTable;
    }
}

