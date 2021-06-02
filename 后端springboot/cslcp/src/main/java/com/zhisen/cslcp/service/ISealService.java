package com.zhisen.cslcp.service;

import java.util.List;

import com.zhisen.cslcp.dao.entity.Seal;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 印章Service接口
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
public interface ISealService 
{
    /**
     * 查询印章
     * 
     * @param id 印章ID
     * @return 印章
     * @throws ServiceException 
     */
    public Seal selectSealById(Long id) throws ServiceException;

    /**
     * 查询印章列表
     * 
     * @param seal 印章
     * @return 印章集合
     * @throws ServiceException 
     */
    public List<Seal> selectSealList(Seal seal) throws ServiceException;

    /**
     * 新增印章
     * 
     * @param seal 印章
     * @return 结果
     * @throws ServiceException 
     */
    public int insertSeal(Seal seal) throws ServiceException;

    /**
     * 修改印章
     * 
     * @param seal 印章
     * @return 结果
     * @throws ServiceException 
     */
    public int updateSeal(Seal seal) throws ServiceException;

    /**
     * 用户修改印章
     * 
     * @param seal 印章
     * @return 结果
     * @throws ServiceException 
     */
    public int userUpdateSeal(Seal seal) throws ServiceException;
    /**
     * 批量删除印章
     * 
     * @param ids 需要删除的印章ID
     * @return 结果
     * @throws ServiceException 
     */
    public int deleteSealByIds(List<String> ids) throws ServiceException;


    /**
     * 查询印章列表数据数量
     * 
     * @param seal 印章
     * @return 印章集合数据数量
     * @throws ServiceException 
     */
	public int selectSealPageNumber(Seal seal) throws ServiceException;
}
