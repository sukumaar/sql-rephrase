package sqlrephrase.datatypemapping;

import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import sqlrephrase.datatypes.MSSqlDatatypes;
import sqlrephrase.datatypes.OracleDatatypes;
import sqlrephrase.render.ColumnDefinitionCloner;
import sqlrephrase.structure.NColDataType;

import java.util.Collections;
import java.util.Map;

public enum OracleToMSSqlTranslator {
    INSTANCE;
    public final Map<ColDataType, ColDataType> DATATYPE_MAPPING = new CaseInsensitiveMap<ColDataType, ColDataType>() {{
        put(OracleDatatypes.VARCHAR2, MSSqlDatatypes.VARCHAR);
        put(OracleDatatypes.DATE, MSSqlDatatypes.DATETIME);
        put(OracleDatatypes.NUMBER, MSSqlDatatypes.DECIMAL);
        put(OracleDatatypes.NUMBER_X, MSSqlDatatypes.INT);
    }};

    public final ColumnDefinition mapDataType(ColumnDefinition columnDefinition) {
        ColumnDefinition columnDefinitionNew = ColumnDefinitionCloner.clone(columnDefinition);
        ColDataType ncd = NColDataType.createWithColDataType(columnDefinitionNew.getColDataType());
        if (!ncd.getArgumentsStringList().isEmpty()) {
            ncd.setArgumentsStringList(Collections.singletonList("X"));
        }
        ColDataType targetColumnDataType = DATATYPE_MAPPING.get(ncd);
        if (!(targetColumnDataType.getArgumentsStringList().isEmpty())
                && targetColumnDataType.getArgumentsStringList().get(0).equals("X")) {
            columnDefinitionNew.getColDataType().setDataType(targetColumnDataType.getDataType());
            return columnDefinitionNew;
        }
        columnDefinitionNew.getColDataType().setArgumentsStringList(null);
        return columnDefinitionNew;
    }
}
