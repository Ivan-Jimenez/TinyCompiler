package symbol;

import java.util.Hashtable;

public class Env {
	private Hashtable table;
	protected Env prev;
	
	public Env (Env perv) {
		table = new Hashtable();
		this.prev = prev;
	}
	
	public void push (String w, Id i) { table.put(w, i); }
	
	public Id get (String w) {
		for (Env e == this; e != null; e = e.prev) {
			Id found = (Id) e.table.get(w);
			if (found != null) { return found; }
		}
		
		return null;
	}
	
	public Id getLocal (String w) {
		return (Id) this.table.get(w);
	}
}
