package sqlrephrase.datatypemapping;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import sqlrephrase.datatypes.MSSqlDatatypes;
import sqlrephrase.datatypes.OracleDatatypes;
import sqlrephrase.structure.NColDataType;

import java.util.Map;

public enum OracleToMSSql {
    INSTANCE;
    public final Map DATATYPE_MAPPING = new CaseInsensitiveMap<NColDataType, NColDataType>() {{
        put(OracleDatatypes.VARCHAR2, MSSqlDatatypes.VARCHAR);
        put(OracleDatatypes.DATE, MSSqlDatatypes.DATETIME);
        put(OracleDatatypes.NUMBER, MSSqlDatatypes.DECIMAL);
        put(OracleDatatypes.NUMBER_X, MSSqlDatatypes.INT);
    }};

}
