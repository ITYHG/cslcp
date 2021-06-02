package com.zhisen.cslcp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhisen.cslcp.dao.CuslevelMapper;
import com.zhisen.cslcp.dao.entity.Cuslevel;
import com.zhisen.cslcp.service.ICuslevelService;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 客户级别Service业务层处理
 * 
 * @author baiqianyi
 * @date 2021-05-18
 */
@Service
public class CuslevelServiceImpl implements ICuslevelService 
{
    @Autowired
    private CuslevelMapper cuslevelMapper;

    /**
     * 当前查询结果数
     * 
     * @param cuslevel 客户级别
     * @return 当前查询结果数
     */
	@Override
	public int selectCuslevelPageNumber(Cuslevel cuslevel) throws ServiceException {
		// TODO Auto-generated method stub
		try {
    		return cuslevelMapper.selectCuslevelPageNumber(cuslevel);
		} catch (Exception e) {
			throw new ServiceException("查询客户级别列表错误",e);
		}
	}


    /**
     * 查询客户级别列表
     * 
     * @param cuslevel 客户级别
     * @return 客户级别
     */
    @Override
    public List<Cuslevel> selectCuslevelList(Cuslevel cuslevel) throws ServiceException
    {
    	try {
    		return cuslevelMapper.selectCuslevelList(cuslevel);
		} catch (Exception e) {
			throw new ServiceException("查询客户级别列表错误",e);
		}
        
    }

    /**
     * 新增客户级别
     * 
     * @param cuslevel 客户级别
     * @return 结果
     */
    @Override
    public int insertCuslevel(Cuslevel cuslevel) throws ServiceException
    {
    	try {
    		//下限要比上限低，不符规则返回-1
    		if(cuslevel.getLow()>cuslevel.getHi()){
    			return -1;
    		}
    		//重复名数量查询
    		int sameNameSum = cuslevelMapper.selectCuslevelNameNumber(cuslevel);
    		//下限数据规则校验
    		int badLowSum = cuslevelMapper.selectCuslevelLowNumber(cuslevel);
    		//上限数据规则校验
    		int badHiSum = cuslevelMapper.selectCuslevelHiNumber(cuslevel);
    		//不符规则返回-1
    		if(sameNameSum>0||badLowSum>0||badHiSum>0) {
    			return -1;
    		}else {
    			return cuslevelMapper.insertCuslevel(cuslevel);
    		}
		} catch (Exception e) {
			throw new ServiceException("新增客户级别错误",e);
		}
        
    }

    /**
     * 修改客户级别
     * 
     * @param cuslevel 客户级别
     * @return 结果
     */
    @Override
    public int updateCuslevel(Cuslevel cuslevel) throws ServiceException
    {
    	try {
    		//下限要比上限低，不符规则返回-1
    		if(cuslevel.getLow()>cuslevel.getHi()){
    			return -1;
    		}
    		int sameNameSum = cuslevelMapper.selectCuslevelNameNumber(cuslevel);
    		int badLowSum = cuslevelMapper.selectCuslevelLowNumber(cuslevel);
    		int badHiSum = cuslevelMapper.selectCuslevelHiNumber(cuslevel);
    		if(sameNameSum>0||badLowSum>0||badHiSum>0) {
    			return -1;
    		}else {
    			return cuslevelMapper.updateCuslevel(cuslevel);
    		}
		} catch (Exception e) {
			throw new ServiceException("修改客户级别错误",e);
		}
        
    }

    /**
     * 批量删除客户级别
     * 
     * @param ids 需要删除的客户级别ID
     * @return 结果
     */
    @Override
    public int deleteCuslevelByIds(List<String> ids) throws ServiceException
    {
    	try {
    		return cuslevelMapper.deleteCuslevelByIds(ids);
		} catch (Exception e) {
			throw new ServiceException("批量删除客户级别错误",e);
		}
        
    }


   
}
