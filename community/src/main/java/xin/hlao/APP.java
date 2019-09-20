package xin.hlao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@MapperScan("xin.hlao.dao")
public class APP extends SpringBootServletInitializer  {
		
	public static void main(String[] args) {
		SpringApplication.run(APP.class, args);
	}
	//为了打包springboot项目 
	 @Override
	 protected SpringApplicationBuilder configure( 
	   SpringApplicationBuilder builder) { 
	  return builder.sources(this.getClass()); 
	 } 
}
