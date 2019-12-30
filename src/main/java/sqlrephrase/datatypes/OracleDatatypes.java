package sqlrephrase.datatypes;

import sqlrephrase.structure.NColDataType;

import java.util.Collections;

public enum OracleDatatypes {
    INSTANCE;
    public static NColDataType NUMBER = NColDataType.EMPTY().setDataType("NUMBER");
    public static NColDataType NUMBER_X = NColDataType.EMPTY().setDataType("NUMBER").setArgumentsStringList(Collections.singletonList("X"));
    public static NColDataType VARCHAR2 = NColDataType.EMPTY().setDataType("VARCHAR2").setArgumentsStringList(Collections.singletonList("X"));
    public static NColDataType DATE = NColDataType.EMPTY().setDataType("DATE");
}
