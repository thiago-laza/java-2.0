package com.produtos_api.dto;



import com.produtos_api.model.Categoria;
import com.produtos_api.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Long id;
    private String codigo;
    private String nome;
    private String descricao;
    private Double preco;
    private Categoria categoria; // Aqui a categoria está como String no DTO

    // Método para converter ProdutoDTO em Produto
    public Produto converteDtoParaProduto() {
        Produto produto = new Produto();
        
        produto.setCodigo(this.codigo);
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setPreco(this.preco);
        produto.setCategoria(this.categoria);
        
        return produto;
    }
}
