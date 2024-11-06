package br.com.luiz.locbem.model.offer;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] byteImage;

    private String linkImage;
}
