
package dao;

import entity.Abouts;
import java.util.List;

public interface AboutsDAO {

    public long Count();

    public long CountFind(String name);

    public List<Abouts> getAllAbouts(Integer currentPage, Integer recordsPerPage);

    public Abouts getAboutById(Integer id);

    public Boolean insertAbout(Abouts a);

    public Boolean updateAbout(Abouts a);

    public Boolean deleteAbout(Integer id);

    public List<Abouts> getAboutsFindByName(String aboutName, Integer currentPage, Integer recordsPerPage);
}
