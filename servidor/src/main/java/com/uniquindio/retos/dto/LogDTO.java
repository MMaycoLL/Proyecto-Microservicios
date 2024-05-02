package com.uniquindio.retos.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class LogDTO {

    private String application;

    private String logType;

    private String module;

    private LocalDateTime dateTime;

    private String summary;

    private String description;

}
