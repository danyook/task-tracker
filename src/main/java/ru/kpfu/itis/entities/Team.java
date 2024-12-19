package ru.kpfu.itis.entities;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Team {

    private int id;

    private String name;

    private User owner;


    private List<User> teammates;

    private List<Section> sections;

}
