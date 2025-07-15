package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendaConsultaService;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJSON;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJSON;

    @MockitoBean
    private AgendaConsultaService agendaConsultaService;

    @Test
    @DisplayName("Deveria retornar o código http 400 quando informações forem inválidas")
    @WithMockUser // Simula um usuário logado para testar a requisição sem se preocupar com questões de segurança
    void agendarCenario1() throws Exception {
        var response = mockMvc.perform(post("/consulta"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Deveria retornar o código http 200 quando informações forem válidas")
    @WithMockUser // Simula um usuário logado para testar a requisição sem se preocupar com questões de segurança
    void agendarCenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2l, 5l, data);
        when(agendaConsultaService.agendar(any()))
                .thenReturn(dadosDetalhamento);

        var response = mockMvc
                .perform(
                    post("/consulta")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(dadosAgendamentoConsultaJSON.write(
                                    new DadosAgendamentoConsulta(2l, 5l, data, especialidade)
                            ).getJson())
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJSON
                .write(dadosDetalhamento).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}