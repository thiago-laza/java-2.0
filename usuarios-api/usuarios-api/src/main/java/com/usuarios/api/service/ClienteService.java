package com.usuarios.api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.usuarios.api.dto.ClienteDTO;
import com.usuarios.api.dto.EnderecoDto;
import com.usuarios.api.exception.CpfJaCadastradoException;
import com.usuarios.api.exception.EmailJaCadastradoException;
import com.usuarios.api.model.Cliente;
import com.usuarios.api.model.Endereco;
import com.usuarios.api.model.Usuario;
import com.usuarios.api.repository.ClienteRepository;
import com.usuarios.api.repository.UsuarioRepository;

@Service
public class ClienteService {
    
    public Cliente salvarCliente(Cliente cliente) {
        String senha = cliente.getUsuario().getSenha();

        //Criptografar a senha para persistir no banco:
        BCryptPasswordEncoder encoder = autenticacaoService.getPasswordEncoder(); //chama o método do AutenticacaoService

        String senhaCriptografada = encoder.encode(senha); //criptografa a senha do corpo da requisição e recebe a senha já criptopgrafada

        cliente.getUsuario().setSenha(senhaCriptografada); //substitui a senha pela senha criptografada

        //buscar detalhes do endereço pelo cep
        Endereco endereco = cliente.getEndereco();

        EnderecoDto enderecoDto = enderecoService.buscarEnderecoPeloCep(cliente.getEndereco().getCep());

        endereco.setBairro(enderecoDto.getBairro());
        endereco.setCidade(enderecoDto.getLocalidade());
        endereco.setLogradouro(enderecoDto.getLogradouro());
        endereco.setUf(enderecoDto.getUf());

        //Salvando do objeto usuário antes de relacionar com o cliente
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(cliente.getUsuario().getEmail());
        

        if (usuarioOpt.isPresent()) {
            throw new EmailJaCadastradoException("E-mail ja cadastrado");
        }

        Optional<Cliente> clienteOpt = clienteRepository.findByCpf(cliente.getCpf());

        if (clienteOpt.isPresent()) {
            throw new CpfJaCadastradoException("Cpf ja cadastrado");
        }
        
        Usuario usuario = usuarioRepository.save(cliente.getUsuario());
        cliente.setUsuario(usuario);

        return clienteRepository.save(cliente);
    }                

    public ClienteDTO buscarClientePeloId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            return cliente.get().converterParaDto();
        }
        return null;
    }

    public ClienteDTO buscarClientePeloEmail(String email) {
        Optional<Cliente> cliente = clienteRepository.findByUsuario_Email(email);

        if (cliente.isPresent()) {
            return cliente.get().converterParaDto();
        }
        return null;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public List<Cliente> obterListaDeClientesPeloNome(String nome) {
        List<Cliente> clientes = clienteRepository.findByUsuario_NomeContainsIgnoreCase(nome);
        return clientes;
    }

    public List<Cliente> obterListaClientesPorDataNascimento(LocalDate dataInicio, LocalDate dataFinal) {
        List<Cliente> clientes = clienteRepository.findByDataNascimentoBetween(dataInicio, dataFinal);
        return clientes;
    }

    public ClienteDTO obterClientePeloCpf(String cpf) {
        Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);

        if (cliente.isPresent()) {
            return cliente.get().converterParaDto();
        }
        return null;
    }

    public Cliente atualizarDadosCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isPresent()) {
            Cliente clienteEncont = clienteOptional.get();

            clienteEncont.getUsuario().setNome(cliente.getUsuario().getNome());
            clienteEncont.getUsuario().setEmail(cliente.getUsuario().getEmail());
            clienteEncont.getUsuario().setSenha(cliente.getUsuario().getSenha());
            clienteEncont.getUsuario().setTelefone(cliente.getUsuario().getTelefone());
            clienteEncont.getUsuario().setCelular(cliente.getUsuario().getCelular());
            clienteEncont.getUsuario().setAdministrador(cliente.getUsuario().getAdministrador());
            clienteEncont.getUsuario().setColaborador(cliente.getUsuario().getColaborador());
            clienteEncont.getUsuario().setUsuarioExterno(cliente.getUsuario().getUsuarioExterno());
            clienteEncont.setCpf(cliente.getCpf());
            clienteEncont.setDataNascimento(cliente.getDataNascimento());

            Endereco endereco = clienteEncont.getEndereco();

            EnderecoDto enderecoDto = enderecoService.buscarEnderecoPeloCep(cliente.getEndereco().getCep());

            endereco.setCep(enderecoDto.getCep());
            endereco.setBairro(enderecoDto.getBairro());
            endereco.setCidade(enderecoDto.getLocalidade());
            clienteEncont.getEndereco().setComplemento(cliente.getEndereco().getComplemento());
            endereco.setLogradouro(enderecoDto.getLogradouro());
            clienteEncont.getEndereco().setNumero(cliente.getEndereco().getNumero());
                       
            return clienteRepository.save(clienteEncont);
        }
        return null;
    }

    public String excluirCliente(Long id) {
        clienteRepository.deleteById(id);
        return "Cliente excluído com sucesso.";
    }



    

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

}
