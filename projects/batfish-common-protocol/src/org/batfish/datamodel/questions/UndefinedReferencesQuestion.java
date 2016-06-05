package org.batfish.datamodel.questions;

import java.util.Iterator;

import org.batfish.common.BatfishException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UndefinedReferencesQuestion extends Question {

   private static final String NODE_REGEX_VAR = "nodeRegex";

   private String _nodeRegex;

   public UndefinedReferencesQuestion() {
      super(QuestionType.UNDEFINED_REFERENCES);
      _nodeRegex = ".*";
   }

   @Override
   public boolean getDataPlane() {
      return false;
   }

   @Override
   public boolean getDifferential() {
      return false;
   }

   @JsonProperty(NODE_REGEX_VAR)
   public String getNodeRegex() {
      return _nodeRegex;
   }

   @Override
   public boolean getTraffic() {
      return false;
   }

   @Override
   public void setJsonParameters(JSONObject parameters) {
      super.setJsonParameters(parameters);
      Iterator<?> paramKeys = parameters.keys();
      while (paramKeys.hasNext()) {
         String paramKey = (String) paramKeys.next();
         try {
            switch (paramKey) {
            case NODE_REGEX_VAR:
               setNodeRegex(parameters.getString(paramKey));
               break;
            default:
               throw new BatfishException("Unknown key in "
                     + getClass().getSimpleName() + ": " + paramKey);
            }
         }
         catch (JSONException e) {
            throw new BatfishException("JSONException in parameters", e);
         }
      }
   }

   public void setNodeRegex(String nodeRegex) {
      _nodeRegex = nodeRegex;
   }
}
