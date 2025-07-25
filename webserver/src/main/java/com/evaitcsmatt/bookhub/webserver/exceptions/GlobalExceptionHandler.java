package com.evaitcsmatt.bookhub.webserver.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorMessage> bookNotFoundExceptionHandler(
			HttpServletRequest request, 
			HttpServletResponse response, 
			BookNotFoundException exception
			) {
		ErrorMessage errorMessage = new ErrorMessage(
				exception.getMessage(),
				request.getRequestURI(),
				HttpStatus.NOT_FOUND.value(),
				LocalDateTime.now()
				);
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public String globalExceptionHandler(
			HttpServletRequest request,
			HttpServletResponse response,
			Exception exception
			) {
		request.setAttribute("errorMessage", exception.getMessage());
		request.setAttribute("requestUri", request.getRequestURI());
		request.setAttribute("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
		request.setAttribute("timestamp", LocalDateTime.now());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return "error";
	}
}
