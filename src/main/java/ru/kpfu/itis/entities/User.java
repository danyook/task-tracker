package ru.kpfu.itis.entities;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private int id;

    private String username;

    private String name;

    private String surname;

    private String email;


    private List<Team> teams;

    private List<Section> sections;


}
