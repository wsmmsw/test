package com.baicheng.fork.core.util.sms.sender;

/**
 * 调用.net短信接口SmsOtherInfoJson参数类
 */
public class SmsOtherInfoJson {

	private String DomanName;
	private String FileURL;
	private String FileName;
	private String FunctionName;
	private String System;
	private String Memo;

	public String getDomanName() {
		return this.DomanName;
	}

	public void setDomanName(String domanName) {
		this.DomanName = domanName;
	}

	public String getFileURL() {
		return this.FileURL;
	}

	public void setFileURL(String fileURL) {
		this.FileURL = fileURL;
	}

	public String getFileName() {
		return this.FileName;
	}

	public void setFileName(String fileName) {
		this.FileName = fileName;
	}

	public String getFunctionName() {
		return this.FunctionName;
	}

	public void setFunctionName(String functionName) {
		this.FunctionName = functionName;
	}

	public String getSystem() {
		return this.System;
	}

	public void setSystem(String system) {
		this.System = system;
	}

	public String getMemo() {
		return this.Memo;
	}

	public void setMemo(String memo) {
		this.Memo = memo;
	}

}
