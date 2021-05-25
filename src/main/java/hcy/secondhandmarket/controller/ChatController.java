package hcy.secondhandmarket.controller;

import hcy.secondhandmarket.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * "/app"로 시작하는 대상이 있는 클라이언트에서 보낸 모든 메시지는
 * @MessageMapping 어노테이션이 달린 메서드로 라우팅 됩니다.
 * WebSocketConfig 의 설정과 관련.
 * 원래는 클라이언트가 요청을 보내야 서버에서 응답하는데,
 * 웹소켓 같은 경우는 서버가 먼저 클라이언트에게 정보를 전달해줌. (SNS, 실시간 게임 등..)
 **/
@Controller
public class ChatController {

    @GetMapping("/chat")
    public String chatPage() {
        return "/chats/chat";
    }

    // /app/chat.sendMessage 인 메세지가 여기로 라우팅됨.
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
