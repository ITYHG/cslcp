package com.zhisen.cslcp.dao.entity;

import java.io.Serializable;

/**
 * 基础类对象 BaseEntity
 * 
 * @author baiqianyi
 * @date 2021-05-19
 */
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 当前页
	 */
	private Integer currentPage;

	/**
	 * 每页多少条
	 */
	private Integer pageSize;

	/**
	 * 分页左参数
	 */
	private Integer limit;

	/**
	 * excel导出查询数据判别标志
	 */
	private String excelFlag;

	public String getExcelFlag() {
		return excelFlag;
	}

	public void setExcelFlag(String excelFlag) {
		this.excelFlag = excelFlag;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer currentPage, Integer pageSize) {
		// 根据页面查询显示条数，及页数确定，limit左参数值
		if (currentPage == 1) {
			this.limit = 0;
		} else {
			this.limit = pageSize * (currentPage - 1);
		}

	}

	@Override
	public String toString() {
		return "BaseEntity [currentPage=" + currentPage + ", pageSize="
				+ pageSize + ", limit=" + limit + ", excelFlag=" + excelFlag
				+ "]";
	}

	public BaseEntity(Integer currentPage, Integer pageSize, Integer limit,
			String excelFlag) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.limit = limit;
		this.excelFlag = excelFlag;
	}

	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
