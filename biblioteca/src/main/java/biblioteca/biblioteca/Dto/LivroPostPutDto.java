package biblioteca.biblioteca.Dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivroPostPutDto {

    @JsonProperty("isbn")
    @NotBlank
    @NotNull
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank
    private String titulo;

    @JsonProperty("ano")
    @Positive
    @NotNull 
    private Integer ano;

    @JsonProperty("edicao")
    @Positive
    private Integer edicao;

    @JsonProperty("idAutor")
    @NotNull 
    private Long idAutor;

}

