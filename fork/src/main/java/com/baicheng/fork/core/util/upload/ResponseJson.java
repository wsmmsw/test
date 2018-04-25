package com.baicheng.fork.core.util.upload;

/**
 * @author mabaoyu
 * 
 * @date：2017年4月24日 下午6:07:53
 */
public class ResponseJson {

	private String FileName;
	private String FilePath;
	private String IsError;
	private String ErrorType;

	public String getFileName() {
		return this.FileName;
	}

	public void setFileName(String fileName) {
		this.FileName = fileName;
	}

	public String getFilePath() {
		return this.FilePath;
	}

	public void setFilePath(String filePath) {
		this.FilePath = filePath;
	}

	public String getIsError() {
		return this.IsError;
	}

	public void setIsError(String isError) {
		this.IsError = isError;
	}

	public String getErrorType() {
		return this.ErrorType;
	}

	public void setErrorType(String errorType) {
		this.ErrorType = errorType;
	}

}
