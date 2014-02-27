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
                PseudoGraphItem pgi;
                GraphItem gi;
                int id;
                for (int i=0; i<this.classes.size(); i++) {
                        pgi = this.classes.get(i);
                        id = pgi.getId();
                        if (map.get(id) == null) {
                                throw new ParserExecption("id inconnu");
                        }
                        else {
                                
                                gi = new VertexClass((PseudoClass) pgi.getName(), id, false);
                                g.addVertex(gi);
                                map.put(id, gi);
                        }
                }
                
                for (int i=0; i<this.abstracts.size(); i++) {
                        pgi = this.abstracts.get(i);
                        id = pgi.getId();
                        if (map.get(id) == null) {
                                throw new ParserExecption("id inconnu");
                        }
                        else {
                                gi = new VertexClass((PseudoAbstract) pgi.getName(), id, true);
                                g.addVertex(gi);
                                map.put(id, gi);
                        }
                }) 
                
                for (int i=0; i<this.interfaces.size(); i++) {
                        pgi = this.interfaces.get(i);
                        id = pgi.getId();
                        if (map.get(id) == null) {
                                throw new ParserExecption("id inconnu");
                        }
                        else {
                                gi = new Vertex((PseudoInterface) pgi.getName(), id);
                                g.addVertex(gi);
                                map.put(id, gi);
                       }
                }
                
                for (int i=0; i<this.attributes.size(); i++) {
                        pgi = this.attributes.get(i);
                        id = pgi.getId();
                        if (map.get(id) == null) {
                                throw new ParserExecption("id inconnu");
                        }
                        else {
                                gi = new attribute((PseudoAttribute) pgi.getName(), (PseudoAttribute) pgi.getType(), (PseudoAttribute) pgi.getVisibility());
                                g.addAttribute(gi);
                                map.put(id, gi);
                       }
                }
                
                for (int i=0; i<this.methods.size(); i++) {
                        pgi = this.methods.get(i);
                        id = pgi.getId();
                        if (map.get(id) == null) {
                                throw new ParserExecption("id inconnu");
                        }
                        else {
                                gi = new attribute(pgi.getName(),pgi.getType(), pgi.getVisibility());
                                g.addAttribute(gi);
                                map.put(id, gi);
                       }
                }
                
                
                
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
