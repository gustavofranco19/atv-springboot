package biblioteca.biblioteca.Service;

import java.util.List;

import biblioteca.biblioteca.Dto.AutorResponseDto;
import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

public interface LivroCrudService {

    LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto);

    List<LivroResponseDto> buscarTodosLivros();

    LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto);

    void removerLivro(Long id);

}
