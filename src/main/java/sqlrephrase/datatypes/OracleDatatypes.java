package sqlrephrase.datatypes;

import sqlrephrase.structure.NColDataType;

import java.util.Collections;

public enum OracleDatatypes {
    INSTANCE;
    public static NColDataType NUMBER = NColDataType.EMPTY().withDataType("NUMBER");
    public static NColDataType NUMBER_X = NColDataType.EMPTY().withDataType("NUMBER").withArgumentsStringList(Collections.singletonList("X"));
    public static NColDataType VARCHAR2 = NColDataType.EMPTY().withDataType("VARCHAR2").withArgumentsStringList(Collections.singletonList("X"));
    public static NColDataType DATE = NColDataType.EMPTY().withDataType("DATE");
}
