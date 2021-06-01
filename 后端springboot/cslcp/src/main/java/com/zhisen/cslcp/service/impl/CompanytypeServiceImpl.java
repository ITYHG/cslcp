package com.zhisen.cslcp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhisen.cslcp.dao.CompanytypeMapper;
import com.zhisen.cslcp.dao.entity.Companytype;
import com.zhisen.cslcp.service.ICompanytypeService;
import com.zhisen.cslcp.service.exception.ServiceException;



/**
 * 公司类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
@Service
public class CompanytypeServiceImpl implements ICompanytypeService 
{
    @Autowired
    private CompanytypeMapper companytypeMapper;

    /**
	 * 查询数据总条数
	 * 
	 * @return 数据总条数
	 * @throws ServiceException
	 */
	@Override
	public int selectCompanytypePageNumber(Companytype companytype)
			throws ServiceException {
		try {
			return companytypeMapper.selectCompanytypePageNumber(companytype);
		} catch (Exception e) {
			throw new ServiceException("查询数据总条数错误", e);
		}
	}

    /**
     * 查询公司类型列表
     * 
     * @param companytype 公司类型
     * @return 公司类型
     */
    @Override
	public List<Companytype> selectCompanytypeList(Companytype companytype)
			throws ServiceException {

		try {
			return companytypeMapper.selectCompanytypeList(companytype);
		} catch (Exception e) {
			throw new ServiceException("查询公司类型列表错误", e);
		}
	}
    
    /**
     * 新增公司类型
     * 
     * @param companytype 公司类型
     * @return 结果
     */

    @Override
    public int insertCompanytype(Companytype companytype) throws ServiceException
    {
    	try {
    		//重复名数量查询
    		int sameNameSum = companytypeMapper.selectCompanytypeNameNumber(companytype);
    	
    		//不符规则返回-1
    		if(sameNameSum>0) {
    			return -1;
    		}else {
    			return companytypeMapper.insertCompanytype(companytype);
    		}
		} catch (Exception e) {
			throw new ServiceException("新增公司类型错误",e);
		}
        
    }

    /**
     * 修改公司类型
     * 
     * @param companytype 公司类型
     * @return 结果
     */
	@Override
	public int updateCompanytype(Companytype companytype) throws ServiceException 

	    {
	    	try {
	    		//重复名数量查询
	    		int sameNameSum = companytypeMapper.selectCompanytypeNameNumber(companytype);
	    	
	    		//不符规则返回-1
	    		if(sameNameSum>0) {
	    			return -1;
	    		}else {
	    			return companytypeMapper.updateCompanytype(companytype);
	    		}
			} catch (Exception e) {
				throw new ServiceException("修改公司类型错误",e);
			}
	        
	    }
	
    /**
     * 批量删除公司类型
     * 
     * @param ids 需要删除的公司类型ID
     * @return 结果
     * @throws ServiceException 
     */
    @Override
    public int deleteCompanytypeByIds(List<String> ids) throws ServiceException
    {
    	  try {
      		return companytypeMapper.deleteCompanytypeByIds(ids);
  		} catch (Exception e) {
  			throw new ServiceException("批量删除公司类型错误",e);
  		}
    }
}

    