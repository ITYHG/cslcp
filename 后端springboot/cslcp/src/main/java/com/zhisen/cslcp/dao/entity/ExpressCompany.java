package com.zhisen.cslcp.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 快递公司对象 expresscompany
 * 
 * @author baiqianyi
 * @date 2021-05-22
 */
public class ExpressCompany extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 物理主键 */
    private Long id;

    /** 快递公司名称 */
    private String name;

    /** 累计发单量 */
    private Long quantity;

    /** 创建时间 */
    private Date createdate;

    /** 创建人账号ID */
    private Long createuserid;

    /** 创建人姓名 */
    private String createusername;

    /** 修改时间 */
    private Date modifydate;

    /** 修改人账号ID */
    private Long modifyuserid;

    /** 修改人姓名 */
    private String modifyusername;

    /** 删除标记 */
    private Integer isdel;

    /** 乐观锁 */
    private Long version;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setCreatedate(Date createdate) 
    {
        this.createdate = createdate;
    }

    public Date getCreatedate() 
    {
        return createdate;
    }
    public void setCreateuserid(Long createuserid) 
    {
        this.createuserid = createuserid;
    }

    public Long getCreateuserid() 
    {
        return createuserid;
    }
    public void setCreateusername(String createusername) 
    {
        this.createusername = createusername;
    }

    public String getCreateusername() 
    {
        return createusername;
    }
    public void setModifydate(Date modifydate) 
    {
        this.modifydate = modifydate;
    }

    public Date getModifydate() 
    {
        return modifydate;
    }
    public void setModifyuserid(Long modifyuserid) 
    {
        this.modifyuserid = modifyuserid;
    }

    public Long getModifyuserid() 
    {
        return modifyuserid;
    }
    public void setModifyusername(String modifyusername) 
    {
        this.modifyusername = modifyusername;
    }

    public String getModifyusername() 
    {
        return modifyusername;
    }
    public void setIsdel(Integer isdel) 
    {
        this.isdel = isdel;
    }

    public Integer getIsdel() 
    {
        return isdel;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }

	@Override
	public String toString() {
		return "ExpressCompany [id=" + id + ", name=" + name + ", quantity="
				+ quantity + ", createdate=" + createdate + ", createuserid="
				+ createuserid + ", createusername=" + createusername
				+ ", modifydate=" + modifydate + ", modifyuserid="
				+ modifyuserid + ", modifyusername=" + modifyusername
				+ ", isdel=" + isdel + ", version=" + version + "]";
	}


}
