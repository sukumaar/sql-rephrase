package sqlrephrase.structure;

import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.select.PlainSelect;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author sukumaar
 */
public class NColDataType {
    private String dataType;
    private List<String> argumentsStringList;
    private String characterSet;
    private List<Integer> arrayData = new ArrayList<Integer>();

    private NColDataType() {
    }

    public static NColDataType EMPTY() {
        return new NColDataType();
    }

    public static NColDataType createWithColDataType(ColDataType colDataType) {
        return new NColDataType()
                .setDataType(colDataType.getDataType())
                .setArgumentsStringList(colDataType.getArgumentsStringList())
                .setArrayData(colDataType.getArrayData())
                .setCharacterSet(colDataType.getCharacterSet());
    }

    public List<String> getArgumentsStringList() {
        return argumentsStringList;
    }

    public NColDataType setArgumentsStringList(List<String> list) {
        argumentsStringList = list;
        return this;
    }

    public String getDataType() {
        return dataType;
    }

    public NColDataType setDataType(String string) {
        dataType = string;
        return this;
    }

    public String getCharacterSet() {
        return characterSet;
    }

    public NColDataType setCharacterSet(String characterSet) {
        this.characterSet = characterSet;
        return this;
    }

    public List<Integer> getArrayData() {
        return arrayData;
    }

    public NColDataType setArrayData(List<Integer> arrayData) {
        this.arrayData = arrayData;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NColDataType that = (NColDataType) o;
        return dataType.equals(that.dataType) &&
                Objects.equals(argumentsStringList, that.argumentsStringList) &&
                Objects.equals(characterSet, that.characterSet) &&
                Objects.equals(arrayData, that.arrayData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataType, argumentsStringList, characterSet, arrayData);
    }

    @Override
    public String toString() {
        StringBuilder arraySpec = new StringBuilder();
        for (Integer item : arrayData) {
            arraySpec.append("[");
            if (item != null) {
                arraySpec.append(item);
            }
            arraySpec.append("]");
        }
        return dataType
                + (argumentsStringList != null ? " " + PlainSelect.
                getStringList(argumentsStringList, true, true) : "")
                + arraySpec.toString()
                + (characterSet != null ? " CHARACTER SET " + characterSet : "");
    }
}
