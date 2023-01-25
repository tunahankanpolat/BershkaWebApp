package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import pure.bershka.entities.concretes.Typology;

import java.util.List;

public interface TypologyDao extends JpaRepository<Typology,Integer> {
    List<Typology> getByCategory_Id(int categoryId);
    List<Typology> getByCategory_IdAndGender(int categoryId,String gender);

}
