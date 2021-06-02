package com.zhisen.cslcp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhisen.cslcp.dao.SealMapper;
import com.zhisen.cslcp.dao.entity.Seal;
import com.zhisen.cslcp.service.ISealService;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 印章Service业务层处理
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
@Service
public class SealServiceImpl implements ISealService 
{
    @Autowired
    private SealMapper sealMapper;

    /**
     * 查询印章
     * 
     * @param id 印章ID
     * @return 印章
     */
    @Override
    public Seal selectSealById(Long id) throws ServiceException
    {
    	try {
    		return sealMapper.selectSealById(id);
		} catch (Exception e) {
			throw new ServiceException("查询印章错误", e);
		}
        
    }

    /**
     * 查询印章列表
     * 
     * @param seal 印章
     * @return 印章
     */
    @Override
    public List<Seal> selectSealList(Seal seal) throws ServiceException
    {
    	try {
    		return sealMapper.selectSealList(seal);
		} catch (Exception e) {
			throw new ServiceException("查询印章列表错误", e);
		}
        
    }

    /**
     * 新增印章
     * 
     * @param seal 印章
     * @return 结果
     */
    @Override
    public int insertSeal(Seal seal) throws ServiceException
    {
    	try {
    		//查询名称重复签章数
    		int sumName = sealMapper.selectSealSameNumber(seal);
			// 如果名称重复不为0返回-1，else返回新增更改行数
			if (sumName > 0) {
				return -1;
			} else {
				return sealMapper.insertSeal(seal);
			}
		} catch (Exception e) {
			throw new ServiceException("新增印章错误", e);
		}
        
    }

    /**
     * 平台修改印章
     * 
     * @param seal 印章
     * @return 结果
     */
    @Override
    public int updateSeal(Seal seal) throws ServiceException
    {
    	try {
    		//转换格式，调mapper接口
    		List<String> ids = new ArrayList<String>();
    		ids.add(seal.getId().toString());
    		//平台管理员只有在待确认状态才可修改
			if ("1".equals(seal.getConfirmstatus())) {
				return sealMapper.updateSeal(seal);
			} else {
				return -1;
			}
    		
		} catch (Exception e) {
			throw new ServiceException("修改印章错误", e);
		}
        
    }

    
    /**
     * 用户修改印章
     * 
     * @param seal 印章
     * @return 结果
     */
    @Override
    public int userUpdateSeal(Seal seal) throws ServiceException
    {
    	try {
    		//转换格式，调mapper接口
    		List<String> ids = new ArrayList<String>();
    		ids.add(seal.getId().toString());
    		//查询是否不可修改
    		int sum;
    		
    		int typeSeal = 0 ;
    		//查询名称重复签章数
    		int sumName = sealMapper.selectSealSameNumber(seal);
			if("2".equals(seal.getConfirmstatus()) || "4".equals(seal.getConfirmstatus())){
			    seal.setConfirmstatus("4");
			    sum = 0;
			    if( "1".equals(seal.getIsenable())){
					typeSeal = sealMapper.selectSealTypeNumber(seal);
				}
			}else if("1".equals(seal.getConfirmstatus()) && "2".equals(seal.getIsenable())){
				sum = 0;
			}else{
				sum = 1;
			}
			//同类型章只能同时启用一个
			if( typeSeal > 0){
				return -2;
			}
			// 如果名称重复不为0返回-1，else返回新增更改行数
			if (sumName > 0 || sum > 0 ) {
				return -1;
			} else {
				return sealMapper.updateSeal(seal);
			}
    		
		} catch (Exception e) {
			throw new ServiceException("修改印章错误", e);
		}
        
    }

    /**
     * 批量删除印章
     * 
     * @param ids 需要删除的印章ID
     * @return 结果
     */
    @Override
    public int deleteSealByIds(List<String>  ids) throws ServiceException
    {
    	try {
    		//查询已确认签章数
    		int sumName = sealMapper.selectSealConfNumber(ids);
			// 如果已确认签章数不为0返回-1，else返回新增更改行数
			if (sumName > 0) {
				return -1;
			} else {
				return sealMapper.deleteSealByIds(ids);
			}
    		
		} catch (Exception e) {
			throw new ServiceException("批量删除印章错误", e);
		}
        
    }


    /**
     * 查询印章列表数据数量
     * 
     * @param seal 印章
     * @return 印章集合数据数量
     * @throws ServiceException 
     */
	@Override
	public int selectSealPageNumber(Seal seal) throws ServiceException {
		try {
    		return sealMapper.selectSealPageNumber(seal);
		} catch (Exception e) {
			throw new ServiceException("查询印章列表数据数量错误", e);
		}
	}
}
