package com.zhisen.cslcp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhisen.cslcp.dao.entity.Cuslevel;
import com.zhisen.cslcp.service.ICuslevelService;
import com.zhisen.cslcp.service.exception.ServiceException;
import com.zhisen.cslcp.utils.ExcelUtil;

import net.sf.jxls.exception.ParsePropertyException;

/**
 * 客户级别Controller
 * 
 * @author baiqianyi
 * @date 2021-05-18
 */
@RestController
@RequestMapping("/cuslevel")
public class CuslevelController {
	@Autowired
	private ICuslevelService cuslevelService;

	/**
	 * 查询客户级别列表
	 */
	@GetMapping("/list")
	public Map<String, Object> list(Cuslevel cuslevel) {

		Map<String, Object> map = new HashMap<>();
		try {
			// 根据分页参数设置limit左参数
			cuslevel.setLimit(cuslevel.getCurrentPage(), cuslevel.getPageSize());
			// 设置查询参数，区别普通查询和excel导出查询
			cuslevel.setExcelFlag("0");
			// 查询返回状态值
			map.put("status", 1);
			// 返回分页，页码
			map.put("currentPage", cuslevel.getCurrentPage());
			// 返回数据条数
			map.put("pageNumber", cuslevelService.selectCuslevelPageNumber(cuslevel));
			// 返回列表数据
			map.put("request", cuslevelService.selectCuslevelList(cuslevel));
			map.put("msg", "查询成功");

		} catch (Exception e) {
			// 查询返回状态值
			map.put("status", 0);

			map.put("msg", e.getMessage());
		}
		return map;
	}

	/**
	 * 导出客户级别列表
	 */
	@RequestMapping("/excelDownload")
	public void excelDownloadtest(HttpServletResponse response,
			@RequestBody Map<String, Object> name) throws IOException,
			ServiceException, ParsePropertyException, InvalidFormatException {
		// 数据查询项入参参数声明
		Cuslevel cuslevel = new Cuslevel();
		cuslevel.setName(name.get("params").toString());
		// 查询标志，excel查询，不考虑limit
		cuslevel.setExcelFlag("1");
		// 查询数据
		List<Cuslevel> date = cuslevelService
				.selectCuslevelList(cuslevel);
		
		// 整理格式
		//声明行数据存储变量
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		//声明行数据存储变量
		Map<String, Object> data0 ;
		for (Cuslevel da : date) {
			data0 = new HashMap<String, Object>();
			data0.put("name", da.getName());
			data0.put("low", da.getLow().toString());
			data0.put("hi", da.getHi().toString());
			dataList.add(data0);
		}
		
		/**
		 * 使用工具类
		 * 第一参数  response
		 * 第二参数  dataList 数据
		 * 第三参数 表格标题
		 * 第四参数 引用模板名
		 * 
		 */
		ExcelUtil.exportExcel(response, dataList,"客户级别信息","客户级别");
		
	}

	/**
	 * 新增客户级别
	 */
	@GetMapping("/add")
	public Map<String, Object> add(Cuslevel cuslevel) {

		Map<String, Object> map = new HashMap<>();

		try {
			// 存储新增返回值
			int sum = cuslevelService.insertCuslevel(cuslevel);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				// 请求返回状态值
				map.put("status", 2);
				// 请求返回状态信息
				map.put("msg", "数据不符合规则");
			} else {
				// 请求返回状态值
				map.put("status", 1);
				map.put("request", sum);
				// 请求返回状态信息
				map.put("msg", "新增成功");
			}
		} catch (Exception e) {
			// 请求返回状态值
			map.put("status", 0);
			// 请求返回状态信息
			map.put("msg", e.getMessage());
		}

		return map;
	}

	/**
	 * 修改客户级别
	 */
	@GetMapping("/edit")
	public Map<String, Object> edit(Cuslevel cuslevel) {
		Map<String, Object> map = new HashMap<>();
		
		try {
			// 存储修改返回值
			int sum = cuslevelService.updateCuslevel(cuslevel);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				// 请求返回状态值
				map.put("status", 2);
				// 请求返回状态信息
				map.put("msg", "数据不符合规则");
			} else {
				// 请求返回状态值
				map.put("status", 1);
				map.put("request", sum);
				// 请求返回状态信息
				map.put("msg", "修改成功");
			}
		} catch (Exception e) {
			// 请求返回状态值
			map.put("status", 0);
			// 请求返回状态信息
			map.put("msg", e.getMessage());
		}
		return map;
	}

	/**
	 * 删除客户级别
	 */

	@PostMapping("/remove")
	public Map<String, Object> remove(@RequestBody List<String> list) {
		Map<String, Object> map = new HashMap<>();
		try {
			// 请求返回状态值
			map.put("status", 1);
			map.put("request", cuslevelService.deleteCuslevelByIds(list));
			// 请求返回状态信息
			map.put("msg", "删除成功");
		} catch (Exception e) {
			// 请求返回状态值
			map.put("status", 0);
			// 请求返回状态信息
			map.put("msg", e.getMessage());
		}
		return map;
	}
}
