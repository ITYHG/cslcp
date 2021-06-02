package com.zhisen.cslcp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhisen.cslcp.dao.BusApplyMapper;
import com.zhisen.cslcp.dao.entity.BusApply;
import com.zhisen.cslcp.service.IBusApplyService;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 服务开通申请Service业务层处理
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
@Service
public class BusApplyServiceImpl implements IBusApplyService {
	@Autowired
	private BusApplyMapper busApplyMapper;

	/**
	 * 查询服务开通申请
	 * 
	 * @param id
	 *            服务开通申请ID
	 * @return 服务开通申请
	 * @throws ServiceException
	 */
	@Override
	public BusApply selectBusApplyById(String id) throws ServiceException {
		try{
			return busApplyMapper.selectBusApplyById(id);
		} catch (Exception e) {
			throw new ServiceException("查询服务开通申请错误", e);
		}
		
	}

	/**
	 * 查询服务开通申请列表
	 * 
	 * @param busApply
	 *            服务开通申请
	 * @return 服务开通申请
	 * @throws ServiceException
	 */
	@Override
	public List<BusApply> selectBusApplyList(BusApply busApply)
			throws ServiceException {
		try{
			if("1".equals(busApply.getCompanyid())){
				busApply.setCompanyid(null);
			}
			return busApplyMapper.selectBusApplyList(busApply);
		} catch (Exception e) {
			throw new ServiceException("查询服务开通申请列表错误", e);
		}
		
	}

	/**
	 * 新增服务开通申请
	 * 
	 * @param busApply
	 *            服务开通申请
	 * @return 结果
	 * @throws ServiceException
	 */
	@Override
	public int insertBusApply(BusApply busApply) throws ServiceException {
		try{
			//查询服务开通申请同类型同公司待审核数量
			int auditSum = busApplyMapper.selectAuditNumber(busApply);
			//查询服务开通申请同类型同公司已通过数量
			int auditResultSum = busApplyMapper.selectAuditresultNumber(busApply);
			if(auditSum>0||auditResultSum>0){
				return -1;
			}else{
				return busApplyMapper.insertBusApply(busApply);
			}
			
		} catch (Exception e) {
			throw new ServiceException("新增服务开通申请错误", e);
		}
		
	}

	/**
	 * 修改服务开通申请
	 * 
	 * @param busApply
	 *            服务开通申请
	 * @return 结果
	 * @throws ServiceException
	 */
	@Override
	public int updateBusApply(BusApply busApply) throws ServiceException {
		try{
			BusApply data = busApplyMapper.selectBusApplyById(busApply.getId().toString());
			if("2".equals(data.getAuditstatus().toString())){
				return -2;
			}
			//查询服务开通申请同类型同公司待审核数量
			int auditSum = busApplyMapper.selectAuditNumber(busApply);
			//查询服务开通申请同类型同公司已通过数量
			int auditResultSum = busApplyMapper.selectAuditresultNumber(busApply);
			if(auditSum>0||auditResultSum>0){
				return -1;
			}else{
				return busApplyMapper.updateBusApply(busApply);
			}
		} catch (Exception e) {
			throw new ServiceException("修改服务开通申请错误", e);
		}
		
	}

	/**
	 * 批量删除服务开通申请
	 * 
	 * @param ids
	 *            需要删除的服务开通申请ID
	 * @return 结果
	 * @throws ServiceException
	 */
	@Override
	public int deleteBusApplyByIds(List<String> ids) throws ServiceException {
		try{
			//查询选中数据已审核数量
			int sum = busApplyMapper.selectAuditStatusNumber(ids);
			//选中数据已审核数量大于0返回-1
			if(sum>0){
				return -1;
			}else{
				return busApplyMapper.deleteBusApplyByIds(ids);
			}
		} catch (Exception e) {
			throw new ServiceException("批量删除服务开通申请错误", e);
		}
		
	}

	/**
	 * 删除服务开通申请信息
	 * 
	 * @param id
	 *            服务开通申请ID
	 * @return 结果
	 * @throws ServiceException
	 */
	@Override
	public int deleteBusApplyById(Long id) throws ServiceException {
		try{
			return busApplyMapper.deleteBusApplyById(id);
		} catch (Exception e) {
			throw new ServiceException("批量删除服务开通申请错误", e);
		}
	}

	/**
     * 查询服务开通申请列表数据数量
     * 
     * @param busApply 服务开通申请
     * @return 服务开通申请列表数据数量
     * @throws ServiceException 
     */
	@Override
	public int selectBusApplyPageNumber(BusApply busApply)
			throws ServiceException {
		try{
			if("1".equals(busApply.getCompanyid())){
				busApply.setCompanyid(null);
			}
			return busApplyMapper.selectBusApplyPageNumber(busApply);
		} catch (Exception e) {
			throw new ServiceException("查询服务开通申请列表数据数量错误", e);
		}
	}
}
