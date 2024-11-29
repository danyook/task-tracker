package ru.kpfu.itis.entities;

import lombok.Data;
import ru.kpfu.itis.entities.enums.TaskStatus;

import java.util.Date;

@Data
public class Task {

    private int id;

    private String name;

    private String description;

    private TaskStatus taskStatus;

    private Date dateOfAdd;


    private Section section;

}
