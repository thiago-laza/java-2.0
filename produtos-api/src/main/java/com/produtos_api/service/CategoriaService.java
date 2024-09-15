package com.produtos_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produtos_api.dto.CategoriaDTO;
import com.produtos_api.model.Categoria;
import com.produtos_api.repository.CategoriaRepository;
import com.produtos_api.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    //salvar categoria
    public CategoriaDTO salvarCategoria(CategoriaDTO categoriaDTO){
        // Converte o CategoriaDTO diretamente para Categoria
        Categoria categoria = categoriaDTO.converteDtoParaCategoria();
        // Salva o Categoria no banco de dados
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        // Retorna o Categoria salva convertido novamente para DTO
        return categoriaSalva.converteCategoriaParaDTO();
    }
    //buscar todas as categorias - List
    public List<CategoriaDTO> listarCategorias(){
        List<Categoria> categoriaList = categoriaRepository.findAll();
        List<CategoriaDTO> categoriaDTOs = new ArrayList<>();

        for (Categoria categoria: categoriaList){
            categoriaDTOs.add(categoria.converteCategoriaParaDTO());
        }

        return categoriaDTOs;
    }
    
    //buscar categoria pelo ID - Optional
    public CategoriaDTO buscaCategoriaPeloId(Long id){
        Optional<Categoria> cOptional = categoriaRepository.findById(id);

        if (cOptional.isPresent()) {
            return cOptional.get().converteCategoriaParaDTO();
        }

        return null;
    }
    //atualizar categoria - Optional
    public CategoriaDTO atualizaCategoria(Long id, Categoria categoria){
        Optional<Categoria> cOptional = categoriaRepository.findById(id);

        if (cOptional.isPresent()) {
            Categoria categoriaEncontrada = cOptional.get();

            categoriaEncontrada.setNome(categoria.getNome());

            categoriaRepository.save(categoriaEncontrada);
            return categoriaEncontrada.converteCategoriaParaDTO();
        }

        return null;
    }
    //deletar categoria - List (Ver exemplo F.)
    @Transactional //Anotação para que fo deleteByCategoria possa funcionar adequadamente e  para garantir que a operação ocorra dentro de uma transação.
    public String excluirCategoria(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();

            //Como é necessário excluir primeiros os proutos vinculados à categoria, criei um método no repository de produto para remover todos os produtos desta categoria e chamei ele aqui através da anotação @Transactional
            produtoRepository.deleteByCategoria(categoria);

            categoriaRepository.delete(categoria);

            return "Categoria excluída com sucesso!";
        }

        return null;
    }
    //buscar categoria pelo nome
    public List<CategoriaDTO> buscaCategoriaPeloNome(String nome){
        List<Categoria> categoriaList = categoriaRepository.findByNomeContainsIgnoreCase(nome);
        List<CategoriaDTO> categoriaDTOs = new ArrayList<>();

        for (Categoria categoria: categoriaList){
            categoriaDTOs.add(categoria.converteCategoriaParaDTO());
        }

        return categoriaDTOs;
    }
}
