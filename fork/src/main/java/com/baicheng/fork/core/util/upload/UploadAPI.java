package com.baicheng.fork.core.util.upload;

import java.io.InputStream;

public interface UploadAPI {

	String upload(InputStream is, String fileName);

}
