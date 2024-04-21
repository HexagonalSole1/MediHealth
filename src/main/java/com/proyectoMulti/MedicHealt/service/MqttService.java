package com.proyectoMulti.MedicHealt.service;

import io.socket.emitter.Emitter;
import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

    SocketIoService socketIoService = new SocketIoService();
    private final IMqttClient mqttClient;
    private MqttConnectOptions options;

    public MqttService() {
        try {
            String publisherId = "/MedicHealth/sensores";
            mqttClient = new MqttClient("tcp://srv502440.hstgr.cloud:1883", publisherId);
            socketIoService.connect();
            configureConnectionOptions();
            connect();
            startListening();

            System.out.println("Escuchando el evento 'sendApiTemp'");
            socketIoService.listenToEvent("sendApiTemp", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    if (args.length > 0) {
                        Object receivedData = args[0];
                        if (receivedData instanceof JSONObject data) {
                            System.out.println("Evento 'sendApiTemp' recibido (JSON): " + data);
                        } else {
                            System.out.println("Evento 'sendApiTemp' recibido (No es JSON): " + receivedData.toString());
                        }
                    } else {
                        System.out.println("Evento 'sendApiTemp' recibido (Sin datos)");
                    }
                }
            });

        } catch (MqttException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el cliente MQTT", e);
        }

        if (socketIoService.isConnected()) {
            System.out.println("Conectado al socket de veras.");
        } else {
            System.out.println("No se pudo conectar al socket.");
        }
    }

    private void configureConnectionOptions() {
        options = new MqttConnectOptions();
        String username = "esp32";
        options.setUserName(username);
        String password = "1234";
        options.setPassword(password.toCharArray());
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(30);
        options.setKeepAliveInterval(60);
        mqttClient.setCallback(new MqttCallbackExtended() {

            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                if (reconnect) {
                    System.out.println("Reconexión exitosa al servidor MQTT: " + serverURI);
                } else {
                    System.out.println("Conexión exitosa al servidor MQTT: " + serverURI);

                }
            }

            @Override
            public void connectionLost(Throwable cause) {
                System.err.println("Conexión perdida con el servidor MQTT: " + cause.getMessage());
            }


            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("Mensaje recibido en el tema " + topic + ": " + new String(message.getPayload()));
                if (topic.equals("/MedicHealth/sensores/temperatura")){
                    // Convertir el mensaje recibido en formato JSON a un objeto JSON
                    JSONObject receivedJson = new JSONObject(new String(message.getPayload()));

                    // Crear un nuevo objeto JSON para enviar el mensaje
                    JSONObject data = new JSONObject();
                    data.put("message", receivedJson);

                    // Enviar el objeto JSON
                    socketIoService.sendMessage("reciveTempApi", data);
                }else {

                    // Convertir el mensaje recibido en formato JSON a un objeto JSON
                    JSONObject receivedJson = new JSONObject(new String(message.getPayload()));

                    // Crear un nuevo objeto JSON para enviar el mensaje
                    JSONObject data = new JSONObject();
                    data.put("message", receivedJson);

                    // Enviar el objeto JSON
                    socketIoService.sendMessage("reciveHeartApi", data);
                }
            }


            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    private void connect() throws MqttException {
        mqttClient.connect(options);
    }

    private void startListening() throws MqttException {
        mqttClient.subscribe("/MedicHealth/#"); // Escuchar todos los temas bajo /MedicHealth/
    }
}

