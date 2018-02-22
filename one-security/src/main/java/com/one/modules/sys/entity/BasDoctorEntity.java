package com.one.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 医生信息表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-02 19:50:29
 */
public class BasDoctorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 医生编号
	private Long docId;
	// 机构编号
	private Long deptId;
	// 姓名
	private String docName;
	// 联系方式
	private String contactWay;
	// 所属机构
	private String dpetId;
	// 工号
	private String jobNum;
	// 家庭住址
	private String docAddress;
	// 备注
	private String remark;

	/**
	 * 设置：医生编号
	 */
	public void setDocId(Long docId) {
		this.docId = docId;
	}

	/**
	 * 获取：医生编号
	 */
	public Long getDocId() {
		return docId;
	}

	/**
	 * 设置：机构编号
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	/**
	 * 获取：机构编号
	 */
	public Long getDeptId() {
		return deptId;
	}

	/**
	 * 设置：姓名
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}

	/**
	 * 获取：姓名
	 */
	public String getDocName() {
		return docName;
	}

	/**
	 * 设置：联系方式
	 */
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	/**
	 * 获取：联系方式
	 */
	public String getContactWay() {
		return contactWay;
	}

	/**
	 * 设置：所属机构
	 */
	public void setDpetId(String dpetId) {
		this.dpetId = dpetId;
	}

	/**
	 * 获取：所属机构
	 */
	public String getDpetId() {
		return dpetId;
	}

	/**
	 * 设置：工号
	 */
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

	/**
	 * 获取：工号
	 */
	public String getJobNum() {
		return jobNum;
	}

	/**
	 * 设置：家庭住址
	 */
	public void setDocAddress(String docAddress) {
		this.docAddress = docAddress;
	}

	/**
	 * 获取：家庭住址
	 */
	public String getDocAddress() {
		return docAddress;
	}

	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}

	@Override
	public String toString() {
		return "BasDoctorEntity [docId=" + docId + ", deptId=" + deptId + ", docName=" + docName + ", contactWay=" + contactWay + ", dpetId=" + dpetId + ", jobNum=" + jobNum + ", docAddress="
				+ docAddress + ", remark=" + remark + "]";
	}

}
