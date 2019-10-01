import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//https://angular.io/guide/deployment#routed-apps-must-fallback-to-indexhtml
@Component(
  service = Servlet.class,
  //scope= ServiceScope.PROTOTYPE,
  property = {
    HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_ERROR_PAGE + " = 404",
  }
)
public class NotFoundError extends HttpServlet {

  Logger LOG = LoggerFactory.getLogger(NotFoundError.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp){
    LOG.info("doGet executing: ");
    try {
      RequestDispatcher dispatcher =  req.getRequestDispatcher("angular-frontend/index.html");
      dispatcher.forward(req, resp);
//      resp.sendRedirect("angular-frontend/index.html");
      LOG.info("doGet executed: ");
    }
    catch (Exception ex)
    {
      LOG.error("doGet error", ex);
    }
  }
}
