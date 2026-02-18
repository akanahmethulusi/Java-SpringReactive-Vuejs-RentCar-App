package at.codefabrik.Springreactivevuejs.filestore.service.s3;


import co.elastic.clients.util.ContentType;

import java.io.File;
import java.io.InputStream;

public interface FileService {
    void save(String id, String contentType, InputStream isFile);
    void delete(String id);
    byte[] get(String id);
}
