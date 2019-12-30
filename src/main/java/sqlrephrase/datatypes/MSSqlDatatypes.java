package sqlrephrase.datatypes;

import sqlrephrase.structure.NColDataType;

import java.util.Collections;

public enum MSSqlDatatypes {
    INSTANCE;
    public static NColDataType DECIMAL = NColDataType.EMPTY().setDataType("DECIMAL");
    public static NColDataType INT = NColDataType.EMPTY().setDataType("INT");
    public static NColDataType VARCHAR = NColDataType.EMPTY().setDataType("VARCHAR").setArgumentsStringList(Collections.singletonList("X"));
    public static NColDataType DATETIME = NColDataType.EMPTY().setDataType("DATETIME");

}
