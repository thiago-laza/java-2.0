package com.usuarios.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.usuarios.api.dto.EnderecoDto;
import com.usuarios.api.dto.FornecedorDTO;
import com.usuarios.api.model.Endereco;
import com.usuarios.api.model.Fornecedor;
import com.usuarios.api.model.Usuario;
import com.usuarios.api.repository.FornecedorRepository;
import com.usuarios.api.repository.UsuarioRepository;

@Service
public class FornecedorService {

    public Fornecedor salvarFornecedor(Fornecedor fornecedor) {
        String senha = fornecedor.getUsuario().getSenha();
        BCryptPasswordEncoder encoder = autenticacaoService.getPasswordEncoder();

        String senhaCriptografada = encoder.encode(senha);
        fornecedor.getUsuario().setSenha(senhaCriptografada);

        Endereco endereco = fornecedor.getEndereco();
        EnderecoDto enderecoDto = enderecoService.buscarEnderecoPeloCep(fornecedor.getEndereco().getCep());

        endereco.setBairro(enderecoDto.getBairro());
        endereco.setCidade(enderecoDto.getLocalidade());
        endereco.setLogradouro(enderecoDto.getLogradouro());
        endereco.setUf(enderecoDto.getUf());

        Usuario usuario = usuarioRepository.save(fornecedor.getUsuario());
        fornecedor.setUsuario(usuario);
        
        return fornecedorRepository.save(fornecedor);

    }

    public List <Fornecedor> listarFornecedores() {
        return fornecedorRepository.findAll();
    }

    public List<Fornecedor> listarFornecedoresRazaoSocial(String razaoSocial) {
        return fornecedorRepository.findByRazaoSocialContainsIgnoreCase(razaoSocial);
    }

    public FornecedorDTO buscarFornecedorPeloId(Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);

        if (fornecedor.isPresent()) {
            return fornecedor.get().converterFornecedorParaDto();
        }
        return null;
    }

    public FornecedorDTO buscarFornecedorPeloCnpj(String cnpj) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findByCnpj(cnpj);

        if (fornecedor.isPresent()) {
            return fornecedor.get().converterFornecedorParaDto();
        }
        return null;
    }

    public FornecedorDTO buscarFornecedorPeloEmail(String email) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findByUsuario_email(email);

        if (fornecedor.isPresent()) {
            return fornecedor.get().converterFornecedorParaDto();
        }
        return null;
    }

    public List<Fornecedor> buscarFornecedorPeloNome(String nome) {
        List<Fornecedor> fornecedores = fornecedorRepository.findByUsuario_NomeContainsIgnoreCase(nome);
        return fornecedores;
    }

    public Fornecedor atualizarDadosFornecedor(Long id, Fornecedor fornecedor) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);

        if (fornecedorOptional.isPresent()) {
            Fornecedor fornecedorEncont = fornecedorOptional.get();

            fornecedorEncont.getUsuario().setNome(fornecedor.getUsuario().getNome());
            fornecedorEncont.getUsuario().setEmail(fornecedor.getUsuario().getEmail());
            fornecedorEncont.getUsuario().setSenha(fornecedor.getUsuario().getSenha());
            fornecedorEncont.getUsuario().setTelefone(fornecedor.getUsuario().getTelefone());
            fornecedorEncont.getUsuario().setCelular(fornecedor.getUsuario().getCelular());
            fornecedorEncont.getUsuario().setAdministrador(fornecedor.getUsuario().getAdministrador());
            fornecedorEncont.getUsuario().setColaborador(fornecedor.getUsuario().getColaborador());
            fornecedorEncont.getUsuario().setUsuarioExterno(fornecedor.getUsuario().getUsuarioExterno());
            fornecedorEncont.setCnpj(fornecedor.getCnpj());
            fornecedorEncont.setRazaoSocial(fornecedor.getRazaoSocial());

            Endereco endereco = fornecedorEncont.getEndereco();

            EnderecoDto enderecoDto = enderecoService.buscarEnderecoPeloCep(fornecedor.getEndereco().getCep());

            endereco.setCep(enderecoDto.getCep());
            endereco.setBairro(enderecoDto.getBairro());
            endereco.setLogradouro(enderecoDto.getLogradouro());
            endereco.setCidade(enderecoDto.getLocalidade());
            endereco.setUf(enderecoDto.getUf());
            fornecedorEncont.getEndereco().setNumero(fornecedor.getEndereco().getNumero());
            fornecedorEncont.getEndereco().setComplemento(fornecedor.getEndereco().getComplemento());

            return fornecedorRepository.save(fornecedorEncont);
        }
        return null;
    }

    public String exlcuirFornecedor(Long id) {

       fornecedorRepository.deleteById(id);
       return "Fornecedor excluído com sucesso.";
    }




    
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;
}
