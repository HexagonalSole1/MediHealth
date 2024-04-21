package com.proyectoMulti.MedicHealt.service;


import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SocketIoService {

    private URI uri = URI.create("http://44.194.186.129");
        private String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyam9obi5kb2VAZXhhbXBsZS5jb20iLCJ1c2VySWQiOjEsImlhdCI6MTcxMzM1OTcyOSwiZXhwIjozNjAwMTcxMzM1OTcyOX0.xBw4DcQrMQO0YCYoFdSpJYERMJ5jxbF9SZecHcjUfr8";

    private Map<String, List<String>> headers = new HashMap<>();
    private IO.Options options;
    private Socket socket;

    public SocketIoService() {
        List<String> authHeader = new ArrayList<>();
        authHeader.add("Bearer " + jwtToken);
        headers.put("Authorization", authHeader);

        options = IO.Options.builder()
                .setExtraHeaders(headers)
                .build();

        socket = IO.socket(uri, options);
    }

    public void connect() {
        socket.connect();
    }

    public boolean isConnected() {
        return socket.connected();
    }

    public void sendMessage(String eventName, JSONObject data) {
        if (socket != null && socket.connected()) {
            System.out.println("Se envio el mensaje a : " + eventName);
            socket.emit(eventName, data);
        } else {
            System.out.println("No se pudo enviar el mensaje, el socket no está conectado.");
        }
    }

    public void disconnect() {
        socket.disconnect();
    }

    public void listenToEvent(String eventName, Emitter.Listener listener) {
        socket.on(eventName, listener);
    }
}
