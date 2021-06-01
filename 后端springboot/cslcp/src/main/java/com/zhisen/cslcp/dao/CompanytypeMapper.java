package com.zhisen.cslcp.dao;



import java.util.List;

import com.zhisen.cslcp.dao.entity.Companytype;


/**
 * 公司类型Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
public interface CompanytypeMapper 
{
	/**
	 * 查询当前查询结果总条数
	 * 
	 * @param cuslevel
	 * @return
	 */
	public int selectCompanytypePageNumber(Companytype companytype);
	/**
	 * 查询姓名是否有重复
	 * 
	 * @param cuslevel
	 * @return
	 */
	public int selectCompanytypeNameNumber(Companytype companytype);
    /**
     * 查询公司类型列表
     * 
     * @param companytype 公司类型
     * @return 公司类型集合
     */
    public List<Companytype> selectCompanytypeList(Companytype companytype);

    /**
     * 新增公司类型
     * 
     * @param companytype 公司类型
     * @return 结果
     */
    public int insertCompanytype(Companytype companytype);

    /**
     * 修改公司类型
     * 
     * @param companytype 公司类型
     * @return 结果
     */
    public int updateCompanytype(Companytype companytype);
    /**
     * 批量删除公司类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCompanytypeByIds(List<String> ids);
}
