package com.zhisen.cslcp.service;

import java.util.List;

import com.zhisen.cslcp.dao.entity.Membertype;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 会员类型Service接口
 * 
 * @author baiyu
 * @date 2021-05-18
 */
public interface IMembertypeService {

	/**
	 * 查询总数量
	 * 
	 * @return 总数量
	 * @throws ServiceException
	 */
	public int selectMembertypePageNumber(Membertype membertype)
			throws ServiceException;

	/**
	 * 查询会员类型列表
	 * 
	 * @param membertype
	 *            会员类型
	 * @return 会员类型集合
	 */
	public List<Membertype> selectMembertypeList(Membertype membertype)
			throws ServiceException;

	/**
	 * 新增会员类型
	 * 
	 * @param membertype
	 *            会员类型
	 * @return 结果
	 */
	public int insertMembertype(Membertype membertype) throws ServiceException;

	/**
	 * 修改会员类型
	 * 
	 * @param membertype
	 *            会员类型
	 * @return 结果
	 */
	public int updateMembertype(Membertype membertype) throws ServiceException;

	/**
	 * 批量删除会员类型
	 * 
	 * @param ids
	 *            需要删除的会员类型ID
	 * @return 结果
	 */
	public int deleteMembertypeByIds(List<String> ids) throws ServiceException;

}
