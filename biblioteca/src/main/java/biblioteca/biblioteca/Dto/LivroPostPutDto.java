package biblioteca.biblioteca.Dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
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
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank
    private String titulo;

    @JsonProperty("autor")
    @NotBlank
    private String autor;

    @JsonProperty("ano")
    @Positive
    private int ano;

    @JsonProperty("edicao")
    @NotBlank
    private String edicao;
}

