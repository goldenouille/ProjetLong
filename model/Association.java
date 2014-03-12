package model;

import java.util.ArrayList;

public class Association extends Edge {


	/* this fonction is not implemented here because association is extended by NaryAssociation and 
		BinaryAssociation which have not the same number of vertex and so a different number of
		multiplicities.
		But putting this fonction here ensure that it exists in both sub-classes and cas be used.
		To usefull it has to be override, wich is not yet the case in NaryAssociation - which is not
		taken into account in this version of L.U.N.E
	*/
	public void setMultiplicities(ArrayList<String> m) {}
}