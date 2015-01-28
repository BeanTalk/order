package com.saituo.order.entity.gift;

public class Gift {
	//礼品ID
	private Long id;
	//所属区域
	private String giftName;
	//所属区域
	private String areaId;
	//所需豆豆
	private int needPea;
	//所需积分
	private int needScore;
   //礼品状态: 0,未开始兑换;1,开始兑换;2,兑换结束
	private int giftStatus;
	//礼品可使用数量
	private int giftNum;
	//创建者
	private String 	createBy;
	//创建者
	private String  createDate;
	//更新者
	private String 	updateBy;
	//更新时间
	private String  	updateDate;
	//备注信息
	private String  	remarks;
	//删除标记
	private String  	delFlag;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public int getNeedPea() {
		return needPea;
	}
	public void setNeedPea(int needPea) {
		this.needPea = needPea;
	}
	public int getNeedScore() {
		return needScore;
	}
	public void setNeedScore(int needScore) {
		this.needScore = needScore;
	}
	public int getGiftStatus() {
		return giftStatus;
	}
	public void setGiftStatus(int giftStatus) {
		this.giftStatus = giftStatus;
	}
	public int getGiftNum() {
		return giftNum;
	}
	public void setGiftNum(int giftNum) {
		this.giftNum = giftNum;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}
