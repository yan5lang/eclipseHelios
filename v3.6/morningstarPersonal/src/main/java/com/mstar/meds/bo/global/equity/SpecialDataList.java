package com.mstar.meds.bo.global.equity;

import java.io.Serializable;

public class SpecialDataList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer ID;
	private String dataId;
	private String dataName;
	private String dataType; // EPS;shares;Dividend
	private int reportStryle;    //1:us; 2:ifrs
	private String specialType;  //unit is 1;
	private int tableType;       //1:bs; 2:cf; 3:is
	private String description;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getReportStryle() {
		return reportStryle;
	}
	public void setReportStryle(int reportStryle) {
		this.reportStryle = reportStryle;
	}
	public String getSpecialType() {
		return specialType;
	}
	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}
	public int getTableType() {
		return tableType;
	}
	public void setTableType(int tableType) {
		this.tableType = tableType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
