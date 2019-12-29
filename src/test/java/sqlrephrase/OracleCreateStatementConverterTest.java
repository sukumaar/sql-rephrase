package sqlrephrase;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.apache.calcite.sql2rel.SqlToRelConverter;
import org.junit.Test;
import sqlrephrase.structure.NColumnDefinition;

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


    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void startingWithJSQLParser() throws Exception {
        final String table1 = "person";
        final String column1 = "first_name";
        final String column2 = "last_name";
        Statement stmt = CCJSqlParserUtil.parse("CREATE TABLE " + table1 + "(\n" +
                "    " + column1 + " " + DATATYPE_VARCHAR2 + "(50) " + NOT_NULL + ",\n" +
                "    " + column2 + " " + DATATYPE_VARCHAR2 + "(50) " + NULL + "\n" +
                ");");
        CreateTable createTableStatement = ((CreateTable) stmt);

        // manage expectations
        final ColDataType VARCHAR2_50 = new ColDataType();
        VARCHAR2_50.setDataType(DATATYPE_VARCHAR2);
        VARCHAR2_50.setArgumentsStringList(Collections.singletonList("50"));
        ColumnDefinition firstName = new ColumnDefinition();
        firstName.setColumnName(column1);
        firstName.setColDataType(VARCHAR2_50);
        firstName.setColumnSpecStrings(Arrays.asList(NOT, NULL));

        ColumnDefinition lastName = new ColumnDefinition();
        lastName.setColumnName(column2);
        lastName.setColDataType(VARCHAR2_50);
        lastName.setColumnSpecStrings(Arrays.asList(NULL));

        List<NColumnDefinition> expectedColumnDefinitions = NColumnDefinition.createArrayWithColumnDefinition(firstName, lastName);

        List<NColumnDefinition> actualColumnDefinitions = NColumnDefinition.createArrayWithColumnDefinition(createTableStatement.getColumnDefinitions().get(0),
                createTableStatement.getColumnDefinitions().get(1));

        assertThat(createTableStatement.getTable().getName(), is(table1));
        assertThat(actualColumnDefinitions, is(expectedColumnDefinitions));
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

