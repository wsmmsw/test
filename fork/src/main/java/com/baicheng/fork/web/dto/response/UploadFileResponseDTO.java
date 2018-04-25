package com.baicheng.fork.web.dto.response;

/**
 * @author wsm
 * @date 2017年5月10日上午10:34:33
 */
public class UploadFileResponseDTO extends BaseResponseDTO {
	// 图片链接地址
	private String resultUrl;

	public String getResultUrl() {
		return resultUrl;
	}

	public void setResultUrl(String resultUrl) {
		this.resultUrl = resultUrl;
	}

}
