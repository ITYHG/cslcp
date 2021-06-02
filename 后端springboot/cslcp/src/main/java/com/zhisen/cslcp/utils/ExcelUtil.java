package com.zhisen.cslcp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import com.zhisen.cslcp.service.exception.ServiceException;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

public class ExcelUtil {
	
	public static void exportExcel(HttpServletResponse response,
			List<Map<String, Object>> excelData ,String title ,String templateName) throws IOException,
			ServiceException, ParsePropertyException, InvalidFormatException {
		String path =System.getProperty("user.dir")+"\\src\\main\\resources";
		InputStream inputStream = new FileInputStream(path+"\\static\\"+ templateName +".xls");
		Map<String, Object> showdata = new HashMap<>();
		showdata.put("title", title);
		
		showdata.put("data", excelData);
		XLSTransformer xlsTransformer = new XLSTransformer();
		Workbook book=xlsTransformer.transformXLS(inputStream, showdata);
		String filename = "测试文件名称";
		filename = new String(filename.getBytes(),"iso-8859-1");
		String haedString= "attachment; filename=\"" + filename +".xlsx\"";
		
		response.setContentType("APPLICATION/OCTET-STREAM;charset=utf-8");	
		response.setHeader("Content-Disposition", haedString);
		OutputStream outputStream = response.getOutputStream();
		book.write(outputStream);
		inputStream.close();
	}
	

}
