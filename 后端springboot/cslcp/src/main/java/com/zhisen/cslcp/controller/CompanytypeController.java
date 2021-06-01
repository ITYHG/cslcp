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

import com.zhisen.cslcp.dao.entity.Companytype;
import com.zhisen.cslcp.service.ICompanytypeService;
import com.zhisen.cslcp.service.exception.ServiceException;
import com.zhisen.cslcp.utils.ExcelUtil;

import net.sf.jxls.exception.ParsePropertyException;





/**
 * 公司类型Controller
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
@RestController
@RequestMapping("/companytype")
public class CompanytypeController 
{
    @Autowired
    private ICompanytypeService companytypeService;

    /**
     * 查询公司类型列表
     */

    @GetMapping("/list")
    public Map<String, Object> list(Companytype companytype)
    {
    	Map<String, Object> map = new HashMap<>();
		try {
			// 根据分页参数设置limit左参数
			companytype.setLimit(companytype.getCurrentPage(),companytype.getPageSize());
			// 设置查询参数，区别普通查询和excel导出查询
			companytype.setExcelFlag("0");
			// 请求返回状态值
			map.put("status", 1);
			// 返回分页，页码
			map.put("currentPage",companytype.getCurrentPage());
			// 返回数据条数
			map.put("pageNumber",
					companytypeService.selectCompanytypePageNumber(companytype));
			// 返回列表数据
			map.put("request",
					companytypeService.selectCompanytypeList(companytype));
			// 请求返回状态信息
			map.put("msg", "查询成功");
		} catch (Exception e) {
			// 请求返回状态值
			map.put("status", 0);
			// 请求返回状态信息
			map.put("msg", e.getMessage());
		}
		return map;

	}

    /**
     * 导出公司类型列表
     */

    @RequestMapping("/excelDownload")
	public void excelDownloadtest(HttpServletResponse response,
			@RequestBody Map<String, Object> name) throws IOException,
			ServiceException, ParsePropertyException, InvalidFormatException {
		// 数据查询项入参参数声明
    	Companytype companytype = new Companytype();
    	companytype.setName(name.get("params").toString());
		// 查询标志，excel查询，不考虑limit
    	companytype.setExcelFlag("1");
		// 查询数据
		List<Companytype> date = companytypeService
				.selectCompanytypeList(companytype);
		
		// 整理格式
		//声明行数据存储变量
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		//声明行数据存储变量
		Map<String, Object> data0 ;
		for (Companytype da : date) {
			data0 = new HashMap<String, Object>();
			data0.put("name", da.getName());
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
		ExcelUtil.exportExcel(response, dataList,"公司类型信息","公司类型");
		
	}
    
    /**
     * 新增公司类型
     */
    @GetMapping(value = "/add")
    public Map<String, Object> add(Companytype companytype)
    {

        Map<String, Object> map = new HashMap<>();
		try {

			// 存储新增返回值
			int sum =companytypeService.insertCompanytype(companytype);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				// 新增返回状态值
				map.put("status", 2);
				// 前端返回信息
				map.put("msg", "公司类型名称已存在，新增失败");
			} else {
				// 请求返回状态值
				map.put("status", 1);
				// 请求返回结果值
				map.put("request", sum);
				// 请求返回状态信息
				map.put("msg", "添加成功");
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
     * 修改公司类型
     */
    
    @GetMapping("/edit")
	public Map<String, Object> edit(Companytype companytype) {
		Map<String, Object> map = new HashMap<>();
		
		try {
			// 存储修改返回值
			int sum = companytypeService.updateCompanytype(companytype);
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
     * 批量删除公司类型
     */


@PostMapping("/remove")
public Map<String, Object> remove(@RequestBody List<String> list) {
	Map<String, Object> map = new HashMap<>();
	try {
		// 请求返回状态值
		map.put("status", 1);
		map.put("request", companytypeService.deleteCompanytypeByIds(list));
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