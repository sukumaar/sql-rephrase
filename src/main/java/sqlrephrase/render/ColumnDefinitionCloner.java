package sqlrephrase.render;

import net.sf.jsqlparser.statement.create.table.ColumnDefinition;

public class ColumnDefinitionCloner {
    private ColumnDefinitionCloner() {

    }

    public static ColumnDefinition clone(ColumnDefinition columnDefinition) {
        ColumnDefinition columnDefinitionNew = new ColumnDefinition();
        columnDefinitionNew.setColDataType(columnDefinition.getColDataType());
        columnDefinitionNew.setColumnSpecStrings(columnDefinition.getColumnSpecStrings());
        columnDefinitionNew.setColumnName(columnDefinition.getColumnName());
        return columnDefinitionNew;
    }
}
