/**
 * 
 */
package Group2_Project_IS1220_part1_GharbiLaaboudi;

import java.util.ArrayList;

/**
 * @author Fouad-Sams
 *
 */
public class UserDatabase implements Database {

	/* (non-Javadoc)
	 * @see Group2_Project_IS1220_part1_GharbiLaaboudi.Database#save()
	 */
	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see Group2_Project_IS1220_part1_GharbiLaaboudi.Database#load()
	 */
	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see Group2_Project_IS1220_part1_GharbiLaaboudi.Database#add(java.lang.Object)
	 */
	@Override
	public void add(Object object) {
		if (object instanceof User){
			
		}
		else {
			throw (new WrongTypeException())
		}

	}

	/* (non-Javadoc)
	 * @see Group2_Project_IS1220_part1_GharbiLaaboudi.Database#getList()
	 */
	@Override
	public ArrayList<Object> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
