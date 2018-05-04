
import com.codecool.meetup.triangels.MyIngatlan;
import com.codecool.meetup.triangels.Utility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MyIngatlan.class})
public class UserServiceTest {

    public Utility utility = Mockito.mock(Utility.class);

    @Autowired
    public UserService userService;


    @Test
    public void createUserIfNameExist() {
        assertEquals(false, userService.ifCreateUser("Tetris","email","pw", "intro"));
    }

    @Test
    public void createUserIfNameNotExist() {
        assertEquals(true, userService.ifCreateUser("name26","email","pw", "intro"));
    }

    @Test
    public void canLoginWrongUserName() {
        assertEquals(false, userService.canLogIn("NotExistingUserName", "pw"));
    }

    @Test
    public void canLoginWrongPw() {
        when(utility.checkPassword("pw", "asdasd")).thenReturn(false);
        assertEquals(false, userService.canLogIn("name","pw"));
    }

    @Test
    public void canLoginAllGood() {
        assertEquals(true, userService.canLogIn("Tetris","asdasd"));
    }



}