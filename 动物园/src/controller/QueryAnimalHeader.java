package controller;

import view.breeder.QueryAnimal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryAnimalHeader implements ActionListener {


    private QueryAnimal queryAnimal;
    public QueryAnimalHeader (QueryAnimal queryAnimal){this.queryAnimal=queryAnimal;}

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button=(JButton) e.getSource();
        String text=button.getText();
        if("添加".equals(text)){

        }else if ("删除".equals(text)){

        }else if ("查询".equals(text)){
            queryAnimal.setPageNow(1);
            queryAnimal.reloadTable();
        }else if ("上一页".equals(text)){
            queryAnimal.setPageNow(queryAnimal.getPageNow()-1);
            queryAnimal.reloadTable();
        }else if ("下一页".equals(text)){
            queryAnimal.setPageNow(queryAnimal.getPageNow()+1);
            queryAnimal.reloadTable();
        }
    }
}
