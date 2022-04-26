package dto;

public class Finedust {
	private String resultCode;
	private String resultMsg;
	private String numOfRows;
	private String pageNo; 
	private String totalCount; 
	private String sn;
	private String dataDate;
	private String districtName;
	private String moveName;
	private String itemCode;
	private String issueGbn;
	private String issueDate;
	private String issueTime;
	private String issueVal;
	private String clearDate;
	private String clearTime;
	private String clearVal;
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(String numOfRows) {
		this.numOfRows = numOfRows;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getDataDate() {
		return dataDate;
	}
	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getMoveName() {
		return moveName;
	}
	public void setMoveName(String moveName) {
		this.moveName = moveName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getIssueGbn() {
		return issueGbn;
	}
	public void setIssueGbn(String issueGbn) {
		this.issueGbn = issueGbn;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}
	public String getIssueVal() {
		return issueVal;
	}
	public void setIssueVal(String issueVal) {
		this.issueVal = issueVal;
	}
	public String getClearDate() {
		return clearDate;
	}
	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}
	public String getClearTime() {
		return clearTime;
	}
	public void setClearTime(String clearTime) {
		this.clearTime = clearTime;
	}
	public String getClearVal() {
		return clearVal;
	}
	public void setClearVal(String clearVal) {
		this.clearVal = clearVal;
	}
	@Override
	public String toString() {
		return "Finedust [resultCode=" + resultCode + ", resultMsg=" + resultMsg + ", numOfRows=" + numOfRows
				+ ", pageNo=" + pageNo + ", totalCount=" + totalCount + ", sn=" + sn + ", dataDate=" + dataDate
				+ ", districtName=" + districtName + ", moveName=" + moveName + ", itemCode=" + itemCode + ", issueGbn="
				+ issueGbn + ", issueDate=" + issueDate + ", issueTime=" + issueTime + ", issueVal=" + issueVal
				+ ", clearDate=" + clearDate + ", clearTime=" + clearTime + ", clearVal=" + clearVal + "]";
	}
	
}
