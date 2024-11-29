package ru.kpfu.itis.entities;

import lombok.Data;

import java.util.List;

@Data
public class Team {

    private int id;

    private String name;

    private User owner;


    private List<User> teammates;

    private List<Section> sections;

}
