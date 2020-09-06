/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Footers;
import java.util.List;

public interface FootersDAO {

    public List<Footers> getAllFooters();

    public Footers getFooterById(Integer id);

    public Boolean insertFooter(Footers f);

    public Boolean updateFooter(Footers f);

    public Boolean deleteFooter(Integer id);

    public List<Footers> getIndexFooters();
}
