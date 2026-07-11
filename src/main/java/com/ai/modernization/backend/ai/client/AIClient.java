package com.ai.modernization.backend.ai.client;

public interface AIClient {

    <T> T prompt(String prompt, Class<T> responseType);

}