package com.souleater.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.souleater.exp.BaseException;
import com.souleater.init.Constants;

/** 
 * 操作excel相关的工具类（07,03版都可用）
 * @author love720720@163.com
 * @date 2017年5月11日 下午3:57:12
 */
public class ExcelUtils {
	
	private static final String FONT_NAME = "宋体";
	
	private static final String DEFAUKT_SHEET_NAME = "sheet";
	
	/**
	 * 创建excel
	 * @param title 内容的标题
	 * @param columunList 生成excel文件列名
	 * @param resultMapList 结果集合 集合中放入是Map集合
	 * @return
	 * @throws IOException
	 * @throws BaseException
	 */
	public static synchronized String createExcel(String title, List<String> columunList, List<Map<String, Object>> resultMapList) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(DEFAUKT_SHEET_NAME);
		// 头部单元格样式
		CellStyle hearderStyle = getExcelHeaderStyle(workbook);
		// 标题单元格样式
		CellStyle titleStyle = getExcelTitleStyle(workbook);
		// 普通单元格样式
		CellStyle cellStyle = getExcelCellStyle(workbook);
		// 普通单元格红色字体样式
		CellStyle redCellStyle = getExcelRedCellStyle(workbook);
		
		if (columunList == null || columunList.size() == 0) {
			return create2003(workbook, null);
		}
		
		int rowIndex = 0;
		if (resultMapList == null) {
			resultMapList = new ArrayList<Map<String, Object>>();
		}
		
		HSSFRow rows = sheet.createRow(rowIndex);
		createCell(rows, 0, title, hearderStyle);
		rows.setHeight((short) 700);
		CellRangeAddress region = new CellRangeAddress(rowIndex, rowIndex, 0, columunList.size() - 1);
		sheet.addMergedRegion(region);
		rowIndex++;
		rows = sheet.createRow(rowIndex++);
		rows.setHeight((short) 550);
		// 声明 列宽集合
		Map<Integer, Integer> columnWidthMap = new HashMap<Integer, Integer>();
		// 设置头部信息
		setWorkSheetTitle(rows, columunList, columnWidthMap, titleStyle);
		
		for (Map<String, Object> map : resultMapList) {
			rows = sheet.createRow(rowIndex++);

			for (int i = 0; i < columunList.size(); i++) {
				String key = columunList.get(i);
				Object mapValue = map.get(key);
				String cellValue = (mapValue == null ? Constants.EMPTY : mapValue.toString());
				if (cellValue.startsWith("♀♂")) {
					createCell(rows, i, cellValue.substring(2), redCellStyle);
				} else {
					createCell(rows, i, cellValue, cellStyle);
				}
				putColumnWidth(columnWidthMap, i, cellValue);
			}
		}
		
		setWorkSheetColumnWidth(sheet, columnWidthMap);
		if (StringUtils.isBlank(title)) {
			createFirstFreeze(sheet, 0, 1, 0, 1);// 首行冻结
		} else {
			createFirstFreeze(sheet, 0, 2, 0, 2);// 标题冻结
		}
		
