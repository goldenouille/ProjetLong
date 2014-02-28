package parser;

import model;

public class PseudoGraph {
        private ArrayList<PseudoClass> classes;
        private ArrayList<PseudoAbstract> abtracts;
        private ArrayList<PseudoInterface> interfaces;
        private ArrayList<PseudoAttribute> attributes;
        private ArrayList<PseudoMethod> methods;
        private ArrayList<PseudoBiEdges> biEdges;
        
        public PseudoGraph () {
                this.classes = new ArrayList<PseudoClass>();
                this.abtracts = new ArrayList<PseudoAbstract>();
                this.interfaces = new ArrayList<PseudoInterface>();
                this.attributes = new ArrayList<PseudoAttribute>();
                this.methods = new ArrayList<PseudoMethod>();
        }
        
        public Graph buildGraph(HashMap<int,GraphItem> map) {
                Graph g = new Graph();
                PseudoClass pc;
                GraphItem gi;
                int id;
                for (int i=0; i<this.classes.size(); i++) {
                        pc = this.classes.get(i);
                        id = pc.getId();
                        if (map.get(id) == null) {
                                throw new ParserExecption("id inconnu");
                        }
                        else {
                                
                                gi = new VertexClass(pc.getName(), id, false);
                                g.addVertex(gi);
                                map.put(id, gi);
                        }
                }
                
                PseudoAbstract pa;
                for (int i=0; i<this.abstracts.size(); i++) {
                        pa = this.abstracts.get(i);
                        id = pa.getId();
                        if (map.get(id) == null) {
                                throw new ParserExecption("id inconnu");
                        }
                        else {
                                gi = new VertexClass(pa.getName(), id, true);
                                g.addVertex(gi);
                                map.put(id, gi);
                        }
                } 
                
                PseudoInterface pi;
                for (int i=0; i<this.interfaces.size(); i++) {
                        pi = this.interfaces.get(i);
                        id = pi.getId();
                        if (map.get(id) == null) {
                                throw new ParserExecption("id inconnu");
                        }
                        else {
                                gi = new Vertex(pi.getName(), id);
                                g.addVertex(gi);
                                map.put(id, gi);
                       }
                }
                
                PseudoAttribute patt;
                for (int i=0; i<this.attributes.size(); i++) {
                        patt = this.attributes.get(i);
                        id = patt.getId();
                        if (map.get(id) == null) {
                                throw new ParserExecption("id inconnu");
                        }
                        else {
                                gi = new attribute(patt.getName()patt.getType(),patt.getVisibility());
                                g.addAttribute(gi);
                                map.put(id, gi);
                       }
                }
                
                PseudoMethod pm;
                for (int i=0; i<this.methods.size(); i++) {
                        pm = this.methods.get(i);
                        id = pm.getId();
                        if (map.get(id) == null) {
                                throw new ParserExecption("id inconnu");
                        }
                        else {
                                gi = new method( pm.getName(),pm.getType(), pm.getVisibility(), pm.getParams);
                                g.addAttribute(gi);
                                map.put(id, gi);
                       }
                }
                
                PseudoBiEdge pbe;
                int srcId;
                int targetId;
                String frenchName;
                for (int i=0; i<this.biEdges.size(); i++) {
                      pe = this.biEdges.get(i);
                      srcId = pe.getSrc();
                      targetId = pe.getTarget();
                      frenchName = pe.getFrenchName();
                      
                      
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
