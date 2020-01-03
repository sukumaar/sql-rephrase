package sqlrephrase.datatypes;

import sqlrephrase.structure.NColDataType;

import java.util.Collections;

public enum MSSqlDatatypes {
    INSTANCE;
    public static NColDataType DECIMAL = NColDataType.EMPTY().withDataType("DECIMAL");
    public static NColDataType INT = NColDataType.EMPTY().withDataType("INT");
    public static NColDataType VARCHAR = NColDataType.EMPTY().withDataType("VARCHAR").withArgumentsStringList(Collections.singletonList("X"));
    public static NColDataType DATETIME = NColDataType.EMPTY().withDataType("DATETIME");

}
