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

import com.zhisen.cslcp.dao.entity.Seal;
import com.zhisen.cslcp.service.ISealService;
import com.zhisen.cslcp.service.exception.ServiceException;
import com.zhisen.cslcp.utils.ExcelUtil;

/**
 * 印章Controller
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
@RestController
@RequestMapping("/seal")
public class SealController
{
    @Autowired
    private ISealService sealService;

    /**
     * 查询印章列表
     */
    @GetMapping("/list")
    public Map<String, Object> list(Seal seal)
    {
    	 Map<String, Object> map = new HashMap<>();
 		try {
 			// 根据分页参数设置limit左参数
 			seal.setLimit(seal.getCurrentPage(),
 					seal.getPageSize());
 			// 设置查询参数，区别普通查询和excel导出查询
 			seal.setExcelFlag("0");
 			// 请求返回状态值
 			map.put("status", 1);
 			// 返回分页，页码
 			map.put("currentPage", seal.getCurrentPage());
 			// 返回数据条数
 			map.put("pageNumber",
 					sealService.selectSealPageNumber(seal));
 			// 返回列表数据
 			map.put("request",
 					sealService.selectSealList(seal));
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
     * 导出印章列表
     * @param response
     * @param name
     * @throws IOException
     * @throws ServiceException
     * @throws ParsePropertyException
     * @throws InvalidFormatException
     */
    @RequestMapping("/excelDownload")
	public void excelDownloadtest(HttpServletResponse response,
			@RequestBody Map<String, Object> map) throws IOException,
			ServiceException, ParsePropertyException, InvalidFormatException {
		// 数据查询项入参参数声明
    	Seal seal = new Seal();
    	seal.setName(map.get("name").toString());
    	seal.setCompanyName(map.get("companyName").toString());
    	seal.setIsenable(map.get("isenable").toString());
		// 查询标志，excel查询，不考虑limit
    	seal.setExcelFlag("1");
		// 查询数据
		List<Seal> date = sealService.selectSealList(seal);
		
		// 整理格式
		//声明行数据存储变量
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		//声明行数据存储变量
		Map<String, Object> data0 ;
		for (Seal da : date) {
			data0 = new HashMap<String, Object>();
			data0.put("companyName", da.getCompanyName());
			data0.put("name", da.getName());
			data0.put("uploadday", da.getUploadday());
			data0.put("isenable", da.getIsenable());
			data0.put("sealtype", da.getSealtype());
			data0.put("confirmstatus", da.getConfirmstatus());
			data0.put("comments", da.getComments());
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
		ExcelUtil.exportExcel(response, dataList,"印章信息","印章信息");
    }


    

    /**
     * 新增印章
     */
    @GetMapping(value = "/add")
    public Map<String, Object> add(Seal seal)
    {
    	Map<String, Object> map = new HashMap<>();
		try {

			// 存储新增返回值
			int sum = sealService.insertSeal(seal);
			System.out.println(sum);
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
     * 修改印章
     */
    @GetMapping(value = "/edit")
    public Map<String, Object> edit(Seal seal)
    {
    	System.out.println(seal);
    	Map<String, Object> map = new HashMap<>();
		try {
			// 存储修改返回值
			int sum = sealService.updateSeal(seal);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				// 请求返回状态值
				map.put("status", 2);
				// 请求返回状态信息
				map.put("msg", "该状态不可更改，修改失败");
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
     * 用户修改印章
     */
    @GetMapping(value = "/useredit")
    public Map<String, Object> useredit(Seal seal)
    {
    	System.out.println(seal);
    	Map<String, Object> map = new HashMap<>();
		try {
			// 存储修改返回值
			int sum = sealService.userUpdateSeal(seal);
			// sum==-1成立则数据已使用
			if (sum == -2) {
				// 请求返回状态值
				map.put("status", 2);
				// 请求返回状态信息
				map.put("msg", "同类签章只能同时启用一个");
			}else if (sum == -1) {
				// sum==-1成立则数据已使用
				// 请求返回状态值
				map.put("status", 2);
				// 请求返回状态信息
				map.put("msg", "该状态不可更改，修改失败");
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
     * 删除印章
     */
    @PostMapping("/remove")
    public Map<String, Object> remove(@RequestBody List<String> ids)
    {
		Map<String, Object> map = new HashMap<>();
		try {
			// 存储删除返回值
			int sum = sealService.deleteSealByIds(ids);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				map.put("status", 2);
				map.put("msg", "该状态不可更改,删除失败");
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
