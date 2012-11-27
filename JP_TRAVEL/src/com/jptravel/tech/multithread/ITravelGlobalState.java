package com.jptravel.tech.multithread;

import com.jptravel.tech.multithread.RequestQueue;

/**
 * GlobalState interface.
 * 
 * @author DUYTX
 */
public interface ITravelGlobalState {

	/**
	 * Returns application wide instance of RequestQueue. Creates one once this
	 * method is called for the first time.
	 * 
	 * @return RequestQueue instance.
	 */
	public RequestQueue getRequestQueue();

}
