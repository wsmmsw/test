package com.baicheng.fork.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UnknownFormatConversionException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel文件解析器，支持xls、xlsx格式文件解析. 当前版本只支持Excel文件的第一个worksheet. Created by
 * 
 * @author SongPengpeng
 * @date 2017/3/14.
 */
public class EXLParser {

	/**
	 * 从xls格式Excel文件里面获取单元格数据.
	 *
	 * @return
	 */
	private List<Map<String, String>> getContentFromXls(InputStream inputStream, String[] cls, int startRow)
			throws IOException {
		Workbook workbook = new HSSFWorkbook(inputStream);
		// TODO:
		return null;
	}

	/**
	 * 从xlsx格式Excel文件里面获取单元格数据.
	 *
	 * @return
	 */
	private List<Map<String, String>> getContentFromXlsx(InputStream inputStream, String[] cls, int startRow)
			throws IOException {
		Workbook workbook = new XSSFWorkbook(inputStream);
		// TODO:
		return null;
	}

	/**
	 * 创建xls格式workbook对象.
	 *
	 * @return
	 */
	private Workbook createXls() {
		// TODO:
		return null;
	}

	/**
	 * 创建xlsx格式workbook对象.
	 *
	 * @return
	 */
	private Workbook createXlsx() {
		// TODO:
		return null;
	}

	/**
	 * Excel文件解析方法，支持xls、xlsx格式.
	 *
	 * @param formater 文件格式
	 * @param cls 列名称数组
	 * @param startRow 开始行
	 * @return
	 * @throws UnknownFormatConversionException
	 */
	public List<Map<String, String>> getContentFromExcel(InputStream inputStream, String formater, String[] cls,
			int startRow)
			throws UnknownFormatConversionException, IOException {
		switch (formater) {
		case "xls":
			return getContentFromXls(inputStream, cls, startRow);
		case "xlsx":
			return getContentFromXlsx(inputStream, cls, startRow);
		default:
			throw new UnknownFormatConversionException("unsupport file format.");
		}
	}

}
