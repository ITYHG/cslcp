package com.zhisen.cslcp.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业信息对象 company
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
public class Company extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 物理主键 */
    private Long id;

    /** 企业名称 */
    private String name;

    /** 公司类型ID */
    private Long companytypeid;
    
    /** 公司类型 */
    private String companyTypeName;

    /** 会员类型ID */
    private Long membertype;
    
    /** 会员类型 */
    private String memberTypeName;

    /** 客户等级 */
    private String level;
    
    /** 客户等级名称 */
    private String levelName;

    /** 所在国家 */
    private String countryId;
    
    /** 所在国家名称 */
    private String countryName;

    /** 所在省份 */
    private String provinceId;
    
    /** 所在省份名称 */
    public String provinceName;

    /** 所在城市 */
    private String cityId;
    
    /** 所在城市名称 */
    private String cityName;

    /** 详细地址 */
    private String address;

    /** 企业信用代码 */
    private String creditcode;

    /** 组织机构代码证附件1 */
    private String cerificateattachment1;

    /** 组织结构代码证附件2 */
    private String cerificateattachment2;

    /** 企业管理员手机号 */
    private String phone;
    
    /** 企业联系邮箱 */
    private String email;

    /** 企业管理员账号ID */
    private Long adminaccountid;

    /** 企业状态 */
    private String status;

    /** 是否开通出单服务 */
    private String issendbl;

    /** 是否开通发单服务 */
    private String isreceive;

    /** 是否开通签章服务 */
    private String isenableseal;

    /** 创建时间 */
    private String createdate;

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
    
    /** logo文件路径 */
    private String file;
    /** 印章文件路径1 */
    private String  Sealfile1;
    /** 印章文件路径2 */
    private String  Sealfile2;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCompanytypeid() {
		return companytypeid;
	}
	public void setCompanytypeid(Long companytypeid) {
		this.companytypeid = companytypeid;
	}
	public String getCompanyTypeName() {
		return companyTypeName;
	}
	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}
	public Long getMembertype() {
		return membertype;
	}
	public void setMembertype(Long membertype) {
		this.membertype = membertype;
	}
	public String getMemberTypeName() {
		return memberTypeName;
	}
	public void setMemberTypeName(String memberTypeName) {
		this.memberTypeName = memberTypeName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreditcode() {
		return creditcode;
	}
	public void setCreditcode(String creditcode) {
		this.creditcode = creditcode;
	}
	public String getCerificateattachment1() {
		return cerificateattachment1;
	}
	public void setCerificateattachment1(String cerificateattachment1) {
		this.cerificateattachment1 = cerificateattachment1;
	}
	public String getCerificateattachment2() {
		return cerificateattachment2;
	}
	public void setCerificateattachment2(String cerificateattachment2) {
		this.cerificateattachment2 = cerificateattachment2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getAdminaccountid() {
		return adminaccountid;
	}
	public void setAdminaccountid(Long adminaccountid) {
		this.adminaccountid = adminaccountid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIssendbl() {
		return issendbl;
	}
	public void setIssendbl(String issendbl) {
		this.issendbl = issendbl;
	}
	public String getIsreceive() {
		return isreceive;
	}
	public void setIsreceive(String isreceive) {
		this.isreceive = isreceive;
	}
	public String getIsenableseal() {
		return isenableseal;
	}
	public void setIsenableseal(String isenableseal) {
		this.isenableseal = isenableseal;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public Long getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(Long createuserid) {
		this.createuserid = createuserid;
	}
	public String getCreateusername() {
		return createusername;
	}
	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	public Long getModifyuserid() {
		return modifyuserid;
	}
	public void setModifyuserid(Long modifyuserid) {
		this.modifyuserid = modifyuserid;
	}
	public String getModifyusername() {
		return modifyusername;
	}
	public void setModifyusername(String modifyusername) {
		this.modifyusername = modifyusername;
	}
	public Integer getIsdel() {
		return isdel;
	}
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getSealfile1() {
		return Sealfile1;
	}
	public void setSealfile1(String sealfile1) {
		Sealfile1 = sealfile1;
	}
	public String getSealfile2() {
		return Sealfile2;
	}
	public void setSealfile2(String sealfile2) {
		Sealfile2 = sealfile2;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", companytypeid=" + companytypeid + ", companyTypeName="
				+ companyTypeName + ", membertype=" + membertype + ", memberTypeName=" + memberTypeName + ", level="
				+ level + ", levelName=" + levelName + ", countryId=" + countryId + ", countryName=" + countryName
				+ ", provinceId=" + provinceId + ", provinceName=" + provinceName + ", cityId=" + cityId + ", cityName="
				+ cityName + ", address=" + address + ", creditcode=" + creditcode + ", cerificateattachment1="
				+ cerificateattachment1 + ", cerificateattachment2=" + cerificateattachment2 + ", phone=" + phone
				+ ", email=" + email + ", adminaccountid=" + adminaccountid + ", status=" + status + ", issendbl="
				+ issendbl + ", isreceive=" + isreceive + ", isenableseal=" + isenableseal + ", createdate="
				+ createdate + ", createuserid=" + createuserid + ", createusername=" + createusername + ", modifydate="
				+ modifydate + ", modifyuserid=" + modifyuserid + ", modifyusername=" + modifyusername + ", isdel="
				+ isdel + ", version=" + version + ", file=" + file + ", Sealfile1=" + Sealfile1 + ", Sealfile2="
				+ Sealfile2 + "]";
	}


    
    
    
}
