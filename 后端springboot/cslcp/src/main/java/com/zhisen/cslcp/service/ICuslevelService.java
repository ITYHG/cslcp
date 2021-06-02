package com.zhisen.cslcp.service;

import java.util.List;

import com.zhisen.cslcp.dao.entity.Cuslevel;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 客户级别Service接口
 * 
 * @author baiqianyi
 * @date 2021-05-18
 */
public interface ICuslevelService 
{
	
	
	/**
	 * 当前查询条件返回结果数
	 * 
	 * @param cuslevel
	 * @return
	 * @throws ServiceException
	 */
	public int selectCuslevelPageNumber(Cuslevel cuslevel) throws ServiceException;

    /**
     * 查询客户级别列表
     * 
     * @param cuslevel 客户级别
     * @return 客户级别集合
     * @throws ServiceException 
     */
    public List<Cuslevel> selectCuslevelList(Cuslevel cuslevel) throws ServiceException;

    /**
     * 新增客户级别
     * 
     * @param cuslevel 客户级别
     * @return 结果
     * @throws ServiceException 
     */
    public int insertCuslevel(Cuslevel cuslevel) throws ServiceException;

    /**
     * 修改客户级别
     * 
     * @param cuslevel 客户级别
     * @return 结果
     * @throws ServiceException 
     */
    public int updateCuslevel(Cuslevel cuslevel) throws ServiceException;

    /**
     * 批量删除客户级别
     * 
     * @param ids 需要删除的客户级别ID
     * @return 结果
     * @throws ServiceException 
     */
    public int deleteCuslevelByIds(List<String> ids) throws ServiceException;


}
