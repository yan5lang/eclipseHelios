
import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the base_template database table.
 * 
 */
@Entity
@Table(name="base_template")
public class BaseTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Id")
	private int id;

	@Column(name="BaseId")
	private String baseId;

	@Column(name="BaseName")
	private String baseName;

	@Column(name="CreateDate")
	private Timestamp createDate;

	@Column(name="Depth")
	private int depth;

	@Column(name="FileSource")
	private String fileSource;

	@Column(name="IndustryTemplateCode")
	private String industryTemplateCode;

	@Column(name="IsHide")
	private int isHide;

	@Column(name="IsValid")
	private int isValid;

	@Column(name="NodeOrder")
	private String nodeOrder;

	@Column(name="ParentId")
	private String parentId;

	@Column(name="ReportStyle")
	private int reportStyle;

	@Column(name="ShortName")
	private String shortName;

	@Column(name="TableType")
	private int tableType;

	@Column(name="UpdateDate")
	private Timestamp updateDate;

    public BaseTemplate() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBaseId() {
		return this.baseId;
	}

	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}

	public String getBaseName() {
		return this.baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public int getDepth() {
		return this.depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getFileSource() {
		return this.fileSource;
	}

	public void setFileSource(String fileSource) {
		this.fileSource = fileSource;
	}

	public String getIndustryTemplateCode() {
		return this.industryTemplateCode;
	}

	public void setIndustryTemplateCode(String industryTemplateCode) {
		this.industryTemplateCode = industryTemplateCode;
	}

	public int getIsHide() {
		return this.isHide;
	}

	public void setIsHide(int isHide) {
		this.isHide = isHide;
	}

	public int getIsValid() {
		return this.isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public String getNodeOrder() {
		return this.nodeOrder;
	}

	public void setNodeOrder(String nodeOrder) {
		this.nodeOrder = nodeOrder;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getReportStyle() {
		return this.reportStyle;
	}

	public void setReportStyle(int reportStyle) {
		this.reportStyle = reportStyle;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public int getTableType() {
		return this.tableType;
	}

	public void setTableType(int tableType) {
		this.tableType = tableType;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

}