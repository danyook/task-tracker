package ru.kpfu.itis.entities;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private int id;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String profilePicture;


    private List<Team> teams;

    private List<Section> sections;


}
