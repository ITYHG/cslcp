package com.zhisen.cslcp.dao;

import java.util.List;

import com.zhisen.cslcp.dao.entity.Seal;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 印章Mapper接口
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
public interface SealMapper 
{
    /**
     * 查询印章
     * 
     * @param id 印章ID
     * @return 印章
     */
    public Seal selectSealById(Long id);

    /**
     * 查询印章列表
     * 
     * @param seal 印章
     * @return 印章集合
     */
    public List<Seal> selectSealList(Seal seal);

    /**
     * 新增印章
     * 
     * @param seal 印章
     * @return 结果
     */
    public int insertSeal(Seal seal);

    /**
     * 修改印章
     * 
     * @param seal 印章
     * @return 结果
     */
    public int updateSeal(Seal seal);


    /**
     * 批量删除印章
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSealByIds(List<String> ids);

    /**
     * 查询印章列表数据数量
     * 
     * @param seal 印章
     * @return 印章集合数据数量
     * @throws ServiceException 
     */
	public int selectSealPageNumber(Seal seal);
	
	/**
     * 查询名称重复数量
     * 
     * @param seal 印章
     * @return 查询名称重复数量
     * @throws ServiceException 
     */
	public int selectSealSameNumber(Seal seal);
	
	/**
	 * 查询该公司该类型印章启用数量
	 * @param seal
	 * @return
	 */
	public int selectSealTypeNumber(Seal seal);
	
	/**
     * 查询选中印章已确认数量
     * 
     * @param ids 需要删除的数据ID
     * @return 查询选中印章已确认数量
     * @throws ServiceException 
     */
	public int selectSealConfNumber(List<String> ids);
	
	
}
