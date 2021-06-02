package com.zhisen.cslcp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhisen.cslcp.dao.entity.Bllogo;
import com.zhisen.cslcp.service.IBllogoService;

/**
 * 提单logoController
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
@RestController
@RequestMapping("/bllogo")
public class BllogoController {
	@Autowired
	private IBllogoService bllogoService;

	/**
	 * 查询提单logo列表
	 */
	@GetMapping("/list")
	public Map<String, Object> list(Bllogo bllogo) {
		Map<String, Object> map = new HashMap<>();
		try {

			// 根据分页参数设置limit左参数
			bllogo.setLimit(bllogo.getCurrentPage(), bllogo.getPageSize());
			// 设置查询参数，区别普通查询和excel导出查询
			bllogo.setExcelFlag("0");
			// 请求返回状态值
			map.put("status", 1);
			// 返回分页，页码
			map.put("currentPage", bllogo.getCurrentPage());
			// 返回数据条数
			map.put("pageNumber", bllogoService.selectBllogoPageNumber(bllogo));
			// 返回列表数据
			map.put("request", bllogoService.selectBllogoList(bllogo));
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
	 * 新增提单logo
	 */
	@GetMapping(value = "/add")
	public Map<String, Object> add(Bllogo bllogo) {
		Map<String, Object> map = new HashMap<>();
		try {
			// 存储新增返回值
			int sum = bllogoService.insertBllogo(bllogo);
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
	 * 修改提单logo
	 */
	@GetMapping(value = "/edit")
	public Map<String, Object> edit(Bllogo bllogo) {
		Map<String, Object> map = new HashMap<>();

		try {
		
			// 存储修改返回值
			int sum = bllogoService.updateBllogo(bllogo);
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
	 * 删除提单logo
	 */
	@PostMapping("/remove")
	public Map<String, Object> remove(@RequestBody List<String> ids) {
		Map<String, Object> map = new HashMap<>();
		try {
			// 存储删除返回值
			int sum = bllogoService.deleteBllogoByIds(ids);
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