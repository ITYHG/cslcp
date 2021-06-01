package com.zhisen.cslcp.service;

import java.util.List;

import com.zhisen.cslcp.dao.entity.Company;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 企业信息Service接口
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
public interface ICompanyService 
{
    /**
     * 导出企业信息列表
     * 
     * @param company 企业信息
     * @return 企业信息集合
     * @throws ServiceException 
     */
    public List<Company> selectCompanyExcel(Company company) throws ServiceException;

    /**
     * 查询企业信息列表
     * 
     * @param company 企业信息
     * @return 企业信息集合
     * @throws ServiceException 
     */
    public List<Company> selectCompanyList(Company company) throws ServiceException;

    /**
     * 新增企业信息
     * 
     * @param company 企业信息
     * @return 结果
     * @throws ServiceException 
     */
    public int insertCompany(Company company) throws ServiceException;

    /**
     * 修改企业信息
     * 
     * @param company 企业信息
     * @return 结果
     * @throws ServiceException 
     */
    public int updateCompany(Company company) throws ServiceException;

    /**
     * 批量删除企业信息
     * 
     * @param ids 需要删除的企业信息ID
     * @return 结果
     * @throws ServiceException 
     */
    public int deleteCompanyByIds(List<String> ids) throws ServiceException;

    /**
     * 删除企业信息信息
     * 
     * @param id 企业信息ID
     * @return 结果
     * @throws ServiceException 
     */
    public int deleteCompanyById(Long id) throws ServiceException;

    /**
     * 查询企业信息列表数据数量
     * 
     * @param company 企业信息
     * @return 企业信息集合数据数量
     * @throws ServiceException 
     */
	public int selectCompanyPageNumber(Company company) throws ServiceException;
	
	/**
     * 查询企业信息详情
     * 
     * @param company 企业信息
     * @return 企业信息集合
     * @throws ServiceException 
     */
	public List<Company> selectCompanyDetail(Company company) throws ServiceException;

}
