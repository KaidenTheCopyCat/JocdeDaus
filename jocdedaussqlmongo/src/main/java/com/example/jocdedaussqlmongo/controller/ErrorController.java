package com.example.jocdedaussqlmongo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

	@GetMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String errorMsg = "Ooops! S'ha produït un error.";

		if (status != null) {
			int statusCode = Integer.parseInt(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				errorMsg = "Pàgina no trobada.";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				errorMsg = "Error intern del servidor.";
			}
		}

		model.addAttribute("errorMsg", errorMsg);
		return "error";
	}

	public String getErrorPath() {
		return "/error";
	}
}
