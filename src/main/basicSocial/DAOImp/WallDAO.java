package DAOImp;

import model.Wall;

/**
 * Created by peppe on 28/05/15.
 */
public interface WallDAO {

    public void saveWall(Wall wall);

    public Wall getWall(String userName);

}
