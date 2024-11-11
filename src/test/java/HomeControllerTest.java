import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mdahsan101.EmployeeManagementSystemApplication;
import org.mdahsan101.Repository.EmployeeRepository;
import org.mdahsan101.controller.HomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = EmployeeManagementSystemApplication.class)
@AutoConfigureMockMvc
public class HomeControllerTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    HomeController controller;

    @Autowired
    EmployeeRepository repository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
        controller= new HomeController(repository);
    }

    @Test
    void testGoToIndex() throws Exception {
        ModelAndView mv= new ModelAndView();
        mv.setViewName("index");

        RequestBuilder requestBuilder= get("/emp/");
        mockMvc.perform(requestBuilder);
    }
}
