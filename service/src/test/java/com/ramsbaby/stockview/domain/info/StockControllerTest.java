package com.ramsbaby.stockview.domain.info;

import static com.ramsbaby.stockview.domain.ApiDocumentUtil.getDocumentRequest;
import static com.ramsbaby.stockview.domain.ApiDocumentUtil.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * @author : RAMSBABY
 * @date : 2023-04-25 오후 11:05:23
 */
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@SpringBootTest
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockService stockService;

    private static boolean isInit = false;

    @BeforeEach
    public void setUp() {
        if (isInit) return;
        stockService.initData();
        isInit = true;
    }

    @Test
    @DisplayName("가장 많이 본")
    void getMostViewedStocks() throws Exception {
        // given
        // 초기데이터 등록

        // when
        // page = 1, size = 120 정보로 sort=code,desc 가장 많이 본 주식종목을 조회하는 경우
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .get("/api/v1/stocks/hot?" + "page=1&size=120&sort=code,desc")
                .header("Content-Type", "application/json"))
            .andDo(MockMvcResultHandlers.print());

        // then
        resultActions
            .andExpect(status().isOk())
            .andDo(document(
                "getMostViewedStocks",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParameters(
                    parameterWithName("page").description("페이지 넘버"),
                    parameterWithName("size").description("페이지 사이즈"),
                    parameterWithName("sort").description("정렬순서")),
                responseFields(
                    fieldWithPath("results[].code").type(JsonFieldType.STRING).description("주식 종목 코드"),
                    fieldWithPath("results[].count").type(JsonFieldType.NUMBER).description("조회수")
                )
            ))
        ;

        isInit = true;
    }

    @Test
    @DisplayName("가장 거래량이 많은")
    void getTopVolumeStocks() throws Exception {
        // given
        // 초기데이터 등록

        // when
        // page = 1, size = 120 정보로 sort=code,desc 가장 거래량이 많은 주식종목을 조회하는 경우
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .get("/api/v1/stocks/top-volume?" + "page=1&size=120&sort=code,desc")
                .header("Content-Type", "application/json"))
            .andDo(MockMvcResultHandlers.print());

        // then
        resultActions
            .andExpect(status().isOk())
            .andDo(document(
                "getTopVolumeStocks",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParameters(
                    parameterWithName("page").description("페이지 넘버"),
                    parameterWithName("size").description("페이지 사이즈"),
                    parameterWithName("sort").description("정렬순서")),
                responseFields(
                    fieldWithPath("results[].code").type(JsonFieldType.STRING).description("주식 종목 코드"),
                    fieldWithPath("results[].volume").type(JsonFieldType.NUMBER).description("거래량")
                )
            ))
        ;
    }

    @Test
    @DisplayName("가장 많이 오른")
    void getTopIncreasedStocks() throws Exception {
        // given
        // 초기데이터 등록

        // when
        // page = 1, size = 120 정보로 sort=code,desc 가장 많이 오른 주식종목을 조회하는 경우
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .get("/api/v1/stocks/top-increase?" + "page=1&size=120&sort=code,desc")
                .header("Content-Type", "application/json"))
            .andDo(MockMvcResultHandlers.print());

        // then
        resultActions
            .andExpect(status().isOk())
            .andDo(document(
                "getTopIncreasedStocks",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParameters(
                    parameterWithName("page").description("페이지 넘버"),
                    parameterWithName("size").description("페이지 사이즈"),
                    parameterWithName("sort").description("정렬순서")),
                responseFields(
                    fieldWithPath("results[].code").type(JsonFieldType.STRING).description("주식 종목 코드"),
                    fieldWithPath("results[].price").type(JsonFieldType.NUMBER).description("현재 가격"),
                    fieldWithPath("results[].changePrice").type(JsonFieldType.NUMBER).description("어제 가격 대비 변화된 가격"),
                    fieldWithPath("results[].changeRate").type(JsonFieldType.NUMBER).description("어제 가격 대비 변화된 비율")
                )
            ))
        ;
    }

    @Test
    @DisplayName("가장 많이 내린")
    void getTopDecreasedStocks() throws Exception {
        // given
        // 초기데이터 등록

        // when
        // page = 1, size = 120 정보로 sort=code,desc 가장 많이 내린 주식종목을 조회하는 경우
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .get("/api/v1/stocks/top-decrease?" + "page=1&size=120&sort=code,desc")
                .header("Content-Type", "application/json"))
            .andDo(MockMvcResultHandlers.print());

        // then
        resultActions
            .andExpect(status().isOk())
            .andDo(document(
                "getTopDecreasedStocks",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParameters(
                    parameterWithName("page").description("페이지 넘버"),
                    parameterWithName("size").description("페이지 사이즈"),
                    parameterWithName("sort").description("정렬순서")),
                responseFields(
                    fieldWithPath("results[].code").type(JsonFieldType.STRING).description("주식 종목 코드"),
                    fieldWithPath("results[].price").type(JsonFieldType.NUMBER).description("현재 가격"),
                    fieldWithPath("results[].changePrice").type(JsonFieldType.NUMBER).description("어제 가격 대비 변화된 가격"),
                    fieldWithPath("results[].changeRate").type(JsonFieldType.NUMBER).description("어제 가격 대비 변화된 비율")
                )
            ))
        ;
    }

    @Test
    @DisplayName("각 종목별 top 5")
    void getAllTop5() throws Exception {
        // given
        // 초기데이터 등록

        // when
        // page = 1, size = 120 정보로 sort=code,desc 각 종목별 TOP 5 주식종목을 조회하는 경우
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .get("/api/v1/stocks/all/top5?" + "page=1&size=120&sort=code,desc")
                .header("Content-Type", "application/json"))
            .andDo(MockMvcResultHandlers.print());

        // then
        resultActions
            .andExpect(status().isOk())
            .andDo(document(
                "getTopDecreasedStocks",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParameters(
                    parameterWithName("page").description("페이지 넘버"),
                    parameterWithName("size").description("페이지 사이즈"),
                    parameterWithName("sort").description("정렬순서")),
                responseFields(
                    fieldWithPath("results.priceDecreasedTopList[].code").type(JsonFieldType.STRING)
                        .description("주식 종목 코드"),
                    fieldWithPath("results.priceDecreasedTopList[].price").type(JsonFieldType.NUMBER)
                        .description("현재 가격"),
                    fieldWithPath("results.priceDecreasedTopList[].changePrice").type(JsonFieldType.NUMBER)
                        .description("어제 가격 대비 변화된 가격"),
                    fieldWithPath("results.priceDecreasedTopList[].changeRate").type(JsonFieldType.NUMBER)
                        .description("어제 가격 대비 변화된 비율"),
                    fieldWithPath("results.priceIncreasedTopList[].code").type(JsonFieldType.STRING)
                        .description("주식 종목 코드"),
                    fieldWithPath("results.priceIncreasedTopList[].price").type(JsonFieldType.NUMBER)
                        .description("현재 가격"),
                    fieldWithPath("results.priceIncreasedTopList[].changePrice").type(JsonFieldType.NUMBER)
                        .description("어제 가격 대비 변화된 가격"),
                    fieldWithPath("results.priceIncreasedTopList[].changeRate").type(JsonFieldType.NUMBER)
                        .description("어제 가격 대비 변화된 비율"),
                    fieldWithPath("results.viewCountTopList[].code").type(JsonFieldType.STRING).description("주식 종목 코드"),
                    fieldWithPath("results.viewCountTopList[].count").type(JsonFieldType.NUMBER).description("조회수"),
                    fieldWithPath("results.tradeDtoTopList[].code").type(JsonFieldType.STRING).description("주식 종목 코드"),
                    fieldWithPath("results.tradeDtoTopList[].volume").type(JsonFieldType.NUMBER).description("거래량")
                )
            ))
        ;
    }

    @Test
    @DisplayName("순위 랜덤 변화 시키기")
    void changeRank() throws Exception {
        // given
        // 초기데이터 등록

        // when
        // 랜덤으로 순위 변화
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .put("/api/v1/stocks/changeRank")
                .header("Content-Type", "application/json"))
            .andDo(MockMvcResultHandlers.print());

        // then
        resultActions
            .andExpect(status().isOk())
            .andDo(document(
                "changeRank",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                    fieldWithPath("results").type(JsonFieldType.BOOLEAN).description("성공 결과")
                )
            ))
        ;
    }
}