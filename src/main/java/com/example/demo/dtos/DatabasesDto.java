package com.example.demo.dtos;

import com.example.demo.enums.DatabaseType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DatabasesDto {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String alias;
    private String driverClassName;
    private String host;
    private Integer port;
    private DatabaseType type;
    private Boolean status;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

}
