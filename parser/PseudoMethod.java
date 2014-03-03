package parser;

import java.util.ArrayList;

import model.*;

public class PseudoMethod {
        private String name;
        private int id;
        private Visibility visibility;
        private int motherId;
        private Type type;
        private ArrayList<Type> params;
        
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
                this.visibility = Visibility.getByName(s);
        }
        
        public Visibility getVisibility() {
                return this.visibility;
        }
        
        public void setMotherId(int n) {
                this.motherId = n;
        }
        
        public int getMotherId() {
                return this.motherId;
        }
        
        public void setType(String s) {
                this.type = TypeBase.getByName(s);
        }
        
        public Type getType() {
                return this.type;
        }
        
        public void setParams(ArrayList<Type> lt) {
                this.params = lt;
        }
        
        public ArrayList<Type> getParams() {
                return this.params;
        }
        
        public void addParam(String param) {
                this.params.add((Type) TypeBase.getByName(param));
        }
}
