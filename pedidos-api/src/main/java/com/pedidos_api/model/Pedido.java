package com.pedidos_api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pedidos_api.dto.PedidoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "email_cliente")
    private String emailCliente;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @OneToMany
    @JoinTable(
        name = "tb_compras_itens",
        joinColumns = @JoinColumn(name = "compra_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> itens;

    public PedidoDTO convertePedidoParaDto() {
        PedidoDTO dto = new PedidoDTO();
        dto.setEmailCliente(emailCliente);
        dto.setTotal(total);
        dto.setData(data);
        dto.setItem(itens);
        
              
        

        return dto;
    }
}

