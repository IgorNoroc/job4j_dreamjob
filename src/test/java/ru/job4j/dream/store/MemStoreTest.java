package ru.job4j.dream.store;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;
import ru.job4j.dream.servlets.CandidateServlet;
import ru.job4j.dream.servlets.DeleteCandidateServlet;
import ru.job4j.dream.servlets.PostServlet;
import ru.job4j.dream.servlets.RegServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class MemStoreTest {
    private final MemStore store = MemStore.instOf();
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final HttpSession session = mock(HttpSession.class);

    @Before
    public void installStore() {
        PowerMockito.mockStatic(PsqlStore.class);
        Mockito.when(PsqlStore.instOf()).thenReturn(store);
    }

    @Test
    public void whenTestCandidateServlet() throws ServletException, IOException {
        CandidateServlet servlet = new CandidateServlet();
        when(request.getParameter("id")).thenReturn("0");
        when(request.getParameter("name")).thenReturn("Tanea");
        when(request.getParameter("city")).thenReturn("0");
        servlet.doPost(request, response);
        Candidate expected = new Candidate(4, "Tanea", "", 0);
        Candidate result = store.getCandidates().get(4);
        assertThat(expected, is(result));
    }

    @Test
    public void whenTestDeleteCandidateServlet() throws ServletException, IOException {
        DeleteCandidateServlet servlet = new DeleteCandidateServlet();
        when(request.getParameter("id")).thenReturn("1");
        servlet.doGet(request, response);
        assertNull(store.getCandidates().get(1));
    }

    @Test
    public void whenTestPostServlet() throws ServletException, IOException {
        PostServlet servlet = new PostServlet();
        when(request.getParameter("id")).thenReturn("0");
        when(request.getParameter("name")).thenReturn("Igor");
        servlet.doPost(request, response);
        Post expected = new Post(4, "Igor");
        Post result = store.getPosts().get(4);
        assertThat(expected, is(result));
    }

    @Test
    public void whenTestRegServlet() throws ServletException, IOException {
        RegServlet servlet = new RegServlet();
        when(request.getParameter("name")).thenReturn("Tanea");
        when(request.getParameter("email")).thenReturn("tanea.com");
        when(request.getParameter("password")).thenReturn("hello");
        when(request.getSession()).thenReturn(session);
        servlet.doPost(request, response);
        User expected = new User(2, "Tanea", "tanea.com", "hello");
        User result = store.findByEmail("tanea.com");
        assertThat(expected, is(result));
    }
}