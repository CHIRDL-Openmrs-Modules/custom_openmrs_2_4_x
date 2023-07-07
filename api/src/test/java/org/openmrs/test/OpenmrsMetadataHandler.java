package org.openmrs.test;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dbunit.database.DefaultMetadataHandler;
import org.dbunit.util.SQLHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles metadata for the OpenMRS test data set.
 */
public class OpenmrsMetadataHandler extends DefaultMetadataHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(OpenmrsMetadataHandler.class);
	private static final String CATALOG_OPENMRS = "OPENMRS";
	
	/**
	 * @see org.dbunit.database.DefaultMetadataHandler#getColumns(
	 * java.sql.DatabaseMetaData, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultSet getColumns(DatabaseMetaData databaseMetaData, String schemaName, String tableName) 
			throws SQLException {
        if(logger.isTraceEnabled()) {
            logger.trace("getColumns(databaseMetaData={}, schemaName={}, tableName={}) - start", 
                    new Object[] {databaseMetaData, schemaName, tableName} );
        }
        
        ResultSet resultSet = databaseMetaData.getColumns(CATALOG_OPENMRS, schemaName, tableName, "%");
        return resultSet;
    }
	
	/**
	 * @see org.dbunit.database.DefaultMetadataHandler#matches(
	 * java.sql.ResultSet, java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public boolean matches(ResultSet resultSet, String schema, String table, boolean caseSensitive) 
			throws SQLException {
        return matches(resultSet, CATALOG_OPENMRS, schema, table, null, caseSensitive);
    }
	
	/**
	 * @see org.dbunit.database.DefaultMetadataHandler#tableExists(
	 * java.sql.DatabaseMetaData, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean tableExists(DatabaseMetaData databaseMetaData, String schemaName, String tableName) 
			throws SQLException {
		ResultSet tableRs = databaseMetaData.getTables(CATALOG_OPENMRS, schemaName, tableName, null);
        try {
            return tableRs.next();
        }
        finally {
            SQLHelper.close(tableRs);
        }
	}

	/**
	 * @see org.dbunit.database.DefaultMetadataHandler#getTables(
	 * java.sql.DatabaseMetaData, java.lang.String, java.lang.String[])
	 */
	@Override
	public ResultSet getTables(DatabaseMetaData databaseMetaData, String schemaName, String[] tableTypes) 
			throws SQLException {
		return databaseMetaData.getTables(CATALOG_OPENMRS, schemaName, "%", tableTypes);
	}
	
	/**
	 * @see org.dbunit.database.DefaultMetadataHandler#getPrimaryKeys(
	 * java.sql.DatabaseMetaData, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultSet getPrimaryKeys(DatabaseMetaData metaData, String schemaName, String tableName) 
			throws SQLException {
        if(logger.isTraceEnabled()) {
            logger.trace("getPrimaryKeys(metaData={}, schemaName={}, tableName={}) - start", 
                    new Object[] {metaData, schemaName, tableName} );
        }

        ResultSet resultSet = metaData.getPrimaryKeys(CATALOG_OPENMRS, schemaName, tableName);
        return resultSet;
    }
}
