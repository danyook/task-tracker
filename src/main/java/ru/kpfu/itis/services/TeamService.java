package ru.kpfu.itis.services;

import ru.kpfu.itis.dao.TeamDAO;
import ru.kpfu.itis.dao.UserDAO;
import ru.kpfu.itis.entities.Section;
import ru.kpfu.itis.entities.Task;
import ru.kpfu.itis.entities.Team;
import ru.kpfu.itis.util.DbException;

import java.util.List;

public class TeamService {
    private static TeamService INSTANCE;

    private final TeamDAO teamDAO = TeamDAO.getInstance();
    private final UserDAO userDAO = UserDAO.getInstance();

    private TeamService() {
    }

    public static TeamService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TeamService();
        }
        return INSTANCE;
    }

    public List<Team> findAll() {
        return teamDAO.findAll();
    }

    public Team findOne(int id) {
        return teamDAO.findById(id);
    }

    public List<Team> findByPeronId(int personId) {
        return teamDAO.findByPersonId(personId);
    }

    public void save(Team team) {
        teamDAO.save(team);
    }

    public void update(int id, Team updatedTeam) {
        teamDAO.update(id, updatedTeam);
    }

    public void delete(int id) {
        teamDAO.delete(id);
    }

    public boolean addPersonToTeam(int personId, int teamId) {
        if (userDAO.findById(personId) == null || teamDAO.findById(teamId) == null) {
            return false;
        }
        teamDAO.addPersonToTeam(personId, teamId);
        return true;
    }
}
