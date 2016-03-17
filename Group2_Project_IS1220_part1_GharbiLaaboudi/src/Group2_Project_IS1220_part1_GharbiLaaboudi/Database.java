package Group2_Project_IS1220_part1_GharbiLaaboudi;

import java.util.ArrayList;

/**
 * @author Fouad-Sams
 *
 */
public interface Database {
	/**
	 * Saves all the data contained in the instance of the database. This means the list or object contained and
	 * and the maps related to these objects will be saved in a file in order to be used later.
	 * This method will use serialization : it will create a file for the list of objects and other files for 
	 * each map
	 */
	public void save();
	public void load();
	public void add(Object object);
	public ArrayList<Object> getList();
	

}
