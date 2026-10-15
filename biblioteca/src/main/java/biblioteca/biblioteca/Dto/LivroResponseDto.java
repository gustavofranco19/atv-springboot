package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivroResponseDto {

    @JsonProperty("idLivro")
    private Long idLivro;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("idAutor")
    private Long idAutor;

    @JsonProperty("ano")
    private int ano;

    @JsonProperty("edicao")
    private int edicao;
}