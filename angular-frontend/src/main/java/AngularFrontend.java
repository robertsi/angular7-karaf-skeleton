import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

@Component(
  service = AngularFrontend.class,
 // scope= ServiceScope.PROTOTYPE,
  property = {
    HttpWhiteboardConstants.HTTP_WHITEBOARD_RESOURCE_PATTERN + " = /angular-frontend/*",
    HttpWhiteboardConstants.HTTP_WHITEBOARD_RESOURCE_PREFIX + " = /META-INF"
  }
)
public class AngularFrontend {
}
