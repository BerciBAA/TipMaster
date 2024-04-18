package com.backend.StudentTipMaster.response;

import lombok.*;
import org.springframework.messaging.handler.annotation.SendTo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {
    private String exceptionMessage;
}
