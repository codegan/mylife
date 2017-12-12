package ru.codegan.mylife.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/errors")
public class ErrorsController {
	@RequestMapping(method = RequestMethod.GET, value="/403")
	String error403() {
		return "Нет привилегии: 403";
	}
	@RequestMapping(method = RequestMethod.GET, value="/404")
	String error404() {
		return "Не найдено: 404";
	}
	@RequestMapping(method = RequestMethod.GET, value="/401")
	String error401() {
		return "Не авторизован: 401";
	}
	@RequestMapping(method = RequestMethod.GET, value="/200")
	String error200() {
		return "Успешно: 200";
	}
}
