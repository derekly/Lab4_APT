import junit.framework.*;
import com.mockobjects.servlet.*;

public class TestServlet extends TestCase {

  public void test_null_parameter() throws Exception {
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    //request.setupAddParameter("farenheitTemperature", null);
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
    assertEquals("<html><head><title>No Temperature</title>"
			+ "</head><body><h2>Need to enter a temperature!"
			+ "</h2></body></html>\n",
                 response.getOutputStreamContents());
  }
  
  public void test_bad_param() throws Exception {
	  TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    request.setupAddParameter("farenheitTemperature", "fooo");
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
    assertEquals("<html><head><title>Bad Temperature</title>"
					+ "</head><body><h2>Need to enter a valid temperature!"
				    + "Got a NumberFormatException on " 
					+ "fooo" 
					+ "</h2></body></html>\n",
                 response.getOutputStreamContents());
  }
  
  public void test_boil() throws Exception {
	  TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    request.setupAddParameter("farenheitTemperature", "212");
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
    assertEquals("<html><head><title>Temperature Converter Result</title>"
					+ "</head><body><h2>" + "212" + " Farenheit = " + "100" + " Celsius "
					+ "</h2>\n"
					+ "<p><h3>The temperature in Austin is 451 degrees Farenheit</h3>\n"
					+ "</body></html>\n",
                 response.getOutputStreamContents());
  }
  
}
