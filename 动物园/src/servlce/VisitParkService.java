package servlce;

import controller.TableDTOvisit;
import po.VisitRequest;

public interface VisitParkService {
    TableDTOvisit retrieveAnimal(VisitRequest request);
}
