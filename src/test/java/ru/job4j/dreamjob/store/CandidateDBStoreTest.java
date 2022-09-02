package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CandidateDBStoreTest {

    @Test
    void whenFindAll() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Java Developer",
                "Spring, sql", LocalDateTime.now(), new byte[]{});
        store.add(candidate);
        List<Candidate> candidateList = store.findAll();
        assertThat(candidateList.get(0).getName(), is(candidate.getName()));
    }

    @Test
    void whenAdd() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Java Developer",
                "Spring, sql", LocalDateTime.now(), new byte[]{});
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    void whenUpdate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Java Developer",
                "Spring, sql", LocalDateTime.now(), new byte[]{});
        store.add(candidate);
        store.update(new Candidate(candidate.getId(), "Java Middle Developer",
                "Spring, sql", LocalDateTime.now(), new byte[]{}));
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is("Java Middle Developer"));
    }

    @Test
    void whenFindById() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Java Developer",
                "Spring, sql", LocalDateTime.now(), new byte[]{});
        Candidate candidate2 = new Candidate(1, "Java Middle Developer",
                "Spring, sql", LocalDateTime.now(), new byte[]{});
        store.add(candidate);
        store.add(candidate2);
        Candidate candidateInDb = store.findById(candidate.getId());
        Candidate candidate2InDb = store.findById(candidate2.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
        assertThat(candidate2InDb.getName(), is(candidate2.getName()));
    }
}