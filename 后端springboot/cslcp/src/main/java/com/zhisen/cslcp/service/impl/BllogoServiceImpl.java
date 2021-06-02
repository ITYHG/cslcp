package com.zhisen.cslcp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhisen.cslcp.dao.BllogoMapper;
import com.zhisen.cslcp.dao.entity.Bllogo;
import com.zhisen.cslcp.service.IBllogoService;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 提单logoService业务层处理
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
@Service
public class BllogoServiceImpl implements IBllogoService 
{
    @Autowired
    private BllogoMapper bllogoMapper;



    /**
     * 查询提单logo列表
     * 
     * @param bllogo 提单logo
     * @return 提单logo
     * @throws ServiceException 
     */
    @Override
    public List<Bllogo> selectBllogoList(Bllogo bllogo) throws ServiceException
    {
    	try {
    		
    		return bllogoMapper.selectBllogoList(bllogo);
		} catch (Exception e) {
			throw new ServiceException("查询提单logo列表错误", e);
		}
        
    }

    /**
     * 新增提单logo
     * 
     * @param bllogo 提单logo
     * @return 结果
     * @throws ServiceException 
     */
    @Override
    public int insertBllogo(Bllogo bllogo) throws ServiceException
    {
    	try {	
    		//当前行名字是否重复
    		int sumName;
    		//入参是否有启用状态是改为启用
    		//判断是否已有启用
    		if(bllogo.getCompanyid()== null){
    			sumName = 0;
    		}else{
    			sumName = bllogoMapper.selectBllogoSame(bllogo);
    		}
			// 如果名称重复不为0返回-1，else返回新增更改行数
			if (sumName > 0 ) {
				return -1;
			} else {
				return bllogoMapper.insertBllogo(bllogo);
			}
    		
		} catch (Exception e) {
			throw new ServiceException("新增ligo错误", e);
		}
 
    }

    /**
     * 修改提单logo
     * 
     * @param bllogo 提单logo
     * @return 结果
     * @throws ServiceException 
     */
    @Override
    public int updateBllogo(Bllogo bllogo) throws ServiceException
    {
    	try {	
    		//当前行名字是否重复
    		int sumName;
    		//入参是否有启用状态是改为启用
    		//判断是否已有启用
    		if(bllogo.getCompanyid()== null){
    			sumName = 0;
    		}else{
    			sumName = bllogoMapper.selectBllogoSameNumber(bllogo);
    		}
			// 如果名称重复不为0返回-1，else返回新增更改行数
			if (sumName > 0 ) {
				return -1;
			} else {
				return bllogoMapper.updateBllogo(bllogo);
			}
    		
		} catch (Exception e) {
			throw new ServiceException("修改提单logo错误", e);
		}
        
    }
   

    /**
     * 批量删除提单logo
     * 
     * @param ids 需要删除的提单logoID
     * @return 结果
     * @throws ServiceException 
     */
    @Override
    public int deleteBllogoByIds(List<String>  ids) throws ServiceException
    {
    	try {
    		//查询已确认签章数
    		int sumName = bllogoMapper.selectBllogoConfNumber(ids);
			// 如果已确认签章数不为0返回-1，else返回新增更改行数
			if (sumName > 0) {
				return -1;
			} else {
				return bllogoMapper.deleteBllogoByIds(ids);
			}
    		
		} catch (Exception e) {
			throw new ServiceException("批量删除提单logo错误", e);
		}
        
    }
    


    /**
     * 查询提单logo列表数据数量
     * 
     * @param bllogo 提单logo
     * @return 提单logo集合数据数量
     * @throws ServiceException 
     */
	@Override
	public int selectBllogoPageNumber(Bllogo bllogo) throws ServiceException {
		try {
    		return bllogoMapper.selectBllogoPageNumber(bllogo);
		} catch (Exception e) {
			throw new ServiceException("查询提单logo列表数据数量错误", e);
		}
	}

}
