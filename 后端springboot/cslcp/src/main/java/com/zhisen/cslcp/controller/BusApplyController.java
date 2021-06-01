package com.zhisen.cslcp.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhisen.cslcp.dao.entity.BusApply;
import com.zhisen.cslcp.service.IBusApplyService;
import com.zhisen.cslcp.service.exception.ServiceException;
import com.zhisen.cslcp.utils.ExcelUtil;
import com.zhisen.cslcp.utils.ZipDownloadUtil;

/**
 * 服务开通申请Controller
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
@RestController
@RequestMapping("/busApply")
public class BusApplyController
{
    @Autowired
    private IBusApplyService busApplyService;

    /**
     * 查询服务开通申请列表
     */
    @GetMapping("/list")
    public Map<String, Object> list(BusApply busApply)
    {
        Map<String, Object> map = new HashMap<>();
		try {
			// 根据分页参数设置limit左参数
			busApply.setLimit(busApply.getCurrentPage(),
					busApply.getPageSize());
			// 设置查询参数，区别普通查询和excel导出查询
			busApply.setExcelFlag("0");
			// 请求返回状态值
			map.put("status", 1);
			// 返回分页，页码
			map.put("currentPage", busApply.getCurrentPage());
			// 返回数据条数
			map.put("pageNumber",
					busApplyService.selectBusApplyPageNumber(busApply));
			// 返回列表数据
			map.put("request",
					busApplyService.selectBusApplyList(busApply));
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
     * 查询服务开通申请列表
     */
    @GetMapping("/byid")
    public Map<String, Object> byid(BusApply busApply)
    {
    	Map<String, Object> map = new HashMap<>();
		try {
			
			BusApply busApply01 = busApplyService.selectBusApplyById(busApply.getId().toString());
			List<String> list = new ArrayList<String>();
			String fileString = busApply01.getFiles();
	        String[] fileAddress = fileString.split(",");

	        for(String file:fileAddress){
	        	list.add(file);
		    }
			// 返回列表数据
			map.put("request",busApply01);
			map.put("filelist",list);
			
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
     * 查询服务开通申请列表
     */
    @GetMapping("/busApplylist")
    public Map<String, Object> cmpanyApplylist(BusApply busApply)
    {
        Map<String, Object> map = new HashMap<>();
		try {
			BusApply busApply01 = busApplyService.selectBusApplyById(busApply.getId().toString());
			List<String> list = new ArrayList<String>();
			String fileString = busApply01.getFiles();
	        String[] fileAddress = fileString.split(",");

	        for(String file:fileAddress){
	        	list.add(file);
		    }
			// 根据分页参数设置limit左参数
			busApply.setLimit(busApply.getCurrentPage(),
					busApply.getPageSize());
			// 设置查询参数，区别普通查询和excel导出查询
			busApply.setExcelFlag("0");
			// 请求返回状态值
			map.put("status", 1);
			// 返回分页，页码
			map.put("currentPage", busApply.getCurrentPage());
			// 返回数据条数
			map.put("pageNumber",
					busApplyService.selectBusApplyPageNumber(busApply));
			// 返回列表数据
			map.put("request",
					busApplyService.selectBusApplyList(busApply));
			map.put("filelist",list);
			
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
     * 导出服务开通申请列表
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
    	BusApply busApply = new BusApply();
    	busApply.setCompanyName(name.get("companyName").toString());
    	busApply.setCompanyid((name.get("companyid").toString()));
    	busApply.setType((name.get("type").toString()));
    	busApply.setAuditstatus((name.get("auditstatus").toString()));
		// 查询标志，excel查询，不考虑limit
    	busApply.setExcelFlag("1");
		// 查询数据
		List<BusApply> date = busApplyService.selectBusApplyList(busApply);
		
		// 整理格式
		//声明行数据存储变量
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		//声明行数据存储变量
		Map<String, Object> data0 ;
		for (BusApply da : date) {
			data0 = new HashMap<String, Object>();
			data0.put("companyName", da.getCompanyName());
			if("1".equals(da.getType())){
				data0.put("type", "出单服务");
			}else if("2".equals(da.getType())){
				data0.put("type", "收单服务");
			}else{
				data0.put("type", "签章服务");
			}
			data0.put("applyaccount", da.getApplyaccount());
			data0.put("applyusername", da.getApplyusername());
			data0.put("applydate", da.getApplydate());
			if(da.getAuditstatus() == "1"){
				data0.put("auditstatus","未审核" );
			}else{
				data0.put("auditstatus","已审核" );
			}
			if(da.getAuditresult() == 1){
				data0.put("auditresult","未通过");
			}else{
				data0.put("auditresult","已通过");
			}
			data0.put("auditopinion", da.getAuditopinion());

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
		ExcelUtil.exportExcel(response, dataList,"服务开通申请","服务开通申请");
    }

    
    @RequestMapping(value = "zipDownload", method = RequestMethod.POST)
    public String zipDownload(HttpServletRequest request, HttpServletResponse response , @RequestBody String id) {
    	BusApply busApply = new BusApply();
    	try {
			busApply = busApplyService.selectBusApplyById(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<File> files = new ArrayList<>();
        String fileString = busApply.getFiles();
        String[] fileAddress = fileString.split(",");

        for(String file:fileAddress){
        	files.add(new File("D:/file"+file));
	    }
        ZipDownloadUtil.zipDownload(response, "demo.zip", files);
        return "download success";
    }




    /**
     * 新增服务开通申请
     */
	@GetMapping(value = "/add")
    public Map<String, Object> add(BusApply busApply)
    {
    	Map<String, Object> map = new HashMap<>();
		try {
			System.out.println(busApply);

			// 存储新增返回值
			int sum = busApplyService.insertBusApply(busApply);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				// 新增返回状态值
				map.put("status", 2);
				// 前端返回信息
				map.put("msg", "该类型服务申请存在待审核申请或已开通");
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
     * 修改服务开通申请
     */
	@GetMapping(value = "/edit")
    public Map<String, Object> edit(BusApply busApply)
    {
    	Map<String, Object> map = new HashMap<>();
		try {
			// 存储修改返回值
			int sum = busApplyService.updateBusApply(busApply);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				// 请求返回状态值
				map.put("status", 2);
				// 请求返回状态信息
				map.put("msg", "该类型服务申请存在待审核申请或已开通");
			} else if(sum == -2) {
				// 请求返回状态值
				map.put("status", 3);
				// 请求返回状态信息
				map.put("msg", "该行已审核不可修改");
				}else{
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
     * 删除服务开通申请
     */
	@PostMapping("/remove")
    public Map<String, Object> remove(@RequestBody List<String> ids)
    {
		Map<String, Object> map = new HashMap<>();
		try {
			// 存储删除返回值
			int sum = busApplyService.deleteBusApplyByIds(ids);
			System.out.println(sum);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				map.put("status", 2);
				map.put("msg", "已审核数据不可删除");
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
