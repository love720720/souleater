package com.souleater.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * 数据库反向生成javaBean相关的一些工具类
 * @author love720720@163.com
 * @date 2017年5月8日 下午4:15:13
 */
public class Db4JUtils {
	
    private static Connection con = null;
    
    private static Logger log = LogUtils.getLogger();
    
    static class oracle{
        static final String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
        static final String DATABASE_URL = "jdbc:oracle:thin:@220.248.226.68:1521:testdb";
        static final String DATABASE_USER = "kyhz";
        static final String DATABASE_PASSWORD = "ky345-!7!";
    }
    
    static class mysql{
    	static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    	static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/test";
    	static final String DATABASE_USER = "root";
    	static final String DATABASE_PASSWORD = "root";
    }
 
    public static Connection getOracleConnection() {
        try {
            Class.forName(oracle.DRIVER_CLASS);
            Properties properties = new Properties();
            properties.put("user", oracle.DATABASE_USER);
            properties.put("password", oracle.DATABASE_PASSWORD);
            properties.put("remarksReporting","true");
            con = DriverManager.getConnection(oracle.DATABASE_URL, properties);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    
    public static List<Map<String,Object>> getOracleTable(String tableName) {
    	if(StringUtils.isBlank(tableName)){
    		throw new NullPointerException("tableName为空");
    	}
        getOracleConnection();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        try {
            DatabaseMetaData dmd = con.getMetaData(); 
            ResultSet colrs = dmd.getColumns(null,oracle.DATABASE_USER.toUpperCase(), tableName,"%");
            while(colrs.next()) {
                String columnName = colrs.getString("COLUMN_NAME"); 
                String columnType = colrs.getString("TYPE_NAME"); 
                int datasize = colrs.getInt("COLUMN_SIZE"); 
                String remarks = colrs.getString("REMARKS") == null ? "" : colrs.getString("REMARKS");
                remarks = remarks.replaceAll("\n", " ");
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("columnName", columnName);
                map.put("columnType", columnType);
                map.put("datasize", datasize);
                map.put("remarks", remarks);
                list.add(map);
                
                log.info(tableName + "-->>columnName = " + columnName + "|remarks = " + remarks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
            	if(con != null){
            		con.close();
            	}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        return list;
    }

    public static String oracleSqlType2JavaType(String sqlType,int size,int scale){
    	//这里可以对javaBean进行类型转换 目前是String类型
        return "String";
    }
    
    public static String getItems(List<Map<String,Object>> list,String packageName, String className){
        StringBuffer sb = new  StringBuffer();
        sb.append(packageName).append(";");
        sb.append("\r\n");
        sb.append("\r\n");
        sb.append("/**").append("\r\n");
        sb.append("  *").append("\r\n");
        sb.append("  *").append(" @author\r\n");
        sb.append("  *").append(" @date\r\n");
        sb.append("  *").append("\r\n");
        sb.append("  */");
        sb.append("\r\n");
        sb.append("public class "+ className + " {\r\n");
        
        log.info("开始创建私有属性");
    	for (int i = 0; i < list.size(); i++) {
    		Map<String, Object> map = list.get(i);
            String columnname = columnNameToDeclareVar(map.get("columnName").toString());
            String columntype = map.get("columnType").toString();
            String columnsize = map.get("datasize").toString();
            String remarks = map.get("remarks").toString();
            String javaType = oracleSqlType2JavaType(columntype.toLowerCase(),Integer.parseInt(columnsize),0);
            StringBuilder temp = new StringBuilder();
            if(i == 0){
            	temp.append("\r\n");
            }
            temp.append("\tprivate ");
            temp.append(javaType);
            temp.append(" ");
            temp.append(columnname);
            temp.append("; //");
            temp.append(remarks);
            temp.append("\r\n");
            sb.append(temp);
        }
    	log.info("结束创建私有属性");
        sb.append("\r\n");
        
        log.info("开始创建set、get方法");
        for (int i = 0; i < list.size(); i++) {
    		Map<String, Object> map = list.get(i);
            String columnName = columnNameToDeclareVar(map.get("columnName").toString());
            String columntype = map.get("columnType").toString();
            String columnsize = map.get("datasize").toString();
            String javaType = oracleSqlType2JavaType(columntype.toLowerCase(),Integer.parseInt(columnsize),0);
            StringBuilder temp = new StringBuilder();
            temp.append("\tpublic ");
            temp.append(javaType);
            temp.append(" ");
            temp.append("get");
            temp.append(toFirstUpper(columnName));
            temp.append("(){\r\n");
            
            
            temp.append("\t\treturn ");
            temp.append(columnName);
            temp.append(";\r\n");
            
            temp.append("\t}\r\n\r\n");
            sb.append(temp);
            
            temp = new StringBuilder();
            temp.append("\tpublic void ");
            temp.append("set");
            temp.append(toFirstUpper(columnName));
            temp.append("(");
            temp.append(javaType);
            temp.append(" ");
            temp.append(columnName);
            temp.append("){\r\n");
            
            
            temp.append("\t\tthis.");
            temp.append(columnName);
            temp.append(" = ");
            temp.append(columnName);
            temp.append(";\r\n");
            
            if(i == (list.size() - 1)){
            	temp.append("\t}\r\n");
            }else{
            	temp.append("\t}\r\n\r\n");
            }
            
            sb.append(temp);
        }
        log.info("结束创建set、get方法");
        sb.append("}");
        return sb.toString();
        
    }
    
	public static String toFirstUpper(String str) {
		if (StringUtils.isBlank(str)) {
			throw new NullPointerException();
		}

		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public static String toFirstLowwer(String str) {
		if (StringUtils.isBlank(str)) {
			throw new NullPointerException();
		}

		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}
	
	public static String columnNameToDeclareVar(String columnName) {
		String[] words = columnName.split("_");
		StringBuilder varName = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			varName.append(toFirstUpper(words[i].toLowerCase()));
		}
		return toFirstLowwer(varName.toString());
	}
	
    public static void main(String[] args) {
        try {
        	log.info("开始javaBean生成");
        	//需要转换的oracle表名
            String tableName  = "T_LBT_SETTLEMENT";
            //生成的文件、类名
            String className  = "settlement";
            //生成的类包路径
            String packageName  = "package com.wtyt.bean";
            //生成文件的本地路径
            String localPath = "D:\\lbt\\";
            
            log.info("tableName = " + tableName);
            log.info("className = " + className);
            log.info("packageName = " + packageName);
            log.info("localPath = " + localPath);
            
            List<Map<String,Object>> list = getOracleTable(tableName);
            String a = getItems(list,packageName,className);
            File file = new File(localPath + className + ".java");
            File fileParent = file.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();  
            }  
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file,false);
            fos.write(a.getBytes());  
            fos.close();
        } catch (IOException e) {
			e.printStackTrace();
		} finally {
        	log.info("结束javaBean生成");
        }
    }
}