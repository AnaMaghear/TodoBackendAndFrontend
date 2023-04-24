package com.ana.todobackend.Models;
import lombok.*;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class TodoDTO {
    private Long id;
    private String title;
    private String description;
    private ZonedDateTime dueDate;
    private boolean done;
}
