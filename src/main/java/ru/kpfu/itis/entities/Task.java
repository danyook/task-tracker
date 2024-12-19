package ru.kpfu.itis.entities;

import lombok.*;
import ru.kpfu.itis.entities.enums.TaskStatus;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {

    private int id;

    private String name;

    private String description;

    private TaskStatus taskStatus;

    private Timestamp dateOfAdd;


    private Section section;

}
