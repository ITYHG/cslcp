package com.zhisen.cslcp.dao;

import java.util.List;

import com.zhisen.cslcp.dao.entity.Bllogo;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 提单logoMapper接口
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
public interface BllogoMapper 
{

    /**
     * 查询提单logo列表
     * 
     * @param bllogo 提单logo
     * @return 提单logo集合
     */
    public List<Bllogo> selectBllogoList(Bllogo bllogo);

    /**
     * 新增提单logo
     * 
     * @param bllogo 提单logo
     * @return 结果
     */
    public int insertBllogo(Bllogo bllogo);

    /**
     * 修改提单logo
     * 
     * @param bllogo 提单logo
     * @return 结果
     */
    public int updateBllogo(Bllogo bllogo);

    /**
     * 删除提单logo
     * 
     * @param id 提单logoID
     * @return 结果
     */
    public int deleteBllogoById(Long id);

    /**
     * 批量删除提单logo
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBllogoByIds(List<String> ids);

    /**
     * 查询提单logo列表数据数量
     * 
     * @param bllogo 提单logo
     * @return 提单logo集合数据数量
     * @throws ServiceException 
     */
	public int selectBllogoPageNumber(Bllogo bllogo);
	
	/**
     * 查询选中logo已确认数量
     * 
     * @param ids 需要删除的数据ID
     * @return 查询选中印章已确认数量
     * @throws ServiceException 
     */
	public int selectBllogoConfNumber(List<String> ids);
	/**
     * 修改时查询logo重复数量
     * 
     * @param logo 印章
     * @return 查询名称重复数量
     * @throws ServiceException 
     */
	public int selectBllogoSameNumber(Bllogo bllogo);
	/**
     * 增添时查询logo重复数量
     * 
     * @param logo 印章
     * @return 查询名称重复数量
     * @throws ServiceException 
     */
	public int selectBllogoSame(Bllogo bllogo);
}
