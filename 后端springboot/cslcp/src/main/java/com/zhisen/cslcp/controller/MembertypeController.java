package com.zhisen.cslcp.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import com.zhisen.cslcp.dao.entity.Membertype;
import com.zhisen.cslcp.service.IMembertypeService;
import com.zhisen.cslcp.service.exception.ServiceException;
import com.zhisen.cslcp.utils.ExcelUtil;

/**
 * 会员类型Controller
 * 
 * @author baiyu
 * @date 2021-05-18
 */
@RestController
@RequestMapping("/membertype")
public class MembertypeController {
	@Autowired
	private IMembertypeService membertypeService;

	/**
	 * 查询会员类型列表
	 */

	@GetMapping("/list")
	public Map<String, Object> list(Membertype membertype) {

		Map<String, Object> map = new HashMap<>();
		try {
			// 根据分页参数设置limit左参数
			membertype.setLimit(membertype.getCurrentPage(),
					membertype.getPageSize());
			// 设置查询参数，区别普通查询和excel导出查询
			membertype.setExcelFlag("0");
			// 请求返回状态值
			map.put("status", 1);
			// 返回分页，页码
			map.put("currentPage", membertype.getCurrentPage());
			// 返回数据条数
			map.put("pageNumber",
					membertypeService.selectMembertypePageNumber(membertype));
			// 返回列表数据
			map.put("request",
					membertypeService.selectMembertypeList(membertype));
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
	 * 导出会员类型列表
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
		Membertype membertype = new Membertype();
		membertype.setName(name.get("params").toString());
		// 查询标志，excel查询，不考虑limit
		membertype.setExcelFlag("1");
		// 查询数据
		List<Membertype> date = membertypeService
				.selectMembertypeList(membertype);
		
		// 整理格式
		//声明行数据存储变量
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		//声明行数据存储变量
		Map<String, Object> data0 ;
		for (Membertype da : date) {
			data0 = new HashMap<String, Object>();
			data0.put("name", da.getName());
			data0.put("amount", da.getAmount().toString());
			data0.put("discount", da.getDiscount().toString());
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
		ExcelUtil.exportExcel(response, dataList,"会员类型信息","会员类型");
		
	}


	/**
	 * 添加会员类型
	 */
	@GetMapping(value = "/add")
	public Map<String, Object> add(Membertype membertype) {
		Map<String, Object> map = new HashMap<>();
		try {

			// 存储新增返回值
			int sum = membertypeService.insertMembertype(membertype);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				// 新增返回状态值
				map.put("status", 2);
				// 前端返回信息
				map.put("msg", "会员名称或充值金额已存在，新增失败");
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
	 * 修改会员类型
	 */

	@GetMapping(value = "/edit")
	public Map<String, Object> edit(Membertype membertype) {

		Map<String, Object> map = new HashMap<>();
		try {
			// 存储修改返回值
			int sum = membertypeService.updateMembertype(membertype);
			// sum==-1成立则数据已使用
			if (sum == -1) {
				// 请求返回状态值
				map.put("status", 2);
				// 请求返回状态信息
				map.put("msg", "会员名称或充值金额已存在，修改失败");
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
	 * 删除会员类型
	 */

	@PostMapping("/remove")
	public Map<String, Object> remove(@RequestBody List<String> list) {

		Map<String, Object> map = new HashMap<>();
		try {
			// 存储删除返回值
			int sum = membertypeService.deleteMembertypeByIds(list);
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
	
	@RequestMapping("/exportPdf")
	public void exportUserPdf1(HttpServletResponse response) throws Exception {

		  try {
			  	String path = System.getProperty("user.dir") + "\\src\\main\\resources";
				// 模板路径
				String templatePath = path + "\\static\\版权与免责声明.pdf";
	            File file = new File(templatePath);
	            BufferedInputStream bis = null;
	            OutputStream os = null;
	            FileInputStream fileInputStream = null;
	            response.setCharacterEncoding("utf-8");
	            response.setContentType("application/pdf"); // pdf格式
	       
	            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("版权与免责声明.pdf", "UTF-8"));

	            try {
	                fileInputStream = new FileInputStream(file);
	                byte[] buff = new byte[1024*10];
	                bis = new BufferedInputStream(fileInputStream);
	                os = response.getOutputStream();
	                int i = fileInputStream.read(buff);
	                while (i != -1) {
	                    os.write(buff, 0, i);
	                    i = fileInputStream.read(buff);
	                    //os.flush();
	                }
	                os.flush();
	                os.close();
	            } catch ( IOException e ) {
	                e.printStackTrace();
	            } finally {
	                if (bis != null) {
	                    try {
	                        bis.close();
	                        fileInputStream.close();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }catch (Exception e){
	        	e.printStackTrace();
	        }
	}

	
	
}
