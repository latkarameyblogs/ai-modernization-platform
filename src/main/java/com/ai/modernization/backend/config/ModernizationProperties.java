package com.ai.modernization.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "modernization.source")
public class ModernizationProperties {

    private String githubFileUrl;

    public String getGithubFileUrl() {
        return githubFileUrl;
    }

    public void setGithubFileUrl(String githubFileUrl) {
        this.githubFileUrl = githubFileUrl;
    }
}