package com.baicheng.fork.core.util.upload;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.baicheng.fork.core.util.JsonUtil;
import com.baicheng.fork.core.util.StringUtils;
import com.baicheng.fork.core.util.endec.Base64;
import com.baicheng.fork.domain.BackConstants;
import com.nicetrip.freetrip.http.NTRequest;
import com.nicetrip.freetrip.http.NTRequestPost;
import com.nicetrip.freetrip.http.NTResponse;

public class BaichengUploadAPI implements UploadAPI {

	private static final Logger LOGGER = Logger
			.getLogger(BaichengUploadAPI.class.getName());

	public static final String UPLOAD_METHOD = "/index.php";
	public static final String KEY_API = "r";
	public static final String KEY_PARAMS = "json_params";

	/**
	 * upload type(1:picture 8:file) 1:签证文件分类 8：其他分类
	 */
	public static final String UPLOAD_TYPE = "8";

	@Override
	public String upload(InputStream is, String fileName) {
		ByteArrayOutputStream output = getOutputStream(is);
		byte[] bytes = output.toByteArray();
		String picStr = Base64.encode(bytes);
		String picURL = "";

		// 图片信息转换为json数据
		RequestJson json = new RequestJson();
		json.setFileName(fileName);
		json.setFileClassify(UPLOAD_TYPE);
		json.setFileStream(picStr);
		String requestJson = JsonUtil.bean2json(json);

		// 返回信息
		String returnJson;
		try {
			NTRequest request = new NTRequestPost(
					BackConstants.UPLOAD_DOMAIN_URL + UPLOAD_METHOD);
			request.addUrlParam(KEY_API, "Api/File/SaveFile");
			request.addBodyParam(KEY_PARAMS, requestJson);
			NTResponse response = request.execute();
			if (response == null) {
				LOGGER.error("文件上传失败 错误信息: 请求返回为空");
				return picURL;
			}

			returnJson = response.getEntity();
			if (returnJson == null) {
				LOGGER.error("文件上传失败 错误信息: 请求返回值的实体为空");
				return picURL;
			}

			LOGGER.info("文件上传返回信息:" + returnJson);
			if (StringUtils.isNotBlank(returnJson)) {
				ResponseJson retJson = JsonUtil.json2bean(returnJson,
						ResponseJson.class);
				if ("false".equals(retJson.getIsError())) {// IsError返回信息:false表示成功
															// true表示失败
					if (StringUtils.isNotBlank(retJson.getFilePath())) {
						picURL = BackConstants.UPLOAD_DOMAIN_URL
								+ "/uploads/" + retJson.getFilePath();
						LOGGER.info("文件上传成功 文件URL:" + picURL);
					} else {
						LOGGER.error("文件上传失败 错误代码为:" + retJson.getErrorType()
								+ " 请联系技术人员！");
					}
				} else {
					LOGGER.error("文件上传失败 错误信息:" + retJson.getErrorType());
				}
			}
		} catch (Exception e) {
			LOGGER.error("文件上传错误:" + e);
		}

		return picURL;
	}

	private ByteArrayOutputStream getOutputStream(InputStream is) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		try {
			while (-1 != (n = is.read(buffer))) {
				output.write(buffer, 0, n);
			}
			// 将字节流转换为String
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return output;
	}

}
