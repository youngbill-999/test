package com.nowcoder.community;


import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;
    @Autowired
	private UserMapper userMapper;
	@Autowired
	private DiscussPostMapper discussPostMapper;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApp(){
		System.out.println(applicationContext);
	}


	@Test
	public void testSelectUser() {
		User user = userMapper.selectById(101);
		System.out.println(user);

		user = userMapper.selectByName("liubei");
		System.out.println(user);

		user = userMapper.selectByEmail("nowcoder101@sina.com");
		System.out.println(user);
	}

	@Test
	public void testSelectPosts() {
		List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149, 0, 10);
		for(DiscussPost post : list) {
			System.out.println(post);
		}

		int rows = discussPostMapper.selectDiscussPostRows(149);
		System.out.println(rows);
	}

	@Test
	public void testPage()
	{
		Page page = new Page();
		page.setRows(0);//首页按0来查询
		page.setPath("/index");
		//System.out.println(page.path);

	}


	@Autowired
	MailClient mailClient;
	@Autowired
	private TemplateEngine templateEngine;
	//MailTest
	@Test
	public void mailTest(){
		Context context = new Context();
		context.setVariable("username","BeiYu");//<p>Hallo,<span style="color:red;" th:text="${username}"></span></p>
		String content = templateEngine.process("/mail/demo",context);
		mailClient.setMailSender("670181662@qq.com","New Test", content);
	}
}

