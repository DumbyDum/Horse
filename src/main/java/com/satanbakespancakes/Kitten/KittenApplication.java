package com.satanbakespancakes.Kitten;


import org.apache.tomcat.jni.Buffer;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

@Controller
@SpringBootApplication
public class KittenApplication {

	long a = 0;
	Random r = new Random();
	double last_g = 0;

	@RequestMapping(
			value = "/kitten",
			method= RequestMethod.GET,
			produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	ResponseEntity<byte[]> getKitten() throws IOException {
		Resource img = new ClassPathResource("src/main/resources/static/kitten.jpg");
		byte[] bytes = StreamUtils.copyToByteArray(img.getInputStream());

		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(bytes);
	}

	@RequestMapping("/gaussian")
	@ResponseBody
	String getGaussian(){
		r.setSeed(a);
		last_g = r.nextGaussian();
		return Double.toString(last_g);
	}

	@RequestMapping("/")
	@ResponseBody
	String hello(){
		return "Hello!";
	}


	public static void main(String[] args) {
		SpringApplication.run(KittenApplication.class, args);
	}

}
