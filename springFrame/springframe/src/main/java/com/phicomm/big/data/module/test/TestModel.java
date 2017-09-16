package com.phicomm.big.data.module.test;

import java.util.Date;

public class TestModel {
	
	private long id;
	
	private long splitTableFlag;
	
	private String city;
	
	private String content;
	
	 private Date createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSplitTableFlag() {
		return splitTableFlag;
	}

	public void setSplitTableFlag(long splitTableFlag) {
		this.splitTableFlag = splitTableFlag;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
