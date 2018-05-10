package com.test.own;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.souleater.util.ExcelUtils;

/** 
 * excel测试类
 * @author love720720@163.com
 * @date 2017年5月12日 上午11:44:23
 */
public class Test_Excel_1 {

	public static void main(String[] args) {
		
		List<String> columunlist = new ArrayList<String>();
		columunlist.add("字段1");
		columunlist.add("字段2");
		columunlist.add("字段3");
		columunlist.add("字段4");
		
		List<Map<String, Object>> resultMapList = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("字段1", "数据1");
		map.put("字段2", "数据2");
		map.put("字段3", "数据3");
		map.put("字段4", "数据4");
		resultMapList.add(map);
		
		map = new HashMap<String, Object>();
		map.put("字段1", "数据5");
		map.put("字段2", "数据6");
		map.put("字段3", "数据7");
		map.put("字段4", "数据8");
		resultMapList.add(map);
		
		map = new HashMap<String, Object>();
		map.put("字段1", "数据9");
		map.put("字段2", "数据10");
		map.put("字段3", "数据11");
		map.put("字段4", "数据12");
		resultMapList.add(map);
		
		try {
			ExcelUtils.createExcel("test", columunlist, resultMapList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
