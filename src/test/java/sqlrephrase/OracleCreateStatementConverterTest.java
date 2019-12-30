package sqlrephrase;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.apache.calcite.sql2rel.SqlToRelConverter;
import org.junit.Test;
import sqlrephrase.datatypemapping.OracleToMSSqlTranslator;
import sqlrephrase.datatypes.OracleDatatypes;
import sqlrephrase.structure.NColDataType;
import sqlrephrase.structure.NColumnDefinition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple OracleCreateStatementConverter.
 */
public class OracleCreateStatementConverterTest {
    /**
     * Rigorous Test :-)
     */

    public final static String NULL = "NULL";
    public final static String NOT = "NOT";
    public final static String NOT_NULL = "NOT NULL";
    public final static String DATATYPE_VARCHAR2 = "VARCHAR2";
    final static String TABLE_1 = "person";
    final static String T1_COLUMN1 = "first_name";
    final static String T1_COLUMN2 = "last_name";

    @Test
    public void checkTableNameColumnNameDatatype() throws Exception {
        String createTableString = "CREATE TABLE " + TABLE_1 + "(\n" +
                "    " + T1_COLUMN1 + " " + DATATYPE_VARCHAR2 + "(50) " + NOT_NULL + ",\n" +
                "    " + T1_COLUMN2 + " " + DATATYPE_VARCHAR2 + "(50) " + NULL + "\n" +
                ");";

        Statement stmt = CCJSqlParserUtil.parse(createTableString);
        CreateTable createTableStatement = ((CreateTable) stmt);

        // manage expectations
        final NColDataType VARCHAR2_50 = OracleDatatypes.VARCHAR2.setArgumentsStringList(Collections.singletonList("50"));

        NColumnDefinition firstName = NColumnDefinition.EMPTY()
                .setColumnName(T1_COLUMN1)
                .setColDataType(VARCHAR2_50)
                .setColumnSpecStrings(Arrays.asList(NOT, NULL));

        NColumnDefinition lastName = NColumnDefinition.EMPTY()
                .setColumnName(T1_COLUMN2)
                .setColDataType(VARCHAR2_50)
                .setColumnSpecStrings(Arrays.asList(NULL));

        List<NColumnDefinition> expectedColumnDefinitions = NColumnDefinition.createArrayWithColumnDefinition(firstName, lastName);

        List<NColumnDefinition> actualColumnDefinitions = NColumnDefinition.createArrayWithColumnDefinition(createTableStatement.getColumnDefinitions().get(0),
                createTableStatement.getColumnDefinitions().get(1));

        assertThat(createTableStatement.getTable().getName(), is(TABLE_1));
        assertThat(actualColumnDefinitions, is(expectedColumnDefinitions));
    }

    @Test
    public void checkOracleToMSSqlConversion() throws Exception {
        String oracleCreateTableString = "CREATE TABLE " + TABLE_1 + "(\n" +
                "    " + T1_COLUMN1 + " " + DATATYPE_VARCHAR2 + "(50) " + NOT_NULL + ",\n" +
                "    " + T1_COLUMN2 + " " + DATATYPE_VARCHAR2 + "(50) " + NULL + "\n" +
                ");";
        Statement stmt = CCJSqlParserUtil.parse(oracleCreateTableString);
        CreateTable createTableStatement = ((CreateTable) stmt);
        List<ColumnDefinition> columnDefinitionList = new ArrayList<>();
        for (ColumnDefinition cd : createTableStatement.getColumnDefinitions()) {
            columnDefinitionList.add(OracleToMSSqlTranslator.INSTANCE.mapDataType(cd));
        }
        createTableStatement.setColumnDefinitions(columnDefinitionList);
        assertThat(createTableStatement.toString(), is("CREATE TABLE person (first_name VARCHAR (50) NOT NULL, last_name VARCHAR (50) NULL)"));
    }

    @Test
    public void fakeTest() {
        System.out.println("Hi this is a test");
        final SqlToRelConverter.Config DEFAULT_REL_CONFIG =
                SqlToRelConverter.configBuilder()
                        .withTrimUnusedFields(false)
                        .withConvertTableAccess(false)
                        .build();

        assertTrue(true);
    }
}

