package com.zhisen.cslcp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhisen.cslcp.dao.CompanyMapper;
import com.zhisen.cslcp.dao.entity.Company;
import com.zhisen.cslcp.service.ICompanyService;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 企业信息Service业务层处理
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
@Service
public class CompanyServiceImpl implements ICompanyService 
{
    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 打印企业信息列表
     * 
     * @param company 企业信息
     * @return 企业信息
     * @throws ServiceException
     */
    @Override
    public List<Company> selectCompanyExcel(Company company) throws ServiceException
    {
    	try{
    		return companyMapper.selectCompanyExcel(company);
		} catch (Exception e) {
			throw new ServiceException("查询企业信息列表错误", e);
		}
    }

    /**
     * 查询企业信息列表
     * 
     * @param company 企业信息
     * @return 企业信息
     * @throws ServiceException
     */
    @Override
    public List<Company> selectCompanyList(Company company) throws ServiceException
    {
    	try{
    		return companyMapper.selectCompanyList(company);
		} catch (Exception e) {
			throw new ServiceException("查询企业信息列表错误", e);
		}
    }

    /**
     * 新增企业信息
     * 
     * @param company 企业信息
     * @return 结果
     * @throws ServiceException
     */
    @Override
    public int insertCompany(Company company) throws ServiceException
    {
    	try{
    		return companyMapper.insertCompany(company);
		} catch (Exception e) {
			throw new ServiceException("新增企业信息错误", e);
		}
        
    }

    /**
     * 修改企业信息
     * 
     * @param company 企业信息
     * @return 结果
     * @throws ServiceException
     */
    @Override
    public int updateCompany(Company company) throws ServiceException
    {
    	try {
    		//转换格式，调mapper接口
    		List<String> ids = new ArrayList<String>();
    		ids.add(company.getId().toString());
    		//查询名称重复签章数
    		int sumName;
    		if(company.getId()== 1){
    			sumName = 0;
    		}else{
    			sumName = companyMapper.selectCompanySameNumber(company);
    		}
			// 如果名称重复不为0返回-1，else返回新增更改行数
			if (sumName > 0) {
				return -1;
			} else {
				return companyMapper.updateCompany(company);
			}
    		
		} catch (Exception e) {
			throw new ServiceException("修改提单logo错误", e);
		}
        
    }

    /**
     * 批量删除企业信息
     * 
     * @param ids 需要删除的企业信息ID
     * @return 结果
     * @throws ServiceException
     */
    @Override
    public int deleteCompanyByIds(List<String> ids) throws ServiceException
    {
    	try{
    		return companyMapper.deleteCompanyByIds(ids);
		} catch (Exception e) {
			throw new ServiceException("批量删除企业信息错误", e);
		}
        
    }

    /**
     * 删除企业信息信息
     * 
     * @param id 企业信息ID
     * @return 结果
     * @throws ServiceException
     */
    @Override
    public int deleteCompanyById(Long id) throws ServiceException
    {
    	try{
    		return companyMapper.deleteCompanyById(id);
		} catch (Exception e) {
			throw new ServiceException("删除企业信息信息错误", e);
		}
        
    }

    /**
     * 查询企业信息列表数据数量
     * 
     * @param company 企业信息
     * @return 企业信息集合数据数量
     * @throws ServiceException 
     */
	@Override
	public int selectCompanyPageNumber(Company company) throws ServiceException {
		try{
    		return companyMapper.selectCompanyPageNumber(company);
		} catch (Exception e) {
			throw new ServiceException("查询企业信息列表数据数量错误", e);
		}
	}

    /**
     * 查询企业信息详情
     * 
     * @param company 企业信息
     * @return 企业信息
     * @throws ServiceException
     */
	 public List<Company> selectCompanyDetail(Company company) throws ServiceException
	    {
	    	try{
	    		return companyMapper.selectCompanyDetail(company);
			} catch (Exception e) {
				throw new ServiceException("查询企业信息列表错误", e);
			}
	        
	    }
}
