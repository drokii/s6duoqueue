package websockets;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@ServerEndpoint("/kwettersocket")
public class WebsocketEndpoint {

    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("A client connected!");
        System.out.println("Sessions connected: " + sessions.size());

    }

    @OnMessage
    public void onMessage(String msg) throws IOException {

        System.out.println(msg);
        synchronized (sessions) {

            for (Session s : sessions
            ) {
                s.getBasicRemote().sendText("Refresh, Please.");
            }

        }


    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

}
