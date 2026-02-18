package at.codefabrik.Springreactivevuejs.filestore.service.s3;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
/*
@Service
@Primary
public class LocalFileService implements FileService {

    private final File baseDir;

    public LocalFileService() {
        this.baseDir = new File(System.getProperty("java.io.tmpdir"), "filestore");
        if (!baseDir.exists()) {
            baseDir.mkdirs();
        }
    }

    @Override
    @SneakyThrows
    public void save(String id, String contentType, InputStream isFile) {
        File file = new File(baseDir, id);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] buffer = new byte[8192];
            int read;
            while ((read = isFile.read(buffer)) != -1) {
                fos.write(buffer, 0, read);
            }
        }
    }

    @Override
    @SneakyThrows
    public void delete(String id) {
        File file = new File(baseDir, id);
        if (file.exists()) {
            Files.delete(file.toPath());
        }
    }

    @Override
    @SneakyThrows
    public byte[] get(String id) {
        File file = new File(baseDir, id);
        if (!file.exists()) return new byte[0];
        return Files.readAllBytes(file.toPath());
    }
}
*/