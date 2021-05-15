package com.emindor.horse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Random;

@Controller
@SpringBootApplication
public class HorseApplication {

	double a = 0;
	Random r = new Random();
	@Autowired
	ResourceLoader loader;


	@RequestMapping(
			value = "/horse",
			method= RequestMethod.GET,
			produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	ResponseEntity<byte[]> getKitten() throws IOException {

		Resource img = new ClassPathResource("classpath:/static/img.png");
		byte[] bytes = StreamUtils.copyToByteArray(img.getInputStream());

		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(bytes);
	}

	@RequestMapping("/gaussian")
	@ResponseBody
	String getGaussian(){
		return Double.toString(r.nextGaussian());
	}

	@RequestMapping("/")
	@ResponseBody
	String hello(){
		return "Hi!";
	}

	@RequestMapping("/gaussian_elapsed")
	@ResponseBody
	String getGraph(){
		a+=0.5;
		double last_g = r.nextGaussian();
		long startTime = System.nanoTime();
		while (last_g < a){
			r.setSeed(r.nextLong());
			last_g = r.nextGaussian();
		}

		return (System.nanoTime()-startTime) + "\n"+ a + "\n" +last_g;
	}



	public static void main(String[] args) {
		SpringApplication.run(HorseApplication.class, args);
	}

}
