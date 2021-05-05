package rev.team.PROBLEM_SERVICE.controller;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import rev.team.PROBLEM_SERVICE.domain.entity.AnswerMain;
import rev.team.PROBLEM_SERVICE.domain.entity.Submit;
import rev.team.PROBLEM_SERVICE.domain.entity.SubmitDTO;
import rev.team.PROBLEM_SERVICE.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestionControllerTest {

    @LocalServerPort
    int port;

    TestRestTemplate client = new TestRestTemplate();

    @MockBean
    private QuestionService questionService;

    @DisplayName("1. Get question")
    @Test
    void test1(){
        String result = client.getForObject("http://localhost:" + port + "/question/256", String.class);

        System.out.println(result);
        assertNotNull(result);
    }

    @DisplayName("2. Get range question")
    @Test
    void test2(){
        String result = client.getForObject("http://localhost:" + port + "/rangeQuestion?start=261&end=270", String.class);

        System.out.println(result);
        assertNotNull(result);
    }

//    @DisplayName("3. Submit question")
//    @Test
//    void test3(){
//        List<Submit> list = new ArrayList<>();
//        list.add(Submit.builder()
//                .questionId(260L)
//                .multipleChoiceIds(Lists.newArrayList(1L, 2L))
//                .build());
//
//        SubmitDTO submit = SubmitDTO.builder()
//                .userId(1L)
//                .submitList(list)
//                .build();
//
//        AnswerMain result = client.postForObject("http://localhost:" + port + "/submit"
//                , submit
//                , AnswerMain.class);
//
//        System.out.println(result.getTotalCount() + ", " + result.getCorrectCount());
//    }
}