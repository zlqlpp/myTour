package com.cpst.emsadrdb.dao.address;

import com.sun.jna.Library;

public interface Libaddrmatch extends Library{
	public String MatchById(String inputStr);
}

