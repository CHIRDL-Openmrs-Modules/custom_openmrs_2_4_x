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
	
	/**
	 * @see org.dbunit.database.DefaultMetadataHandler#tableExists(java.sql.DatabaseMetaData, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean tableExists(DatabaseMetaData databaseMetaData, String schemaName, String tableName) 
			throws SQLException {
		ResultSet tableRs = databaseMetaData.getTables("OPENMRS", schemaName, tableName, null);
        try {
            return tableRs.next();
        }
        finally {
            SQLHelper.close(tableRs);
        }
	}

	/**
	 * @see org.dbunit.database.DefaultMetadataHandler#getTables(java.sql.DatabaseMetaData, java.lang.String, java.lang.String[])
	 */
	@Override
	public ResultSet getTables(DatabaseMetaData databaseMetaData, String schemaName, String[] tableTypes) 
			throws SQLException {
		return databaseMetaData.getTables("OPENMRS", schemaName, "%", tableTypes);
	}
	
	/**
	 * @see org.dbunit.database.DefaultMetadataHandler#getPrimaryKeys(java.sql.DatabaseMetaData, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultSet getPrimaryKeys(DatabaseMetaData metaData, String schemaName, String tableName) 
			throws SQLException {
        if(logger.isTraceEnabled()) {
            logger.trace("getPrimaryKeys(metaData={}, schemaName={}, tableName={}) - start", 
                    new Object[] {metaData, schemaName, tableName} );
        }

        ResultSet resultSet = metaData.getPrimaryKeys("OPENMRS", schemaName, tableName);
        return resultSet;
    }
}
