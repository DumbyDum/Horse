package com.satanbakespancakes.Kitten;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class KittenApplication {

	@RequestMapping("/")
	@ResponseBody
	String home(){
		return "hello";
	}

	public static void main(String[] args) {
		SpringApplication.run(KittenApplication.class, args);
	}

}
