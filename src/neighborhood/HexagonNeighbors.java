package neighborhood;

import java.util.ArrayList;
import java.util.List;

import species.Species;
import util.Location;

public class HexagonNeighbors extends Neighborhood{

	public HexagonNeighbors(List<Species> neighborhood, Location location) {
		super(neighborhood, location);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public List<Species> findMyNeighbors(List<Species> neighborhood, Location mylocation) {
		List<Species> ret = new ArrayList<Species>();
		for (Species s : neighborhood){
			if (s.getMyLocation().getX() == mylocation.getX()){
				if (s.getMyLocation().getY() == (mylocation.getY()-1)
				|| s.getMyLocation().getY() == (mylocation.getY()+1)){
					ret.add(s);
				}
			}
			
			else if (s.getMyLocation().getX() == mylocation.getX()-1){
				if (s.getMyLocation().getY() == (mylocation.getY()-1)
				|| s.getMyLocation().getY() == (mylocation.getY()+1)
				|| s.getMyLocation().getY() == (mylocation.getY())){
					ret.add(s);
				}
			}
			else if (s.getMyLocation().getX() == mylocation.getX()+1){
				if( s.getMyLocation().getY() == (mylocation.getY())){
					ret.add(s);
				}
			}
			
			
			
			
			else if(s.getMyLocation().getY() == mylocation.getY()){
				if (s.getMyLocation().getX() == (mylocation.getX()-1)
				|| s.getMyLocation().getX() == (mylocation.getX()+1)){
					ret.add(s);
				}
			}
		}
		System.out.println(ret.size());
		return ret;
	}

}
