package com.pedidos_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos_api.dto.ItemDTO;
import com.pedidos_api.model.Item;
import com.pedidos_api.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired ItemRepository itemRepository;

    //salvar item
    public ItemDTO salvarItem(ItemDTO itemDTO){
        Item item = itemDTO.converteDtoParaItem();

        Item itemSalvo = itemRepository.save(item);

        return itemSalvo.converteItemParaDto();
    }
    //buscar pelo ID
    public ItemDTO buscaItemPeloId(Long id){
        Optional<Item> iOptional = itemRepository.findById(id);

        if (iOptional.isPresent()) {
            return iOptional.get().converteItemParaDto();
        }

        return null;
    }

    //buscar todos
    public List<ItemDTO> buscaTodosItens(){
        List<Item> itemList = itemRepository.findAll();
        List<ItemDTO> itemDTOs = new ArrayList<>();

        for (Item item: itemList){
            itemDTOs.add(item.converteItemParaDto());
        }

        return itemDTOs;
    }

    //atualizar
    public ItemDTO atualizaItem(Long id, Item item){
        Optional<Item> iOptional = itemRepository.findById(id);

        if (iOptional.isPresent()) {
            Item itemEncontrado = iOptional.get();

            itemEncontrado.setCodigoProduto(item.getCodigoProduto());
            itemEncontrado.setQuantidade(item.getQuantidade());
            itemEncontrado.setPreco(item.getPreco());

            itemRepository.save(itemEncontrado);
            return itemEncontrado.converteItemParaDto();
        }

        return null;
    }

    //deletar
    public String deletaItem(Long id){
        Optional<Item> iOptional = itemRepository.findById(id);
        
        if (iOptional.isPresent()) {
            itemRepository.deleteById(id);
        }

        return "Item deletado";
    }

    //DEMAIS METODOS

    //buscar pelo codigo do produto
    public ItemDTO buscaItemPeloCodigo(String codigoProduto){
        Optional<Item> iOptional = itemRepository.findByCodigoProduto(codigoProduto);

        if (iOptional.isPresent()) {
            return iOptional.get().converteItemParaDto();
        }

        return null;
    }

    //buscar pela quantidade
    public List<ItemDTO> buscaItemPelaQuantidade(Integer quantidade){
        List<Item> itemList = itemRepository.findByQuantidade(quantidade);
        List<ItemDTO> itemDTOs = new ArrayList<>();

        for (Item item: itemList){
            itemDTOs.add(item.converteItemParaDto());
        }

        return itemDTOs;
    }

    //buscar pelo preco
    public List<ItemDTO> buscaItemPeloPreco(Double preco){
        List<Item> itemList = itemRepository.findByPreco(preco);
        List<ItemDTO> itemDTOs = new ArrayList<>();

        for (Item item: itemList){
            itemDTOs.add(item.converteItemParaDto());
        }

        return itemDTOs;
    }

    



}
