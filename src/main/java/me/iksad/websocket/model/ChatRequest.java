package me.iksad.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatRequest {
    private Long senderId;
    private Long receiverId;
    private Long roomId;
    private String message;
}
