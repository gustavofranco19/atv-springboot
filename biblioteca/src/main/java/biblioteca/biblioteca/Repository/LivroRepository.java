package biblioteca.biblioteca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import biblioteca.biblioteca.Model.*;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
