package com.zhisen.cslcp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhisen.cslcp.dao.entity.ExpressCompany;
import com.zhisen.cslcp.service.IExpressCompanyService;
import com.zhisen.cslcp.service.exception.ServiceException;
import com.zhisen.cslcp.utils.ExcelUtil;

/**
 * 快递公司Controller
 * 
 * @author baiqianyi
 * @date 2021-05-22
 */
@RestController
@RequestMapping("/expressCompany")
public class ExpressCompanyController
{
    @Autowired
    private IExpressCompanyService expressCompanyService;

    /**
     * 查询快递公司列表
     */
    @GetMapping("/list")
    public Map<String, Object> list(ExpressCompany expressCompany)
    {
    	Map<String, Object> map = new HashMap<>();
        try {
			// 根据分页参数设置limit左参数
        	expressCompany.setLimit(expressCompany.getCurrentPage(),
        			expressCompany.getPageSize());
			// 设置查询参数，区别普通查询和excel导出查询
        	expressCompany.setExcelFlag("0");
			// 请求返回状态值
			map.put("status", 1);
			// 返回分页，页码
			map.put("currentPage", expressCompany.getCurrentPage());
			// 返回数据条数
			map.put("pageNumber",
					expressCompanyService.selectExpressCompanyPageNumber(expressCompany));
			// 返回列表数据
			map.put("request",
					expressCompanyService.selectExpressCompanyList(expressCompany));
			// 请求返回状态信息
			map.put("msg", "查询成功");
		} catch (Exception e) {
			// 请求返回状态值
			map.put("status", 0);
			// 请求返回状态信息
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return map;
    }

    /**
     * 导出快递公司列表
     * @param response
     * @param name
     * @throws IOException
     * @throws ServiceException
     * @throws ParsePropertyException
     * @throws InvalidFormatException
     */
    @RequestMapping("/excelDownload")
	public void excelDownloadtest(HttpServletResponse response,
			@RequestBody Map<String, Object> name) throws IOException,
			ServiceException, ParsePropertyException, InvalidFormatException {
		// 数据查询项入参参数声明
    	ExpressCompany expressCompany = new ExpressCompany();
    	expressCompany.setName(name.get("params").toString());
		// 查询标志，excel查询，不考虑limit
    	expressCompany.setExcelFlag("1");
		// 查询数据
		List<ExpressCompany> date = expressCompanyService
				.selectExpressCompanyList(expressCompany);
		
		// 整理格式
		//声明行数据存储变量
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		//声明行数据存储变量
		Map<String, Object> data0 ;
		for (ExpressCompany da : date) {
			data0 = new HashMap<String, Object>();
			data0.put("name", da.getName());
			data0.put("quantity", da.getQuantity().toString());
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
		ExcelUtil.exportExcel(response, dataList,"快递公司信息","快递公司");
    }


    /**
     * 新增快递公司
     */
    @GetMapping(value = "/add")
    public Map<String, Object> add(ExpressCompany expressCompany)
    {
    	Map<String, Object> map = new HashMap<>();
		try {

			// 存储新增返回值
			int sum = expressCompanyService.insertExpressCompany(expressCompany);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				// 新增返回状态值
				map.put("status", 2);
				// 前端返回信息
				map.put("msg", "名称已存在，新增失败");
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
     * 修改快递公司
     */
    @GetMapping(value = "/edit")
    public Map<String, Object> edit(ExpressCompany expressCompany)
    {
    	Map<String, Object> map = new HashMap<>();
		try {
			// 存储修改返回值
			int sum = expressCompanyService.updateExpressCompany(expressCompany);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				// 请求返回状态值
				map.put("status", 2);
				// 请求返回状态信息
				map.put("msg", "名称已存在，修改失败");
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
     * 删除快递公司
     */
    @PostMapping("/remove")
    public Map<String, Object> remove(@RequestBody List<String> list)
    {
    	Map<String, Object> map = new HashMap<>();
		try {
			// 存储删除返回值
			int sum = expressCompanyService.deleteExpressCompanyByIds(list);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				map.put("status", 2);
				map.put("msg", "选中行已有发单记录,删除失败");
			} else {
				map.put("status", 1);
				map.put("request", sum);
				map.put("msg", "删除成功");
			}
		} catch (Exception e) {
			map.put("status", 0);
			map.put("msg", e.getMessage());
		}
		return map;
    }
}
