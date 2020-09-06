/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Contents;
import java.util.List;


public interface ContentsDAO {

    public long Count();

    public long CountFind(String Title);

    public List<Contents> getAllContents(Integer currentPage, Integer recordsPerPage);

    public Contents getContentById(Integer id);

    public Boolean insertContent(Contents co);

    public Boolean updateContent(Contents co);

    public Boolean deleteContent(Integer id);

    public List<Contents> getContentsFindByName(String contentName, Integer currentPage, Integer recordsPerPage);
}
