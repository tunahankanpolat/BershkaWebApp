package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import pure.bershka.core.utilities.Log;

public interface LogDao extends JpaRepository<Log,Integer> {
}
