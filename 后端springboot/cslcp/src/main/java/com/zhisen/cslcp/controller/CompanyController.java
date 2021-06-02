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

import com.zhisen.cslcp.dao.entity.Company;
import com.zhisen.cslcp.service.ICompanyService;
import com.zhisen.cslcp.service.exception.ServiceException;
import com.zhisen.cslcp.utils.ExcelUtil;

import net.sf.jxls.exception.ParsePropertyException;

/**
 * 企业信息Controller
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
@RestController
@RequestMapping("/company")
public class CompanyController
{
    @Autowired
    private ICompanyService companyService;

    /**
     * 查询企业信息列表
     */

    @GetMapping("/list")
    public Map<String, Object> list(Company company)
    {
    	
    	Map<String, Object> map = new HashMap<>();
		try {
			// 根据分页参数设置limit左参数
			company.setLimit(company.getCurrentPage(),company.getPageSize());
			// 设置查询参数，区别普通查询和excel导出查询
			company.setExcelFlag("0");
			// 请求返回状态值
			map.put("status", 1);
			// 返回分页，页码
			map.put("currentPage",company.getCurrentPage());
			// 返回数据条数
			map.put("pageNumber",
					companyService.selectCompanyPageNumber(company));
		
			// 返回列表数据
			map.put("request",
					companyService.selectCompanyList(company));
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
     * 导出企业信息列表
     */

    @RequestMapping("/excelDownload")
	public void excelDownloadtest(HttpServletResponse response,
			@RequestBody Map<String, Object> name) throws IOException,
			ServiceException, ParsePropertyException, InvalidFormatException {
		// 数据查询项入参参数声明
    	Company company = new Company();
    	company.setName(name.get("params").toString());
		// 查询标志，excel查询，不考虑limit
    	company.setExcelFlag("1");
		// 查询数据
		List<Company> date = companyService
				.selectCompanyExcel(company);
		// 整理格式
		//声明行数据存储变量
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		//声明行数据存储变量
		Map<String, Object> data0 ;
		for (Company da : date) {
			data0 = new HashMap<String, Object>();
			data0.put("name", da.getName());
			data0.put("companyTypeName", da.getCompanyTypeName());
			data0.put("memberTypeName", da.getMemberTypeName());
			data0.put("level", da.getLevel());
			data0.put("countryName", da.getCountryName());
			data0.put("provinceName", da.getProvinceName());
			data0.put("cityName", da.getCityName());
			data0.put("address", da.getAddress());
			data0.put("creditcode", da.getCreditcode());
			data0.put("phone", da.getPhone());
			data0.put("email", da.getEmail());
			data0.put("status", da.getStatus());
			data0.put("issendbl", da.getIssendbl());
			data0.put("isreceive", da.getIsreceive());
			data0.put("isenableseal", da.getIsenableseal());
			data0.put("createdate", da.getCreatedate());
			data0.put("createusername", da.getCreateusername());
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
		ExcelUtil.exportExcel(response, dataList,"企业信息","企业信息");
		
	}   
    /**
     * 新增企业信息
     */
    @GetMapping(value = "/add")
    public Map<String, Object> add(Company company)
    {
        Map<String, Object> map = new HashMap<>();
		try {

			// 存储新增返回值
			int sum = companyService.insertCompany(company);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				// 新增返回状态值
				map.put("status", 2);
				// 前端返回信息
				map.put("msg", "新增失败");
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
     * 修改企业信息
     */

    @GetMapping("/edit")
	public Map<String, Object> edit(Company company) {
		Map<String, Object> map = new HashMap<>();
		try {
			// 存储修改返回值
			int sum = companyService.updateCompany(company);
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
     * 删除企业信息
     */
    @PostMapping("/remove")
    public Map<String, Object> remove(@RequestBody List<String> ids)
    {
        Map<String, Object> map = new HashMap<>();
		try {
			// 存储删除返回值
			int sum = companyService.deleteCompanyByIds(ids);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				map.put("status", 2);
				map.put("msg", "选中数据已使用,删除失败");
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
    /**
     * 获取企业信息详细信息
     */
    @GetMapping("/detail")
	public Map<String, Object> detail(Company company) {
    	Map<String, Object> map = new HashMap<>();
    	
		try {
		
			// 返回列表数据
			map.put("request",
					companyService.selectCompanyDetail(company));

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

}
