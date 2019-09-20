package xin.hlao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;

import xin.hlao.APP;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {APP.class})
public class MyTest {

	@Test
	public void fun()throws Exception {
//		System.out.println("haha");
//		System.out.println(ResourceUtils.getURL("static/image/user").getPath());
	}
	
}
