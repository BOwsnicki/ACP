package edu.uwf.cs.acp.project1.map;

public class DerbyTypeMapper implements TypeMapper {

	public String mapToDBType(String javaType) {
		if (javaType.equals("int")) {
			return "INTEGER";
		}
		if (javaType.equals("class java.lang.String")) {
			return "CHAR(50)";
		}
		return javaType;
	}

}
