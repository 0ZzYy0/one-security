package com.one.modules.sys.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.weixin.pojo.SNSUserInfo;



/**
 * 患者信息表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-01 19:04:33
 */
public class BasPatientEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//患者编号
	private Long patId;
	//机构编号
	private Long deptId;
	//患者code
	private String patCode;
	//患者类型
	private String patType;
	//患者姓名
	private String patName;
	//患者年龄
	private Integer patAge;
	//患者性别
	private String patGender;
	//联系方式
	private String contactWay;
	//家庭住址
	private String patAddress;
	//备注
	private String remark;

	/**
	 * 设置：患者编号
	 */
	public void setPatId(Long patId) {
		this.patId = patId;
	}
	/**
	 * 获取：患者编号
	 */
	public Long getPatId() {
		return patId;
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
	 * 设置：患者code
	 */
	public void setPatCode(String patCode) {
		this.patCode = patCode;
	}
	/**
	 * 获取：患者code
	 */
	public String getPatCode() {
		return patCode;
	}
	/**
	 * 设置：患者类型
	 */
	public void setPatType(String patType) {
		this.patType = patType;
	}
	/**
	 * 获取：患者类型
	 */
	public String getPatType() {
		return patType;
	}
	/**
	 * 设置：患者姓名
	 */
	public void setPatName(String patName) {
		this.patName = patName;
	}
	/**
	 * 获取：患者姓名
	 */
	public String getPatName() {
		return patName;
	}
	/**
	 * 设置：患者年龄
	 */
	public void setPatAge(Integer patAge) {
		this.patAge = patAge;
	}
	/**
	 * 获取：患者年龄
	 */
	public Integer getPatAge() {
		return patAge;
	}
	/**
	 * 设置：患者性别
	 */
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	/**
	 * 获取：患者性别
	 */
	public String getPatGender() {
		return patGender;
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
	 * 设置：家庭住址
	 */
	public void setPatAddress(String patAddress) {
		this.patAddress = patAddress;
	}
	/**
	 * 获取：家庭住址
	 */
	public String getPatAddress() {
		return patAddress;
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
}
