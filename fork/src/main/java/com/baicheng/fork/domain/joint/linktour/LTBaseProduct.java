package com.baicheng.fork.domain.joint.linktour;

import java.util.List;

import com.baicheng.domain.Domain;

/**
 * 领拓产品基础信息
 * 
 * @author wsm 2018年4月23日下午3:09:53
 */
public class LTBaseProduct extends Domain {

	private Long pid; // 产品id
	private String name; // 产品名称
	private String ahead; // 提前几天几点钟预订，D, H:i 格式，D表示天（0表示当天），H:i 表示具体时间，精确到分钟
	private String durations; // 游玩时长，表示 天,晚,小时,分钟
	private String generals; // 订购当前产品必须提供的游客信息
	private String visitors; // 订购当前产品必须提供的游客信息
	private String created; // 产品创建时间，ISO8601标准
	private String modified; // 最后更新时间，ISO8601标准
	private String confirmation; // 订单确认方式 AUTO（自动确认） HAND（人工确认）
	private String issue;
	private String category; // 产品类型
	private String departures; // 出发地
	private String destinations; // 目的地
	private String timezone; // 时区
	private String summary; // 简介
	private String latlng; // 谷歌地图坐标
	private String languages; // 服务语言，多个语言用,分割
	private String cover; // 封面图
	private String images; // 产品图片,多个图片用,分隔
	private String currency;// 当前报价对应的货币
	private String price1; // 最低起售价
	private String price2; // 最高售卖价
	private LTSeller seller; // 卖家结构
	private List<LTChangeRule> changes; // 退改规则
	private List<String> attachments; // 补充信息
	private List<LTSku> skus;
	private List<LTStock> stocks;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAhead() {
		return ahead;
	}

	public void setAhead(String ahead) {
		this.ahead = ahead;
	}

	public String getDurations() {
		return durations;
	}

	public void setDurations(String durations) {
		this.durations = durations;
	}

	public String getGenerals() {
		return generals;
	}

	public void setGenerals(String generals) {
		this.generals = generals;
	}

	public String getVisitors() {
		return visitors;
	}

	public void setVisitors(String visitors) {
		this.visitors = visitors;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDepartures() {
		return departures;
	}

	public void setDepartures(String departures) {
		this.departures = departures;
	}

	public String getDestinations() {
		return destinations;
	}

	public void setDestinations(String destinations) {
		this.destinations = destinations;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLatlng() {
		return latlng;
	}

	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPrice1() {
		return price1;
	}

	public void setPrice1(String price1) {
		this.price1 = price1;
	}

	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	public LTSeller getSeller() {
		return seller;
	}

	public void setSeller(LTSeller seller) {
		this.seller = seller;
	}

	public List<LTChangeRule> getChanges() {
		return changes;
	}

	public void setChanges(List<LTChangeRule> changes) {
		this.changes = changes;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	public List<LTSku> getSkus() {
		return skus;
	}

	public void setSkus(List<LTSku> skus) {
		this.skus = skus;
	}

	public List<LTStock> getStocks() {
		return stocks;
	}

	public void setStocks(List<LTStock> stocks) {
		this.stocks = stocks;
	}

}
