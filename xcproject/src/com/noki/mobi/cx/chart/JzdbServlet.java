package com.noki.mobi.cx.chart;

import com.keypoint.PngEncoder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class JzdbServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
            IOException {
        // get the chart from session
        HttpSession session = request.getSession();
        BufferedImage chartImage = (BufferedImage) session.getAttribute(
                "image_jzdb");
        // set the content type so the browser can see this as a picture
        response.setContentType("image/png");
        // send the picture
        PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
        response.getOutputStream().write(encoder.pngEncode());

    }

    //Process the HTTP Post request
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);

    }

    //Process the HTTP Put request
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws
            ServletException, IOException {

    }

    //Clean up resources
    public void destroy() {}

}
