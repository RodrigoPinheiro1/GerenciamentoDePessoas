package com.gerenciamentopessoasprovatecnica.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciamentopessoasprovatecnica.Controller.Dtos.CadastroEnderecoDto;
import com.gerenciamentopessoasprovatecnica.Controller.Dtos.EnderecoDto;
import com.gerenciamentopessoasprovatecnica.Controller.Dtos.PessoaDto;
import com.gerenciamentopessoasprovatecnica.Controller.Entities.Endereco;
import com.gerenciamentopessoasprovatecnica.Controller.Entities.Pessoas;
import com.gerenciamentopessoasprovatecnica.Controller.Repository.EnderecoRepository;
import com.gerenciamentopessoasprovatecnica.Controller.Repository.PessoaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private PessoaRepository pessoaRepository;


    @Autowired
    private EnderecoRepository enderecoRepository;

    @MockBean
    private PessoaDto pessoaDto;

    @MockBean
    private EnderecoDto enderecoDto;


    @MockBean
    private CadastroEnderecoDto cadastroEnderecoDto;

    private String stringId;


    private Pessoas pessoas;


    private final URI uriPessoas = URI.create("/pessoas");
    private final URI uriIdNotFound = URI.create("/pessoas/0");
    private final URI uriId = URI.create("/pessoas/");
    private final URI uriPessoasEndereco = URI.create("/pessoas/endereco");



    @BeforeAll
    void put() {
        Endereco endereco = new Endereco(true, "logradouro", "08568000", "2393", "poa");
        pessoas = new Pessoas("nome", LocalDate.now(), Collections.singletonList(endereco));

        pessoaRepository.save(pessoas);

        stringId = String.valueOf(pessoas.getId());

    }

    @BeforeEach
    public void setup() {

        enderecoDto = new EnderecoDto("logradouro", "08568000", true, "2393", "poa");
        pessoaDto = new PessoaDto("nome", LocalDate.now(), Collections.singletonList(enderecoDto));

        cadastroEnderecoDto = new CadastroEnderecoDto("logradouro",
                "08568000", true, "2393", "poa", pessoas.getId());
    }


    @Test
    void deveriaDevolver201ParaCadastroDeEndereçoParaPessoa() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.post(uriPessoasEndereco)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cadastroEnderecoDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    void deveriaDevolver201ParaCadastro() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(uriPessoas)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoaDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void deveriaDevolver400ParaCadastroComCamposNullos() throws Exception {
        pessoaDto.setNome(null);
        mockMvc.perform(MockMvcRequestBuilders.post(uriPessoas)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoaDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void deveriaDevolver200AoAtualizar() throws Exception {


        pessoaDto.setId(pessoas.getId());
        pessoaDto.setNome("atualizaNome");

        //  pessoas.setDataNascimento(LocalDate.now());
        mockMvc.perform(MockMvcRequestBuilders.put(uriId + stringId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoaDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deveriaDevolver404AoAtualizarIdInesxistente() throws Exception {

        pessoaDto.setNome("atualizaNome");
        //   pessoas.setDataNascimento(LocalDate.now());
        mockMvc.perform(MockMvcRequestBuilders.put(uriIdNotFound)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoaDto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void listar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(uriPessoas))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void listarPorId() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(uriId+stringId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
