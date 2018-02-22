package com.one.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 患者牙位表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-09 09:52:17
 */
public class BasToothPositionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 牙位编号
	private Long posId;
	// 关联表id
	private Long infoId;
	// 患者表、成员表
	private String operateTable;
	// 牙位code
	private String posCode;
	// 备注
	private String remark;

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
	 * 设置：关联表id
	 */
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	/**
	 * 获取：关联表id
	 */
	public Long getInfoId() {
		return infoId;
	}

	/**
	 * 设置：患者表、成员表
	 */
	public void setOperateTable(String operateTable) {
		this.operateTable = operateTable;
	}

	/**
	 * 获取：患者表、成员表
	 */
	public String getOperateTable() {
		return operateTable;
	}

	/**
	 * 设置：牙位code
	 */
	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	/**
	 * 获取：牙位code
	 */
	public String getPosCode() {
		return posCode;
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
		return "BasToothPositionEntity [posId=" + posId + ", infoId=" + infoId + ", operateTable=" + operateTable + ", posCode=" + posCode + ", remark=" + remark + "]";
	}
}