		String filePath = create2003(workbook, null);
		return filePath;
	}

	/**
	 * 头部样式
	 * @param workBook
	 * @return
	 */
	private static CellStyle getExcelHeaderStyle(Workbook workBook) {
		CellStyle hearderStyle = workBook.createCellStyle();
		Font font = workBook.createFont();
		font.setFontName(FONT_NAME);
		font.setFontHeight((short) 20);
		font.setFontHeightInPoints((short) 20);
		hearderStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		hearderStyle.setFont(font);
		hearderStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		return hearderStyle;
	}

	/**
	 * 标题样式
	 * @param workBook
	 * @return
	 */
	private static CellStyle getExcelTitleStyle(Workbook workBook) {
		CellStyle titleStyle = workBook.createCellStyle();
		Font headerFont = workBook.createFont();
		headerFont.setFontHeightInPoints((short) 12);
		headerFont.setFontName(FONT_NAME);
		titleStyle.setFont(headerFont);
		titleStyle.setWrapText(true);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		titleStyle.setBorderBottom((short) 0);
		titleStyle.setBorderLeft((short) 0);
		titleStyle.setBorderRight((short) 0);
		titleStyle.setBorderTop((short) 0);
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 填充的背景颜色
		HSSFPalette palette = ((HSSFWorkbook) workBook).getCustomPalette();
		palette.setColorAtIndex(HSSFColor.LIME.index, (byte) 147, (byte) 205, (byte) 221);
		titleStyle.setFillForegroundColor(HSSFColor.LIME.index);// 前景色
		titleStyle.setFillBackgroundColor(HSSFCellStyle.THICK_FORWARD_DIAG);
		return titleStyle;

	}

	/**
	 * 普通单元格样式
	 * @param workBook
	 * @return
	 */
	private static CellStyle getExcelCellStyle(Workbook workBook) {
		// 普通单元格样式
		CellStyle cellStyle = workBook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setWrapText(false);
		HSSFPalette cellPalette = ((HSSFWorkbook) workBook).getCustomPalette();
		cellPalette.setColorAtIndex(HSSFColor.GREEN.index, (byte) 116, (byte) 210, (byte) 129);
		cellStyle.setLeftBorderColor(HSSFColor.GREEN.index);
		cellStyle.setRightBorderColor(HSSFColor.GREEN.index);
		cellStyle.setTopBorderColor(HSSFColor.GREEN.index);
		cellStyle.setBottomBorderColor(HSSFColor.GREEN.index);
		return cellStyle;
	}

	/**
	 * 普通单元格标红样式
	 * @param workBook
	 * @return
	 */
	private static CellStyle getExcelRedCellStyle(Workbook workBook) {
		// 普通单元格样式
		CellStyle cellStyle = workBook.createCellStyle();
		Font cellFont = workBook.createFont();
		cellFont.setColor(HSSFFont.COLOR_RED);
		cellStyle.setFont(cellFont);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setWrapText(true);
		HSSFPalette cellPalette = ((HSSFWorkbook) workBook).getCustomPalette();
		cellPalette.setColorAtIndex(HSSFColor.GREEN.index, (byte) 116, (byte) 210, (byte) 129);
		cellStyle.setLeftBorderColor(HSSFColor.GREEN.index);
		cellStyle.setRightBorderColor(HSSFColor.GREEN.index);
		cellStyle.setTopBorderColor(HSSFColor.GREEN.index);
		cellStyle.setBottomBorderColor(HSSFColor.GREEN.index);
		return cellStyle;
	}

	/**
	 * 创建文件并写入
	 * @param workBook
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @throws BaseException
	 */
	private static String create2003(HSSFWorkbook workBook, String filePath) {
		FileOutputStream out = null;
		try {
			if (StringUtils.isBlank(filePath)) {
				filePath = Constants.EXCEL_PATH;
			}
			if (StringUtils.isBlank(filePath)) {
				filePath = ".";
			}
			filePath = FileUtils.createFile(filePath, ".xls");
			out = new FileOutputStream(filePath);
			workBook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null){
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return filePath;
	}

	/**
	 * 创建单元格
	 * @param row
	 * @param index
	 * @param value
	 * @param cellStyle
	 * @return
	 */
	private static HSSFCell createCell(HSSFRow row, int index, Object value, CellStyle cellStyle) {
		HSSFCell cell = row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(new HSSFRichTextString(value == null ? Constants.EMPTY : value.toString()));
		return cell;
	}

	/**
	 * 单元格宽度
	 * @param columnWidtMap
	 * @param columnkey
	 * @param object
	 */
	private static void putColumnWidth(Map<Integer, Integer> columnWidtMap, int columnkey, Object object) {
		int currentWidth = columnWidtMap.get(columnkey) == null ? 0 : columnWidtMap.get(columnkey);
		String currentValue = object == null ? Constants.EMPTY : object.toString();
		if (currentValue.length() >= currentWidth) {
			columnWidtMap.put(columnkey, currentValue.length());
		}
	}

	/**
	 * 设置表格头部
	 * @param rows
	 * @param columunList
	 * @param columnWidthMap
	 * @param titleStyle
	 */
	private static void setWorkSheetTitle(HSSFRow rows, List<String> columunList, Map<Integer, Integer> columnWidthMap, CellStyle titleStyle) {
		for (int i = 0; i < columunList.size(); i++) {
			String key = columunList.get(i);
			createCell(rows, i, key, titleStyle);
			columnWidthMap.put(i, key.length());
		}
	}

	/**
	 * 设置excel表格的列宽度
	 * @param sheet
	 * @param columnWidthMap
	 */
	private static void setWorkSheetColumnWidth(HSSFSheet sheet, Map<Integer, Integer> columnWidthMap) {
		Iterator<Entry<Integer, Integer>> iterator = columnWidthMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Integer> next = iterator.next();
			Integer index = next.getKey();
			Integer width = next.getValue();
			
			if (width <= 6) {
				width = 6;
			} else if (width >= 50) {
				width = 50;
			}
			sheet.setColumnWidth(index, width * 700);
		}
	}

	/**
	 * 创建冻结窗口
	 * @param sheet
	 * @param colSplit
	 * @param rowSplit
	 * @param leftmostColumn
	 * @param topRow
	 */
	private static void createFirstFreeze(HSSFSheet sheet, int colSplit, int rowSplit, int leftmostColumn, int topRow) {
		sheet.createFreezePane(colSplit, rowSplit, leftmostColumn, topRow);
	}
}