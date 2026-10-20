package LowLevelDesign.GoogleForms.Controllers;

import LowLevelDesign.GoogleForms.Entity.Forms;
import LowLevelDesign.GoogleForms.Exceptions.FormNotFoundException;
import LowLevelDesign.GoogleForms.Service.FormsService;

public class FormsController {
  FormsService formsService;

    public FormsController(FormsService formsService) {
        this.formsService = formsService;
    }

    public Forms createForm(Forms form){

        formsService.createForm(form);
        return form;
    }

    public Forms searchForm(Forms form){

        try {
            formsService.searchForm(form);
        }catch (Exception e){
            throw new FormNotFoundException("Form is not available", e);
        }
        return form;
    }

}
