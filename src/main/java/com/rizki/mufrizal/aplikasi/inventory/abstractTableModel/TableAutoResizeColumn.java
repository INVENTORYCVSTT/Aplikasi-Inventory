/**
 * Copyright (C) 2016 Rizki Mufrizal (https://rizkimufrizal.github.io/) (mufrizalrizki@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rizki.mufrizal.aplikasi.inventory.abstractTableModel;

import java.util.Enumeration;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 20, 2016
 * @Time 1:34:53 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.abstractTableModel
 *
 */
public class TableAutoResizeColumn {

    public void autoResizeColumn(JTable jTable) {
        JTableHeader jTableHeader = jTable.getTableHeader();
        int rowCount = jTable.getRowCount();

        final Enumeration enumeration = jTable.getColumnModel().getColumns();
        while (enumeration.hasMoreElements()) {
            TableColumn tableColumn = (TableColumn) enumeration.nextElement();
            int col = jTableHeader.getColumnModel().getColumnIndex(tableColumn.getIdentifier());
            int width = (int) jTable.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(jTable, tableColumn.getIdentifier(), Boolean.FALSE, Boolean.FALSE, -1, col).getPreferredSize().getWidth();

            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) jTable.getCellRenderer(row, col).getTableCellRendererComponent(jTable, jTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            jTableHeader.setResizingColumn(tableColumn);
            tableColumn.setWidth(width + jTable.getIntercellSpacing().width);
        }
    }

}
