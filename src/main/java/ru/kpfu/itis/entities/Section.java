package ru.kpfu.itis.entities;

import lombok.Data;
import ru.kpfu.itis.entities.enums.SectionRole;
import ru.kpfu.itis.entities.enums.SectionType;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;


@Data
public class Section {

    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SectionType type;

    @Enumerated(EnumType.STRING)
    private SectionRole role;


    private List<Task> tasks;

    private Team team;

    private User user;
}
