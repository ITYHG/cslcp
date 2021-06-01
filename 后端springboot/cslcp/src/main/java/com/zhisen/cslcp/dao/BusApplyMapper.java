package com.zhisen.cslcp.dao;

import java.util.List;

import com.zhisen.cslcp.dao.entity.BusApply;
import com.zhisen.cslcp.service.exception.ServiceException;

/**
 * 服务开通申请Mapper接口
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
public interface BusApplyMapper 
{
    /**
     * 查询服务开通申请
     * 
     * @param id 服务开通申请ID
     * @return 服务开通申请
     */
    public BusApply selectBusApplyById(String id);

    /**
     * 查询服务开通申请列表
     * 
     * @param busApply 服务开通申请
     * @return 服务开通申请集合
     */
    public List<BusApply> selectBusApplyList(BusApply busApply);

    /**
     * 查询服务开通申请同类型同公司待审核数量
     * @param busApply 服务开通申请
     * @return
     */
    public int selectAuditNumber(BusApply busApply);
    
    /**
     * 查询服务开通申请同类型同公司已通过数量
     * @param busApply 服务开通申请
     * @return
     */
    public int selectAuditresultNumber(BusApply busApply);
    
    /**
     * 新增服务开通申请
     * 
     * @param busApply 服务开通申请
     * @return 结果
     */
    public int insertBusApply(BusApply busApply);

    /**
     * 修改服务开通申请
     * 
     * @param busApply 服务开通申请
     * @return 结果
     */
    public int updateBusApply(BusApply busApply);

    /**
     * 删除服务开通申请
     * 
     * @param id 服务开通申请ID
     * @return 结果
     */
    public int deleteBusApplyById(Long id);

    /**
     * 批量删除服务开通申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusApplyByIds(List<String> ids);

    /**
     * 查询选中数据已审核数量
     * @param ids 需要删除的数据ID
     * @return 选中数据已审核数量
     */
    public int selectAuditStatusNumber(List<String> ids);
    
    /**
     * 查询服务开通申请列表数据数量
     * 
     * @param busApply 服务开通申请
     * @return 服务开通申请列表数据数量
     * @throws ServiceException 
     */
	public int selectBusApplyPageNumber(BusApply busApply);
}
