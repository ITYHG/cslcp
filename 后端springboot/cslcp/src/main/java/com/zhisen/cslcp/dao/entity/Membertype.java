package com.zhisen.cslcp.dao.entity;


import java.io.Serializable;

/**
 * 会员类型对象 membertype
 * 
 * @author baiyu
 * @date 2021-05-18
 */
public class Membertype extends BaseEntity  implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 物理主键 */
    private Long id;

    /** 类型名称 */

    private String name;

    /** 折扣比例 */
  
    private Long discount;

    /** 充值金额 */
 
    private Double amount;
    
    /** 乐观锁*/
    
    private Integer version;

    
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
    public void setDiscount(Long discount) 
    {
        this.discount = discount;
    }

    public Long getDiscount() 
    {
        return discount;
    }
    public void setAmount(Double amount) 
    {
        this.amount = amount;
    }

    public Double getAmount() 
    {
        return amount;
    }

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Membertype [id=" + id + ", name=" + name + ", discount="
				+ discount + ", amount=" + amount + ", version=" + version
				+ "]";
	}

	public Membertype(Long id, String name, Long discount, Double amount,
			Integer version) {
		super();
		this.id = id;
		this.name = name;
		this.discount = discount;
		this.amount = amount;
		this.version = version;
	}

	public Membertype() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Membertype(Integer currentPage, Integer pageSize, Integer limit,
			String excelFlag) {
		super(currentPage, pageSize, limit, excelFlag);
		// TODO Auto-generated constructor stub
	}

    
}
