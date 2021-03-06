package com.mstar.meds.bo.global.equity;

// Generated Sep 6, 2010 4:47:40 PM by Hibernate Tools 3.2.0.beta8

import java.util.Date;

/**
 * CompanyQcRule generated by hbm2java
 */
public class CompanyQcRule implements java.io.Serializable {

	// Fields

	private Integer ruleId;
	private Integer templateClass;
	private Integer ruleLevel;
	private String levelId;
	private String tableType;
	private Integer reportType;
	private String numOfMonth;
	private Integer warningLevel;
	private String keyDataPoint;
	private String qualityControlId;
	private String threshold;
	private Integer ruleType;
	private String ruleExpression;
	private String error;
	private String description;
	private Integer creatorId;
	private Date createDate;
	private Integer updaterId;
	private Date updateDate;
	private Integer isValid;
	private Integer qcStatus;
	//add by cyan,2011-10-07
	private String hpAndMs;
	//add 2012-03-01
	private Integer reportStyle;
	private String fileSource;

	// Constructors

	public String getHpAndMs() {
		return hpAndMs;
	}

	public void setHpAndMs(String hpAndMs) {
		this.hpAndMs = hpAndMs;
	}

	/** default constructor */
	public CompanyQcRule() {
	}

	/** minimal constructor */
	public CompanyQcRule(Integer ruleId, Integer templateClass,
			Integer ruleLevel, String levelId, String tableType,
			Integer reportType, Integer warningLevel, Date updateDate,
			Integer isValid,
			Integer reportStyle, String fileSource) {
		this.ruleId = ruleId;
		this.templateClass = templateClass;
		this.ruleLevel = ruleLevel;
		this.levelId = levelId;
		this.tableType = tableType;
		this.reportType = reportType;
		this.warningLevel = warningLevel;
		this.updateDate = updateDate;
		this.isValid = isValid;
		this.reportStyle = reportStyle;
		this.fileSource = fileSource;
	}

	/** full constructor */
	public CompanyQcRule(Integer ruleId, Integer templateClass,
			Integer ruleLevel, String levelId, String tableType,
			Integer reportType, String numOfMonth, Integer warningLevel,
			String keyDataPoint, String threshold, Integer ruleType,
			String ruleExpression, String error, String description,
			Integer creatorId, Date createDate, Integer updaterId,
			Date updateDate, Integer isValid, Integer qcStatus,
			Integer reportStyle, String fileSource) {
		this.ruleId = ruleId;
		this.templateClass = templateClass;
		this.ruleLevel = ruleLevel;
		this.levelId = levelId;
		this.tableType = tableType;
		this.reportType = reportType;
		this.numOfMonth = numOfMonth;
		this.warningLevel = warningLevel;
		this.keyDataPoint = keyDataPoint;
		this.threshold = threshold;
		this.ruleType = ruleType;
		this.ruleExpression = ruleExpression;
		this.error = error;
		this.description = description;
		this.creatorId = creatorId;
		this.createDate = createDate;
		this.updaterId = updaterId;
		this.updateDate = updateDate;
		this.isValid = isValid;
		this.qcStatus = qcStatus;
		this.reportStyle = reportStyle;
		this.fileSource = fileSource;
	}

	// Property accessors
	public Integer getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public Integer getTemplateClass() {
		return this.templateClass;
	}

	public void setTemplateClass(Integer templateClass) {
		this.templateClass = templateClass;
	}

	public Integer getRuleLevel() {
		return this.ruleLevel;
	}

	public void setRuleLevel(Integer ruleLevel) {
		this.ruleLevel = ruleLevel;
	}

	public String getLevelId() {
		return this.levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getTableType() {
		return this.tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public Integer getReportType() {
		return this.reportType;
	}

	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}

	public String getNumOfMonth() {
		return this.numOfMonth;
	}

	public void setNumOfMonth(String numOfMonth) {
		this.numOfMonth = numOfMonth;
	}

	public Integer getWarningLevel() {
		return this.warningLevel;
	}

	public void setWarningLevel(Integer warningLevel) {
		this.warningLevel = warningLevel;
	}

	public String getKeyDataPoint() {
		return this.keyDataPoint;
	}

	public void setKeyDataPoint(String keyDataPoint) {
		this.keyDataPoint = keyDataPoint;
	}

	public String getThreshold() {
		return this.threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public Integer getRuleType() {
		return this.ruleType;
	}

	public void setRuleType(Integer ruleType) {
		this.ruleType = ruleType;
	}

	public String getRuleExpression() {
		return this.ruleExpression;
	}

	public void setRuleExpression(String ruleExpression) {
		this.ruleExpression = ruleExpression;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getUpdaterId() {
		return this.updaterId;
	}

	public void setUpdaterId(Integer updaterId) {
		this.updaterId = updaterId;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	private String qcResult;

	/**
	 * @return the qcResult
	 */
	public String getQcResult() {
		return qcResult;
	}

	/**
	 * @param qcResult the qcResult to set
	 */
	public void setQcResult(String qcResult) {
		this.qcResult = qcResult;
	}

	private Integer fileId;

	/**
	 * @return the fileId
	 */
	public Integer getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	private String notes;

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getQualityControlId() {
		return qualityControlId;
	}

	public void setQualityControlId(String qualityControlId) {
		this.qualityControlId = qualityControlId;
	}

	public Integer getQcStatus() {
		return qcStatus;
	}

	public void setQcStatus(Integer qcStatus) {
		this.qcStatus = qcStatus;
	}

	public Integer getReportStyle() {
		return reportStyle;
	}

	public void setReportStyle(Integer reportStyle) {
		this.reportStyle = reportStyle;
	}

	public String getFileSource() {
		return fileSource;
	}

	public void setFileSource(String fileSource) {
		this.fileSource = fileSource;
	}

}
