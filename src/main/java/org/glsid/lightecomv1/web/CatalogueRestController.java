package org.glsid.lightecomv1.web;

import org.glsid.lightecomv1.dao.ProductRepository;
import org.glsid.lightecomv1.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin("*")
@RestController
public class CatalogueRestController {

    private ProductRepository productRepository;

    public CatalogueRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/photoProduct/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws IOException {
        Product p= productRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()));
    }

    @PostMapping(path = "/uploadPhoto/{id}")
    public void uploadFile(MultipartFile file,@PathVariable("id")  Long id) throws IOException {
        Product p=productRepository.findById(id).get();
        p.setPhotoName(id+".jpg");
        Files.write(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()),file.getBytes());
        productRepository.save(p);
    }
}
