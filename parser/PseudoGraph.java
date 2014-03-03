package parser;

import java.util.ArrayList;
import java.util.HashMap;

import model.*;

public class PseudoGraph {
        private ArrayList<PseudoClass> classes;
        private ArrayList<PseudoAbstract> abstracts;
        private ArrayList<PseudoInterface> interfaces;
        private ArrayList<PseudoAttribute> attributes;
        private ArrayList<PseudoMethod> methods;
        private ArrayList<PseudoBiEdge> biEdges;
        
        public PseudoGraph () {
                this.classes = new ArrayList<PseudoClass>();
                this.abstracts = new ArrayList<PseudoAbstract>();
                this.interfaces = new ArrayList<PseudoInterface>();
                this.attributes = new ArrayList<PseudoAttribute>();
                this.methods = new ArrayList<PseudoMethod>();
        }
        
        public Graph buildGraph(HashMap<Integer,model.GraphItem> map) {
                Graph g = new Graph();
                PseudoClass pc;
                int id;
                for (int i=0; i<this.classes.size(); i++) {
                        pc = this.classes.get(i);
                        id = pc.getId();
                        if (map.get(id) == null) {
                                throw new ParserException("id inconnu");
                        }
                        else {
                                
                        		VertexClass gi = new VertexClass(pc.getName(), id);
                                g.addVertex(gi);
                                map.put(id,gi);
                        }
                }
                
                PseudoAbstract pa;
                for (int i=0; i<this.abstracts.size(); i++) {
                        pa = this.abstracts.get(i);
                        id = pa.getId();
                        if (map.get(id) == null) {
                                throw new ParserException("id inconnu");
                        }
                        else {
                                VertexAbstract gi = new VertexAbstract(pa.getName(), id);
                                g.addVertex(gi);
                                map.put(id, gi);
                        }
                } 
                
                PseudoInterface pi;
                for (int i=0; i<this.interfaces.size(); i++) {
                        pi = this.interfaces.get(i);
                        id = pi.getId();
                        if (map.get(id) == null) {
                                throw new ParserException("id inconnu");
                        }
                        else {
                                Vertex gi = new Vertex(pi.getName(), id);
                                g.addVertex(gi);
                                map.put(id, gi);
                       }
                }
                
                PseudoAttribute patt;
                for (int i=0; i<this.attributes.size(); i++) {
                        patt = this.attributes.get(i);
                        id = patt.getId();
                        if (map.get(id) == null) {
                                throw new ParserException("id inconnu");
                        }
                        else {
                                Attribute gi = new Attribute(patt.getName(),patt.getType(),patt.getVisibility());
                                g.addAttribute(gi);
                                map.put(id, gi);
                       }
                }
                
                PseudoMethod pm;
                for (int i=0; i<this.methods.size(); i++) {
                        pm = this.methods.get(i);
                        id = pm.getId();
                        if (map.get(id) == null) {
                                throw new ParserException("id inconnu");
                        }
                        else {
                                Method gi = new Method( pm.getName(),pm.getType(), pm.getVisibility(), pm.getParams());
                                g.addMethod(gi);
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
                this.abstracts.add(pa);
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
                return this.abstracts;
        }
        
        public ArrayList<PseudoInterface> getInterfaces() {
                return this.interfaces;
        }
        
        public ArrayList<PseudoAttribute> getAttributes() {
                return this.attributes;
        }
        
        public ArrayList<PseudoMethod> getMethods() {
                return this.methods;
        }
}
