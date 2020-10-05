package com.ilkengin.messagingrabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ilkengin.messagingrabbitmq.config.RabbitConfig;

@RestController
@RequestMapping(value = { "/send" })
public class SendMessageController {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String sendDefaultMessage()
    {
		System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(RabbitConfig.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
        return "SENT";
    }
	
	@PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String sendMessage(@RequestBody String message)
    {
		System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(RabbitConfig.topicExchangeName, "foo.bar.baz", message);
        return "SENT";
    }
}
