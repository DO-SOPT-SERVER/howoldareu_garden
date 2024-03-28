package com.sopt.Server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

//@ExtendWith(SpringExtension.class) // JUnit4 RunWith(SpringRunner.class) 테스트 실행 방법을 확장할 때 사용, ApplicationContext만들고 관리하는 작업을 설정된 class로 사용
@ExtendWith(MockitoExtension.class)
public abstract class MockManager {
    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper = new ObjectMapper();
}
