package uebung9;

/**
 * Only purpose is to help sort my Data
 * @author Jonathan
 *
 */
public class valueAndAmount {

	int value;
	int amount;
	
	public valueAndAmount(int value, int amount) {
		this.value = value;
		this.amount = amount;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o.getClass().equals(this.getClass())) {
			valueAndAmount v = (valueAndAmount) o;
			if(v.value == this.value && v.amount == this.amount) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
}
