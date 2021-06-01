package com.zhisen.cslcp.service;

import java.util.List;

import com.zhisen.cslcp.dao.entity.Bllogo;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 提单logoService接口
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
public interface IBllogoService 
{


    /**
     * 查询提单logo列表
     * 
     * @param bllogo 提单logo
     * @return 提单logo集合
     * @throws ServiceException 
     */
    public List<Bllogo> selectBllogoList(Bllogo bllogo) throws ServiceException;

    /**
     * 新增提单logo
     * 
     * @param bllogo 提单logo
     * @return 结果
     * @throws ServiceException 
     */
    public int insertBllogo(Bllogo bllogo) throws ServiceException;

    /**
     * 修改提单logo
     * 
     * @param bllogo 提单logo
     * @return 结果
     * @throws ServiceException 
     */
    public int updateBllogo(Bllogo bllogo) throws ServiceException;

    /**
     * 批量删除提单logo
     * 
     * @param ids 需要删除的提单logoID
     * @return 结果
     * @throws ServiceException 
     */
    public int deleteBllogoByIds(List<String> ids) throws ServiceException;


    /**
     * 查询提单logo列表数据数量
     * 
     * @param bllogo 提单logo
     * @return 提单logo集合数据数量
     * @throws ServiceException 
     */
	public int selectBllogoPageNumber(Bllogo bllogo) throws ServiceException;
}
