package com.example.spread_ranker.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class BidAskDeserializer extends JsonDeserializer<BidAskResponse> {

    @Override
    public BidAskResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);

        String price = node.get(0).asText();
        String amount = node.get(1).asText();

        return new BidAskResponse(price, amount);
    }
}