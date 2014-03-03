package parser;

public class PseudoBiEdge {
        private int src;
        private int target;
        protected String frenchName;
        
        public void setSrc(int n) {
                this.src = n;
        }
        
        public int getSrc() {
                return this.src;
        }
        
        public void setTarget(int n) {
                this.target = n;
        }
        
        public int getTarget() {
                return this.target;
        }
}
