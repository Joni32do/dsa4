package uebung4;

public class OffenesHashing {

	public String hashCode;
	public OffenesHashing nextKey;
	public OffenesHashing succesor;

	public OffenesHashing(String hashCode) {
		this.hashCode = hashCode;
	}
	
	public OffenesHashing(Integer size) {
		if(size > 0) {
		this.succesor = new OffenesHashing(size - 1);
		}
		else {
			 System.out.println("Ich haben fertig!");
		}
	}
	
	public static void hashCode(OffenesHashing hash, Integer slot, String hashCode) {
		if(slot != 0) {
			hashCode(hash.succesor, slot-1, hashCode);
		}
		else {
			if(hash.hashCode == null) {
				hash.hashCode = hashCode;
			}
			else if(hash.nextKey == null){
				hash.nextKey = new OffenesHashing(hashCode);
			}
			else {
				hashCode(hash.nextKey, slot, hashCode);
			}
		}
	}
	
	

}
