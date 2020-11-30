package com.sp.spproj.excel;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * transform csv to excel
 */
@Slf4j
public class ExcelUtil {
	private static final Integer MAX_COUNT = 100000;

	public static <T> void write2Sheet(Sheet sheet, List<T> dataList, LinkedHashMap map, String[] header, String pattern) {
		if (StringUtils.isEmpty(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		// create a row
		Row row = sheet.createRow(0);
		int i = 0;
		// create title
		Iterator propertyIterator = map.entrySet().iterator();
		while (propertyIterator.hasNext()) {
			Map.Entry propertyEntry = (Map.Entry) propertyIterator.next();
			XSSFRichTextString text = new XSSFRichTextString(propertyEntry.getValue() == null ? "" : propertyEntry.getValue().toString());
			row.createCell(i).setCellValue(text);
			i++;
		}
		// create data
		Iterator<T> data = dataList.iterator();
		int j = 0;
		while (data.hasNext()) {
			row = sheet.createRow(j + 1);
			T t = data.next();
			if (t instanceof Map) {
				Map<String, Object> d = (Map<String, Object>) t;
				for (Map.Entry mm : d.entrySet()) {
					for (int k = 0; k < header.length; k++) {
						if (header[k].equals(mm.getKey())) {
							setCellValue(row.createCell(k), mm.getValue(), null, pattern);
						}
					}
				}
			} else {
				Class<?> clazz = t.getClass();
				Field[] fields = clazz.getDeclaredFields();
				for (Field f : fields) {
					ExcelCell ec = f.getAnnotation(ExcelCell.class);
					if (ec == null) {
						continue;
					}
					for (int k = 0; k < header.length; k++) {
						if (!header[k].equals(f.getName())) {
							continue;
						}
						String methodName = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
						try {
							Method method = clazz.getMethod(methodName);
							Object value = method.invoke(t);
							setCellValue(row.createCell(k), value, f, pattern);
						} catch (Exception e) {
							log.error("excel导出数据反射失败:", e);
						}
					}
				}
			}
			j++;
		}
	}

	private static void setCellValue(Cell cell, Object value, Field field, String pattern) {
		String textValue = null;
		if (value instanceof Integer) {
			int intValue = (Integer) value;
			cell.setCellValue(intValue);
		} else if (value instanceof Float) {
			float fValue = (Float) value;
			cell.setCellValue(fValue);
		} else if (value instanceof Double) {
			double dValue = (Double) value;
			cell.setCellValue(dValue);
		} else if (value instanceof Long) {
			long longValue = (Long) value;
			cell.setCellValue(longValue);
		} else if (value instanceof Boolean) {
			boolean bValue = (Boolean) value;
			cell.setCellValue(bValue);
		} else if (value instanceof Date) {
			Date date = (Date) value;
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			textValue = sdf.format(date);
		} else if (value instanceof BigDecimal) {
			BigDecimal v = (BigDecimal) value;
			double v1 = v.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			cell.setCellValue(v1);
		} else {
			// 其它数据类型都当作字符串简单处理
			String empty = StringUtils.EMPTY;
			if (field != null) {
				ExcelCell anno = field.getAnnotation(ExcelCell.class);
				if (anno != null) {
					empty = anno.defaultValue();
				}
			}
			textValue = value == null ? empty : value.toString();
		}
		if (textValue != null) {
			XSSFRichTextString richString = new XSSFRichTextString(textValue);
			cell.setCellValue(richString);
		}
	}

	public static <T> XSSFWorkbook exportExcel(List<T> dataList, LinkedHashMap map, String[] header, String fileName, String pattern) throws Exception {
		// create a workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			// create a sheet 每个sheet最多50000条
			XSSFSheet sheet = null;
			Integer size = dataList.size();
			if (size > MAX_COUNT) {
				Integer avg = size / MAX_COUNT;
				for (int i = 0; i < avg + 1; i++) {
					sheet = workbook.createSheet(fileName + (i + 1));
				}
			} else {
				sheet = workbook.createSheet(fileName);
			}
			// write content
			write2Sheet(sheet, dataList, map, header, pattern);
		} catch (Exception e) {
			log.error("生成excel文件出错：", e);
			throw new Exception(e);
		}
		return workbook;
	}

	public static void exportExcel(HttpServletResponse response, SXSSFWorkbook excel, String filename) throws UnsupportedEncodingException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.addHeader("Content-disposition", "attachment;filename=" + new String((filename + ".xlsx").getBytes(), "iso-8859-1"));
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			excel.write(out);
			excel.close();
			out.flush();

		} catch (FileNotFoundException e) {
			log.error("file not found:", e);
		} catch (IOException e) {
			log.error("excel write failure:", e);
		}finally {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		String[] header = new String[] { "sendDate", "barCode" };
		String pattern = "yyyy-MM-dd HH:mm:ss";
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("sendDate", "发货时间");
		map.put("barCode", "条码");

		List<Map> data = Lists.newArrayList();
		Map map1 = new HashMap();
		Map map2 = new HashMap();

		map1.put("sendDate","11");
		map1.put("barCode","1111");

		map2.put("sendDate","22");
		map2.put("barCode","2222");

		data.add(map1);
		data.add(map2);

		long beginTime = System.currentTimeMillis();
		try {
			// 生成文件，写入内容，可百万级别数据,只要1分钟
			XSSFWorkbook excel = exportExcel(data, map, header, "积分", pattern);
			FileOutputStream fout = new FileOutputStream("-OrderDelivery.xlsx");
			excel.write(fout);
			fout.close();

			long endTime = System.currentTimeMillis();
			log.info("Cast time : " + (endTime - beginTime));
			log.info("excel生成成功");
		} catch (Exception e) {
			log.error("excel生成成功", e);
		}
	}
}
