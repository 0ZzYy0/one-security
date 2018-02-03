package com.one.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 家庭成员表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-03 17:40:32
 */
public class BasPatMemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//成员编号
	private Long memberId;
	//患者编号
	private Long patId;
	//机构编号
	private Long deptId;
	//成员code
	private String memberCode;
	//成员姓名
	private String memberName;
	//成员年龄
	private Integer memberAge;
	//成员性别
	private String memberGender;
	//成员类型：成人、孩子
	private String memberType;
	//联系方式
	private String contactWay;
	//家庭住址
	private String memberAddress;
	//备注
	private String remark;

	/**
	 * 设置：成员编号
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：成员编号
	 */
	public Long getMemberId() {
		return memberId;
	}
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
	 * 设置：成员code
	 */
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	/**
	 * 获取：成员code
	 */
	public String getMemberCode() {
		return memberCode;
	}
	/**
	 * 设置：成员姓名
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	/**
	 * 获取：成员姓名
	 */
	public String getMemberName() {
		return memberName;
	}
	/**
	 * 设置：成员年龄
	 */
	public void setMemberAge(Integer memberAge) {
		this.memberAge = memberAge;
	}
	/**
	 * 获取：成员年龄
	 */
	public Integer getMemberAge() {
		return memberAge;
	}
	/**
	 * 设置：成员性别
	 */
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	/**
	 * 获取：成员性别
	 */
	public String getMemberGender() {
		return memberGender;
	}
	/**
	 * 设置：成员类型：成人、孩子
	 */
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	/**
	 * 获取：成员类型：成人、孩子
	 */
	public String getMemberType() {
		return memberType;
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
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	/**
	 * 获取：家庭住址
	 */
	public String getMemberAddress() {
		return memberAddress;
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
		return "BasPatMemberEntity [memberId=" + memberId + ", patId=" + patId
				+ ", deptId=" + deptId + ", memberCode=" + memberCode
				+ ", memberName=" + memberName + ", memberAge=" + memberAge
				+ ", memberGender=" + memberGender + ", memberType="
				+ memberType + ", contactWay=" + contactWay
				+ ", memberAddress=" + memberAddress + ", remark=" + remark
				+ "]";
	}
}
