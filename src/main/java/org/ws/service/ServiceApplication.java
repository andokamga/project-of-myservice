package org.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.ws.service.security.IServiceInit;

@SpringBootApplication
public class ServiceApplication implements CommandLineRunner{

	@Autowired
	private IServiceInit iServiceInit;
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		iServiceInit.initRole();
		iServiceInit.initUser();
		iServiceInit.initCategory();
		iServiceInit.initTown();
		iServiceInit.initService();
		iServiceInit.initOrder();
	}

}
