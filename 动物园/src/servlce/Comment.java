package servlce;

import controller.TableDTOpark;
import controller.TableDTOvisit;
import po.VisitRequest;

public interface Comment {
    TableDTOpark retrieveAnimal(VisitRequest request);
}
