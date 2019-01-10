package com.company.model.mots.cases;

import com.company.model.mots.Antonyme;
import com.company.model.mots.Definition;
import com.company.model.mots.Indication;
import com.company.model.mots.Synonyme;

public class IndicationFactory {

    public Indication createIndication(String type,String question){
        Indication ind;
        if(type.equals("SYNONYME")) { // On instancie une indication selon le type
            ind = new Synonyme(question);
        } else if(type.equals("ANTONYME")){
            ind = new Antonyme(question);
        }else{
            ind = new Definition(question);
        }

        return ind;
    }

}
