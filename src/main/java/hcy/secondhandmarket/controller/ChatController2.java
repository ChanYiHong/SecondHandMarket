package hcy.secondhandmarket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//@Controller
//@Slf4j
//@ServerEndpoint("/websocket")
public class ChatController2 {
// extends Socket {

//    private static final List<Session> session = new ArrayList<>();
//
//    @GetMapping("/chat")
//    public String testChat() {
//        return "/chat";
//    }
//
//    @OnOpen
//    public void open(Session newUser) {
//        log.info("connected");
//        session.add(newUser);
//        log.info(newUser.getId());
//    }
//
//    @OnMessage
//    public void getMsg(Session receiveSession, String msg) {
//        for(int i = 0; i < session.size(); i++) {
//            if(!receiveSession.getId().equals(session.get(i).getId())) {
//                try {
//                    session.get(i).getBasicRemote().sendText(session.get(i).getUserPrincipal().getName() + " : " + msg);
//                }catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            else {
//                try {
//                    session.get(i).getBasicRemote().sendText(session.get(i).getUserPrincipal().getName() + " : " + msg);
//                }catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

}
