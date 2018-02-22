package com.one.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 牙位病灶表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-09 09:52:17
 */
public class BasToothDiseaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 病灶编号
	private Long diseaseId;
	// 牙位编号
	private Long posId;
	// 病灶code
	private String diseaseCode;
	// 病灶名称
	private String diseaseName;
	// 备注
	private String remark;

	/**
	 * 设置：病灶编号
	 */
	public void setDiseaseId(Long diseaseId) {
		this.diseaseId = diseaseId;
	}

	/**
	 * 获取：病灶编号
	 */
	public Long getDiseaseId() {
		return diseaseId;
	}

	/**
	 * 设置：牙位编号
	 */
	public void setPosId(Long posId) {
		this.posId = posId;
	}

	/**
	 * 获取：牙位编号
	 */
	public Long getPosId() {
		return posId;
	}

	/**
	 * 设置：病灶code
	 */
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}

	/**
	 * 获取：病灶code
	 */
	public String getDiseaseCode() {
		return diseaseCode;
	}

	/**
	 * 设置：病灶名称
	 */
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	/**
	 * 获取：病灶名称
	 */
	public String getDiseaseName() {
		return diseaseName;
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
		return "BasToothDiseaseEntity [diseaseId=" + diseaseId + ", posId=" + posId + ", diseaseCode=" + diseaseCode + ", diseaseName=" + diseaseName + ", remark=" + remark + "]";
	}
}
