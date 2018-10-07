package com.clairvoyant.notes.sqlserverdialect;

import org.hibernate.dialect.SQLServer2012Dialect;
import org.hibernate.type.StringNVarcharType;

import java.sql.Types;

public class SQLServerDBDialect extends SQLServer2012Dialect {
	public SQLServerDBDialect() {
		super();
		registerHibernateType(Types.NVARCHAR, StringNVarcharType.INSTANCE.getName());
	}

}
