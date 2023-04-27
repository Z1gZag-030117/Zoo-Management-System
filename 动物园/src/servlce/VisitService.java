package servlce;

import controller.TableDTOvisit;
import po.VisitRequest;

public interface VisitService {
    TableDTOvisit retrieveStudents(VisitRequest request);

}
