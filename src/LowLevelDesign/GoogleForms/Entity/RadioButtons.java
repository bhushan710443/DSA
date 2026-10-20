package LowLevelDesign.GoogleForms.Entity;

import LowLevelDesign.GoogleForms.Enum.Types;

public class RadioButtons {

      private String id;
      private String formId;
      private Types types;

      public RadioButtons(String id, String formId, Types types) {
            this.id = id;
            this.formId = formId;
            this.types = types;
      }

      public String getId() {
            return id;
      }

      public void setId(String id) {
            this.id = id;
      }

      public String getFormId() {
            return formId;
      }

      public void setFormId(String formId) {
            this.formId = formId;
      }

      public Types getTypes() {
            return types;
      }

      public void setTypes(Types types) {
            this.types = types;
      }
}
