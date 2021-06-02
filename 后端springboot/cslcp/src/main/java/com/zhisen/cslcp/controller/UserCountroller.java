package com.zhisen.cslcp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserCountroller {
	
	/**
     * 删除服务开通申请
     */
	@PostMapping("/login")
    public Map<String, Object> remove(@RequestBody Map<String, Object> user)
    {
		Map<String, Object> map = new HashMap<>();
		try {
			if( "张三".equals(user.get("userName")) && "123456".equals(user.get("password"))){
				map.put("status", 1);
				map.put("msg", "验证成功");
			}else if( "admin".equals(user.get("userName")) && "123456".equals(user.get("password"))){
				map.put("status", 1);
				map.put("msg", "验证成功");
			} else if( "staff".equals(user.get("userName")) && "123456".equals(user.get("password"))){
				map.put("status", 1);
				map.put("msg", "验证成功");
			} else {
				map.put("status", 2);
				map.put("msg", "账号或密码不正确");
			}
		} catch (Exception e) {
			map.put("status", 0);
			map.put("msg", e.getMessage());
		}
		return map;
    }
	
	@PostMapping("/menu")
    public Map<String, Object> menu(@RequestBody Map<String, Object> user)
    {
		Map<String, Object> map = new HashMap<>();
		try {
			if( "张三".equals(user.get("userName"))){
				List<Map<String, Object>> dataList =new ArrayList<Map<String,Object>>();
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("path", "/home/UserDetails");
				dataMap.put("icon", "el-icon-notebook-2");
				dataMap.put("title", "企业信息");
				dataList.add(dataMap);
				
				Map<String, Object> dataMap1 = new HashMap<String, Object>();
				dataMap1.put("path", "/home/UserSeal");
				dataMap1.put("icon", "el-icon-coordinate");
				dataMap1.put("title", "印章");
				dataList.add(dataMap1);
				
				Map<String, Object> dataMap2 = new HashMap<String, Object>();
				dataMap2.put("path", "/home/UserLadingLogo");
				dataMap2.put("icon", "el-icon-s-grid");
				dataMap2.put("title", "提单LOGO");
				dataList.add(dataMap2);
				
				Map<String, Object> dataMap4 = new HashMap<String, Object>();
				dataMap4.put("path", "/home/UserServiceOpen");
				dataMap4.put("icon", "el-icon-setting");
				dataMap4.put("title", "服务开通申请");
				dataList.add(dataMap4);
				
				map.put("status", 1);
				map.put("resultData", dataList);
				map.put("msg", "验证成功");
			}else if( "admin".equals(user.get("userName"))){
				List<Map<String, Object>> dataList =new ArrayList<Map<String,Object>>();
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("path", "/home/CustumerLevel");
				dataMap.put("icon", "el-icon-user");
				dataMap.put("title", "客户级别");
				dataList.add(dataMap);
				
				Map<String, Object> dataMap0 = new HashMap<String, Object>();
				dataMap0.put("path", "/home/Members");
				dataMap0.put("icon", "el-icon-trophy");
				dataMap0.put("title", "会员类型");
				dataList.add(dataMap0);
				
				Map<String, Object> dataMap1 = new HashMap<String, Object>();
				dataMap1.put("path", "/home/PortInformation");
				dataMap1.put("icon", "el-icon-ship");
				dataMap1.put("title", "港口信息");
				dataList.add(dataMap1);
				
				Map<String, Object> dataMap2 = new HashMap<String, Object>();
				dataMap2.put("path", "/home/Courier");
				dataMap2.put("icon", "el-icon-truck");
				dataMap2.put("title", "快递公司");
				dataList.add(dataMap2);
				
				Map<String, Object> dataMap3 = new HashMap<String, Object>();
				dataMap3.put("path", "/home/CompanyType");
				dataMap3.put("icon", "el-icon-office-building");
				dataMap3.put("title", "公司类型");
				dataList.add(dataMap3);
				
				map.put("status", 1);
				map.put("resultData", dataList);
				map.put("msg", "验证成功");
			} else if( "staff".equals(user.get("userName"))){
				List<Map<String, Object>> dataList =new ArrayList<Map<String,Object>>();
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("path", "/home/HomePage");
				dataMap.put("icon", "el-icon-s-home");
				dataMap.put("title", "统计信息");
				dataList.add(dataMap);

				Map<String, Object> dataMap1 = new HashMap<String, Object>();
				dataMap1.put("path", "/home/EnterpriseInformation");
				dataMap1.put("icon", "el-icon-notebook-2");
				dataMap1.put("title", "企业信息");
				dataList.add(dataMap1);
				
				Map<String, Object> dataMap2 = new HashMap<String, Object>();
				dataMap2.put("path", "/home/Seal");
				dataMap2.put("icon", "el-icon-coordinate");
				dataMap2.put("title", "印章");
				dataList.add(dataMap2);
				
				Map<String, Object> dataMap3 = new HashMap<String, Object>();
				dataMap3.put("path", "/home/LadingLogo");
				dataMap3.put("icon", "el-icon-s-grid");
				dataMap3.put("title", "提单LOGO");
				dataList.add(dataMap3);
				
				Map<String, Object> dataMap4 = new HashMap<String, Object>();
				dataMap4.put("path", "/home/ServiceOpen");
				dataMap4.put("icon", "el-icon-setting");
				dataMap4.put("title", "服务开通申请");
				dataList.add(dataMap4);
				
				map.put("status", 1);
				map.put("resultData", dataList);
				map.put("msg", "验证成功");
			} else {
				map.put("status", 2);
				map.put("msg", "账号或密码不正确");
			}
		} catch (Exception e) {
			map.put("status", 0);
			map.put("msg", e.getMessage());
		}
		return map;
    }
	
	@PostMapping("/userPermission")
    public Map<String, Object> userPermission(@RequestBody Map<String, Object> user)
    {
		
		Map<String, Object> map = new HashMap<>();
		try {
			if( "张三".equals(user.get("userName"))){
				List<String> dataList  = new ArrayList<String>();
				dataList.add("/Login");
				dataList.add("/home");
				dataList.add("/home/UserDetails");
				dataList.add("/home/UserSeal");
				dataList.add("/home/UserLadingLogo");
				dataList.add("/home/UserServiceOpen");
				map.put("status", 1);	
				map.put("resultData", dataList);
				map.put("msg", "验证成功");
				map.put("userCompanyId", "2");
				map.put("userCompanyPosition", "user");
			}else if( "admin".equals(user.get("userName"))){
				List<String> dataList  = new ArrayList<String>();
				dataList.add("/Login");
				dataList.add("/home");
				dataList.add("/home/CustumerLevel");
				dataList.add("/home/Members");
				dataList.add("/home/PortInformation");
				dataList.add("/home/Courier");
				dataList.add("/home/CompanyType");
				map.put("status", 1);
				map.put("resultData", dataList);
				map.put("msg", "验证成功");
				map.put("userCompanyId", "1");
				map.put("userCompanyPosition", "admin");
			} else if( "staff".equals(user.get("userName"))){
				List<String> dataList  = new ArrayList<String>();
				dataList.add("/Login");
				dataList.add("/home");
				dataList.add("/home/HomePage");
				dataList.add("/home/EnterpriseInformation");
				dataList.add("/home/Details");
				dataList.add("/home/Seal");
				dataList.add("/home/LadingLogo");
				dataList.add("/home/CompanyType");
				dataList.add("/home/ServiceOpen");
				dataList.add("/home/Review");
				dataList.add("/MyCenter");
				map.put("status", 1);
				map.put("resultData", dataList);
				map.put("msg", "验证成功");
				map.put("userCompanyId", "1");
				map.put("userCompanyPosition", "staff");
			} else {
				map.put("status", 2);
				map.put("msg", "账号或密码不正确");
			}
		} catch (Exception e) {
			map.put("status", 0);
			map.put("msg", e.getMessage());
		}
		return map;
    }

}
