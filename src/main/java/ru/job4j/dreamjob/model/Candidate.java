package ru.job4j.dreamjob.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Candidate {

    private int id = count++;
    private String name;
    private String desc;
    private String created;
    private static int count = 1;
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Candidate() {
    }

    public Candidate(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.created = LocalDateTime.now().format(FORMATTER);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreated() {
        return created.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id && Objects.equals(name, candidate.name)
                && Objects.equals(desc, candidate.desc)
                && Objects.equals(created, candidate.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, created);
    }


}
