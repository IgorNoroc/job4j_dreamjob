package ru.job4j.dream.files_download_service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.dream.model.Candidate;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Class special for servlet Candidate.
 */
public class FilesDownloader {
    /**
     * Upload photo to the server and set user's photo name.
     *
     * @param servlet   servlet.
     * @param req       request.
     * @param candidate current candidate.
     */
    public static void downloadFiles(HttpServlet servlet, HttpServletRequest req, Candidate candidate) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = servlet.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("images");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (item != null) {
                    candidate.setPhoto(item.getName());
                    File folderId = new File("images" + File.separator + candidate.getPhoto());
                    try (FileOutputStream out = new FileOutputStream(folderId)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
