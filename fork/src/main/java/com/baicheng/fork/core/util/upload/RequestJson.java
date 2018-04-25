package com.baicheng.fork.core.util.upload;

import java.io.Serializable;

/**
 * @author mabaoyu
 * 
 * @date：2017年4月24日 下午6:06:59
 */
public class RequestJson implements Serializable {

	private static final long serialVersionUID = 7999226097863531390L;

	private String FileName;
	private String FileClassify;
	private String FileStream;

	public String getFileName() {
		return this.FileName;
	}

	public void setFileName(String fileName) {
		this.FileName = fileName;
	}

	public String getFileClassify() {
		return this.FileClassify;
	}

	public void setFileClassify(String fileClassify) {
		this.FileClassify = fileClassify;
	}

	public String getFileStream() {
		return this.FileStream;
	}

	public void setFileStream(String fileStream) {
		this.FileStream = fileStream;
	}

}
