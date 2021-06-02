package com.zhisen.cslcp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhisen.cslcp.dao.MembertypeMapper;
import com.zhisen.cslcp.dao.entity.Membertype;
import com.zhisen.cslcp.service.IMembertypeService;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 会员类型Service业务层处理
 * 
 * @author baiyu
 * @date 2021-05-18
 */
@Service
public class MembertypeServiceImpl implements IMembertypeService {
	@Autowired
	private MembertypeMapper membertypeMapper;

	/**
	 * 查询数据总条数
	 * 
	 * @return 数据总条数
	 * @throws ServiceException
	 */
	@Override
	public int selectMembertypePageNumber(Membertype membertype)
			throws ServiceException {
		try {
			return membertypeMapper.selectMembertypePageNumber(membertype);
		} catch (Exception e) {
			throw new ServiceException("查询数据总条数错误", e);
		}
	}

	/**
	 * 查询会员类型列表
	 * 
	 * @param membertype
	 *            会员类型
	 * @return 会员类型
	 * @throws ServiceException
	 */
	@Override
	public List<Membertype> selectMembertypeList(Membertype membertype)
			throws ServiceException {

		try {
			return membertypeMapper.selectMembertypeList(membertype);
		} catch (Exception e) {
			throw new ServiceException("查询会员类型列表错误", e);
		}
	}

	/**
	 * 新增会员类型
	 * 
	 * @param membertype
	 *            会员类型
	 * @return 结果
	 * @throws ServiceException
	 */
	@Override
	public int insertMembertype(Membertype membertype) throws ServiceException {

		try {
			// 查询充值金额已存在数
			int sumAmount = membertypeMapper
					.selectMembertypeAmountNumber(membertype);
			// 查询会员名称已存在数
			int sumName = membertypeMapper
					.selectMembertypeNameNumber(membertype);
			// 如果充值金额、会员名称任何一个已存在返回-1，else返回新增更改行数
			if (sumAmount > 0 || sumName > 0) {
				return -1;
			} else {
				return membertypeMapper.insertMembertype(membertype);
			}
		} catch (Exception e) {
			throw new ServiceException("新增会员类型错误", e);
		}
	}

	/**
	 * 修改会员类型
	 * 
	 * @param membertype
	 *            会员类型
	 * @return 结果
	 * @throws ServiceException
	 */
	@Override
	public int updateMembertype(Membertype membertype) throws ServiceException {

		try {
			// 查询充值金额已存在数
			int sumAmount = membertypeMapper
					.selectMembertypeAmountNumber(membertype);
			// 查询会员名称已存在数
			int sumName = membertypeMapper
					.selectMembertypeNameNumber(membertype);
			// 如果充值金额、会员名称任何一个已存在返回-1，else返回新增更改行数
			if (sumAmount > 0 || sumName > 0) {
				return -1;
			} else {
				return membertypeMapper.updateMembertype(membertype);
			}
		} catch (Exception e) {
			throw new ServiceException("修改会员类型错误", e);
		}
	}

	/**
	 * 批量删除会员类型
	 * 
	 * @param ids
	 *            需要删除的会员类型ID
	 * @return 结果
	 * @throws ServiceException
	 */
	@Override
	public int deleteMembertypeByIds(List<String> ids) throws ServiceException {

		try {
			// 查询使用数量，大于零为有已使用
			int sum = membertypeMapper.selectMembertypeInCompany(ids);
			// 已使用，返回-1，否则执行删除，返回更改行数
			if (sum > 0) {
				return -1;
			} else {
				return membertypeMapper.deleteMembertypeByIds(ids);
			}
		} catch (Exception e) {
			throw new ServiceException("批量删除会员类型错误", e);
		}
	}

}
