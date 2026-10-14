package biblioteca.biblioteca.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Livro")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idLivro")
    private Long idLivro;

    @JsonProperty("isbn")
    @Column(name = "titulo")
    private String isbn;

    @JsonProperty("titulo")
    @Column(name = "titulo")
    private String titulo;

    @JsonProperty("autor")
    @Column(name = "autor")
    private String autor;


    @JsonProperty("ano")
    @Column(name = "ano")
    private int ano;

    @JsonProperty("edicao")
    @Column(name = "edicao")
    private String edicao;
}