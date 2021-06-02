package com.zhisen.cslcp.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 印章对象 seal
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
public class Seal extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 物理主键 */
    private Long id;

    /** 公司ID */
    private Long companyId;
    
    /** 公司名称 */
    private String companyName;

    /** 印章名称 */
    private String name;

    /** 印章源文件 */
    private String file;

    /** 上传日期 */
    private String uploadday;

    /** 启用状态  启用1 禁用2*/
    private String isenable;

    /** 印章类型  1-Carrier 2- Agent*/
    private String sealtype;

    /** 印章确认状态  待确认1 平台否认2  平台已确认3  确认4 */
    private String confirmstatus;
    
    /** 印章确认意见 */
    private String comments;

    /** 创建人账户 */
    private String createuseraccount;

    /** 创建姓名 */
    private String createusername;

    /** 更新人账户 */
    private String modifieruseraccount;

    /** 更新人姓名 */
    private String modifierusername;

    /** 更新时间 */
    private Date modifytime;

    /** 乐观锁 */
    private Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getUploadday() {
		return uploadday;
	}

	public void setUploadday(String uploadday) {
		this.uploadday = uploadday;
	}

	public String getIsenable() {
		return isenable;
	}

	public void setIsenable(String isenable) {
		this.isenable = isenable;
	}

	public String getSealtype() {
		return sealtype;
	}

	public void setSealtype(String sealtype) {
		this.sealtype = sealtype;
	}

	public String getConfirmstatus() {
		return confirmstatus;
	}

	public void setConfirmstatus(String confirmstatus) {
		this.confirmstatus = confirmstatus;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCreateuseraccount() {
		return createuseraccount;
	}

	public void setCreateuseraccount(String createuseraccount) {
		this.createuseraccount = createuseraccount;
	}

	public String getCreateusername() {
		return createusername;
	}

	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	public String getModifieruseraccount() {
		return modifieruseraccount;
	}

	public void setModifieruseraccount(String modifieruseraccount) {
		this.modifieruseraccount = modifieruseraccount;
	}

	public String getModifierusername() {
		return modifierusername;
	}

	public void setModifierusername(String modifierusername) {
		this.modifierusername = modifierusername;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Seal [id=" + id + ", companyId=" + companyId + ", companyName="
				+ companyName + ", name=" + name + ", file=" + file
				+ ", uploadday=" + uploadday + ", isenable=" + isenable
				+ ", sealtype=" + sealtype + ", confirmstatus=" + confirmstatus
				+ ", comments=" + comments + ", createuseraccount="
				+ createuseraccount + ", createusername=" + createusername
				+ ", modifieruseraccount=" + modifieruseraccount
				+ ", modifierusername=" + modifierusername + ", modifytime="
				+ modifytime + ", version=" + version + "]";
	}

	public Seal(Integer currentPage, Integer pageSize, Integer limit,
			String excelFlag, Long id, Long companyId, String companyName,
			String name, String file, String uploadday, String isenable,
			String sealtype, String confirmstatus, String comments,
			String createuseraccount, String createusername,
			String modifieruseraccount, String modifierusername,
			Date modifytime, Long version) {
		super(currentPage, pageSize, limit, excelFlag);
		this.id = id;
		this.companyId = companyId;
		this.companyName = companyName;
		this.name = name;
		this.file = file;
		this.uploadday = uploadday;
		this.isenable = isenable;
		this.sealtype = sealtype;
		this.confirmstatus = confirmstatus;
		this.comments = comments;
		this.createuseraccount = createuseraccount;
		this.createusername = createusername;
		this.modifieruseraccount = modifieruseraccount;
		this.modifierusername = modifierusername;
		this.modifytime = modifytime;
		this.version = version;
	}

	public Seal() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
