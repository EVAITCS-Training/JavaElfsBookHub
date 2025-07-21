package com.evaitcsmatt.bookhub.webserver.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import com.evaitcsmatt.bookhub.webserver.service.BookService;

@Component
public class BookServiceFactory {
	
	@Autowired
	private BookService userBookService;
	
	@Autowired
	@Qualifier("admin")
	private BookService adminBookService;
	
	public BookService getBookService(String roleString) {
		if(roleString != null && roleString.equals("admin")) {
			return adminBookService;
		}
		return userBookService;
	}

}
