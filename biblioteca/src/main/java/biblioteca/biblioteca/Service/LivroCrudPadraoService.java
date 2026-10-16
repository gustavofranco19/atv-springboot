package biblioteca.biblioteca.Service;

import java.util.ArrayList;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.AutorRepository;
import biblioteca.biblioteca.Repository.LivroRepository;

@Service
public class LivroCrudPadraoService implements LivroCrudService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private Autor obterAutorExistente(Long id){
        Autor autor = autorRepository.findById(id)
            .orElseThrow(() -> new 
            ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
            return autor;
    }

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto) {
        Livro livro = modelMapper.map(livroPostPutDto, Livro.class);
        Autor autor = obterAutorExistente(livroPostPutDto.getIdAutor());
        livro.setIdLivro(null);
        livro.setAutor(autor);
        LivroResponseDto response = modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
        response.setIdAutor(autor.getId());
        return response;
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(livro -> {
                    LivroResponseDto response = modelMapper.map(livro, LivroResponseDto.class);
                    if(livro.getAutor() != null){
                        response.setIdAutor(livro.getAutor().getId());
                    }
                    return response;
                })
                .toList();
    }

    @Override
    public List<LivroResponseDto> buscarLivrosPorIsbn(String isbn){
        List<Livro> livros = livroRepository.findAll();
        List<LivroResponseDto> livrosResponse = new ArrayList<>();
        for(Livro livro : livros){
            if(livro.getIsbn().toLowerCase().contains(isbn.toLowerCase())){
                LivroResponseDto response = modelMapper.map(livro, LivroResponseDto.class);
                if(livro.getAutor() != null){
                    response.setIdAutor(livro.getAutor().getId());
                }
                livrosResponse.add(response);
            }
        }
        return livrosResponse;
    }

    @Override
    public List<LivroResponseDto> buscarLivrosPorTitulo(String titulo){
        List<Livro> livros = livroRepository.findAll();
        List<LivroResponseDto> livrosResponse = new ArrayList<>();
        for(Livro livro : livros){
            if(livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                LivroResponseDto response = modelMapper.map(livro, LivroResponseDto.class);
                if(livro.getAutor() != null){
                    response.setIdAutor(livro.getAutor().getId());
                }
                livrosResponse.add(response);
            }
        }
        return livrosResponse;
    }


    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto) {
        Livro livro = livroRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
        Autor autor = obterAutorExistente(id);
        modelMapper.map(livroPostPutDto, livro);
        livro.setIdLivro(id);
        livro.setAutor(autor);
        LivroResponseDto response = modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
        response.setIdAutor(autor.getId());
        return response;
    }

    @Override
    public void removerLivro(Long id) {
        if(!livroRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado");
        }
        livroRepository.deleteById(id);
    }
}


