package ru.job4j.dream.servlets;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Http servlet what deletes candidate from database
 * and  deletes photo from server.
 */
public class DeleteCandidateServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Candidate candidate = PsqlStore.instOf().findCandidateById(Integer.valueOf(req.getParameter("id")));
        PsqlStore.instOf().deleteCandidate(candidate);
        Files.deleteIfExists(Paths.get("images" + File.separator + candidate.getPhoto()));
        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
