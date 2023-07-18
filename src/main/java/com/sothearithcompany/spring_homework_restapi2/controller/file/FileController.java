package com.sothearithcompany.spring_homework_restapi2.controller.file;

import com.sothearithcompany.spring_homework_restapi2.model.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.UUID;

@RestController
@Tag(name = "File Controller")
@RequestMapping("/api/v1/library/")
public class FileController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String uuid = UUID.randomUUID().toString();
//            File path = new File("D:\\Images\\" + uuid + file.getOriginalFilename());
            String dir = System.getProperty("user.dir");
            System.out.println(dir);
            File path = new File(dir + "\\src\\main\\resources\\images\\" + uuid + file.getOriginalFilename());
            path.createNewFile();
            FileOutputStream output = new FileOutputStream(path);
            output.write(file.getBytes());
            output.close();
            return ResponseEntity.ok(new ApiResponse<String>(HttpStatus.CREATED.value(), "Uploaded file", uuid + file.getOriginalFilename(), new Date().toString()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
