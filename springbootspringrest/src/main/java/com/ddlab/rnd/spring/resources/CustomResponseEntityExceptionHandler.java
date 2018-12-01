package com.ddlab.rnd.spring.resources;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ddlab.rnd.spring.entity.EndPointApiError;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;


@ControllerAdvice
public class CustomResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { NullPointerException.class, IllegalArgumentException.class, UnrecognizedPropertyException.class })
	protected ResponseEntity<EndPointApiError> handleInvalidInput(RuntimeException ex) {
		String shortErrorMessage = ex.getMessage();
		shortErrorMessage = (shortErrorMessage == null) ?  getMessageByLine(ex,1) : shortErrorMessage;
		EndPointApiError epError = new EndPointApiError(HttpStatus.BAD_REQUEST, shortErrorMessage, ex);
		return new ResponseEntity(epError, HttpStatus.BAD_REQUEST);
	}
	
	private static String getMessageByLine(Exception e, int maxLines) {
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		String[] lines = writer.toString().split("\n");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Math.min(lines.length, maxLines); i++) {
			sb.append(lines[i]).append("\n");
		}
		return sb.toString();
	}
	
}