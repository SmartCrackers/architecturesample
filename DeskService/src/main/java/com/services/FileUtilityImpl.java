package com.services;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.exception.BookException;

@Service("fileUtility")
public class FileUtilityImpl implements FileUtility {

	private static  String locationFormat = "%s/%s/";	
	private final  String fileNameFormat = "%s.%s";
	
	@Value("${book.media.location}")
	private String mediaLocation;
	
	@Override
	public  void saveFile(MultipartFile multiPartFile, String clientId, String folder, String fileName) {

	    if (multiPartFile.isEmpty()) {
	      return;
	    }
	    String assetLocation = String.format(locationFormat, mediaLocation, clientId);
	    isFileLocationValid(assetLocation);
	    String fileLocation = String.format(locationFormat, assetLocation, folder);
	    isFileLocationValid(fileLocation);
	    fileLocation += fileName;
	    File file = new File(fileLocation);
	    try {
	      multiPartFile.transferTo(file);
	    } catch (IllegalStateException | IOException e) {
	      String message =
	          String.format("Error while saving file with name : %s for client id : %s",
	              multiPartFile.getOriginalFilename(), clientId);
	      throw new BookException(message, e);
	    }
	  }
	
	@Override
	public  void deleteFile(String clientId, String folder, String fileName) {
	    String assetLocation = String.format(locationFormat, mediaLocation, clientId);
	    String fileLocation = String.format(locationFormat, assetLocation, folder);
	    File fileToBeDeleted = new File(fileLocation += fileName);
	    if (fileToBeDeleted.exists()) {
	      fileToBeDeleted.delete();
	    }
	  }
	
	@Override
	public  String getFileName(String fileName) {
	    return String.format(fileNameFormat, UUID.fromString(UUID.randomUUID().toString()).toString(),
	        FilenameUtils.getExtension(fileName));
	  }
	
	private  void isFileLocationValid(String fileLocation) {
	    File file = new File(fileLocation);
	    if (!file.exists()) {
	      file.mkdirs();
	    }
	  }
}
