package com.fpt.swd.utils;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)


import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class FileUploadUtils {
    private static final Logger log = LoggerFactory.getLogger(FileUploadUtils.class);
    private final MinioClient minioClient;
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String domain;
    public static final String IMAGE_PNG_CONTENT_TYPE = "image/png";
    public static final String IMAGE_JPEG_CONTENT_TYPE = "image/jpeg";
    public static final String PDF_CONTENT_TYPE = "application/pdf";
    public static final String XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String DOCX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String TEXT_CONTENT_TYPE = "text/plain";
    public static final String VIDEO_CONTENT_TYPE = "video/mp4";
    public static final String IMAGE_FOLDER = "images/";
    public static final String PDF_FOLDER = "pdf/";
    public static final String EXCEL_FOLDER = "excel/";
    public static final String WORD_FOLDER = "word/";
    public static final String TEXT_FOLDER = "text/";
    public static final String VIDEO_FOLDER = "videos/";
    public static final String PNG_END = ".png";
    public static final String JPEG_END = ".jpeg";
    public static final String PDF_END = ".pdf";
    public static final String EXCEL_END = ".xlsx";
    public static final String WORD_END = ".docx";
    public static final String TEXT_END = ".txt";
    public static final String VIDEO_END = ".mp4";
    public static final Map<String, String> FOLDER_MAP = new HashMap();

    public FileUploadUtils(String endpoint, String accessKey, String secretKey, String domain) {
        this.domain = domain;
        this.minioClient = MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
    }

    public boolean isBucketExist(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return this.minioClient.bucketExists((BucketExistsArgs)((BucketExistsArgs.Builder)BucketExistsArgs.builder().bucket(bucketName)).build());
    }

    public void makeBucket(String bucketName) {
        try {
            log.info("Start making bucket with name " + bucketName);
            this.minioClient.makeBucket((MakeBucketArgs)((MakeBucketArgs.Builder)MakeBucketArgs.builder().bucket(bucketName)).build());
            log.info("Make bucket successfully");
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void makeFolder(String bucketName, String folderName) {
        try {
            log.info("Start making folder with name " + folderName);
            this.minioClient.putObject((PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(bucketName)).object(folderName + "/")).stream(new ByteArrayInputStream(new byte[0]), 0L, -1L).build());
            log.info("Make folder successfully");
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    @Async
    public String uploadFile(InputStream inputStream, String bucketName, String folderName, String contentType) {
        try {
            if (this.isBucketExist(bucketName)) {
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                String objectName = UUID.randomUUID().toString().replace("-", "");
                String object = this.handleObjectName(contentType, objectName, folderName);
                log.info("Start uploading object " + object);
                this.minioClient.putObject((PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(bucketName)).object(object)).stream(new ByteArrayInputStream(buffer), (long)buffer.length, -1L).contentType(contentType).build());
                log.info("Upload file successfully");
                return this.getObjectUrl(bucketName, object);
            } else {
                return null;
            }
        } catch (Exception var8) {
            log.error("Error while uploading:", var8);
            return null;
        }
    }

    @Async
    public String uploadFile(InputStream inputStream, String bucketName, String folderName, String fileType, String contentType) {
        try {
            if (this.isBucketExist(bucketName)) {
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                String objectName = UUID.randomUUID().toString().replace("-", "");
                if (!DataUtils.isNullObject(fileType)) {
                    objectName = objectName + "." + fileType;
                }

                String object = this.handleObjectName(contentType, objectName, folderName);
                log.info("Start uploading object " + object);
                this.minioClient.putObject((PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(bucketName)).object(object)).stream(new ByteArrayInputStream(buffer), (long)buffer.length, -1L).contentType(contentType).build());
                log.info("Upload file successfully");
                return this.getObjectUrl(bucketName, object);
            } else {
                return null;
            }
        } catch (Exception var9) {
            log.error("Error while uploading:", var9);
            return null;
        }
    }

    @Async
    public String uploadFile(byte[] buffer, String bucketName, String folderName, String contentType) {
        try {
            if (this.isBucketExist(bucketName)) {
                String objectName = UUID.randomUUID().toString().replace("-", "");
                String object = this.handleObjectName(contentType, objectName, folderName);
                log.info("Start uploading object " + object);
                this.minioClient.putObject((PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(bucketName)).object(object)).stream(new ByteArrayInputStream(buffer), (long)buffer.length, -1L).contentType(contentType).build());
                log.info("Upload file successfully");
                return this.getObjectUrl(bucketName, object);
            } else {
                return null;
            }
        } catch (Exception var7) {
            log.error("Error while uploading:", var7);
            return null;
        }
    }

    @Async
    public String uploadFile(byte[] buffer, String bucketName, String folderName, String fileType, String contentType) {
        try {
            if (this.isBucketExist(bucketName)) {
                String objectName = UUID.randomUUID().toString().replace("-", "");
                if (!DataUtils.isNullObject(fileType)) {
                    objectName = objectName + "." + fileType;
                }

                String object = this.handleObjectName(contentType, objectName, folderName);
                log.info("Start uploading object " + object);
                this.minioClient.putObject((PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(bucketName)).object(object)).stream(new ByteArrayInputStream(buffer), (long)buffer.length, -1L).contentType(contentType).build());
                log.info("Upload file successfully");
                return this.getObjectUrl(bucketName, object);
            } else {
                return null;
            }
        } catch (Exception var8) {
            log.error("Error while uploading:", var8);
            return null;
        }
    }

    private String getFilePath(String contentType, String filename) {
        if (FOLDER_MAP.containsKey(contentType)) {
            String var10000 = (String)FOLDER_MAP.get(contentType);
            return var10000 + filename;
        } else {
            return filename;
        }
    }

    public CompletableFuture<String> upload(byte[] data, String fileName, String bucketName, String folderName, String contentType) {
        return CompletableFuture.supplyAsync(() -> {
            String filePath = folderName + "/" + this.getFilePath(contentType, fileName);
            log.info("Start uploading object " + fileName);

            try {
                this.minioClient.putObject((PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(bucketName)).object(filePath)).stream(new ByteArrayInputStream(data), (long)data.length, -1L).contentType(contentType).build());
            } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException | IllegalArgumentException | IOException | InvalidKeyException var8) {
                try {
                    throw new Exception(var8.getMessage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            log.info("Upload file successfully");
            return this.getObjectUrl(bucketName, filePath);
        });
    }

    @Async
    public CompletableFuture<List<String>> uploadFiles(List<InputStream> inputStreamList, String bucketName, String folderName, String contentType) {
        try {
            List<String> urlList = new ArrayList();
            if (!this.isBucketExist(bucketName)) {
                return CompletableFuture.completedFuture((List<String>) null);
            } else {
                Iterator var6 = inputStreamList.iterator();

                while(var6.hasNext()) {
                    InputStream inputStream = (InputStream)var6.next();
                    byte[] buffer = new byte[inputStream.available()];
                    inputStream.read(buffer);
                    String objectName = UUID.randomUUID().toString().replace("-", "");
                    String object = this.handleObjectName(contentType, objectName, folderName);
                    log.info("Start uploading object " + object);
                    this.minioClient.putObject((PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(bucketName)).object(object)).stream(new ByteArrayInputStream(buffer), (long)buffer.length, -1L).contentType(contentType).build());
                    log.info("Upload file successfully");
                    urlList.add(this.getObjectUrl(bucketName, object));
                }

                return CompletableFuture.completedFuture(urlList);
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            return null;
        }
    }

    public List<String> downloadFileByFilePath(String bucket, String object) {
        InputStream inputStream = null;
        List<String> contentLines = null;

        try {
            log.info("bucket,object: " + bucket + " " + object);
            inputStream = this.minioClient.getObject((GetObjectArgs)((GetObjectArgs.Builder)((GetObjectArgs.Builder)GetObjectArgs.builder().bucket(bucket)).object(object)).build());
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            contentLines = new ArrayList();

            String line;
            while((line = br.readLine()) != null) {
                contentLines.add(line);
            }
        } catch (Exception var10) {
            log.error("downloadFileByFilePath error", var10);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }

        return contentLines;
    }

    @Async
    public void downloadFile(String bucketName, String objectName, String fileName) {
        try {
            log.info("Start downloading object " + objectName);
            this.minioClient.downloadObject((DownloadObjectArgs)((DownloadObjectArgs.Builder)((DownloadObjectArgs.Builder)DownloadObjectArgs.builder().bucket(bucketName)).object(objectName)).filename(fileName).build());
            log.info("Download file successfully");
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    @Async
    public ObjectWriteResponse copyObject(String bucketName, String objectName) {
        try {
            log.info("Start downloading object " + objectName);
            ObjectWriteResponse o = this.minioClient.copyObject((CopyObjectArgs)((CopyObjectArgs.Builder)((CopyObjectArgs.Builder)CopyObjectArgs.builder().bucket(bucketName)).object(objectName)).build());
            log.info("Download file successfully");
            return o;
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public String getObjectUrl(String bucketName, String objectName) {
        try {
            if (this.isBucketExist(bucketName)) {
                log.info("Getting object url with object name " + objectName);
                String url = this.domain + "/" + bucketName + "/" + objectName;
                return url;
            } else {
                log.info("Bucket not found");
                return "Bucket doesn't exist";
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            return var4.getMessage();
        }
    }

    @Async
    public void updateFile(String filePath, String bucketName, String objectName, String contentType) {
        try {
            InputStream in = new FileInputStream(new File(filePath));

            try {
                byte[] imageBuffer = new byte[in.available()];
                in.read(imageBuffer);
                log.info("Start updating file with object name" + objectName);
                this.minioClient.putObject((PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(bucketName)).object(objectName)).stream(new ByteArrayInputStream(imageBuffer), (long)imageBuffer.length, -1L).contentType(contentType).build());
                log.info("Update file successfully ");
            } catch (Throwable var9) {
                try {
                    in.close();
                } catch (Throwable var8) {
                    var9.addSuppressed(var8);
                }

                throw var9;
            }

            in.close();
        } catch (Exception var10) {
            var10.printStackTrace();
        }

    }

    public String handleObjectName(String contentType, String inputObjectName, String folderName) {
        String objectName = "";
        if (contentType.equals("image/png")) {
            objectName = "images/" + inputObjectName + ".png";
        } else if (contentType.equals("image/jpeg")) {
            objectName = "images/" + inputObjectName + ".jpeg";
        } else if (contentType.equals("application/pdf")) {
            objectName = "pdf/" + inputObjectName + ".pdf";
        } else if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            objectName = "excel/" + inputObjectName + ".xlsx";
        } else if (contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
            objectName = "word/" + inputObjectName + ".docx";
        } else if (contentType.equals("text/plain")) {
            objectName = "text/" + inputObjectName + ".txt";
        } else if (contentType.equals("video/mp4")) {
            objectName = "videos/" + inputObjectName + ".mp4";
        } else {
            objectName = inputObjectName;
        }

        if (folderName != null && !folderName.isEmpty()) {
            objectName = folderName + "/" + objectName;
        }

        return objectName;
    }

    public String getPrivateObjectUrl(String bucketName, String objectName) {
        try {
            log.info("Start get private object url with bucket name " + bucketName + "and object name = " + objectName);
            return this.minioClient.getPresignedObjectUrl((GetPresignedObjectUrlArgs)((GetPresignedObjectUrlArgs.Builder)((GetPresignedObjectUrlArgs.Builder)GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucketName)).object(objectName)).build());
        } catch (Exception var4) {
            log.error("Error while getting private url:", var4);
            return null;
        }
    }

    public void removeObject(String bucketName, String objectName) {
        try {
            log.info("Start remove object with bucket name = " + bucketName + " and object name = " + objectName);
            this.minioClient.removeObject((RemoveObjectArgs)((RemoveObjectArgs.Builder)((RemoveObjectArgs.Builder)RemoveObjectArgs.builder().bucket(bucketName)).object(objectName)).build());
            log.info("Finish remove object with bucket name = " + bucketName + " and object name = " + objectName);
        } catch (Exception var4) {
            log.error("Error while delete object " + var4);
        }

    }

    public String getPrivateObjectUrl(String bucketName, String objectName, int timeout, TimeUnit timeUnit) {
        try {
            log.info("Start get private object url with bucket name " + bucketName + "and object name = " + objectName);
            return this.minioClient.getPresignedObjectUrl((GetPresignedObjectUrlArgs)((GetPresignedObjectUrlArgs.Builder)((GetPresignedObjectUrlArgs.Builder)GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucketName)).object(objectName)).expiry(timeout, timeUnit).build());
        } catch (Exception var6) {
            log.error("Error while getting private url:", var6);
            return null;
        }
    }

    public void validateBucketExisted(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!this.isBucketExist(bucketName)) {
            this.makeBucket(bucketName);
        }

    }

    public String uploadFile(InputStream inputStream, String bucketName, String contentType) throws Exception {
        this.validateBucketExisted(bucketName);
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        String objectName = UUID.randomUUID().toString().replace("-", "");
        String object = this.handleObjectName(contentType, objectName, (String)null);
        log.info("Start uploading object " + object);
        this.minioClient.putObject((PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(bucketName)).object(object)).stream(new ByteArrayInputStream(buffer), (long)buffer.length, -1L).contentType(contentType).build());
        log.info("Upload file successfully");
        return this.getObjectUrl(bucketName, object);
    }

    public static String getObjectFromUrl(String objectLink, String bucketName, String domain) throws Exception {
        if (!domain.endsWith("/")) {
            domain = domain.concat("/");
        }

        int indexOfDomain = objectLink.indexOf(domain);
        if (indexOfDomain == -1) {
            throw new Exception("bucket.not.existed");
        } else {
            String prefix = domain.concat(bucketName).concat("/");
            int indexOfPrefix = objectLink.indexOf(prefix);
            if (indexOfPrefix == -1) {
                throw new Exception("bucket.not.existed");
            } else {
                return objectLink.substring(prefix.length());
            }
        }
    }

    public void removeObject(String url) throws Exception {
        String[] splits = url.split("/");
        String bucketName = "";
        String objectName = "";
        if (url.startsWith("http")) {
            bucketName = splits[3];
        } else if (url.startsWith("/")) {
            bucketName = splits[1];
        } else {
            bucketName = splits[0];
        }

        if (bucketName.equals("")) {
            throw new RuntimeException("Bucket not valid");
        } else {
            int indexOfObjName = url.indexOf(bucketName) + bucketName.length() + 1;
            if (indexOfObjName > url.length() - 1) {
                throw new RuntimeException("Object not valid");
            } else {
                objectName = url.substring(indexOfObjName);
                log.info("Start remove object with bucket name = " + bucketName + " and object name = " + objectName);
                this.minioClient.removeObject((RemoveObjectArgs)((RemoveObjectArgs.Builder)((RemoveObjectArgs.Builder)RemoveObjectArgs.builder().bucket(bucketName)).object(objectName)).build());
                log.info("Finish remove object with bucket name = " + bucketName + " and object name = " + objectName);
            }
        }
    }

    static {
        FOLDER_MAP.put("image/png", "images/");
        FOLDER_MAP.put("image/jpeg", "images/");
        FOLDER_MAP.put("video/mp4", "videos/");
        FOLDER_MAP.put("text/plain", "text/");
        FOLDER_MAP.put("application/pdf", "pdf/");
        FOLDER_MAP.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "excel/");
        FOLDER_MAP.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "word/");
    }
}
