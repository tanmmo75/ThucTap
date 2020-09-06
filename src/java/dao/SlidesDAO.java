/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Slides;
import java.util.List;


public interface SlidesDAO {

    public List<Slides> getAllSlides();

    public Slides getSlideById(Integer id);

    public Boolean insertSlide(Slides s);

    public Boolean updateSlide(Slides s);

    public Boolean deleteSlide(Integer id);

    public List<Slides> getIndexSlides();
}
