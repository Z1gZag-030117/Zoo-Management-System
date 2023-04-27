package servlce;

import controller.TableDTO;
import po.AnimalRequest;

public interface AnimalSearch {
    TableDTO retrieveAnimal(AnimalRequest request);

    //boolean add(TableDTO studentDO);

//    TableDTO getById(int selectedStudentId);
//
//    boolean update(TableDTO studentDO);
//
//    boolean delete(int[] selectedStudentIds);
}
