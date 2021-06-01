package com.zhisen.cslcp.dao.entity;

import java.util.Date;

/**
 * 港口信息对象 port
 * 
 * @author baiqianyi
 * @date 2021-05-22
 */
public class Port extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物理主键 */
    private Long id;

    /** 港口名称 */
    private String name;

    /** 港口英文名 */
    private String ename;

    /** 所在国家 */
    private String countryId;
    
    /** 所在国家名称 */
    private String countryName;

    /** 所在省份 */
    private String provinceId;
    
    /** 所在省份名称 */
    private String provinceName;

    /** 所在城市 */
    private String cityId;
    
    /** 所在城市名称 */
    private String cityName;

    /** 出单费用 */
    private Long outlay;

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

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
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

	public Long getOutlay() {
		return outlay;
	}

	public void setOutlay(Long outlay) {
		this.outlay = outlay;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
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

	@Override
	public String toString() {
		return "Port [id=" + id + ", name=" + name + ", ename=" + ename
				+ ", countryId=" + countryId + ", countryName=" + countryName
				+ ", provinceId=" + provinceId + ", provinceName="
				+ provinceName + ", cityId=" + cityId + ", cityName="
				+ cityName + ", outlay=" + outlay + ", createdate="
				+ createdate + ", createuserid=" + createuserid
				+ ", createusername=" + createusername + ", modifydate="
				+ modifydate + ", modifyuserid=" + modifyuserid
				+ ", modifyusername=" + modifyusername + ", isdel=" + isdel
				+ ", version=" + version + "]";
	}

	public Port(Integer currentPage, Integer pageSize, Integer limit,
			String excelFlag, Long id, String name, String ename,
			String countryId, String countryName, String provinceId,
			String provinceName, String cityId, String cityName, Long outlay,
			Date createdate, Long createuserid, String createusername,
			Date modifydate, Long modifyuserid, String modifyusername,
			Integer isdel, Long version) {
		super(currentPage, pageSize, limit, excelFlag);
		this.id = id;
		this.name = name;
		this.ename = ename;
		this.countryId = countryId;
		this.countryName = countryName;
		this.provinceId = provinceId;
		this.provinceName = provinceName;
		this.cityId = cityId;
		this.cityName = cityName;
		this.outlay = outlay;
		this.createdate = createdate;
		this.createuserid = createuserid;
		this.createusername = createusername;
		this.modifydate = modifydate;
		this.modifyuserid = modifyuserid;
		this.modifyusername = modifyusername;
		this.isdel = isdel;
		this.version = version;
	}

	public Port() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Port(Integer currentPage, Integer pageSize, Integer limit,
			String excelFlag) {
		super(currentPage, pageSize, limit, excelFlag);
		// TODO Auto-generated constructor stub
	}

	
   

}
