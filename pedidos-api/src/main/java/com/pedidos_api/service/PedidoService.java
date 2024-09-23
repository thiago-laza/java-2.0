package com.pedidos_api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos_api.dto.PedidoDTO;
import com.pedidos_api.model.Item;
import com.pedidos_api.model.Pedido;
import com.pedidos_api.repository.PedidoRepository;

@Service
public class PedidoService {


    @Autowired
    private PedidoRepository pedidoRepository;


    //salvar
    public PedidoDTO salvarPedido(PedidoDTO pedidoDTO){
        Pedido pedido = pedidoDTO.converteDtoParaPedido();

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return pedidoSalvo.convertePedidoParaDto();
    }
      
    //buscar pelo ID
    public PedidoDTO buscaPedidoPeloId(Long id){
        Optional<Pedido> pOptional = pedidoRepository.findById(id);

        if (pOptional.isPresent()) {
            return pOptional.get().convertePedidoParaDto();
        }

        return null;
    }
    //buscar todos
    public List<PedidoDTO> buscaTodosPedidos(){
        List<Pedido> pedidoList = pedidoRepository.findAll();
        List<PedidoDTO> pedidoDTOs = new ArrayList<>();

        for (Pedido pedido: pedidoList){
            pedidoDTOs.add(pedido.convertePedidoParaDto());
        }

        return pedidoDTOs;
    }
    //atualizar
    public PedidoDTO atualizarPedido(Long id, Pedido pedido){
        Optional<Pedido> pOptional = pedidoRepository.findById(id);

        if (pOptional.isPresent()) {
            Pedido pedidoEncontrado = pOptional.get();

            pedidoEncontrado.setEmailCliente(pedido.getEmailCliente());
            pedidoEncontrado.setTotal(pedido.getTotal());
            pedidoEncontrado.setData(pedido.getData());
            pedidoEncontrado.setItens(pedido.getItens());

            pedidoRepository.save(pedidoEncontrado);
            return pedidoEncontrado.convertePedidoParaDto();
        }

        return null;
    }

    //deletar
    public String deletaPedido(Long id){
        Optional<Pedido> pOptional = pedidoRepository.findById(id);
        
        if (pOptional.isPresent()) {
            pedidoRepository.deleteById(id);
        }

        return "Pedido deletado com sucesso.";
    }

    //DEMAIS METODOS

    //buscar pelo email do cliente
    public PedidoDTO buscaPedidoPeloEmail(String emailCliente){
        Optional<Pedido> pOptional = pedidoRepository.findByEmailCliente(emailCliente);

        if (pOptional.isPresent()) {
            return pOptional.get().convertePedidoParaDto();
        }

        return null;
    }
    
    //buscar pelo total
    public List<PedidoDTO> buscaPedidoPeloTotal(Double total){
        List<Pedido> pedidoList = pedidoRepository.findByTotal(total);
        List<PedidoDTO> pedidoDTOs = new ArrayList<>();

        for (Pedido pedido: pedidoList){
            pedidoDTOs.add(pedido.convertePedidoParaDto());
        }

        return pedidoDTOs;
    }

    //buscar pela lista de itens
    public List<PedidoDTO> buscaListaDeItens(List<Item> itens){
        List<Pedido> pedidoList = pedidoRepository.findByItensIn(itens);
        List<PedidoDTO> pedidoDTOs = new ArrayList<>();

        for (Pedido pedido: pedidoList){
            pedidoDTOs.add(pedido.convertePedidoParaDto());
        }

        return pedidoDTOs;
    }

    //buscar pela data
    public List<PedidoDTO> buscaPedidoPelaData(LocalDate data){
        List<Pedido> pedidoList = pedidoRepository.findByData(data);
        List<PedidoDTO> pedidoDTOs = new ArrayList<>();

        for (Pedido pedido: pedidoList){
            pedidoDTOs.add(pedido.convertePedidoParaDto());
        }

        return pedidoDTOs;
    }


}
