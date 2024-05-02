package com.example.projetokafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class GsonDeserializer<T> implements Deserializer {

    public static final String TYPE_CONFIG = "projeto.kafka";
    private final Gson gson = new GsonBuilder().create();
    private Class<T> classType;

    @Override
    public void configure(Map configs, boolean isKey) {
        String type = String.valueOf(configs.get(TYPE_CONFIG));
        try {
            classType = (Class<T>) Class.forName(type);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Tipo incorreto para deserializar",e);
        }
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        return gson.fromJson(new String(bytes), this.classType);
    }

}