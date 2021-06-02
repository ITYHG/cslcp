package com.zhisen.cslcp.service;

import java.util.List;
import java.util.Map;

import com.zhisen.cslcp.dao.entity.Port;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 港口信息Service接口
 * 
 * @author baiqianyi
 * @date 2021-05-22
 */
public interface IPortService 
{

    /**
     * 查询港口信息列表
     * 
     * @param port 港口信息
     * @return 港口信息集合
     * @throws ServiceException 
     */
    public List<Port> selectPortList(Port port) throws ServiceException;

    /**
     * 新增港口信息
     * 
     * @param port 港口信息
     * @return 结果
     * @throws ServiceException 
     */
    public int insertPort(Port port) throws ServiceException;

    /**
     * 修改港口信息
     * 
     * @param port 港口信息
     * @return 结果
     * @throws ServiceException 
     */
    public int updatePort(Port port) throws ServiceException;

    /**
     * 批量删除港口信息
     * 
     * @param ids 需要删除的港口信息ID
     * @return 结果
     * @throws ServiceException 
     */
    public int deletePortByIds(List<String> ids) throws ServiceException;

    /**
     * 查询港口信息列表数量
     * 
     * @param port 港口信息
     * @return 港口信息列表信息数量
     * @throws ServiceException 
     */
	public int selectPortPageNumber(Port port) throws ServiceException;

	/**
	 * 查询国家列表
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> selectCountryList() throws ServiceException;

	/**
	 * 查询省份列表
	 * @param id 国家id
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> selectProvinceList(String id) throws ServiceException;

	/**
	 * 查询城市列表
	 * @param id 省份id
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> selectCityList(String id) throws ServiceException;

}
