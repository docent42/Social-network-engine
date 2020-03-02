package com.skillbox.sw.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadResponseApi extends AbstractResponse {

    private String id;
    private int ownerId;
    private String fileName;
    private String relativeFilePath;
    private String rawFileURL;
    private String fileFormat;
    private long bytes;
    private FileType fileType;
    private long createdAt;

    public enum FileType {IMAGE}
}
