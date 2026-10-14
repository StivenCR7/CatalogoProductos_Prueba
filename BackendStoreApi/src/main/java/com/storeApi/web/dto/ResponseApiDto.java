package com.storeApi.web.dto;

public class ResponseApiDto {

	private boolean success;
    private int importedCount;
    private int skippedCount;
    private String message;
    
	public ResponseApiDto(boolean success, int importedCount, int skippedCount, String message) {
		super();
		this.success = success;
		this.importedCount = importedCount;
		this.skippedCount = skippedCount;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getImportedCount() {
		return importedCount;
	}

	public void setImportedCount(int importedCount) {
		this.importedCount = importedCount;
	}

	public int getSkippedCount() {
		return skippedCount;
	}

	public void setSkippedCount(int skippedCount) {
		this.skippedCount = skippedCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
    
}
