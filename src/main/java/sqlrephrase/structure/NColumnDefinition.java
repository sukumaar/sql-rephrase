package sqlrephrase.structure;

import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.select.PlainSelect;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mainly created to include equals and hashcode for object of parent class
 *
 * @author sukumaar
 */
public class NColumnDefinition extends ColumnDefinition {

    private NColumnDefinition() {
    }

    public static NColumnDefinition EMPTY() {
        return new NColumnDefinition();
    }

    public static List<NColumnDefinition> createArrayWithColumnDefinition(ColumnDefinition... columnDefinition) {
        List nColumnDefinitionList = new ArrayList<NColumnDefinition>();
        for (ColumnDefinition cd : columnDefinition) {
            nColumnDefinitionList.add(NColumnDefinition.createWithColumnDefinition(cd));
        }
        return nColumnDefinitionList;
    }

    public static ColumnDefinition createWithColumnDefinition(ColumnDefinition columnDefinition) {
        return new NColumnDefinition()
                .withColumnName(columnDefinition.getColumnName())
                .withColDataType(NColDataType.createWithColDataType(columnDefinition.getColDataType()))
                .withColumnSpecStrings(columnDefinition.getColumnSpecStrings());
    }

    public NColumnDefinition withColumnSpecStrings(List<String> list) {
        this.setColumnSpecStrings(list);
        return this;
    }

    public NColumnDefinition withColDataType(ColDataType type) {
        this.setColDataType(type);
        return this;
    }

    public NColumnDefinition withColumnName(String string) {
        this.setColumnName(string);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NColumnDefinition that = (NColumnDefinition) o;
        return this.getColumnName().equals(that.getColumnName()) &&
                this.getColDataType().equals(that.getColDataType()) &&
                Objects.equals(this.getColumnSpecStrings(), that.getColumnSpecStrings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getColumnName(), this.getColDataType(), this.getColumnSpecStrings());
    }

    @Override
    public String toString() {
        return this.getColumnName() + " " + this.getColDataType() + (this.getColumnSpecStrings() != null ? " " + PlainSelect.
                getStringList(this.getColumnSpecStrings(), false, false) : "");
    }
}
