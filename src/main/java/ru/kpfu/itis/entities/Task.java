package ru.kpfu.itis.entities;

import lombok.Data;
import ru.kpfu.itis.entities.enums.TaskStatus;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Task {

    private int id;

    private String name;

    private String description;

    private TaskStatus taskStatus;

    private Timestamp dateOfAdd;


    private Section section;

}
