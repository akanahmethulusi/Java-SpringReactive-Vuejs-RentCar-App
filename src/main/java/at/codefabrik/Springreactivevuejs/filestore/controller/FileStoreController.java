package at.codefabrik.Springreactivevuejs.filestore.controller;

import at.codefabrik.Springreactivevuejs.filestore.service.FileStoreService;
import at.codefabrik.Springreactivevuejs.product.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/filestore")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FileStoreController {
    private final FileStoreService fileStoreService;

    @GetMapping("{id}")
    public Mono<Void> getImage(@PathVariable String id, ServerWebExchange serverWebExchange) throws Exception{
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.IMAGE_JPEG);
        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
        return fileStoreService.getImage(id)
                .flatMap(img -> {
                        //return response.writeWith(Mono.just(response.bufferFactory().wrap(img)));
                        return response.writeWith(Mono.just(dataBufferFactory.wrap(img)));
                });
    }
}
