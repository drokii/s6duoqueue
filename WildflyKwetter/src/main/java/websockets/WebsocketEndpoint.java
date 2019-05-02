package websockets;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;

@ApplicationScoped
@ServerEndpoint("/kwettersocket")
public class WebsocketEndpoint {

    HashSet<Session> sessions;

    @PostConstruct
    public void init(){
        sessions = new HashSet<>();
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("A client connected!");
         sessions.add(session);
    }

    @OnMessage
    public void onMessage(String msg) {

        System.out.println(msg);
        try {
            for (Session s : sessions
            ) {
                s.getBasicRemote().sendText("Refresh, Please.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnClose
    public void onClose(Session session){
        sessions.remove(session);
    }

}
