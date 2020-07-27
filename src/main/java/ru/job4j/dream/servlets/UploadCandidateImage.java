package ru.job4j.dream.servlets;

import ru.job4j.dream.files_download_service.FilesDownloader;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Http servlet what uploads image to server.
 */
public class UploadCandidateImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Candidate candidate = PsqlStore.instOf().findCandidateById(Integer.parseInt(req.getParameter("id")));
        FilesDownloader.downloadFiles(this, req, candidate);
        PsqlStore.instOf().save(candidate);
        doGet(req, resp);
    }
}
