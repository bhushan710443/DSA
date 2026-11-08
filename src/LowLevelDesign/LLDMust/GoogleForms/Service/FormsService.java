package LowLevelDesign.LLDMust.GoogleForms.Service;

import LowLevelDesign.LLDMust.GoogleForms.Entity.Forms;
import LowLevelDesign.LLDMust.GoogleForms.Repository.FormsRepository;
import LowLevelDesign.LLDMust.GoogleForms.Strategy.FormsStrategy;

public class FormsService {


    FormsRepository formsRepository;
    FormsStrategy formsStrategy;

    public FormsService(FormsRepository formsRepository, FormsStrategy formsStrategy) {
        this.formsRepository = formsRepository;
        this.formsStrategy = formsStrategy;
    }

    public void createForm(Forms form) {

        formsStrategy.findActualStrtegy();
        formsRepository.createForm(form);
    }

    public void searchForm(Forms form) {
    }
}
