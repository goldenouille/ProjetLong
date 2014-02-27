package parser;

import model;

public class PseudoGraph {
        private ArrayList<PseudoClass> classes;
        private ArrayList<PseudoAbstract> abtracts;
        private ArrayList<PseudoInterface> interfaces;
        private ArrayList<PseudoAttribute> attributes;
        private ArrayList<PseudoMethod> methods;
        
        public PseudoGraph () {
                this.classes = new ArrayList<PseudoClass>();
                this.abtracts = new ArrayList<PseudoAbstract>();
                this.interfaces = new ArrayList<PseudoInterface>();
                this.attributes = new ArrayList<PseudoAttribute>();
                this.methods = new ArrayList<PseudoMethod>();
        }
        
        public Graph buildGraph(HashMap<int,GraphItem> map) {
                Graph g = new Graph();
                
                
                return g;
        }
        
        public void addClass (PseudoClass pc) {
                this.classes.add(pc);
        }

        public void addAbstract (PseudoAbstract pa) {
                this.abstrats.add(pa);
        }

        public void addInterface (PseudoInterface pi) {
                this.interfaces.add(pi);
        }

        public void addAttribute (PseudoAttribute pa) {
                this.attributes.add(pa);
        }

        public void addMethod (PseudoMethod pm) {
                this.methods.add(pm);
        }
        
        public ArrayList<PseudoClass> getClasses() {
                return this.classes;
        }

        public ArrayList<PseudoAbstract> getAbstracts() {
                return this.abtracts;
        }
        
        public ArrayList<PseudoInterface> getInterfaces() {
                return this.interfaces;
        }
        
        public ArrayList<PseudoAttribute> getAttributes() {
                return this.attributes;
        }
        
        public ArrayList<PseudoMethods> getMethods() {
                return this.methods;
        }
}
