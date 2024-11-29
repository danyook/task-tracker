package ru.kpfu.itis.services;

import ru.kpfu.itis.dao.SectionDAO;
import ru.kpfu.itis.entities.Section;
import ru.kpfu.itis.util.DbException;

import java.util.List;

public class SectionService {
    private static SectionService INSTANCE;
    private final SectionDAO sectionDAO = SectionDAO.getInstance();

    private SectionService() {
    }

    public static SectionService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SectionService();
        }
        return INSTANCE;
    }

    public List<Section> findAll() {
        return sectionDAO.findAll();
    }

    public Section findOne(int id) {
        return sectionDAO.findById(id);
    }

    public void save(Section section) {
        sectionDAO.save(section);
    }

    public void update(int id, Section updatedSection) {
        sectionDAO.update(id, updatedSection);
    }

    public void delete(int id) {
        sectionDAO.delete(id);
    }
}
