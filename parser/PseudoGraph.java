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
        private ArrayList<PseudoRealization> realizations;
        private ArrayList<PseudoGeneralization> generalizations;
        private ArrayList<PseudoDependancy> dependancies;
        private ArrayList<PseudoAggregation> aggregations;
        private ArrayList<PseudoComposition> compositions;
        private ArrayList<PseudoBinaryAssociation> associations;
        
        
        public PseudoGraph () {
                this.classes = new ArrayList<PseudoClass>();
                this.abstracts = new ArrayList<PseudoAbstract>();
                this.interfaces = new ArrayList<PseudoInterface>();
                this.attributes = new ArrayList<PseudoAttribute>();
                this.methods = new ArrayList<PseudoMethod>();
                this.realizations = new ArrayList<PseudoRealization>() ;
                this.generalizations = new ArrayList<PseudoGeneralization>() ;
                this.dependancies = new ArrayList<PseudoDependancy>() ;
                this.aggregations = new ArrayList<PseudoAggregation>() ;
                this.compositions = new ArrayList<PseudoComposition>() ;
                this.associations = new ArrayList<PseudoBinaryAssociation>() ;
                
        }
        
        /** Transform the PseudoGraph into model.Graph
         *  Browsing all the ArrayList from PseudoGraph and create the associated GraphItems into a new Graph
         * 
         * @param map The hashmap linking ids to the associated GraphItem
         * @return	The Graph respecting the model data structure
         * @throws ParserException
         */
        public Graph buildGraph(HashMap<Integer,model.GraphItem> map) throws ParserException {
                Graph g = new Graph();
                PseudoClass pc;
                int id;
                for (int i=0; i<this.classes.size(); i++) {
                        pc = this.classes.get(i);
                        id = pc.getId();
                        VertexClass gi = new VertexClass(pc.getName(), id);
                        g.addVertex(gi);
                        map.put(id,gi);
                }
                
                PseudoAbstract pa;
                for (int i=0; i<this.abstracts.size(); i++) {
                        pa = this.abstracts.get(i);
                        id = pa.getId();
                        VertexAbstract gi = new VertexAbstract(pa.getName(), id);
                        g.addVertex(gi);
                        map.put(id, gi);
                } 
                
                PseudoInterface pi;
                for (int i=0; i<this.interfaces.size(); i++) {
                        pi = this.interfaces.get(i);
                        id = pi.getId();
                        Vertex gi = new Vertex(pi.getName(), id);
                        g.addVertex(gi);
                        map.put(id, gi);
                }
                
                PseudoAttribute patt;
                for (int i=0; i<this.attributes.size(); i++) {
                        patt = this.attributes.get(i);
                        id = patt.getId();
                        Attribute gi = new Attribute(patt.getName(),TypeBase.getByName(patt.getType()),Visibility.getByName(patt.getVisibility()),(Vertex) map.get(patt.getMotherId()),patt.getId());
                        g.addAttribute(gi);
                        map.put(id, gi);
                }
                
                PseudoMethod pm;
                for (int i=0; i<this.methods.size(); i++) {
                        pm = this.methods.get(i);
                        id = pm.getId();
                        Method gi = new Method( pm.getName(),TypeBase.getByName(pm.getType()), Visibility.getByName(pm.getVisibility()),(Vertex) map.get(pm.getMotherId()),pm.getId(), pm.getParamsType());
                        g.addMethod(gi);
                        map.put(id, gi);
                }
                
                PseudoAggregation pagg;
                for (int i=0; i<this.aggregations.size(); i++) {
                        pagg = this.aggregations.get(i);
                        id = pagg.getId();
                        Aggregation gi = new Aggregation((Vertex) map.get(pagg.getTarget()),pagg.getTargetMult(),(Vertex) map.get(pagg.getSource()),pagg.getSourceMult(), id, pagg.getName());
                        g.addEdge(gi);
                        map.put(id, gi);
                }
                
                PseudoComposition pcom;
                for (int i=0; i<this.compositions.size(); i++) {
                        pcom = this.compositions.get(i);
                        id = pcom.getId();
                        Composition gi = new Composition((Vertex) map.get(pcom.getTarget()),pcom.getTargetMult(),(Vertex) map.get(pcom.getSource()),pcom.getSourceMult(), id, pcom.getName());
                        g.addEdge(gi);
                        map.put(id, gi);
                }
                
                PseudoBinaryAssociation pba;
                for (int i=0; i<this.associations.size(); i++) {
                        pba = this.associations.get(i);
                        id = pba.getId();
                        BinaryAssociation gi = new BinaryAssociation((Vertex) map.get(pba.getTarget()),pba.getTargetMult(),(Vertex) map.get(pba.getSource()),pba.getSourceMult(), id, pba.getName());
                        g.addEdge(gi);
                        map.put(id, gi);
                }
                
                PseudoRealization pr;
                for (int i=0; i<this.realizations.size(); i++) {
                        pr = this.realizations.get(i);
                        id = pr.getId();
                        Realization gi = new Realization((Vertex) map.get(pr.getTarget()),(Vertex) map.get(pr.getSource()), id, pr.getName());
                        g.addEdge(gi);
                        map.put(id, gi);
                }
                
                PseudoGeneralization pg;
                for (int i=0; i<this.generalizations.size(); i++) {
                        pg = this.generalizations.get(i);
                        id = pg.getId();
                        Generalization gi = new Generalization((Vertex) map.get(pg.getTarget()),(Vertex) map.get(pg.getSource()), id, pg.getName());
                        g.addEdge(gi);
                        map.put(id, gi);
                }
                
                PseudoDependancy pd;
                for (int i=0; i<this.dependancies.size(); i++) {
                        pd = this.dependancies.get(i);
                        id = pd.getId();
                        Dependancy gi = new Dependancy((Vertex) map.get(pd.getTarget()),(Vertex) map.get(pd.getSource()), id, pd.getName());
                        g.addEdge(gi);
                        map.put(id, gi);
                }
                
                
                
                return g;
        }
        
        /** Add a class
         * @param pc the PseudoClass to add
         */
        public void addClass (PseudoClass pc) {
                this.classes.add(pc);
        }

        /** Add an abstract class
         * @param pa the PseudoAbstract to add
         */
        public void addAbstract (PseudoAbstract pa) {
                this.abstracts.add(pa);
        }

        /** Add an interface
         * @param pi the PseudoInterface to add
         */
        public void addInterface (PseudoInterface pi) {
                this.interfaces.add(pi);
        }

        /** Add an attribute
         * @param pa the PseudoAttribute to add
         */
        public void addAttribute (PseudoAttribute pa) {
                this.attributes.add(pa);
        }

        /** Add a Realization
         * @param pr the PseudoRealization to add
         */
        public void addRealization (PseudoRealization pr) {
                this.realizations.add(pr);
        }
        
        /** Add a Generalization
         * @param pg the PseudoRealization to add
         */
        public void addGeneralization (PseudoGeneralization pg) {
            this.generalizations.add(pg);
        }
        
        /** Add a Dependancy
         * @param pd the PseudoDependancy to add
         */
        public void addDependancy (PseudoDependancy pd) {
            this.dependancies.add(pd);
        }
        
        /** Add an Aggregation
         * @param pa the PseudoAggregation to add
         */
        public void addAggregation (PseudoAggregation pa) {
            this.aggregations.add(pa);
        }
        
        /** Add a Composition
         * @param pc the PseudoComposition to add
         */
        public void addComposition (PseudoComposition pc) {
            this.compositions.add(pc);
        }
        
        /** Add a method
         * @param pm the PseudoMethod to add
         */
        public void addMethod (PseudoMethod pm) {
            this.methods.add(pm);
        }
        
        /** Add an association
         * @param pba the PseudoBinaryAssociation to add
         */
        public void addAssociation (PseudoBinaryAssociation pba) {
            this.associations.add(pba);
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
