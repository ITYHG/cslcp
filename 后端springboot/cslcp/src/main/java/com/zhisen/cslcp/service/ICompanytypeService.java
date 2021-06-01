package com.zhisen.cslcp.service;

import java.util.List;

import com.zhisen.cslcp.dao.entity.Companytype;
import com.zhisen.cslcp.service.exception.ServiceException;


/**
 * 公司类型Service接口
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
public interface ICompanytypeService 
{
	/**
	 * 当前查询条件返回结果数
	 * 
	 * @param companytype
	 * @return
	 * @throws ServiceException
	 */
	public int selectCompanytypePageNumber(Companytype companytype) throws ServiceException;

    /**
     * 查询公司类型列表
     * 
     * @param companytype 公司类型
     * @return 公司类型集合
     */
    public List<Companytype> selectCompanytypeList(Companytype companytype) throws ServiceException;

    /**
     * 新增公司类型
     * 
     * @param companytype 公司类型
     * @return 结果
     */
    public int insertCompanytype(Companytype companytype) throws ServiceException;

    /**
     * 修改公司类型
     * 
     * @param companytype 公司类型
     * @return 结果
     */
    public int updateCompanytype(Companytype companytype) throws ServiceException;

    /**
     * 批量删除公司类型
     * 
     * @param ids 需要删除的公司类型ID
     * @return 结果
     */
    public int deleteCompanytypeByIds(List<String> ids) throws ServiceException;
}