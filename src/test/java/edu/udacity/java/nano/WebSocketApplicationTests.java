package edu.udacity.java.nano;

import edu.udacity.java.nano.chat.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebMvcTest(WebSocketChatApplication.class)
public class WebSocketApplicationTests {

    @Autowired
    private MockMvc mvc;

    private Message message = new Message("Hello World!", "Key", "SPEAK", "1");

    @Test
    public void ModelAndViewApi() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void ModelAndViewChatApi() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/chat")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("chat"));
    }

    @Test
    public void chat() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/chat")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(view().name("chat"));
    }

    @Test
    public void onOpen() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/chat?username=key"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("key")))
                .andExpect(view().name("chat"));
    }

}

