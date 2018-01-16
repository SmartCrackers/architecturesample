package com.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileUtility {

	void saveFile(MultipartFile multiPartFile, String clientId, String folder, String fileName);

	void deleteFile(String clientId, String folder, String fileName);

	String getFileName(String fileName);

}
