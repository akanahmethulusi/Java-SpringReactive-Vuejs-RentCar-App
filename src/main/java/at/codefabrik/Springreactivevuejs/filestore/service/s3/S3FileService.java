package at.codefabrik.Springreactivevuejs.filestore.service.s3;

import at.codefabrik.Springreactivevuejs.filestore.config.S3ConfigProperties;
import co.elastic.clients.util.ContentType;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3FileService implements FileService {
    private final MinioClient minioClient;
    private final S3ConfigProperties properties;

    @Override
    public void save(String id, String contentType, InputStream isFile) {
        try {
            var object = PutObjectArgs.builder()
                    .contentType(contentType)
                    .stream(isFile, isFile.available(), -1)
                    .bucket(properties.getBucketName())
                    .object(id)
                    .build();
            minioClient.putObject(object);
        }catch (Exception e){
            log.error("Error uploading file to S3", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            var object = RemoveObjectArgs.builder()
                    .bucket(properties.getBucketName())
                    .object(id)
                    .build();
            minioClient.removeObject(object);
        }catch (Exception e){
            log.error("Error deleting file from S3", e);
        }
    }

    @Override
    public byte[] get(String id) {
        try {
            var object = GetObjectArgs.builder()
                    .bucket(properties.getBucketName())
                    .object(id)
                    .build();
            return minioClient.getObject(object).readAllBytes();
        }catch (Exception e){
            log.error("Error getting file from S3", e);
        }
        return new byte[0];
    }
}
