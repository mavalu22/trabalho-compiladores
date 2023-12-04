package com.ufes.compilador.Model;

import com.ufes.compilador.DAO.errorDAO;
import java.util.Collections;
import java.util.List;

public class errorCollection {
    public List<errorModel> errors; // erros começando do ultimo para o primeiro
    public List<errorModel> errorsReverse; // erros começando do primeiro para o ultimo

    public errorCollection() {
        this.errors = new errorDAO().getErrors();
        this.errorsReverse = new errorDAO().getErrors();
        Collections.reverse(errorsReverse);
    }
}
