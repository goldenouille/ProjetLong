package parser;

import model.*;

public class PseudoAttribute {
        private String name;
        private int id;
		private String visibility;
        private int motherId;
        private String type;
        
        public PseudoAttribute() {
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
        		System.out.println("att n°"+ id + "  setVisibility : " + s);
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
        
}
