package com.globallogic.catalogo.configuration;

import com.globallogic.catalogo.dto.MessageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("messages")
public class MessagesConfiguration {

    private MessageDto movieDeleted;
}
