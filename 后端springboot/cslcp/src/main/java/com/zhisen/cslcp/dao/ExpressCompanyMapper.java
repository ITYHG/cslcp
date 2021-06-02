package com.zhisen.cslcp.dao;

import java.util.List;

import com.zhisen.cslcp.dao.entity.ExpressCompany;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 快递公司Mapper接口
 * 
 * @author baiqianyi
 * @date 2021-05-22
 */
public interface ExpressCompanyMapper 
{

    /**
     * 查询快递公司列表
     * 
     * @param expressCompany 快递公司
     * @return 快递公司集合
     */
    public List<ExpressCompany> selectExpressCompanyList(ExpressCompany expressCompany);

    /**
     * 判断名字重复
     * @param expressCompany
     * @return
     */
    public int selectExpressCompanyNameNumber(ExpressCompany expressCompany);
    
    /**
     * 新增快递公司
     * 
     * @param expressCompany 快递公司
     * @return 结果
     */
    public int insertExpressCompany(ExpressCompany expressCompany);

    /**
     * 修改快递公司
     * 
     * @param expressCompany 快递公司
     * @return 结果
     */
    public int updateExpressCompany(ExpressCompany expressCompany);

    /**
     * 批量删除快递公司
     * 
     * @param list 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpressCompanyByIds(List<String> list);

    /**
     * 查询列表数据数量
     * @param expressCompany
     * @return
     * @throws ServiceException 
     */
	public int selectExpressCompanyPageNumber(ExpressCompany expressCompany);
	
	/**
     * 查询已选中快递公司，发单数量
     * 
     * @param list 已选中快递公司ID
     * @return 发单数量
     */
	public int selectExpressCompanyQuantityNumber(List<String> list);
}
