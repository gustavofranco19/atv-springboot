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
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.LivroRepository;

@Service
public class LivroCrudPadraoService implements LivroCrudService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto) {
        Livro livro = modelMapper.map(livroPostPutDto, Livro.class);
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    public List<LivroResponseDto> buscarLivrosPorIsbn(String isbn){
        List<Livro> livros = livroRepository.findAll();
        List<LivroResponseDto> livrosResponse = new ArrayList<>();
        for(Livro livro : livros){
            if(livro.getIsbn().toLowerCase().contains(isbn.toLowerCase())){
                livrosResponse.add(modelMapper.map(livro, LivroResponseDto.class));
            }
        }
        return livrosResponse;
    }

    @Override
    public List<LivroResponseDto> buscarLivrosPorTitulo(String titulo){
        List<Livro> livros = livroRepository.findAll();
        List<LivroResponseDto> livrosResponse = new ArrayList<>();
        for(Livro livro : livros){
            if(livro.getIsbn().toLowerCase().contains(titulo.toLowerCase())){
                livrosResponse.add(modelMapper.map(livro, LivroResponseDto.class));
            }
        }
        return livrosResponse;
    }


    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto) {
        Livro livro = livroRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
        modelMapper.map(livroPostPutDto, livro);
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public void removerLivro(Long id) {
        if(!livroRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado");
        }
        livroRepository.deleteById(id);
    }

}
