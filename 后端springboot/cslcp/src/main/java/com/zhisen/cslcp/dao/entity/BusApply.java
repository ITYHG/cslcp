package com.zhisen.cslcp.dao.entity;


/**
 * 服务开通申请对象 busapply
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
public class BusApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物理主键 */
    private Integer id;

    /** 开通服务类型 */
    private String type;

    /** 资质证明文件集*/
    private String files;
    
    
    /** 申请公司 */
    private String companyid;
    
    /** 申请公司 名称*/
    private String companyName;
    
    /** 申请人账号 */
    private String applyaccount;

    /** 申请人姓名 */
    private String applyusername;

    /** 申请时间 */
    private String applydate;

    /** 审核人账号 */
    private String auditaccount;

    /** 审核人姓名 */
    private String auditusername;

    /** 审核时间 */
    private String auditdate;

    /** 审核结果 1未通过2通过 */
    private Long auditresult;

    /** 审核状态  1 未审核 */
    private String auditstatus;

    /** 审核意见 */
    private String auditopinion;

    /** 乐观锁 */
    private Long version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getApplyaccount() {
		return applyaccount;
	}

	public void setApplyaccount(String applyaccount) {
		this.applyaccount = applyaccount;
	}

	public String getApplyusername() {
		return applyusername;
	}

	public void setApplyusername(String applyusername) {
		this.applyusername = applyusername;
	}

	public String getApplydate() {
		return applydate;
	}

	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}

	public String getAuditaccount() {
		return auditaccount;
	}

	public void setAuditaccount(String auditaccount) {
		this.auditaccount = auditaccount;
	}

	public String getAuditusername() {
		return auditusername;
	}

	public void setAuditusername(String auditusername) {
		this.auditusername = auditusername;
	}

	public String getAuditdate() {
		return auditdate;
	}

	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}

	public Long getAuditresult() {
		return auditresult;
	}

	public void setAuditresult(Long auditresult) {
		this.auditresult = auditresult;
	}

	public String getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}

	public String getAuditopinion() {
		return auditopinion;
	}

	public void setAuditopinion(String auditopinion) {
		this.auditopinion = auditopinion;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "BusApply [id=" + id + ", type=" + type + ", files=" + files
				+ ", companyid=" + companyid + ", companyName=" + companyName
				+ ", applyaccount=" + applyaccount + ", applyusername="
				+ applyusername + ", applydate=" + applydate
				+ ", auditaccount=" + auditaccount + ", auditusername="
				+ auditusername + ", auditdate=" + auditdate + ", auditresult="
				+ auditresult + ", auditstatus=" + auditstatus
				+ ", auditopinion=" + auditopinion + ", version=" + version
				+ "]";
	}

	public BusApply(Integer currentPage, Integer pageSize, Integer limit,
			String excelFlag, Integer id, String type, String files,
			String companyid, String companyName, String applyaccount,
			String applyusername, String applydate, String auditaccount,
			String auditusername, String auditdate, Long auditresult,
			String auditstatus, String auditopinion, Long version) {
		super(currentPage, pageSize, limit, excelFlag);
		this.id = id;
		this.type = type;
		this.files = files;
		this.companyid = companyid;
		this.companyName = companyName;
		this.applyaccount = applyaccount;
		this.applyusername = applyusername;
		this.applydate = applydate;
		this.auditaccount = auditaccount;
		this.auditusername = auditusername;
		this.auditdate = auditdate;
		this.auditresult = auditresult;
		this.auditstatus = auditstatus;
		this.auditopinion = auditopinion;
		this.version = version;
	}

	public BusApply() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
