package ru.kpfu.itis.entities;

import lombok.*;
import ru.kpfu.itis.entities.enums.SectionRole;
import ru.kpfu.itis.entities.enums.SectionType;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Section {

    private int id;

    private String name;

    private SectionType type;

    private SectionRole role;


    private List<Task> tasks;

    private Team team;

    private User user;
}
