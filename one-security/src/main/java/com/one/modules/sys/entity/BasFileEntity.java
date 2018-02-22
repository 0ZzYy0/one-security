package com.one.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件信息表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-09 09:52:17
 */
public class BasFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 文件编号
	private String fileId;
	// 牙位编号
	private Long posId;
	// 文件类型 牙面
	private String fileType;
	// 文件地址
	private String fileAddr;
	// 文件后缀
	private String fileSuffix;
	// 文件信息
	private String fileInfo;
	// 备注
	private String remark;

	/**
	 * 设置：文件编号
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * 获取：文件编号
	 */
	public String getFileId() {
		return fileId;
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
	 * 设置：文件类型 牙面
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * 获取：文件类型 牙面
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * 设置：文件地址
	 */
	public void setFileAddr(String fileAddr) {
		this.fileAddr = fileAddr;
	}

	/**
	 * 获取：文件地址
	 */
	public String getFileAddr() {
		return fileAddr;
	}

	/**
	 * 设置：文件后缀
	 */
	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	/**
	 * 获取：文件后缀
	 */
	public String getFileSuffix() {
		return fileSuffix;
	}

	/**
	 * 设置：文件信息
	 */
	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
	}

	/**
	 * 获取：文件信息
	 */
	public String getFileInfo() {
		return fileInfo;
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
		return "BasFileEntity [fileId=" + fileId + ", posId=" + posId + ", fileType=" + fileType + ", fileAddr=" + fileAddr + ", fileSuffix=" + fileSuffix + ", fileInfo=" + fileInfo + ", remark="
				+ remark + "]";
	}
}
