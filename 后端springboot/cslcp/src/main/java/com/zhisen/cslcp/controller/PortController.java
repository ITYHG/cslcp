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

import com.zhisen.cslcp.dao.entity.Port;
import com.zhisen.cslcp.service.IPortService;
import com.zhisen.cslcp.service.exception.ServiceException;
import com.zhisen.cslcp.utils.ExcelUtil;

/**
 * 港口信息Controller
 * 
 * @author baiqianyi
 * @date 2021-05-22
 */
@RestController
@RequestMapping("/port")
public class PortController 
{
    @Autowired
    private IPortService portService;

    /**
     * 查询港口信息列表
     */
    @GetMapping("/list")
    public Map<String, Object> list(Port port)
    {
        Map<String, Object> map = new HashMap<>();
		try {
			// 根据分页参数设置limit左参数
			port.setLimit(port.getCurrentPage(),
					port.getPageSize());
			// 设置查询参数，区别普通查询和excel导出查询
			port.setExcelFlag("0");
			// 请求返回状态值
			map.put("status", 1);
			// 返回分页，页码
			map.put("currentPage", port.getCurrentPage());
			// 返回数据条数
			map.put("pageNumber",
					portService.selectPortPageNumber(port));
			// 返回列表数据
			map.put("request",
					portService.selectPortList(port));
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
     * 导出港口信息列表
     */
    @RequestMapping("/excelDownload")
	public void excelDownloadtest(HttpServletResponse response,
			@RequestBody Map<String, Object> name) throws IOException,
			ServiceException, ParsePropertyException, InvalidFormatException {
		// 数据查询项入参参数声明
    	Port port = new Port();
    	port.setName(name.get("name").toString());
    	port.setProvinceName(name.get("provinceName").toString());
		// 查询标志，excel查询，不考虑limit
    	port.setExcelFlag("1");
		// 查询数据
		List<Port> date = portService.selectPortList(port);
		
		// 整理格式
		//声明行数据存储变量
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		//声明行数据存储变量
		Map<String, Object> data0 ;
		for (Port da : date) {
			data0 = new HashMap<String, Object>();
			data0.put("name", da.getName());
			data0.put("ename", da.getEname());
			data0.put("countryName", da.getCountryName());
			data0.put("provinceName", da.getProvinceName());
			data0.put("cityName", da.getCityName());
			data0.put("outlay", da.getOutlay());
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
		ExcelUtil.exportExcel(response, dataList,"港口信息","港口信息");
		
	}



    /**
     * 新增港口信息
     */
	@GetMapping(value = "/add")
    public Map<String, Object> add(Port port)
    {
		Map<String, Object> map = new HashMap<>();
		try {

			// 存储新增返回值
			int sum = portService.insertPort(port);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				// 新增返回状态值
				map.put("status", 2);
				// 前端返回信息
				map.put("msg", "港口名称或英文名称已存在，新增失败");
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
     * 修改港口信息
     */
	@GetMapping(value = "/edit")
    public Map<String, Object> edit(Port port)
    {
		Map<String, Object> map = new HashMap<>();
		try {
			// 存储修改返回值
			int sum = portService.updatePort(port);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				// 请求返回状态值
				map.put("status", 2);
				// 请求返回状态信息
				map.put("msg", "港口名称或英文名称已存在，修改失败");
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
     * 删除港口信息
     */
	@PostMapping("/remove")
    public Map<String, Object> remove(@RequestBody List<String> ids)
    {
		Map<String, Object> map = new HashMap<>();
		try {
			// 存储删除返回值
			int sum = portService.deletePortByIds(ids);
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
     * 查询国家列表
     */
	@PostMapping("/cpclist")
    public Map<String, Object> cpclist(@RequestBody Map<String, Object> params)
    {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<String, Object>();
        
		try {
			
			data.put("countryList", portService.selectCountryList());
			data.put("provinceList", portService.selectProvinceList(params.get("countryId").toString()));
			data.put("cityList", portService.selectCityList(params.get("provinceId").toString()));
			// 请求返回状态值
			map.put("status", 1);
			// 返回列表数据
			map.put("data",
					data);
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
     * 查询国家列表
     */
    @GetMapping("/countrylist")
    public Map<String, Object> countryList()
    {
        Map<String, Object> map = new HashMap<>();
		try {
			// 请求返回状态值
			map.put("status", 1);
			// 返回列表数据
			map.put("request",
					portService.selectCountryList());
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
     * 查询省份列表
     */
    @PostMapping("/provincelist")
    public Map<String, Object> provinceList(@RequestBody Map<String, Object> params)
    {
        Map<String, Object> map = new HashMap<>();
		try {
			// 请求返回状态值
			map.put("status", 1);
			// 返回列表数据
			map.put("request",
					portService.selectProvinceList(params.get("params").toString()));
			
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
     * 查询城市列表
     */
    @PostMapping("/citylist")
    public Map<String, Object> citylist(@RequestBody Map<String, Object> params)
    {
        Map<String, Object> map = new HashMap<>();
		try {
			// 请求返回状态值
			map.put("status", 1);
			// 返回列表数据
			map.put("request",
					portService.selectCityList(params.get("params").toString()));
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
