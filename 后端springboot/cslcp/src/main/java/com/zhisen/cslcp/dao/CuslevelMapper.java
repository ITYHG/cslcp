package com.zhisen.cslcp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhisen.cslcp.dao.entity.Cuslevel;

/**
 * 客户级别Mapper接口
 * 
 * @author baiqianyi
 * @date 2021-05-18
 */
@Repository
public interface CuslevelMapper {

	/**
	 * 查询当前查询结果总条数
	 * 
	 * @param cuslevel
	 * @return
	 */
	public int selectCuslevelPageNumber(Cuslevel cuslevel);

	/**
	 * 查询姓名是否有重复
	 * 
	 * @param cuslevel
	 * @return
	 */
	public int selectCuslevelNameNumber(Cuslevel cuslevel);

	/**
	 * 查询下限是否符合规则
	 * 
	 * @param cuslevel
	 * @return
	 */
	public int selectCuslevelLowNumber(Cuslevel cuslevel);

	/**
	 * 查询上限是否符合规则
	 * 
	 * @param cuslevel
	 * @return
	 */
	public int selectCuslevelHiNumber(Cuslevel cuslevel);

	/**
	 * 查询客户级别列表
	 * 
	 * @param cuslevel 客户级别
	 * @return 客户级别集合
	 */
	public List<Cuslevel> selectCuslevelList(Cuslevel cuslevel);

	/**
	 * 新增客户级别
	 * 
	 * @param cuslevel 客户级别
	 * @return 结果
	 */
	public int insertCuslevel(Cuslevel cuslevel);

	/**
	 * 修改客户级别
	 * 
	 * @param cuslevel 客户级别
	 * @return 结果
	 */
	public int updateCuslevel(Cuslevel cuslevel);

	/**
	 * 批量删除客户级别
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteCuslevelByIds(List<String> ids);
}
