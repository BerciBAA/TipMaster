package com.backend.StudentTipMaster.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;

@Getter
@SendTo
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {
    private String exceptionMessage;
}
