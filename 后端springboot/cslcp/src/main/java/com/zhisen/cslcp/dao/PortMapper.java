package com.zhisen.cslcp.dao;

import java.util.List;
import java.util.Map;

import com.zhisen.cslcp.dao.entity.Port;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 港口信息Mapper接口
 * 
 * @author baiqianyi
 * @date 2021-05-22
 */
public interface PortMapper 
{

    /**
     * 查询港口信息列表
     * 
     * @param port 港口信息
     * @return 港口信息集合
     */
    public List<Port> selectPortList(Port port);

    /**
     * 查询港口名称或英文名称重复的数量
     * 
     * @param port 港口信息
     * @return 港口名称或英文名称重复的数量
     */
    public int selectPortSameNumber(Port port);

    
    /**
     * 新增港口信息
     * 
     * @param port 港口信息
     * @return 结果
     */
    public int insertPort(Port port);

    /**
     * 修改港口信息
     * 
     * @param port 港口信息
     * @return 结果
     */
    public int updatePort(Port port);


    /**
     * 批量删除港口信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePortByIds(List<String> ids);

    /**
     * 查询港口信息列表数量
     * 
     * @param port 港口信息
     * @return 港口信息列表信息数量
     */
	public int selectPortPageNumber(Port port);

	/**
	 * 查询国家列表
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> selectCountryList();

	/**
	 * 查询省份列表
	 * @param id 国家id
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> selectProvinceList(String id);

	/**
	 * 查询城市列表
	 * @param id 省份id
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> selectCityList(String id);
}
