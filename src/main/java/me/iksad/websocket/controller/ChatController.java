package me.iksad.websocket.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.iksad.websocket.model.ChatRequest;
import me.iksad.websocket.service.ChattingService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController {
    private final ChattingService chattingService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/messages")
    public void chat(ChatRequest chatRequest) {
        chattingService.save(chatRequest);
        log.trace(chatRequest.toString());
        simpMessagingTemplate.convertAndSend("/sub/rooms/" + chatRequest.getRoomId(), chatRequest.getMessage());
    }
}
