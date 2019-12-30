package sqlrephrase.structure;

import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.select.PlainSelect;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author sukumaar
 */
public class NColumnDefinition {

    private String columnName;
    private NColDataType colDataType;
    private List<String> columnSpecStrings;

    private NColumnDefinition() {
    }

    public static NColumnDefinition EMPTY() {
        return new NColumnDefinition();
    }

    public static List<NColumnDefinition> createArrayWithColumnDefinition(NColumnDefinition... nColumnDefinition) {
        List nColumnDefinitionList = new ArrayList<NColumnDefinition>();
        for (NColumnDefinition cd : nColumnDefinition) {
            nColumnDefinitionList.add(cd);
        }
        return nColumnDefinitionList;
    }

    public static List<NColumnDefinition> createArrayWithColumnDefinition(ColumnDefinition... columnDefinition) {
        List nColumnDefinitionList = new ArrayList<NColumnDefinition>();
        for (ColumnDefinition cd : columnDefinition) {
            nColumnDefinitionList.add(NColumnDefinition.createWithColumnDefinition(cd));
        }
        return nColumnDefinitionList;
    }

    public static NColumnDefinition createWithColumnDefinition(ColumnDefinition columnDefinition) {
        return new NColumnDefinition()
                .setColumnName(columnDefinition.getColumnName())
                .setColDataType(NColDataType.createWithColDataType(columnDefinition.getColDataType()))
                .setColumnSpecStrings(columnDefinition.getColumnSpecStrings());
    }

    /**
     * Use only while cloning
     *
     * @param nColumnDefinition
     * @return
     */
    public static NColumnDefinition createWithNColumnDefinition(NColumnDefinition nColumnDefinition) {
        return new NColumnDefinition()
                .setColumnName(nColumnDefinition.getColumnName())
                .setColDataType(nColumnDefinition.getColDataType())
                .setColumnSpecStrings(nColumnDefinition.getColumnSpecStrings());
    }

    public List<String> getColumnSpecStrings() {
        return columnSpecStrings;
    }

    public NColumnDefinition setColumnSpecStrings(List<String> list) {
        columnSpecStrings = list;
        return this;
    }

    public NColDataType getColDataType() {
        return colDataType;
    }

    public NColumnDefinition setColDataType(NColDataType type) {
        colDataType = type;
        return this;
    }

    public String getColumnName() {
        return columnName;
    }

    public NColumnDefinition setColumnName(String string) {
        columnName = string;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NColumnDefinition that = (NColumnDefinition) o;
        return columnName.equals(that.columnName) &&
                colDataType.equals(that.colDataType) &&
                Objects.equals(columnSpecStrings, that.columnSpecStrings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnName, colDataType, columnSpecStrings);
    }

    @Override
    public String toString() {
        return columnName + " " + colDataType + (columnSpecStrings != null ? " " + PlainSelect.
                getStringList(columnSpecStrings, false, false) : "");
    }
}
