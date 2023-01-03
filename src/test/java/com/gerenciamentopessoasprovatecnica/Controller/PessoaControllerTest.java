package com.gerenciamentopessoasprovatecnica.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciamentopessoasprovatecnica.Controller.Entities.Endereco;
import com.gerenciamentopessoasprovatecnica.Controller.Entities.Pessoas;
import com.gerenciamentopessoasprovatecnica.Controller.Repository.PessoaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private Endereco endereco;

    @Autowired
    private PessoaRepository repository;


    @Mock
    private Pessoas pessoas;


    private final UUID clientId = UUID.fromString("2fa85f64-5717-4562-b3fc-2c963f66afa6"); //id already in BD
    private final UUID idRepetido = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"); //id already in BD

    private final URI uriForOk = URI.create("/pessoas");
    private final URI uriForNotFound = URI.create("/pessoas/0aa00a00-0000-2000-a0aa-0a020a00aaa0");
    private final UUID id = UUID.randomUUID();
    private final URI uriForFound = URI.create("/pessoas/3fa85f64-5717-4562-b3fc-2c963f66afa6");



    @BeforeEach
    public void setup() {
        endereco = new Endereco( id,"logradouro","08568000","2393","poa");
        pessoas = new Pessoas(idRepetido,"nome", LocalDate.now(),
                Collections.singletonList(endereco));


    }


    @Test
    void deveriaDevolver201ParaCadastro() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(uriForOk)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoas)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    void deveriaDevolver400ParaCadastroComCamposNullos() throws Exception {
        pessoas.setNome(null);
        mockMvc.perform(MockMvcRequestBuilders.post(uriForOk)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoas)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void deveriaDevolver200AoAtualizar() throws Exception{

        pessoas.setNome("atualizaNome");
        pessoas.setDataNascimento(LocalDate.now());
        mockMvc.perform(MockMvcRequestBuilders.put(uriForFound)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoas)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void deveriaDevolver404AoAtualizarIdInesxistente() throws Exception{

        pessoas.setNome("atualizaNome");
        pessoas.setDataNascimento(LocalDate.now());
        mockMvc.perform(MockMvcRequestBuilders.put(uriForFound)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoas)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void listar() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(uriForOk))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void listarPorId() throws Exception{
    }
}
