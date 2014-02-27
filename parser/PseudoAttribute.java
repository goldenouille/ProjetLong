package parser;

import model;

public class PseudoAttribute {
        private String name;
        private int id;
        private String visibility;
        private int motherId;
        private String type;
        
        public void setName(String s) {
                this.name = s;
        }
        
        public string getName() {
                return this.name;
        }
        
        public void setId(int n) {
                this.id = n;
        }
        
        public int getId() {
                return this.id;
        }
        
        public void setVisibility(String s) {
                this.Visibility = s;
        }
        
        public string getVisibility() {
                return this.Visibility;
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
        
        public string getType() {
                return this.type;
        }
}
