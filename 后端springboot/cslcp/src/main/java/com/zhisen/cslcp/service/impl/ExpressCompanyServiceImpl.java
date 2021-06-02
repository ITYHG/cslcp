package com.zhisen.cslcp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhisen.cslcp.dao.ExpressCompanyMapper;
import com.zhisen.cslcp.dao.entity.ExpressCompany;
import com.zhisen.cslcp.service.IExpressCompanyService;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 快递公司Service业务层处理
 * 
 * @author baiqianyi
 * @date 2021-05-22
 */
@Service
public class ExpressCompanyServiceImpl implements IExpressCompanyService {
	@Autowired
	private ExpressCompanyMapper expressCompanyMapper;

	/**
	 * 查询快递公司列表
	 * 
	 * @param expressCompany
	 *            快递公司
	 * @return 快递公司
	 * @throws ServiceException
	 */
	@Override
	public List<ExpressCompany> selectExpressCompanyList(
			ExpressCompany expressCompany) throws ServiceException {
		try {
			return expressCompanyMapper
					.selectExpressCompanyList(expressCompany);
		} catch (Exception e) {
			throw new ServiceException("查询快递公司列表错误", e);
		}
	}

	/**
	 * 新增快递公司
	 * 
	 * @param expressCompany
	 *            快递公司
	 * @return 结果
	 * @throws ServiceException
	 */
	@Override
	public int insertExpressCompany(ExpressCompany expressCompany)
			throws ServiceException {
		try {
			// 查询快递公司名称已存在数
			int sumName = expressCompanyMapper
					.selectExpressCompanyNameNumber(expressCompany);
			// 如果快递公司任何一个已存在返回-1，else返回新增更改行数
			if (sumName > 0) {
				return -1;
			} else {
				return expressCompanyMapper
						.insertExpressCompany(expressCompany);
			}
		} catch (Exception e) {
			throw new ServiceException("新增快递公司错误", e);
		}
	}

	/**
	 * 修改快递公司
	 * 
	 * @param expressCompany
	 *            快递公司
	 * @return 结果
	 * @throws ServiceException
	 */
	@Override
	public int updateExpressCompany(ExpressCompany expressCompany)
			throws ServiceException {
		try {
			// 查询快递公司名称已存在数
			int sumName = expressCompanyMapper
					.selectExpressCompanyNameNumber(expressCompany);
			// 如果快递公司任何一个已存在返回-1，else返回新增更改行数
			if (sumName > 0) {
				return -1;
			} else {
				return expressCompanyMapper
						.updateExpressCompany(expressCompany);
			}

		} catch (Exception e) {
			throw new ServiceException("修改快递公司错误", e);
		}
	}

	/**
	 * 批量删除快递公司
	 * 
	 * @param list
	 *            需要删除的快递公司ID
	 * @return 结果
	 * @throws ServiceException
	 */
	@Override
	public int deleteExpressCompanyByIds(List<String> list)
			throws ServiceException {
		try {
			// 查询选中快递公司发单量
			int sumName = expressCompanyMapper
					.selectExpressCompanyQuantityNumber(list);
			// 如果选中快递公司发单量不为0返回-1，else返回新增更改行数
			if (sumName > 0) {
				return -1;
			} else {
				return expressCompanyMapper.deleteExpressCompanyByIds(list);
			}
		} catch (Exception e) {
			throw new ServiceException("批量删除快递公司错误", e);
		}
	}

	/**
	 * 查询列表数据数量
	 * 
	 * @param expressCompany
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public int selectExpressCompanyPageNumber(ExpressCompany expressCompany)
			throws ServiceException {
		try {
			return expressCompanyMapper
					.selectExpressCompanyPageNumber(expressCompany);
		} catch (Exception e) {
			throw new ServiceException("查询列表数据数量错误", e);
		}
	}

}
