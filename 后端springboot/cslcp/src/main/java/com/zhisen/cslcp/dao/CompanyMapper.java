package com.zhisen.cslcp.dao;

import java.util.List;

import com.zhisen.cslcp.dao.entity.Company;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 企业信息Mapper接口
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
public interface CompanyMapper 
{

	
    /**
     * 查询企业信息列表
     * 
     * @param company 企业信息
     * @return 企业信息集合
     */
    public List<Company> selectCompanyList(Company company);
    
    /**
     * 打印企业信息列表
     * 
     * @param company 企业信息
     * @return 企业信息集合
     */
    public List<Company> selectCompanyExcel(Company company);

    /**
     * 新增企业信息
     * 
     * @param company 企业信息
     * @return 结果
     */
    public int insertCompany(Company company);

    /**
     * 修改企业信息
     * 
     * @param company 企业信息
     * @return 结果
     */
    public int updateCompany(Company company);

    /**
     * 删除企业信息
     * 
     * @param id 企业信息ID
     * @return 结果
     */
    public int deleteCompanyById(Long id);

    /**
     * 批量删除企业信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCompanyByIds(List<String> ids);

    /**
     * 查询企业信息列表数据数量
     * 
     * @param company 企业信息
     * @return 企业信息集合数据数量
     * @throws ServiceException 
     */
	public int selectCompanyPageNumber(Company company);
	
    /**
     * 查询企业详情
     * 
     * @param company 企业信息
     * @return 企业信息集合
     */
	public List<Company> selectCompanyDetail(Company company) ;
    
    /**
     * 查询重複企业
     * 
     * @param company 企业信息
     * @return 企业信息集合
     */
	public int selectCompanySameNumber(Company company);
		
	
}
