package com.zhisen.cslcp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhisen.cslcp.dao.PortMapper;
import com.zhisen.cslcp.dao.entity.Port;
import com.zhisen.cslcp.service.IPortService;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 港口信息Service业务层处理
 * 
 * @author baiqianyi
 * @date 2021-05-22
 */
@Service
public class PortServiceImpl implements IPortService 
{
    @Autowired
    private PortMapper portMapper;


    /**
     * 查询港口信息列表
     * 
     * @param port 港口信息
     * @return 港口信息
     * @throws ServiceException 
     */
    @Override
    public List<Port> selectPortList(Port port) throws ServiceException
    {
    	try {
			return portMapper.selectPortList(port);
		} catch (Exception e) {
			throw new ServiceException("查询港口信息列表错误", e);
		}
        
    }

    /**
     * 新增港口信息
     * 
     * @param port 港口信息
     * @return 结果
     * @throws ServiceException 
     */
    @Override
    public int insertPort(Port port) throws ServiceException
    {
    	
    	try {
    		// 查询港口信息名称或英文名称已存在数
            int sumName = portMapper.selectPortSameNumber(port);
        	// 如果港口信息名称或英文名称任何一个已存在返回-1，else返回新增更改行数
        	if (sumName > 0) {
        		return -1;
        	} else {
        		return portMapper.insertPort(port);
        	}
		} catch (Exception e) {
			throw new ServiceException("新增港口信息错误", e);
		}
    	
    }

    /**
     * 修改港口信息
     * 
     * @param port 港口信息
     * @return 结果
     * @throws ServiceException 
     */
    @Override
    public int updatePort(Port port) throws ServiceException
    {
    	try {
    		// 查询港口信息名称或英文名称已存在数
            int sumName = portMapper.selectPortSameNumber(port);
        	// 如果港口信息名称或英文名称任何一个已存在返回-1，else返回新增更改行数
        	if (sumName > 0) {
        		return -1;
        	} else {
        		return portMapper.updatePort(port);
        	}
		} catch (Exception e) {
			throw new ServiceException("修改港口信息错误", e);
		}
    	
    }

    /**
     * 批量删除港口信息
     * 
     * @param ids 需要删除的港口信息ID
     * @return 结果
     * @throws ServiceException 
     */
    @Override
    public int deletePortByIds(List<String> ids) throws ServiceException
    {
    	try {
    		return portMapper.deletePortByIds(ids);
		} catch (Exception e) {
			throw new ServiceException("批量删除港口信息错误", e);
		}
        
    }

    /**
     * 查询港口信息列表数量
     * 
     * @param port 港口信息
     * @return 港口信息列表信息数量
     * @throws ServiceException 
     */
	@Override
	public int selectPortPageNumber(Port port) throws ServiceException {
		
		try {
			return portMapper.selectPortPageNumber( port);
		} catch (Exception e) {
			throw new ServiceException("查询港口信息列表数量错误", e);
		}
		
	}

	/**
	 * 查询国家列表
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<Map<String, Object>> selectCountryList()
			throws ServiceException {
		try {
			return portMapper.selectCountryList();
		} catch (Exception e) {
			throw new ServiceException("查询国家列表错误", e);
		}
		
	}

	/**
	 * 查询省份列表
	 * @param id 国家id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<Map<String, Object>> selectProvinceList(String id)
			throws ServiceException {
		try {
			return portMapper.selectProvinceList(id);
		} catch (Exception e) {
			throw new ServiceException("查询省份列表列表错误", e);
		}
		
	}

	/**
	 * 查询城市列表
	 * @param id 省份id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<Map<String, Object>> selectCityList(String id)
			throws ServiceException {
		try {
			return portMapper.selectCityList(id);
		} catch (Exception e) {
			throw new ServiceException("查询城市列表错误", e);
		}
		
	}

}
