package br.com.luiz.locbem.model.offer;

import br.com.luiz.locbem.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private int anoFabricacao;
    private int anoModelo;
    private double preco;
    private String descricao;
    private double quilometragem;
    private double latitude;
    private double longitude;

    @ManyToOne
    User user;

    @ElementCollection
    private List<String> imagens;

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;

    @Enumerated(EnumType.STRING)
    private Combustivel combustivel;

    @Enumerated(EnumType.STRING)
    private EstadoVeiculo estadoVeiculo;

    @ElementCollection(targetClass = CaracteristicaVeiculo.class)
    @Enumerated(EnumType.STRING)
    private List<CaracteristicaVeiculo> caracteristica;

}
