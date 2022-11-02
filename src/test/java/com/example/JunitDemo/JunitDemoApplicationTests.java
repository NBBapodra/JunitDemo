package com.example.JunitDemo;

import com.example.JunitDemo.Repository.UserRepository;
import com.example.JunitDemo.Services.UserService;
import com.example.JunitDemo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class JunitDemoApplicationTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void getUserTest()
	{
		when(userRepository.findAll()).thenReturn(Stream.of(new User(111,"Niteen",22,"India"),
				new User(112,"Rahul",20,"USA")).collect(Collectors.toList()));

		assertEquals(2,userService.getUsers().size());
	}

	@Test
	public void getUserByAddressTest()
	{
		String address="Bangalore";
		when(userRepository.findByAddress(address)).thenReturn(Stream.of(new User(111,"Niteen",22,"India")).collect(Collectors.toList()));
		assertEquals(1,userService.getUserbyAddress(address).size());
	}

	@Test
	public void saveUserTest()
	{
		User user=new User(113,"Manish",23,"Canada");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user,userService.addUser(user));
	}

	@Test
	public void deleteUserTest()
	{
		User user=new User(113,"Manish",23,"Canada");
		userService.deleteUser(user);
		verify(userRepository,times(1)).delete(user);
	}
}


