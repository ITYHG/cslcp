package com.zhisen.cslcp.service;

import java.util.List;

import com.zhisen.cslcp.dao.entity.ExpressCompany;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 快递公司Service接口
 * 
 * @author baiqianyi
 * @date 2021-05-22
 */
public interface IExpressCompanyService 
{


    /**
     * 查询快递公司列表
     * 
     * @param expressCompany 快递公司
     * @return 快递公司集合
     * @throws ServiceException 
     */
    public List<ExpressCompany> selectExpressCompanyList(ExpressCompany expressCompany) throws ServiceException;

    /**
     * 新增快递公司
     * 
     * @param expressCompany 快递公司
     * @return 结果
     * @throws ServiceException 
     */
    public int insertExpressCompany(ExpressCompany expressCompany) throws ServiceException;

    /**
     * 修改快递公司
     * 
     * @param expressCompany 快递公司
     * @return 结果
     * @throws ServiceException 
     */
    public int updateExpressCompany(ExpressCompany expressCompany) throws ServiceException;

    /**
     * 批量删除快递公司
     * 
     * @param list 需要删除的快递公司ID
     * @return 结果
     * @throws ServiceException 
     */
    public int deleteExpressCompanyByIds(List<String> list) throws ServiceException;

    /**
     * 查询列表数据数量
     * @param expressCompany
     * @return
     * @throws ServiceException 
     */
	public int selectExpressCompanyPageNumber(ExpressCompany expressCompany) throws ServiceException;

}
