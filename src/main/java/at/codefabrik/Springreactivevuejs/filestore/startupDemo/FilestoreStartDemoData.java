package at.codefabrik.Springreactivevuejs.filestore.startupDemo;


import at.codefabrik.Springreactivevuejs.filestore.config.S3ConfigProperties;
import at.codefabrik.Springreactivevuejs.filestore.service.FileStoreService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FilestoreStartDemoData {
    private final S3ConfigProperties s3ConfigProperties;
    private final MinioClient minioClient;

    @EventListener(ApplicationStartedEvent.class)
    public void migrate() throws Exception{
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(s3ConfigProperties.getBucketName())
                .build());
        if (!bucketExists) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(s3ConfigProperties.getBucketName())
                    .build());
        }
    }
}
