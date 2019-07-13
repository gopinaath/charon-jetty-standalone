package com.github.mkopylec.charon.jetty;

import com.github.mkopylec.charon.configuration.CharonConfigurer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.mkopylec.charon.configuration.CharonConfigurer.charonConfiguration;
import static com.github.mkopylec.charon.configuration.RequestMappingConfigurer.requestMapping;
import static com.github.mkopylec.charon.forwarding.interceptors.rewrite.RegexRequestPathRewriterConfigurer.regexRequestPathRewriter;
import static com.github.mkopylec.charon.forwarding.interceptors.rewrite.RequestServerNameRewriterConfigurer.requestServerNameRewriter;

@Configuration
class CharonConfiguration {

    @Bean
    @ConditionalOnMissingBean
    CharonConfigurer charonConfigurer() {
        return charonConfiguration()
                .set(requestServerNameRewriter().outgoingServers("https://github.com"))
                .set(regexRequestPathRewriter().paths("/charon-jetty-standalone-1.0/(?<path>.*)", "/<path>"))
                .add(requestMapping("all requests"));
    }
}
