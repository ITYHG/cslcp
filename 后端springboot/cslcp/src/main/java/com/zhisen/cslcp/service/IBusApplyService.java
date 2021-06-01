package com.zhisen.cslcp.service;

import java.util.List;

import com.zhisen.cslcp.dao.entity.BusApply;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 服务开通申请Service接口
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
public interface IBusApplyService 
{
    /**
     * 查询服务开通申请
     * 
     * @param id 服务开通申请ID
     * @return 服务开通申请
     * @throws ServiceException 
     */
    public BusApply selectBusApplyById(String id) throws ServiceException;

    /**
     * 查询服务开通申请列表
     * 
     * @param busApply 服务开通申请
     * @return 服务开通申请集合
     * @throws ServiceException 
     */
    public List<BusApply> selectBusApplyList(BusApply busApply) throws ServiceException;

    /**
     * 新增服务开通申请
     * 
     * @param busApply 服务开通申请
     * @return 结果
     * @throws ServiceException 
     */
    public int insertBusApply(BusApply busApply) throws ServiceException;

    /**
     * 修改服务开通申请
     * 
     * @param busApply 服务开通申请
     * @return 结果
     * @throws ServiceException 
     */
    public int updateBusApply(BusApply busApply) throws ServiceException;

    /**
     * 批量删除服务开通申请
     * 
     * @param ids 需要删除的服务开通申请ID
     * @return 结果
     * @throws ServiceException 
     */
    public int deleteBusApplyByIds(List<String> ids) throws ServiceException;

    /**
     * 删除服务开通申请信息
     * 
     * @param id 服务开通申请ID
     * @return 结果
     * @throws ServiceException 
     */
    public int deleteBusApplyById(Long id) throws ServiceException;

    /**
     * 查询服务开通申请列表数据数量
     * 
     * @param busApply 服务开通申请
     * @return 服务开通申请列表数据数量
     * @throws ServiceException 
     */
	public int selectBusApplyPageNumber(BusApply busApply) throws ServiceException;
}
