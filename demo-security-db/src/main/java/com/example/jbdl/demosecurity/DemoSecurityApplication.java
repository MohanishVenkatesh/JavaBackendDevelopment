package com.example.jbdl.demosecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoSecurityApplication implements CommandLineRunner {
//	@Autowired
//	static UserRepository userRepository;
	/*
		No dependency injection can't be static. since it need to create a object and put it in the IOC container .
		 So here userRepository will be null.
		 Even for all the @Value variables will contain null.
	 */
//	@Value("${ADMIN_AUTHORITY}")
//	private static String ADMIN_AUTHORITY ;
//
//	@Value("${STUDENT_ATTENDANCE_AUTHORITY}")
//	private static String STUDENT_ATTENDANCE_AUTHORITY;
//
//	@Value("${STUDENT_ONLY_AUTHORITY}")
//	private static String STUDENT_ONLY_AUTHORITY;
	@Autowired
	UserRepository userRepository;

	@Value("${ADMIN_AUTHORITY}")
	private String ADMIN_AUTHORITY ;

	@Value("${STUDENT_ATTENDANCE_AUTHORITY}")
	private String STUDENT_ATTENDANCE_AUTHORITY;

	@Value("${STUDENT_ONLY_AUTHORITY}")
	private String STUDENT_ONLY_AUTHORITY;

	public static void main(String[] args) {
		SpringApplication.run(DemoSecurityApplication.class, args);

//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//		MyUser myUser = MyUser.builder()
//				.userName("archit")
//				.password(bCryptPasswordEncoder.encode("archit123"))
//				.authorities(ADMIN_AUTHORITY + ":" + STUDENT_ATTENDANCE_AUTHORITY)
//				.build();
//
//		userRespository.save(myUser); // Null point Exception .
	}
	/*
	to overcome the above issue.
	 */
	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		MyUser myUser = MyUser.builder()
				.userName("dheeraj")
				.password(bCryptPasswordEncoder.encode("dheeraj123"))
				.authorities(STUDENT_ONLY_AUTHORITY+ ":" + STUDENT_ATTENDANCE_AUTHORITY)
				.build();

		userRepository.save(myUser);

	}
}
