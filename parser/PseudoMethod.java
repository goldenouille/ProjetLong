package parser;

import java.util.ArrayList;

import model.*;

public class PseudoMethod {
        private String name;
        private int id;
        private String visibility;
        private int motherId;
        private String type;
        private ArrayList<String> params;
        
        public PseudoMethod() {
        	this.params = new ArrayList<String>();
        }
        
        public void setName(String s) {
                this.name = s;
        }
        
        public String getName() {
                return this.name;
        }
        
        public void setId(int n) {
                this.id = n;
        }
        
        public int getId() {
                return this.id;
        }
        
        public void setVisibility(String s) {
                this.visibility = s;
        }
        
        public String getVisibility() {
                return this.visibility;
        }
        
        public void setMotherId(int n) {
                this.motherId = n;
        }
        
        public int getMotherId() {
                return this.motherId;
        }
        
        public void setType(String s) {
                this.type = s;
        }
        
        public String getType() {
                return this.type;
        }
        
        public void setParams(ArrayList<String> ls) {
                this.params = ls;
        }
        
        public ArrayList<String> getParams() {
                return this.params;
        }
        
        public void addParam(PseudoParam param) {
                this.params.add(param.getType());
        }
        
        public ArrayList<Type> getParamsType() {
        	ArrayList<Type> lt = new ArrayList<Type>();
        	for (int i=0; i<params.size(); i++) {
        		lt.add(TypeBase.getByName(params.get(i)));
        	}
        	return lt;
        }
}
