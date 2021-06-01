package com.zhisen.cslcp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhisen.cslcp.dao.entity.Membertype;

/**
 * 会员类型Mapper接口
 * 
 * @author baiyu
 * @date 2021-05-18
 */
@Repository
public interface MembertypeMapper {

	/**
	 * 查询总数量
	 * 
	 * @return 总数量
	 */
	public int selectMembertypePageNumber(Membertype membertype);

	/**
	 * 查询会员类型列表
	 * 
	 * @param membertype
	 *            会员类型
	 * @return 会员类型集合
	 */
	public List<Membertype> selectMembertypeList(Membertype membertype);

	/**
	 * 查询充值金额是否已存在
	 * 
	 * @param membertype
	 * @return 充值金额存在数
	 */
	public int selectMembertypeAmountNumber(Membertype membertype);

	/**
	 * 查询会员类型名称是否已存在
	 * 
	 * @param membertype
	 * @return 充值会员类型名称存在数
	 */
	public int selectMembertypeNameNumber(Membertype membertype);

	/**
	 * 新增会员类型
	 * 
	 * @param membertype
	 *            会员类型
	 * @return 结果
	 */
	public int insertMembertype(Membertype membertype);

	/**
	 * 修改会员类型
	 * 
	 * @param membertype
	 *            会员类型
	 * @return 结果
	 */
	public int updateMembertype(Membertype membertype);

	/**
	 * 查询会员类型是否使用
	 * 
	 * @param ids
	 * @return
	 */
	public int selectMembertypeInCompany(List<String> ids);

	/**
	 * 批量删除会员类型
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deleteMembertypeByIds(List<String> ids);
}
