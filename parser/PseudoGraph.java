package parser;

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
}
