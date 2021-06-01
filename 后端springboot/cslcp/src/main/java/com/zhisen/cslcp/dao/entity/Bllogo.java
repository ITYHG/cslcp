package com.zhisen.cslcp.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 提单logo对象 bllogo
 * 
 * @author baiqianyi
 * @date 2021-05-23
 */
public class Bllogo extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 物理主键 */
    private Long id;

    /** 公司ID */
    private Long companyid;
    
    /** 公司名称 */
    private String companyName;

    /** Logo名称 */
    private String name;

    /** Logo源文件 */
    private String file;

    /** 上传时间 */
    private String uploadday;

    /** 启用状态  启用1 禁用2*/
    private Integer isenable;

    /** 确认状态  待确认1 平台已确认2  平台未否认3  确认4 */
    private Integer confirmstatus;
    
    /** 确认意见 */
    private String  comments;

    /** 创建人账户 */
    private String createuseraccount;

    /** 创建姓名 */
    private String createusername;
    /** 创建时间 */
    private String createtime;

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

	public Long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
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

	public Integer getIsenable() {
		return isenable;
	}

	public void setIsenable(Integer isenable) {
		this.isenable = isenable;
	}

	public Integer getConfirmstatus() {
		return confirmstatus;
	}

	public void setConfirmstatus(Integer confirmstatus) {
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

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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
		return "Bllogo [id=" + id + ", companyid=" + companyid + ", companyName=" + companyName + ", name=" + name
				+ ", file=" + file + ", uploadday=" + uploadday + ", isenable=" + isenable + ", confirmstatus="
				+ confirmstatus + ", comments=" + comments + ", createuseraccount=" + createuseraccount
				+ ", createusername=" + createusername + ", createtime=" + createtime + ", modifieruseraccount="
				+ modifieruseraccount + ", modifierusername=" + modifierusername + ", modifytime=" + modifytime
				+ ", version=" + version + "]";
	}

	public Bllogo(Integer currentPage, Integer pageSize, Integer limit, String excelFlag, Long id, Long companyid,
			String companyName, String name, String file, String uploadday, Integer isenable, Integer confirmstatus,
			String comments, String createuseraccount, String createusername, String createtime,
			String modifieruseraccount, String modifierusername, Date modifytime, Long version) {
		super(currentPage, pageSize, limit, excelFlag);
		this.id = id;
		this.companyid = companyid;
		this.companyName = companyName;
		this.name = name;
		this.file = file;
		this.uploadday = uploadday;
		this.isenable = isenable;
		this.confirmstatus = confirmstatus;
		this.comments = comments;
		this.createuseraccount = createuseraccount;
		this.createusername = createusername;
		this.createtime = createtime;
		this.modifieruseraccount = modifieruseraccount;
		this.modifierusername = modifierusername;
		this.modifytime = modifytime;
		this.version = version;
	}

	public Bllogo() {
		super();
		// TODO Auto-generated constructor stub
	}


	

	
}
