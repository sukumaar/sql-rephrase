package sqlrephrase.structure;

import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.select.PlainSelect;

import java.util.List;
import java.util.Objects;

/**
 * Mainly created to include equals and hashcode for object of parent class
 * @author sukumaar
 */
public class NColDataType extends ColDataType {

    private NColDataType() {
    }

    public static NColDataType EMPTY() {
        return new NColDataType();
    }

    public static ColDataType createWithColDataType(ColDataType colDataType) {
        return new NColDataType()
                .withDataType(colDataType.getDataType())
                .withArgumentsStringList(colDataType.getArgumentsStringList())
                .withArrayData(colDataType.getArrayData())
                .withCharacterSet(colDataType.getCharacterSet());
    }

    public NColDataType withArgumentsStringList(List<String> list) {
        this.setArgumentsStringList(list);
        return this;
    }

    public NColDataType withDataType(String string) {
        this.setDataType(string);
        return this;
    }

    public NColDataType withCharacterSet(String characterSet) {
        this.setCharacterSet(characterSet);
        return this;
    }

    public NColDataType withArrayData(List<Integer> arrayData) {
        this.setArrayData(arrayData);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColDataType that = (ColDataType) o;
        return this.getDataType().equals(that.getDataType()) &&
                Objects.equals(this.getArgumentsStringList(), that.getArgumentsStringList()) &&
                Objects.equals(this.getCharacterSet(), that.getCharacterSet()) &&
                Objects.equals(this.getArrayData(), that.getArrayData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getDataType(), this.getArgumentsStringList(), this.getCharacterSet(), this.getArrayData());
    }

    @Override
    public String toString() {
        StringBuilder arraySpec = new StringBuilder();
        for (Integer item : this.getArrayData()) {
            arraySpec.append("[");
            if (item != null) {
                arraySpec.append(item);
            }
            arraySpec.append("]");
        }
        return this.getDataType()
                + (this.getArgumentsStringList() != null ? " " + PlainSelect.
                getStringList(this.getArgumentsStringList(), true, true) : "")
                + arraySpec.toString()
                + (this.getCharacterSet() != null ? " CHARACTER SET " + this.getCharacterSet() : "");
    }
}
